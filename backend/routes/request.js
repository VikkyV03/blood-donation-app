const express = require('express');
const router = express.Router();
const admin = require('../firebase'); // ✅ For future Firebase notifications
const Request = require('../models/Request');

// ✅ Create a new blood request
router.post('/', async (req, res) => {
  try {
    const { userId, bloodType, hospitalName, location, reason } = req.body;

    // Basic validation (optional)
    if (!userId || !bloodType || !hospitalName || !location || !reason) {
      return res.status(400).json({ error: 'All fields are required' });
    }

    const newRequest = new Request({
      userId,
      bloodType,
      hospitalName,
      location, // [longitude, latitude]
      reason
    });

    await newRequest.save();

    res.status(201).json({ message: 'Blood request created successfully' });

    // 🔔 OPTIONAL: Trigger push notification (future feature)
    // const topic = `blood_${bloodType.replace('+', 'pos').replace('-', 'neg')}`;
    // await admin.messaging().sendToTopic(topic, {
    //   notification: {
    //     title: 'Blood Request Alert',
    //     body: `Urgent need for ${bloodType} blood at ${hospitalName}.`
    //   }
    // });

  } catch (err) {
    console.error('❌ Error creating blood request:', err);
    res.status(500).json({ error: 'Server error' });
  }
});

// ✅ Get all blood requests
router.get('/', async (req, res) => {
  try {
    const requests = await Request.find().sort({ createdAt: -1 });
    res.json(requests);
  } catch (err) {
    console.error('❌ Error fetching requests:', err);
    res.status(500).json({ error: 'Server error' });
  }
});

module.exports = router;
