const admin = require('firebase-admin');
const fs = require('fs');

try {
  const serviceAccount = JSON.parse(
    fs.readFileSync('/etc/secrets/FIREBASE_SERVICE_ACCOUNT_KEY', 'utf8')
  );

  admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
  });

  console.log('✅ Firebase initialized');
} catch (error) {
  console.error('❌ Firebase Admin init error:', error.message);
}

module.exports = admin;
