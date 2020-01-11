package com.sda.naicu.onlinevoting.view;


import com.sda.naicu.onlinevoting.model.Candidate;
import com.sda.naicu.onlinevoting.model.Team;
import com.sda.naicu.onlinevoting.model.User;
import com.sda.naicu.onlinevoting.model.UserType;
import com.sda.naicu.onlinevoting.service.CandidateDisplayService;
import com.sda.naicu.onlinevoting.service.TeamDisplayService;
import com.sda.naicu.onlinevoting.service.UserDisplayService;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class VotersView extends PrincipalView {

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Online Voting System");
//        primaryStage.setWidth(800);
//        primaryStage.setHeight(600);
//        primaryStage.setScene(buildVotersScene());
//        primaryStage.show();
//    }
    public Scene buildVotersScene(Stage window, Scene principalScene) {
        CandidateDisplayService candidateDisplayService = new CandidateDisplayService();
        TeamDisplayService teamDisplayService = new TeamDisplayService();
        UserDisplayService userDisplayService = new UserDisplayService();
        //Button button1 = new Button("Get candidates");
        //Button button2 = new Button("Get candidate by id");

        Label registerUser = new Label("Register a new user");
        registerUser.setTextFill(Color.web("#0076a3"));

        Label firstNameLabel = new Label("Enter user's first name");
        TextField firstNameText = new TextField();
        HBox firstNameHBox = new HBox();
        firstNameHBox.getChildren().addAll(firstNameLabel, firstNameText);
        firstNameHBox.setSpacing(15);

        Label lastNameLabel = new Label("Enter user's last name");
        TextField lastNameText = new TextField();
        HBox lastNameHBox = new HBox();
        lastNameHBox.getChildren().addAll(lastNameLabel, lastNameText);
        lastNameHBox.setSpacing(15);

        Label addressLabel = new Label("Enter user's address");
        TextArea addressText = new TextArea();
        addressText.setMaxSize(150, 50);
        HBox addressHBox = new HBox();
        addressHBox.getChildren().addAll(addressLabel, addressText);
        addressHBox.setSpacing(25);

        Label countyLabel = new Label("Enter user's county");
        TextField countyText = new TextField();
        HBox countyHBox = new HBox();
        countyHBox.getChildren().addAll(countyLabel, countyText);
        countyHBox.setSpacing(29);

        Label cnpLabel = new Label("Enter user's CNP");
        TextField cnpText = new TextField();
        HBox cnpHBox = new HBox();
        cnpHBox.getChildren().addAll(cnpLabel, cnpText);
        cnpHBox.setSpacing(41);

//        Label userTypeLabel = new Label("Enter user's type");
//        TextField userTypeText = new TextField();
//        HBox userTypeHBox = new HBox();
//        userTypeHBox.getChildren().addAll(userTypeLabel, userTypeText);
//        userTypeHBox.setSpacing(41);

        ComboBox userTypeComboBox = new ComboBox();
        userTypeComboBox.setValue("Choose user type");
        userTypeComboBox.getItems().addAll(UserType.values());


        Button submitUser = new Button("Register user");
        submitUser.setMaxWidth(310);
        Text userText = new Text();
        submitUser.setOnAction(event -> {
            List<String> allUsersCnps = userDisplayService.getAllUsersCnp();
            boolean userAlreadyReigstred = false;
            for (String cnp : allUsersCnps) {
                if (cnpText.getText().equals(cnp)) {
                    userText.setText("User already registred");
                    userText.setFill(Color.web("#f51c0c"));
                    userAlreadyReigstred = true;
                }
            }

                if(userAlreadyReigstred == false){
                    User newUser = new User(firstNameText.getText(), lastNameText.getText(), addressText.getText(),
                            countyText.getText(), cnpText.getText(), (UserType) userTypeComboBox.getValue());

                    userDisplayService.saveUser(newUser);
                    firstNameText.clear();
                    lastNameText.clear();
                    addressText.clear();
                    countyText.clear();
                    cnpText.clear();
                    userText.setText("User successfully registred");
                    userText.setFill(Color.web("#f51c0c"));
                }

        });

        Label votersTableLabel = new Label("Voters table");
        votersTableLabel.setTextFill(Color.web("#0076a3"));

        TableView tableView1 = new TableView();
        tableView1.setMaxHeight(100);
        tableView1.setMaxWidth(600);

        TableColumn<String, User> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<User, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<User, String> column3 = new TableColumn<>("Address");
        column3.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<String, User> column4 = new TableColumn<>("County");
        column4.setCellValueFactory(new PropertyValueFactory<>("county"));

        TableColumn<User, String> column5 = new TableColumn<>("CNP");
        column5.setCellValueFactory(new PropertyValueFactory<>("cnp"));

        TableColumn<User, String> column6 = new TableColumn<>("userType");
        column6.setCellValueFactory(new PropertyValueFactory<>("userType"));

        tableView1.getColumns().add(column1);
        tableView1.getColumns().add(column2);
        tableView1.getColumns().add(column3);
        tableView1.getColumns().add(column4);
        tableView1.getColumns().add(column5);
        tableView1.getColumns().add(column6);

        for (User user : userDisplayService.getUsersByUserType(UserType.VOTER)) {
            tableView1.getItems().add(user);
        }

        Label adminTableLabel = new Label("Admins table");
        adminTableLabel.setTextFill(Color.web("#0076a3"));

        TableView tableView2 = new TableView();
        tableView2.setMaxHeight(100);
        tableView2.setMaxWidth(600);

        TableColumn<String, User> column10 = new TableColumn<>("First Name");
        column10.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<User, String> column11 = new TableColumn<>("Last Name");
        column11.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<User, String> column12 = new TableColumn<>("Address");
        column12.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<String, User> column13 = new TableColumn<>("County");
        column13.setCellValueFactory(new PropertyValueFactory<>("county"));

        TableColumn<User, String> column14 = new TableColumn<>("CNP");
        column14.setCellValueFactory(new PropertyValueFactory<>("cnp"));

        TableColumn<User, String> column15 = new TableColumn<>("userType");
        column15.setCellValueFactory(new PropertyValueFactory<>("userType"));

        tableView2.getColumns().add(column10);
        tableView2.getColumns().add(column11);
        tableView2.getColumns().add(column12);
        tableView2.getColumns().add(column13);
        tableView2.getColumns().add(column14);
        tableView2.getColumns().add(column15);

        for (User user : userDisplayService.getUsersByUserType(UserType.ADMIN)) {
            tableView2.getItems().add(user);
        }

        VBox userVBox = new VBox();
        userVBox.getChildren().addAll(registerUser, firstNameHBox, lastNameHBox, addressHBox, countyHBox, cnpHBox, userTypeComboBox, submitUser, userText);
        userVBox.setSpacing(5);

        VBox votersTableVbox = new VBox();
        votersTableVbox.getChildren().addAll(votersTableLabel, tableView1);
        votersTableVbox.setSpacing(10);

        VBox adminTableVbox = new VBox();
        adminTableVbox.getChildren().addAll(adminTableLabel, tableView2);
        adminTableVbox.setSpacing(10);

        Button backButton = new Button("Back");
        backButton.setFont(new Font(13));
        backButton.setOnAction(event -> {
            window.setScene(principalScene);
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(userVBox, votersTableVbox, adminTableVbox, backButton);
        vBox.setSpacing(25);
        vBox.setPadding(new Insets(25));
        Scene scene = new Scene(vBox);
        return scene;
    }


}