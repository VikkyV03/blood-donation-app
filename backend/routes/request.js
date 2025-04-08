const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');
const Request = require('../models/Request');
const User = require('../models/User');
const admin = require('../firebase');

// Middleware to check token
function auth(req, res, next) {
  const token = req.headers.authorization?.split(' ')[1];
  if (!token) return res.status(401).json({ error: 'Token missing' });

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch {
    res.status(401).json({ error: 'Token invalid' });
  }
}

// Create blood request & notify donors
router.post('/', auth, async (req, res) => {
  try {
    const { bloodType, hospitalName, location, reason } = req.body;

    const request = new Request({
      userId: req.user.userId,
      bloodType,
      hospitalName,
      location,
      reason,
    });

    await request.save();

    // Notify donors with matching bloodType
    const donors = await User.find({
      role: 'donor',
      fcmToken: { $exists: true, $ne: null }
    });

    const tokens = donors.map(d => d.fcmToken);
    if (tokens.length > 0) {
      const message = {
        notification: {
          title: `🩸 Blood Request: ${bloodType}`,
          body: `${hospitalName} needs ${bloodType} blood urgently!`,
        },
        tokens: tokens,
      };

      admin.messaging().sendMulticast(message)
        .then(response => {
          console.log(`✅ FCM sent to ${response.successCount} donors`);
        })
        .catch(err => {
          console.error('❌ FCM Error:', err);
        });
    }

    res.status(201).json({ message: 'Request created & notifications sent' });

  } catch (err) {
    res.status(500).json({ error: 'Request creation failed' });
  }
});

// View all open requests
router.get('/', async (req, res) => {
  try {
    const requests = await Request.find({ status: 'open' }).populate('userId', 'name phone');
    res.json(requests);
  } catch (err) {
    res.status(500).json({ error: 'Failed to fetch requests' });
  }
});

// Mark request as fulfilled
router.patch('/:id/fulfill', auth, async (req, res) => {
  try {
    await Request.findByIdAndUpdate(req.params.id, { status: 'fulfilled' });
    res.json({ message: 'Request marked as fulfilled' });
  } catch (err) {
    res.status(500).json({ error: 'Update failed' });
  }
});

module.exports = router;
