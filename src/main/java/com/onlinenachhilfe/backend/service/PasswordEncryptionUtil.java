package com.onlinenachhilfe.backend.service;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
@Service
public class PasswordEncryptionUtil {
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "A1b2C3d4E5!f6*G7H8i9J0kLmNo#Pq21".getBytes();

    public  String encrypt(String password) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            System.err.println("Fehler beim Verschlüsseln: " + e.getMessage());
            return null;
        }
    }

    public String decrypt(String encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPassword)));
        } catch (Exception e) {
            System.err.println("Fehler beim Entschlüsseln: " + e.getMessage());
            return null; // Kannst du auf eine passende Rückgabe ändern
        }
    }
}
