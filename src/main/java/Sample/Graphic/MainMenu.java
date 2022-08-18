package Sample.Graphic;

import Sample.Controller.Main;
import Sample.Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MainMenu extends Application {
    private StackPane root = new StackPane();
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        String username = stage.getTitle();
        stage.setTitle("Main Menu");



        VBox vBox = new VBox();


        Button button = new Button("New Game");
        button.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400)," +
                "linear-gradient(#ffef84, #f2ba44)," +
                "linear-gradient(#ffea6a, #efaa22)," +
                "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%)," +
                "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));" +
                "-fx-background-radius: 30;" +
                "-fx-background-insets: 0,1,2,3,0;" +
                "-fx-text-fill: #654b00;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10 20 10 20;");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setTitle(username);
                try {
                    new StartNewGame().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Button button1 = new Button("Load Game");
        button1.setStyle("-fx-background-color: #000000," +
                "linear-gradient(#7ebcea, #2f4b8f)," +
                "linear-gradient(#426ab7, #263e75)," +
                "linear-gradient(#395cab, #223768);" +
                "-fx-background-insets: 0,1,2,3;" +
                "-fx-background-radius: 3,2,2,2;" +
                "-fx-padding: 12 30 12 30; -fx-text-fill: white;" +
                "-fx-font-size: 12px;");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (User.allUsers.get(username).getSavingTheGame() == null){
                    Popup popup = new Popup();
                    Label label2 = new Label("you dont have any game to load.");
                    label2.setTextFill(Color.web("red"));
                    label2.setFont(Font.font(20));
                    popup.getContent().add(label2);
                    popup.setAnchorX(600);
                    popup.setAnchorY(600);
                    popup.show(stage);
                    popup.setAutoHide(true);
                }
                else {
                    stage.setTitle(username);
                    try {
                        Battlefield battlefield = new Battlefield();
                        battlefield.stage = stage;
                        battlefield.userName = username;
                        battlefield.how = "load";
                        battlefield.init();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        Button button2 = new Button("Score Board");
        button2.setStyle("-fx-background-color: #ecebe9," +
                "rgba(0,0,0,0.05), linear-gradient(#dcca8a, #c7a740)," +
                "linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%)," +
                "linear-gradient(#f6ebbe, #e6c34d);" +
                "-fx-background-insets: 0,9 9 8 9,9,10,11;" +
                "-fx-background-radius: 50; -fx-padding: 15 30 15 30;" +
                "-fx-font-family: \"Helvetica\"; -fx-font-size: 18px;" +
                "-fx-text-fill: #311c09;" +
                "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    stage.setTitle(username);
                    new ScoreBoard().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Button button3 = new Button("Change Password");
        button3.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                "linear-gradient(#020b02, #3a3a3a), linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                "linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%)," +
                "linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                "-fx-background-insets: 0,1,4,5,6; -fx-background-radius: 9,8,5,4,3;" +
                "-fx-padding: 15 30 15 30; -fx-font-family: \"Helvetica\";" +
                "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;" +
                "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vBox1 = new VBox();

                Popup popup = new Popup();

                Label label2 = new Label("enter your current password");
                label2.setTextFill(Color.web("Black"));
                label2.setFont(Font.font(15));
                PasswordField passwordField = new PasswordField();
                passwordField.setPrefColumnCount(10);
                HBox hBox = new HBox(passwordField);


                Label label = new Label("enter your new password");
                label.setTextFill(Color.web("Black"));
                label.setFont(Font.font(15));
                PasswordField passwordField1 = new PasswordField();
                passwordField1.setPrefColumnCount(10);
                HBox hBox1 = new HBox(passwordField1);



                Button buttonForChangePassword = new Button("OK!");
                buttonForChangePassword.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String currentPassword = passwordField.getText();
                        String newPassword = passwordField1.getText();
                        if (currentPassword.equals("") || newPassword.equals("")){
                            Popup popup = new Popup();
                            Label label2 = new Label("fields can not be empty");
                            label2.setTextFill(Color.web("red"));
                            label2.setFont(Font.font(15));
                            popup.getContent().add(label2);
                            popup.setAnchorX(700);
                            popup.setAnchorY(690);
                            popup.show(stage);
                            popup.setAutoHide(true);
                        }
                        else{
                            String response = Main.changePassword(username,"change password --currentPassword " + currentPassword + "" +
                                    " --newPassword " + newPassword);
                            if (response.startsWith("Wrong")){
                                Popup popup = new Popup();
                                Label label2 = new Label("Wrong password");
                                label2.setTextFill(Color.web("red"));
                                label2.setFont(Font.font(15));
                                popup.getContent().add(label2);
                                popup.setAnchorX(700);
                                popup.setAnchorY(690);
                                popup.show(stage);
                                popup.setAutoHide(true);
                            }
                            else if (response.startsWith("Current")){
                                Popup popup = new Popup();
                                Label label2 = new Label("current password is equal to new password");
                                label2.setTextFill(Color.web("red"));
                                label2.setFont(Font.font(15));
                                popup.getContent().add(label2);
                                popup.setAnchorX(700);
                                popup.setAnchorY(690);
                                popup.show(stage);
                                popup.setAutoHide(true);
                            }
                            else{
                                Popup popup = new Popup();
                                Label label2 = new Label("password changed successfully");
                                label2.setTextFill(Color.web("green"));
                                label2.setFont(Font.font(15));
                                popup.getContent().add(label2);
                                popup.setAnchorX(700);
                                popup.setAnchorY(690);
                                popup.show(stage);
                                popup.setAutoHide(true);
                            }
                        }
                        System.out.println(User.allUsers.get(username).getPassword());
                    }
                });


                vBox1.getChildren().addAll(label2,
                        hBox,
                        label,
                        hBox1,
                        buttonForChangePassword);

                popup.getContent().add(vBox1);
                popup.setAnchorX(700);
                popup.setAnchorY(550);
                popup.show(stage);
                popup.setAutoHide(true);
            }
        });


        Button button4 = new Button("Delete Account");
        button4.setStyle("-fx-background-color: #c3c4c4," +
                "linear-gradient(#d6d6d6 50%, white 100%)," +
                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);" +
                "-fx-background-radius: 30; -fx-background-insets: 0,1,1;" +
                "-fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vBox1 = new VBox();

                Popup popup = new Popup();

                Label label2 = new Label("Are you sure about that?");
                label2.setTextFill(Color.web("red"));
                label2.setFont(Font.font(20));

                Button buttonYes = new Button("Yes");
                buttonYes.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        ByteArrayInputStream in2 = new ByteArrayInputStream("yes".getBytes());
                        System.setIn(in2);
                        try {
                            Main.deleteAccount(username);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        new LoginMenu().start(stage);
                    }
                });

                vBox1.getChildren().addAll(label2,
                        buttonYes);

                popup.getContent().add(vBox1);
                popup.setAnchorX(700);
                popup.setAnchorY(600);
                popup.show(stage);
                popup.setAutoHide(true);
            }
        });


        Button button5 = new Button("Back");
        button5.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);" +
                "-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new LoginMenu().start(stage);
            }
        });







        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(
                button,
                button1,
                button2,
                button3,
                button4,
                button5);

        root.getChildren().addAll(vBox);
        // create a image
        Image image = new Image(getClass().getResource("/Sample/IMG/pack4.jpg").toExternalForm());
        double width = image.getWidth();
        double height = image.getHeight();


        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);
        root.setBackground(background);




        Scene scene = new Scene(root,400,600);
        primaryStage.setScene(scene);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }
}
