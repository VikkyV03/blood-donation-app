const admin = require('firebase-admin');

// Use absolute path for Render's secret file
const serviceAccount = require('/etc/secrets/FIREBASE_SERVICE_ACCOUNT_KEY');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

module.exports = admin;
