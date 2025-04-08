// backend/routes/donor.js

const express = require('express');
const router = express.Router();
const Donor = require('../models/Donor');
const jwt = require('jsonwebtoken');

// Middleware to decode JWT
function auth(req, res, next) {
  const token = req.headers.authorization?.split(' ')[1];
  if (!token) return res.status(401).json({ error: 'Missing token' });

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch {
    res.status(401).json({ error: 'Invalid token' });
  }
}

// Register donor
router.post('/', auth, async (req, res) => {
  try {
    const donor = new Donor({
      userId: req.user.userId,
      ...req.body
    });
    await donor.save();
    res.status(201).json({ message: 'Donor registered' });
  } catch (err) {
    res.status(500).json({ error: 'Failed to register donor' });
  }
});

// Search donors
router.get('/search', async (req, res) => {
  const { bloodType, city } = req.query;
  const filter = {};
  if (bloodType) filter.bloodType = bloodType;
  if (city) filter.city = city;

  try {
    const donors = await Donor.find(filter).populate('userId', 'name phone');
    res.json(donors);
  } catch (err) {
    res.status(500).json({ error: 'Search failed' });
  }
});

module.exports = router;
