package com.sda.naicu.onlinevoting.view;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PrincipalView extends Application {

    private Stage window;
    private Scene usersScene, candidateScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("Online Voting System");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        Scene princScene = buildPrincipalScene();

        usersScene = new VotersView().buildVotersScene(window, princScene);
        candidateScene = new OnlineVotingMainView().buildCandidatesScene(window,princScene);


        primaryStage.setScene(princScene);
        primaryStage.show();
    }

    public Scene buildPrincipalScene() {
        Button candidateButon = new Button("Candidates and teams");
        candidateButon.setPrefSize(200, 50);
        candidateButon.setTextFill(Color.web("#751e82"));
        candidateButon.setFont(new Font(15));

        candidateButon.setOnAction(event -> {
            window.setScene(candidateScene);
        });

        Button userButton = new Button ("Users");
        userButton.setPrefSize(200, 50);
        userButton.setTextFill(Color.web("#751e82"));
        userButton.setFont(new Font(15));

        userButton.setOnAction(event -> {
            window.setScene(usersScene);
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(candidateButon, userButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);

        Scene scene = new Scene(vBox);
        return scene;
    }

}