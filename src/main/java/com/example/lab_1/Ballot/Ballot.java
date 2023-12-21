package com.example.lab_1.Ballot;
import com.example.lab_1.HashCalculator;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Ballot {
    private int ballot_num;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    HashCalculator calc = new HashCalculator();
    public Ballot(int ballot_num) {
        this.ballot_num = ballot_num;
        generateRSAKeys();
    }

    public int getBallot_num() {
        return ballot_num;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger signEDS(String message){
        BigInteger hash = calc.calculateHash(message, this.n);
        BigInteger EDS = hash.modPow(this.d, this.n);
        //System.out.println(hash + " | " + EDS.modPow(this.e, this.n));
        return EDS;
    }



    public void generateRSAKeys(){ // метод генераціх ключів
        BigInteger p = generatePrime(); // генеруємо просте число p
        BigInteger q = generatePrime(); // генеруємо просте число q
        this.n = p.multiply(q); // обраховуємо їх добуток
        BigInteger eyler = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // обраховуємо функцію Ейлера
        this.e = chooseE(eyler); // обираємо непарне число e, яке взаємно просте з eyler і 0 < e < eyler
        this.d = this.e.modInverse(eyler);
    }
    private BigInteger generatePrime() {
        BigInteger bi;
        int bitLength = 20;
        Random rnd = new Random();
        return BigInteger.probablePrime(bitLength, rnd);
    }
    private BigInteger chooseE(BigInteger eyler) {
        SecureRandom random = new SecureRandom();
        BigInteger e;
        do {
            e = new BigInteger(eyler.bitLength(), random);
        } while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(eyler) >= 0 || !e.gcd(eyler).equals(BigInteger.ONE) || !e.testBit(0)); // Перевірка на парність
        return e;
    }
}
