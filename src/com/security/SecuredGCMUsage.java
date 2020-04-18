package com.security;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Logger;

/**
 * This class shows how to securely perform AES encryption in GCM mode, with 256 bits key size.
 */
public class SecuredGCMUsage {

    public static int AES_KEY_SIZE = 256;
    public static int IV_SIZE = 96;
    public static int TAG_BIT_LENGTH = 128;
    public static String ALGO_TRANSFORMATION_STRING = "AES/GCM/PKCS5Padding";
    static  Logger logger = Logger.getLogger(SecuredGCMUsage.class.getName());

    public static GCMParameterSpec getGcmParameterSpec() {
        // Generating Initialization Vector (IV) random number for more security
        byte iv[] = new byte[IV_SIZE];
        SecureRandom secRandom = new SecureRandom();
        secRandom.nextBytes(iv); // SecureRandom initialized using self-seeding
        // Initialize GCM Parameters
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
        // Make sure not to repeat Key + IV pair, for encrypting more than one plaintext.
        secRandom.nextBytes(iv);
        return gcmParameterSpec;
    }


    public static SecretKey getSecretKey() {
        // Generating Key
        SecretKey aesKey = null;
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES"); // Specifying algorithm key will be used for
            keygen.init(AES_KEY_SIZE); // Specifying Key size to be used, Note: This would need JCE Unlimited Strength to be installed explicitly
            aesKey = keygen.generateKey();
        } catch (NoSuchAlgorithmException noSuchAlgoExc) {
            logger.info(noSuchAlgoExc.getMessage());
            System.out.println("Key being request is for AES algorithm, but this cryptographic algorithm is not available in the environment " + noSuchAlgoExc);
            System.exit(1);
        }
        return aesKey;
    }


    public static byte[] aesEncrypt(String message, SecretKey aesKey, GCMParameterSpec gcmParamSpec, byte[] aadData, SecureRandom secRandom) {
        //Null check required
        Cipher c = null;

        try {
            c = Cipher.getInstance(ALGO_TRANSFORMATION_STRING); // Transformation specifies algortihm, mode of operation and padding
        } catch (NoSuchAlgorithmException noSuchAlgoExc) {
            System.out.println("Exception while encrypting. Algorithm being requested is not available in this environment " + noSuchAlgoExc);
            System.exit(1);
        } catch (NoSuchPaddingException noSuchPaddingExc) {
            System.out.println("Exception while encrypting. Padding Scheme being requested is not available this environment " + noSuchPaddingExc);
            System.exit(1);
        }


        try {
            c.init(Cipher.ENCRYPT_MODE, aesKey, gcmParamSpec, secRandom);
        } catch (InvalidKeyException invalidKeyExc) {
            System.out.println("Exception while encrypting. Key being used is not valid. It could be due to invalid encoding, wrong length or uninitialized " + invalidKeyExc);
            System.exit(1);
        } catch (InvalidAlgorithmParameterException invalidAlgoParamExc) {
            System.out.println("Exception while encrypting. Algorithm parameters being specified are not valid " + invalidAlgoParamExc);
            System.exit(1);
        }

        try {
            c.updateAAD(aadData); // add AAD tag data before encrypting
        } catch (IllegalArgumentException illegalArgumentExc) {
            System.out.println("Exception thrown while encrypting. Byte array might be null " + illegalArgumentExc);
            System.exit(1);
        } catch (IllegalStateException illegalStateExc) {
            System.out.println("Exception thrown while encrypting. CIpher is in an illegal state " + illegalStateExc);
            System.exit(1);
        } catch (UnsupportedOperationException unsupportedExc) {
            System.out.println("Exception thrown while encrypting. Provider might not be supporting this method " + unsupportedExc);
            System.exit(1);
        }

        byte[] cipherTextInByteArr = null;
        try {
            cipherTextInByteArr = c.doFinal(message.getBytes());
        } catch (IllegalBlockSizeException illegalBlockSizeExc) {
            System.out.println("Exception while encrypting, due to block size " + illegalBlockSizeExc);
            System.exit(1);
        } catch (BadPaddingException badPaddingExc) {
            System.out.println("Exception while encrypting, due to padding scheme " + badPaddingExc);
            System.exit(1);
        }

        return cipherTextInByteArr;
    }


    public static byte[] aesDecrypt(byte[] encryptedMessage, SecretKey aesKey, GCMParameterSpec gcmParamSpec, byte[] aadData) {
        Cipher c = null;

        try {
            c = Cipher.getInstance(ALGO_TRANSFORMATION_STRING); // Transformation specifies algortihm, mode of operation and padding
        } catch (NoSuchAlgorithmException noSuchAlgoExc) {
            System.out.println("Exception while decrypting. Algorithm being requested is not available in environment " + noSuchAlgoExc);
            System.exit(1);
        } catch (NoSuchPaddingException noSuchAlgoExc) {
            System.out.println("Exception while decrypting. Padding scheme being requested is not available in environment " + noSuchAlgoExc);
            System.exit(1);
        }

        try {
            c.init(Cipher.DECRYPT_MODE, aesKey, gcmParamSpec, new SecureRandom());
        } catch (InvalidKeyException invalidKeyExc) {
            System.out.println("Exception while encrypting. Key being used is not valid. It could be due to invalid encoding, wrong length or uninitialized " + invalidKeyExc);
            System.exit(1);
        } catch (InvalidAlgorithmParameterException invalidParamSpecExc) {
            System.out.println("Exception while encrypting. Algorithm Param being used is not valid. " + invalidParamSpecExc);
            System.exit(1);
        }

        try {
            c.updateAAD(aadData); // Add AAD details before decrypting
        } catch (IllegalArgumentException illegalArgumentExc) {
            System.out.println("Exception thrown while encrypting. Byte array might be null " + illegalArgumentExc);
            System.exit(1);
        } catch (IllegalStateException illegalStateExc) {
            System.out.println("Exception thrown while encrypting. CIpher is in an illegal state " + illegalStateExc);
            System.exit(1);
        }

        byte[] plainTextInByteArr = null;
        try {
            plainTextInByteArr = c.doFinal(encryptedMessage);
        } catch (IllegalBlockSizeException illegalBlockSizeExc) {
            System.out.println("Exception while decryption, due to block size " + illegalBlockSizeExc);
            System.exit(1);
        } catch (BadPaddingException badPaddingExc) {
            System.out.println("Exception while decryption, due to padding scheme " + badPaddingExc);
            System.exit(1);
        }

        return plainTextInByteArr;
    }
}
