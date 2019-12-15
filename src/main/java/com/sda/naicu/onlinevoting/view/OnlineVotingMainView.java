package com.sda.naicu.onlinevoting.view;


import com.sda.naicu.onlinevoting.model.Candidate;
import com.sda.naicu.onlinevoting.service.CandidateDisplayService;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OnlineVotingMainView extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Online Voting System");
        CandidateDisplayService candidateDisplayService = new CandidateDisplayService();
        Button button1 = new Button("Get candidates");
        Button button2 = new Button("Get candidate by id");
        VBox vBox = new VBox();

        ListView<Candidate> listView = new ListView<>();
        ObservableList<Candidate> items = listView.getItems();

        button1.setOnAction(event -> {
            for (Candidate candidate:candidateDisplayService.getAllCandidates()) {
                items.add(candidate);
            };
        });

        vBox.getChildren().addAll(button1, button2);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox,listView);
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
