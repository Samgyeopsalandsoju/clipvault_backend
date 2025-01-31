package com.samso.linkjoa.core.Utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryptor {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_SECRET_KEY = "7254527391128452";

    public static String oneWayEncrypt(String text){
        return passwordEncoder.encode(text);
    }

    public static boolean matchOneWay(String plainText, String encryptedText){
        return passwordEncoder.matches(plainText, encryptedText);
    }

    public static String twoWayEncrypt(String text){
        try {
            SecretKeySpec keySpec = new SecretKeySpec(AES_SECRET_KEY.getBytes(), AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedBytes = cipher.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }

    public static String twoWayDecrypt(String text){
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(text);
            SecretKeySpec keySpec = new SecretKeySpec(AES_SECRET_KEY.getBytes(), AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch(Exception e) {
            throw new RuntimeException("Decryption error", e);
        }
    }
}
