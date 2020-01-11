package com.sda.naicu.onlinevoting.view;


import com.sda.naicu.onlinevoting.model.Candidate;
import com.sda.naicu.onlinevoting.model.Team;
import com.sda.naicu.onlinevoting.model.User;
import com.sda.naicu.onlinevoting.service.CandidateDisplayService;
import com.sda.naicu.onlinevoting.service.TeamDisplayService;
import com.sda.naicu.onlinevoting.service.UserDisplayService;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.hibernate.TransientObjectException;


import java.util.Date;
import java.util.List;

public class OnlineVotingMainView extends PrincipalView {
//    private Stage window;
//    private Scene principalScene;

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        window = primaryStage;
//        primaryStage.setTitle("Online Voting System");
//        primaryStage.setWidth(800);
//        primaryStage.setHeight(600);
//
//        //Scene votersScene = new VotersView().buildVotersScene();
//
//        principalScene = new PrincipalView().buildPrincipalScene();
//
////        primaryStage.setScene(buildCandidatesScene());
//        //primaryStage.setScene(votersScene);
//        primaryStage.show();
//    }

    public Scene buildCandidatesScene(Stage window, Scene principalScene) {
        CandidateDisplayService candidateDisplayService = new CandidateDisplayService();
        TeamDisplayService teamDisplayService = new TeamDisplayService();
        //Button button1 = new Button("Get candidates");
        //Button button2 = new Button("Get candidate by id");


        TableView tableView1 = new TableView();
        tableView1.setMaxHeight(100);
        tableView1.setMaxWidth(600);

        TableColumn<String, Candidate> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Candidate, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Candidate, String> column3 = new TableColumn<>("Team");
        column3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeam().getName()));

        TableColumn<String, Candidate> column4 = new TableColumn<>("Birth");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        TableColumn<Candidate, String> column5 = new TableColumn<>("CNP");
        column5.setCellValueFactory(new PropertyValueFactory<>("cnp"));

        tableView1.getColumns().add(column1);
        tableView1.getColumns().add(column2);
        tableView1.getColumns().add(column3);
        tableView1.getColumns().add(column4);
        tableView1.getColumns().add(column5);

        for (Candidate candidate : candidateDisplayService.getAllCandidates()) {
            tableView1.getItems().add(candidate);
        }


        ComboBox teamsComboBox = new ComboBox();
        teamsComboBox.setValue("All teams");
        teamsComboBox.getItems().addAll(teamDisplayService.getAllTeamsNames());

        teamsComboBox.setOnAction(event -> {
            tableView1.getItems().clear();
            List<Candidate> candidateList = candidateDisplayService.getCandidatesByTeam((String) teamsComboBox.getValue());

            for (Candidate candidate : candidateList) {
                tableView1.getItems().add(candidate);
            }


        });

        Label insertTeamLabel = new Label("Register a new team");
        insertTeamLabel.setTextFill(Color.web("#0076a3"));
        TextField teamTextField = new TextField();
        teamTextField.setPrefWidth(100);
        Button teamButton = new Button("Submit team");

        Label teamExistLabel = new Label();  //this Label can be replaced by Text

        //daca inserez un nume de team care exista crapa cu unique!!!! - SOLVED -> definit un boolean
        teamButton.setOnAction(event -> {
            List<Team> teamList = teamDisplayService.getAllTeams();
            boolean teamAlreadyExist = false;

            if (!teamTextField.getText().isEmpty()) {
                for (Team t : teamList) {
                    if (t.getName().equalsIgnoreCase(teamTextField.getText().toUpperCase())) {
                        teamExistLabel.setText("Team already registred");
                        teamAlreadyExist = true;
                        teamTextField.clear();
                        teamExistLabel.setTextFill(Color.web("#f51c0c"));
                    }
                }

                if (teamAlreadyExist == false && !teamTextField.getText().isEmpty()) {
                    Team newTeam = new Team(teamTextField.getText().toUpperCase());
                    teamDisplayService.saveTeam(newTeam);
                    teamTextField.clear();
                    teamExistLabel.setText("Team successfully registred");
                    teamExistLabel.setTextFill(Color.web("#f51c0c"));
                }
            } else {
                teamExistLabel.setText("A team must be registred. Please insert team name");
                teamExistLabel.setTextFill(Color.web("#f51c0c"));
            }

        });

        Label registerCandidateLabel = new Label("Register a new candidate");
        registerCandidateLabel.setTextFill(Color.web("#0076a3"));

        Label firstNameLabel = new Label("Enter candidate's first name");
        TextField firstNameText = new TextField();
        HBox firstNameHBox = new HBox();
        firstNameHBox.getChildren().addAll(firstNameLabel, firstNameText);
        firstNameHBox.setSpacing(15);

        Label lastNameLabel = new Label("Enter candidate's last name");
        TextField lastNameText = new TextField();
        HBox lastNameHBox = new HBox();
        lastNameHBox.getChildren().addAll(lastNameLabel, lastNameText);
        lastNameHBox.setSpacing(15);

        Label birthDateLabel = new Label("Enter candidate's birth date");
        DatePicker datePicker = new DatePicker();
        HBox birthDateHBox = new HBox();
        birthDateHBox.getChildren().addAll(birthDateLabel, datePicker);
        birthDateHBox.setSpacing(15);

        Label cnpLabel = new Label("Enter candidate's CNP");
        TextField cnpText = new TextField();
        HBox cnpHBox = new HBox();
        cnpHBox.getChildren().addAll(cnpLabel, cnpText);
        cnpHBox.setSpacing(45);

        //Label teamLabel = new Label("Enter candidate's team");
        ComboBox candidateTeamComboBox = new ComboBox();
        candidateTeamComboBox.setValue("Choose team");
        candidateTeamComboBox.getItems().addAll(teamDisplayService.getAllTeamsNames());
        HBox candidateTeamHBox = new HBox();
        candidateTeamHBox.getChildren().addAll(candidateTeamComboBox);
        candidateTeamHBox.setSpacing(15);

        Button submitCandidate = new Button("Submit candidate");
        submitCandidate.setMaxWidth(310);
        Text candidateText = new Text();
        Label exeptionLabel = new Label();
        submitCandidate.setOnAction(event -> {
            List<String> allCnps = candidateDisplayService.getAllCnps();
            boolean candidateAlreadyExist = false;
            //daca inserez un cnp  care exista crapa!!!! cu duplicate

            if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() ||
                                         cnpText.getText().isEmpty() || datePicker.getValue() == null ||
                                                    candidateTeamComboBox.getSelectionModel().isEmpty()) {
                exeptionLabel.setText("Please complete all fields");
                exeptionLabel.setTextFill(Color.web("#fc0505"));
            } else {
                for (String cnp : allCnps) {
                    if (cnpText.getText().equals(cnp)) {
                        candidateText.setText("Candidate already registred");
                        candidateText.setFill(Color.web("#f51c0c"));
                        candidateAlreadyExist = true;
                    }
                }
                try {
                    if (candidateAlreadyExist == false) {
                        Team candidateTeam = new Team(teamDisplayService.getIdByTeamName((String) candidateTeamComboBox.getValue()), (String) candidateTeamComboBox.getValue());
                        Candidate newCandidate = new Candidate(firstNameText.getText(), lastNameText.getText(), datePicker.getValue(), cnpText.getText(), candidateTeam);

                        candidateDisplayService.saveCandidate(newCandidate);
                        firstNameText.clear();
                        lastNameText.clear();
                        datePicker.getEditor().clear();
                        cnpText.clear();
                        candidateTeamComboBox.valueProperty().set(null);
                        candidateText.setText("Candidate successfully registred");
                        candidateText.setFill(Color.web("#f51c0c"));
                        exeptionLabel.setText("");
                    }
                } catch (Exception e) {
                    //System.out.println("Team not selected");
                    exeptionLabel.setText("Please complete all fields");
                    exeptionLabel.setTextFill(Color.web("#fc0505"));
                }
            }

        });


        HBox hBox = new HBox();
        hBox.getChildren().addAll(tableView1, teamsComboBox);
        hBox.setSpacing(20);
        hBox.setMaxWidth(800);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(insertTeamLabel, teamTextField, teamButton, teamExistLabel);
        hBox1.setSpacing(15);

        VBox candidateVBox = new VBox();
        candidateVBox.getChildren().addAll(registerCandidateLabel, firstNameHBox, lastNameHBox, cnpHBox,
                birthDateHBox, candidateTeamHBox, submitCandidate, candidateText, exeptionLabel);
        candidateVBox.setSpacing(5);

        Button backButton = new Button("Back");
        backButton.setFont(new Font(13));
        backButton.setOnAction(event -> {
            window.setScene(principalScene);
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, hBox1, candidateVBox, backButton);
        vBox.setSpacing(40);
        vBox.setPadding(new Insets(25));
        Scene scene = new Scene(vBox);
        return scene;
    }

}