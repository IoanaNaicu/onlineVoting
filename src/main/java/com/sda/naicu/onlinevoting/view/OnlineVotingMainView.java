package com.sda.naicu.onlinevoting.view;


import com.sda.naicu.onlinevoting.model.Candidate;
import com.sda.naicu.onlinevoting.model.Team;
import com.sda.naicu.onlinevoting.service.CandidateDisplayService;
import com.sda.naicu.onlinevoting.service.TeamDisplayService;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class OnlineVotingMainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Online Voting System");
        CandidateDisplayService candidateDisplayService = new CandidateDisplayService();
        TeamDisplayService teamDisplayService = new TeamDisplayService();
        Button button1 = new Button("Get candidates");
        Button button2 = new Button("Get candidate by id");
        VBox vBox = new VBox();

        TableView tableView1 = new TableView();
        tableView1.setMaxHeight(100);
        TableColumn<String, Candidate> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Candidate, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Candidate, String> column3 = new TableColumn<>("Team");
        column3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeam().getName()));




        TableColumn<String, Candidate> column4 = new TableColumn<>("Birth");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        tableView1.getColumns().add(column1);
        tableView1.getColumns().add(column2);
        tableView1.getColumns().add(column3);
        tableView1.getColumns().add(column4);

        for (Candidate candidate:candidateDisplayService.getAllCandidates()) {
            tableView1.getItems().add(candidate);
        };

        ComboBox teamsComboBox = new ComboBox();
        teamsComboBox.setValue("All teams");
        teamsComboBox.getItems().addAll(teamDisplayService.getAllTeams());

        teamsComboBox.setOnAction(event -> {
            tableView1.getItems().clear();
            List<Candidate> candidateList = candidateDisplayService.getCandidatesByTeam((String)teamsComboBox.getValue());

            for (Candidate candidate:candidateList) {
                tableView1.getItems().add(candidate);
            };

        });



        vBox.getChildren().addAll(button1, button2);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox,tableView1, teamsComboBox);
        Scene scene = new Scene(hBox);
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
