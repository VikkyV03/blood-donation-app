const admin = require('firebase-admin');
const fs = require('fs');

let serviceAccount;

try {
  serviceAccount = JSON.parse(
    fs.readFileSync('/etc/secrets/FIREBASE_SERVICE_ACCOUNT_KEY', 'utf8')
  );
} catch (err) {
  console.error("❌ Firebase Admin init error:", err.message);
}

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

module.exports = admin;
