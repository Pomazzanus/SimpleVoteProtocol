package com.example.lab_1.Voting_Centre;

import com.example.lab_1.Gammuvannya.GamEncoderDecoder;
import com.example.lab_1.HashCalculator;
import com.example.lab_1.VoteParticipants.Elector;
import com.example.lab_1.VoteParticipants.Candidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigInteger;

public class VotingCentre {
    private String personal_key;
    private BigInteger n; // модуль (открытый ключ избирателя)
    private BigInteger e; // открытая экспонента (открытый ключ избирателя)
    HashCalculator calc = new HashCalculator();
    private final char[] gamma = {'7','1','9','2','4','1','8','8','6','0','5','7'};
    private GamEncoderDecoder decoder = new GamEncoderDecoder();
    //public void decodeMsg(){}

    private boolean isEDSCorrect(BigInteger EDS, BigInteger e, BigInteger n, BigInteger hash){
        return hash.equals(EDS.modPow(e, n));
    }

    private ObservableList<Elector> elector_list = FXCollections.observableArrayList(
            new Elector("009796001", "Антон", "Головенко", "Михайлович"),
            new Elector("009796002", "Наталія", "Парасюк", "Валеріївна"),
            new Elector("009796003", "Валерій", "Смолєнцев", "Ігорович"),
            new Elector("009796004", "Олександр", "Ручкін", "Максимович"),
            new Elector("009796005", "Олена", "Димитрова", "Микитівна"),
            new Elector("009796006", "Микола", "Миколенко", "Миколайович"),
            new Elector("009796007", "Єкатерина", "Скуфенко", "Єфімівна"),
            new Elector("009796008", "Ігор", "Острозький", "Петрович")
    );

    private ObservableList<Candidate> candidate_list = FXCollections.observableArrayList(
            new Candidate("009796009", "Петро", "Горошенко", "Олександрович", 1),
            new Candidate("009796010", "Володимир", "Дученко", "Олексійович", 2),
            new Candidate("009796011", "Ольга", "Ювелірова", "Сергіївна", 3),
            new Candidate("009796012", "Максим", "Собаченко", "Іванович", 4),
            new Candidate("009796013", "Вікторія", "Іпова", "Генадіївна", 5)
    );


    public void addNewVote(int[] msg, BigInteger EDS, BigInteger keyN, BigInteger keyE){
        String decoded_msg = decoder.decode(msg, this.gamma);
        System.out.println("Decoded : " + decoded_msg);
        System.out.println("Message hash : " + calc.calculateHash(decoded_msg, keyN));
        System.out.println("EDS hash : " + EDS.modPow(keyE, keyN));
        //System.out.println("Done " +msg);
    }

    public ObservableList<Elector> getElector_list() {
        return elector_list;
    }

    public ObservableList<Candidate> getCandidate_list() {
        return candidate_list;
    }
}
