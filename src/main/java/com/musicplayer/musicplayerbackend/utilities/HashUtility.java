package com.musicplayer.musicplayerbackend.utilities;

import org.bson.BsonObjectId;
import org.bson.types.ObjectId;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtility {
    private static final int HASH_SIZE_BYTES = 12; // 96 bits

    public static String generateHash(String input) {
        byte[] hash = calculateSHA256(input);
        byte[] truncatedHash = new byte[HASH_SIZE_BYTES];
        System.arraycopy(hash, 0, truncatedHash, 0, HASH_SIZE_BYTES);
        return bytesToHexString(truncatedHash);
    }

    public static BsonObjectId generateBsonId(String input){

        return new BsonObjectId(new ObjectId(generateHash(input)));
    }

    private static byte[] calculateSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
