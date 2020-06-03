package com.security;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * This class shows how to securely perform AES encryption in GCM mode, with 256 bits key size.
 */
public class SecuredGCMUsageMain {

    public static void main(String args[]) {

        //String messageToEncrypt = args[0];
        String messageToEncrypt = "Lekhraj Prasad";

        byte[] aadData = "random-domain-name".getBytes(); // Any random data can be used as tag. Some common examples could be domain name...
        // Use different key+IV pair for encrypting/decrypting different parameters
        SecretKey aesKey = SecuredGCMUsage.getSecretKey();
        System.out.println(aesKey);
        GCMParameterSpec gcmParameterSpec = SecuredGCMUsage.getGcmParameterSpec();

        System.out.println(gcmParameterSpec);
        byte[] encryptedText = SecuredGCMUsage.aesEncrypt(messageToEncrypt, aesKey, gcmParameterSpec, aadData, new SecureRandom());

        String encryptedStr = Base64.getEncoder().encodeToString(encryptedText);

        // String encryptedText = encrypt(messageToEncrypt,aesKey);
        System.out.println("Encrypted Text = " + encryptedStr);

        //decrypt(encryptedText, aesKey);

        byte[] decryptedText = SecuredGCMUsage.aesDecrypt(encryptedText, aesKey, gcmParameterSpec, aadData); // Same key, IV and GCM Specs for decryption as used for encryption.

        System.out.println("Decrypted text " + new String(decryptedText));

    }

}
