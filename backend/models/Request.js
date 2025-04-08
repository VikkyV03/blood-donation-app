// backend/models/Request.js

const mongoose = require('mongoose');

const RequestSchema = new mongoose.Schema({
  userId: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
  bloodType: String,
  hospitalName: String,
  location: {
    type: [Number], // [longitude, latitude]
    index: '2dsphere'
  },
  reason: String,
  status: { type: String, enum: ['open', 'fulfilled'], default: 'open' },
  createdAt: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Request', RequestSchema);
