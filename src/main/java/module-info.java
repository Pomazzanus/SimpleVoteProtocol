module com.example.lab_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab_1 to javafx.fxml;
    exports com.example.lab_1;
    exports com.example.lab_1.Ballot;
    opens com.example.lab_1.Ballot to javafx.fxml;
    exports com.example.lab_1.Voting_Centre;
    opens com.example.lab_1.Voting_Centre to javafx.fxml;
    exports com.example.lab_1.VoteParticipants;
    opens com.example.lab_1.VoteParticipants to javafx.fxml;
}