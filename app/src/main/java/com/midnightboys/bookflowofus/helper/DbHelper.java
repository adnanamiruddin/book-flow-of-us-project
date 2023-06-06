package com.midnightboys.bookflowofus.helper;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DbHelper {

    private static final String SECRET_KEY = "MySecretKey12345";

    public static String hashPassword(String password) {
        String hashedPassword = null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            hashedPassword = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    public static String decryptPassword(String encryptedPassword) {
        String decryptedPassword = null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedPassword = new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedPassword;
    }

}
