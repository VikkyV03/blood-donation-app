// backend/models/User.js

const mongoose = require('mongoose');

const UserSchema = new mongoose.Schema({
  name: String,
  email: { type: String, unique: true },
  password: String,
  phone: String,
  role: { type: String, enum: ['donor', 'hospital', 'admin'], default: 'donor' },
  fcmToken: String
});

module.exports = mongoose.model('User', UserSchema);
