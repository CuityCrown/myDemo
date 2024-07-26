package com.ryml.util;

import org.junit.Test;

import java.security.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 刘一博
 * @date: 2024/7/25 14:40
 */
public class RSAUtils {


    public static void main(String[] args) {
        int p = 83;
        int q = 89;
        int m = (p - 1) * (q - 1);
        List<Long> primeFactors = findPrimeFactors(m);
        System.out.println(m);
        System.out.println(primeFactors);
    }

    public static List<Long> findPrimeFactors(long n) {
        List<Long> primeFactors = new ArrayList<>();
        for (long i = 2; i <= n; i++) {
            while (n % i == 0 && isPrime(i)) {
                primeFactors.add(i);
                n /= i;
            }
        }
        return primeFactors;
    }

    public static boolean isPrime(long num) {
        if (num <= 1) return false;
        if (num <= 3) return true;

        int limit = (int) Math.sqrt(num);
        for (int i = 2; i <= limit; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCreate() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        System.out.println(aPrivate);
        System.out.println(aPublic);
    }

}
