const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const User = require('../models/User');

router.post('/register', async (req, res) => {
  try {
    const { name, email, password, phone, role } = req.body;
    console.log("📦 Register attempt:", email);

    const existingUser = await User.findOne({ email });

    if (existingUser) {
      console.log("⚠️ Email already registered:", email);
      return res.status(400).json({ error: 'User already exists' });
    }

    const hashedPassword = await bcrypt.hash(password, 10);

    const user = new User({
      name,
      email,
      password: hashedPassword,
      phone,
      role
    });

    await user.save();
    console.log("✅ Registered user:", email);
    res.status(201).json({ message: 'User registered successfully' });

  } catch (err) {
    console.error("🚨 Registration error:", err);
    res.status(500).json({ error: 'Server error' });
  }
});

router.post('/login', async (req, res) => {
  try {
    const { email, password } = req.body;
    console.log("🔑 Login attempt:");
    console.log("Email:", email);
    console.log("Password:", password); // Only for debugging (remove in production)

    const user = await User.findOne({ email });

    if (!user) {
      console.log("❌ No user found with this email:", email);
      return res.status(400).json({ error: 'Invalid email' });
    }

    const isMatch = await bcrypt.compare(password, user.password);
    console.log("🔍 Password match:", isMatch);

    if (!isMatch) {
      console.log("❌ Incorrect password for:", email);
      return res.status(400).json({ error: 'Invalid password' });
    }

    const token = jwt.sign({ userId: user._id }, process.env.JWT_SECRET, {
      expiresIn: '7d'
    });

    const userWithToken = {
      ...user.toObject(),
      token
    };

    console.log("✅ Login successful for:", email);
    res.json(userWithToken);

  } catch (err) {
    console.error("🚨 Login error:", err);
    res.status(500).json({ error: 'Login error' });
  }
});

module.exports = router;
