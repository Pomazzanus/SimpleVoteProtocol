package com.example.lab_1.Ballot;


import com.example.lab_1.Gammuvannya.GamEncoderDecoder;
import com.example.lab_1.VoteParticipants.Candidate;
import com.example.lab_1.VoteParticipants.Person;
import com.example.lab_1.Voting_Centre.VotingCentre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {

    @FXML
    private Label dateLabel;
    @FXML
    private Label blank;
    private ObservableList<Candidate> candidate_data = FXCollections.observableArrayList();
    private GamEncoderDecoder encoder = new GamEncoderDecoder();
    private final char[] gamma = {'7','1','9','2','4','1','8','8','6','0','5','7'};
    @FXML
    private TableView<Candidate> candidate_table;
    @FXML
    private TableColumn<Candidate, Integer> idColumn;
    @FXML
    private TableColumn<Candidate, String> fname;
    @FXML
    private TableColumn<Candidate, String> lname;
    @FXML
    private TableColumn<Candidate, String> faname;
    @FXML
    private TableColumn<Candidate, Integer> numVotes;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField fnameField;
    private int ballot_count = 1;
    VotingCentre votingCentre = new VotingCentre();
    @FXML
    public void initialize() {
        blank.setText("Бланк № " + ballot_count);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        dateLabel.setText(formattedDate);

    }

    public void setCandidateData(ObservableList<Candidate> data) {
        candidate_data = data;
        idColumn.setCellValueFactory(new PropertyValueFactory<Candidate,Integer>("ballot_list_id"));
        lname.setCellValueFactory(new PropertyValueFactory<Candidate,String>("lastName"));
        fname.setCellValueFactory(new PropertyValueFactory<Candidate,String>("firstName"));
        faname.setCellValueFactory(new PropertyValueFactory<Candidate,String>("fatherName"));
        numVotes.setCellValueFactory(new PropertyValueFactory<Candidate,Integer>("num_votes"));
        candidate_table.setItems(candidate_data);
    }

    @FXML
    protected void onNewButtonClick() {
        initialize();
        candidate_table.setItems(votingCentre.getCandidate_list());
        candidate_table.refresh();
    }
    @FXML
    protected void onNextButtonClick() {
        Ballot ballot = new Ballot(ballot_count +=1);
        int msg = candidate_table.getSelectionModel().getSelectedItem().getBallot_list_id() * 100 + 15;
        System.out.println(msg);
        System.out.println("Підписуємо");
        System.out.println("ЕЦП - " + ballot.signEDS(String.valueOf(msg)));
        votingCentre.addNewVote(firstnameField.getText(), lastnameField.getText(), fnameField.getText(),
                encoder.encode(String.valueOf(msg), gamma), ballot.signEDS(String.valueOf(msg)),
                ballot.getN(), ballot.getE());
        onNewButtonClick();
    }
}