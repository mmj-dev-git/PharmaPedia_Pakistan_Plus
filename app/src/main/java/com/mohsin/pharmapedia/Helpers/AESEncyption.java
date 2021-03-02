package com.mohsin.pharmapedia.Helpers;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;

import com.mohsin.pharmapedia.BuildConfig;
import com.mohsin.pharmapedia.Helpers.Entities.Utils;

public class AESEncyption {
    private static final int pswdIterations = 10;
    private static final int keySize = 128;
    private static final String cypherInstance = "AES/CBC/PKCS5Padding";
    private static final String secretKeyInstance = "PBKDF2WithHmacSHA1";
    private static final String plainText = BuildConfig.PlainText;
    private static final String AESSalt = "";
    private static final String initializationVector = BuildConfig.InitializationVector;

    public static String encrypt(String textToEncrypt,boolean random) throws Exception {
        String randomString ;
        if(random)
        {
            randomString = Utils.random();
        }
        else
            {
            randomString = BuildConfig.SECRET_FOR_PREF_KEY;
        }
        SecretKeySpec skeySpec = new SecretKeySpec(getRaw(plainText, randomString), "AES");
        Cipher cipher = Cipher.getInstance(cypherInstance);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(initializationVector.getBytes()));
        byte[] encrypted = cipher.doFinal(textToEncrypt.getBytes());
        String temp  = randomString + "|"+Base64.encodeToString(encrypted, Base64.DEFAULT);
        return Base64.encodeToString(temp.getBytes(),Base64.DEFAULT);
    }



    public static String decrypt(String textToDecrypt) throws Exception {
        byte[] encryted_bytes = Base64.decode(textToDecrypt, Base64.DEFAULT);
        String temp = new String(encryted_bytes);
        String salt = temp.substring(0,32);
        String text = temp.substring(33);
        encryted_bytes = Base64.decode(text, Base64.DEFAULT);
        SecretKeySpec skeySpec = new SecretKeySpec(getRaw(plainText, salt), "AES");
        Cipher cipher = Cipher.getInstance(cypherInstance);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(initializationVector.getBytes()));
        byte[] decrypted = cipher.doFinal(encryted_bytes);
        return new String(decrypted, "UTF-8");
    }

    private static byte[] getRaw(String plainText, String salt) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyInstance);
            KeySpec spec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(), pswdIterations, keySize);
            return factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }



}
