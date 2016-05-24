package com.morecruit.ext.utils;

import com.morecruit.ext.component.logger.Logger;
import com.morecruit.ext.utils.security.CRC64;
import com.morecruit.ext.utils.security.Digest;
import com.morecruit.ext.utils.security.MD5;
import com.morecruit.ext.utils.security.SHA1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

/**
 * 安全工具类. 提供包括{@link Digest}, {@link MD5}, {@link CRC64}, {@link SHA1}, etc.
 * <p>
 * Created by zhaiyifan on 2015/8/3.
 */
public final class SecurityUtils {

    private final static String TAG = "SecurityUtils";

    private SecurityUtils() {
        // static usage.
    }

    // -------------------- digest --------------------

    /**
     * Digest source string with default MD5 algorithm.
     *
     * @param source Source string to digest.
     * @return Digest of source string.
     */
    public static String digest(String source) {
        return digest(source, "MD5");
    }

    /**
     * Digest source string with corresponding algorithm.
     *
     * @param source    Source string to digest.
     * @param algorithm Digest algorithm such as "MD5" .etc.
     * @return Digest of source string.
     */
    public static String digest(String source, String algorithm) {
        if (source == null) {
            return null;
        }
        try {
            return digestOrThrow(source, algorithm);
        } catch (NoSuchAlgorithmException e) {
            Logger.i(TAG, "fail to digest " + source + " with " + algorithm, e);
        }
        return null;
    }

    /**
     * Digest source string with default MD5 algorithm. Throw exception if error occurs.
     *
     * @param source Source string to digest.
     * @return Digest of source string.
     */
    public static String digestOrThrow(String source) throws NoSuchAlgorithmException {
        return digestOrThrow(source, "MD5");
    }

    /**
     * Digest source string with corresponding algorithm. Throw exception if error occurs.
     *
     * @param source    Source string to digest.
     * @param algorithm Digest algorithm such as "MD5" .etc.
     * @return Digest of source string.
     */
    public static String digestOrThrow(String source, String algorithm) throws NoSuchAlgorithmException {
        if (source == null) {
            return null;
        }
        return (new Digest(algorithm)).digest(source);
    }

    /**
     * Digest source file with default MD5 algorithm.
     *
     * @param file Source file to digest.
     * @return Digest of source string.
     */
    public static String digest(File file) {
        return digest(file, "MD5");
    }

    /**
     * Digest source file with corresponding algorithm.
     *
     * @param file      Source file to digest.
     * @param algorithm Digest algorithm such as "MD5" .etc.
     * @return Digest of source string.
     */
    public static String digest(File file, String algorithm) {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        try {
            return digestOrThrow(file, algorithm);
        } catch (IOException e) {
            Logger.i(TAG, "fail to digest " + file + " with " + algorithm, e);
        } catch (NoSuchAlgorithmException e) {
            Logger.i(TAG, "fail to digest " + file + " with " + algorithm, e);
        }
        return null;
    }

    /**
     * Digest source file with default MD5 algorithm. Throw exception if error occurs.
     *
     * @param file Source file to digest.
     * @return Digest of source string.
     */
    public static String digestOrThrow(File file) throws IOException, NoSuchAlgorithmException {
        return digestOrThrow(file, "MD5");
    }

    /**
     * Digest source file with corresponding algorithm. Throw exception if error occurs.
     *
     * @param file      Source file to digest.
     * @param algorithm such as "MD5" .etc.
     * @return Digest of source string.
     */
    public static String digestOrThrow(File file, String algorithm) throws IOException, NoSuchAlgorithmException {
        if (file == null) {
            return null;
        }
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return (new Digest(algorithm)).digest(fis);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // ignore.
                }
            }
        }
    }

    // -------------------- crc --------------------

    /**
     * A function that returns a 64-bit crc for byte array.
     *
     * @param buffer Input byte array.
     * @return A 64-bit crc value
     */
    public static long crc64(byte[] buffer) {
        return CRC64.getValue(buffer);
    }

    /**
     * A function that returns a 64-bit crc for string.
     *
     * @param in Input string
     * @return A 64-bit crc value
     */
    public static long crc64(String in) {
        return CRC64.getValue(in);
    }
}