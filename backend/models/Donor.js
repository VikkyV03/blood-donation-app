// backend/models/Donor.js

const mongoose = require('mongoose');

const DonorSchema = new mongoose.Schema({
  userId: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
  bloodType: String,
  city: String,
  lastDonated: String,
  availabilityStatus: Boolean,
  coordinates: {
    type: [Number], // [longitude, latitude]
    index: '2dsphere'
  }
}, {
  timestamps: true
});

module.exports = mongoose.model('Donor', DonorSchema);
