package com.example.lab_1;

import com.example.lab_1.Ballot.MainController;
import com.example.lab_1.Voting_Centre.VotingCentre;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class SimpleVoteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VotingCentre votingCentre = new VotingCentre();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleVoteApplication.class.getResource("ballot-view-l1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainController controller = fxmlLoader.getController();
        // Передайте данные в контроллер
        controller.setCandidateData(votingCentre.getCandidate_list());
        stage.setTitle("Електронне голосування lab1");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}