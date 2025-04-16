// backend/models/Donor.js

const mongoose = require('mongoose');

const DonorSchema = new mongoose.Schema({
  userId: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'User',
    required: true,
    unique: true // ✅ ensures only one donor entry per user
  },
  bloodType: {
    type: String,
    required: true
  },
  city: {
    type: String,
    required: true
  },
  lastDonated: {
    type: String
  },
  availabilityStatus: {
    type: Boolean,
    default: true
  },
  coordinates: {
    type: [Number], // [longitude, latitude]
    index: '2dsphere'
  }
}, {
  timestamps: true
});

module.exports = mongoose.model('Donor', DonorSchema);
