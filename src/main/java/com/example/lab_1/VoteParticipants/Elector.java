package com.example.lab_1.VoteParticipants;

import com.example.lab_1.VoteParticipants.Person;

import java.math.BigInteger;

public class Elector extends Person {
    private BigInteger publicKeyN;
    public Elector(String firstName, String lastName, String fatherName, BigInteger publicKeyN) {
        super(firstName, lastName, fatherName);
        this.publicKeyN = publicKeyN;
    }

    public BigInteger getPublicKeyN() {
        return publicKeyN;
    }

    public void setPublicKeyN(BigInteger publicKeyN) {
        this.publicKeyN = publicKeyN;
    }
}
