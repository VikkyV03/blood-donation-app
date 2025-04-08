// backend/firebase.js

const admin = require('firebase-admin');
const fs = require('fs');

// Read and parse the Firebase service account secret
const serviceAccount = JSON.parse(
  fs.readFileSync('/etc/secrets/FIREBASE_SERVICE_ACCOUNT_KEY', 'utf8')
);

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

module.exports = admin;
