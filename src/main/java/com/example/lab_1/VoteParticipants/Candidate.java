package com.example.lab_1.VoteParticipants;

public class Candidate extends Person {
    private int ballot_list_id;
    private int num_votes;
    public Candidate(String firstName, String lastName, String fatherName, int ballot_list_id) {
        super(firstName, lastName, fatherName);
        this.ballot_list_id = ballot_list_id;
        this.num_votes = 0;
    }

    public int getBallot_list_id() {
        return ballot_list_id;
    }

    public int getNum_votes() {
        return num_votes;
    }

    public void add_vote(){
        this.num_votes = this.num_votes + 1;
    }

}
