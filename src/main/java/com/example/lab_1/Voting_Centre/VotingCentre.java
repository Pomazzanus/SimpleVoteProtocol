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

    public boolean isEDSCorrect(BigInteger EDS, BigInteger e, BigInteger n, BigInteger hash){
        return hash.equals(EDS.modPow(e, n));
    }

    private ObservableList<Elector> elector_list = FXCollections.observableArrayList(
            new Elector( "Антон", "Головенко", "Михайлович", null),
            new Elector( "Наталія", "Парасюк", "Валеріївна", null),
            new Elector( "Валерій", "Смолєнцев", "Ігорович", null),
            new Elector( "Олександр", "Ручкін", "Максимович", null),
            new Elector( "Олена", "Димитрова", "Микитівна", null),
            new Elector( "Микола", "Миколенко", "Миколайович", null),
            new Elector( "Єкатерина", "Скуфенко", "Єфімівна", null),
            new Elector("Ігор", "Острозький", "Петрович", null)
    );

    private ObservableList<Candidate> candidate_list = FXCollections.observableArrayList(
            new Candidate( "Петро", "Горошенко", "Олександрович", 1),
            new Candidate( "Володимир", "Дученко", "Олексійович", 2),
            new Candidate( "Ольга", "Ювелірова", "Сергіївна", 3),
            new Candidate( "Максим", "Собаченко", "Іванович", 4),
            new Candidate( "Вікторія", "Іпова", "Генадіївна", 5)
    );


    public void addNewVote(String firstname, String lastname, String fname, int[] msg, BigInteger EDS, BigInteger keyN, BigInteger keyE){
        System.out.println(firstname + lastname + fname);
        if(isElectorInList(firstname, lastname, fname, keyN)){
            String decoded_msg = decoder.decode(msg, this.gamma);
            candidate_list.get((Integer.parseInt(decoded_msg)-16)/100).add_vote();
            System.out.println("Decoded : " + decoded_msg);
            System.out.println("Message hash : " + calc.calculateHash(decoded_msg, keyN));
            System.out.println("EDS hash : " + EDS.modPow(keyE, keyN));
        }
        else{
            System.out.println("Ви на маєте права голосувати!");
        }
    }

    public boolean isElectorInList(String firstName, String lastName, String fatherName, BigInteger keyN) {
        for (Elector elector : getElector_list()) {
            if (elector.getFirstName().equals(firstName) &&
                    elector.getLastName().equals(lastName) &&
                    elector.getFatherName().equals(fatherName)) {
                if(elector.getPublicKeyN() == null){
                    elector.setPublicKeyN(keyN);
                    return true;
                }
                else{ System.out.println("Ви вже голосували!");
                    return false;} // виборця знайдено, голосував
            }
        }
        return false; // немає такого виборця
    }

    public ObservableList<Elector> getElector_list() {
        return elector_list;
    }

    public ObservableList<Candidate> getCandidate_list() {
        return candidate_list;
    }
}
