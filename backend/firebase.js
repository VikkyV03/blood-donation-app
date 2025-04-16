const admin = require('firebase-admin');
const fs = require('fs');
const path = require('path');

let serviceAccount;

try {
  const keyPath = process.env.FIREBASE_KEY_PATH || path.join(__dirname, 'firebase-service-account.json');
  serviceAccount = JSON.parse(fs.readFileSync(keyPath, 'utf8'));

  admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
  });

  console.log("✅ Firebase initialized");
} catch (error) {
  console.error("❌ Firebase Admin init error:", error.message);
}

module.exports = admin;
