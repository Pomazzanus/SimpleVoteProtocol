package com.example.lab_1;

import java.math.BigInteger;

public class HashCalculator {
    public BigInteger calculateHash(String message, BigInteger n) {
        BigInteger hash = BigInteger.ZERO; // Початкове значення хешу

        // Обробляємо кожен символ повідомлення
        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            int characterValue = character - 'A' + 1; // Номер букви в алфавіті

            // Обчислюємо нове значення хешу
            hash = hash.add(BigInteger.valueOf(characterValue));
            hash = hash.pow(2).mod(n);
        }

        return hash;
    }
}
