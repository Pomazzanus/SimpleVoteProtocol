package com.example.lab_1.VoteParticipants;

public class Candidate extends Person {
    private int ballot_list_id;
    public Candidate(String id, String firstName, String lastName, String fatherName, int ballot_list_id) {
        super(id, firstName, lastName, fatherName);
        this.ballot_list_id = ballot_list_id;
    }

    public int getBallot_list_id() {
        return ballot_list_id;
    }
}
