package Sample.Graphic;

import Sample.Controller.LoadUsers;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginMenu extends Application  {
    private StackPane root = new StackPane();
    private Stage stage;
    double width;
    double height;

    @Override
    public void init() throws Exception {
        try {
            LoadUsers.loadAllUsers();
        } catch (IOException e) {
            System.out.println("no");
        }
    }

    @Override
    public void start(Stage primaryStage) {

//        try {
//            LoadUsers.loadAllUsers();
//        } catch (IOException e) {
//            System.out.println("no");
//        }
        VBox vBox = new VBox();



        Label label = new Label("Your Username");
        label.setTextFill(Color.web("white"));
        TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        HBox hBox = new HBox();
        hBox.getChildren().add(textField);

        Label label1 = new Label("Your Password");
        label1.setTextFill(Color.web("white"));
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(10);
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(passwordField);

        Button button1 = new Button("LOGIN");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = textField.getText();
                String password = passwordField.getText();
                if (username.equals("") || password.equals("")){
                    Popup popup = new Popup();
                    Label label2 = new Label("Fields can not " +
                            "be empty");
                    label2.setTextFill(Color.web("red"));
                    label2.setFont(Font.font(20));
                    popup.getContent().add(label2);
                    popup.setAnchorX(900);
                    popup.setAnchorY(600);
                    popup.show(stage);
                    popup.setAutoHide(true);
                }
                else{
                    String response = "successful";
                    try {
                        response = Main.login("user login --username " + username + " --password " + password);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (response.startsWith("There is")){
                        Popup popup = new Popup();
                        Label label2 = new Label("There is no user with this username.");
                        label2.setTextFill(Color.web("red"));
                        label2.setFont(Font.font(20));
                        popup.getContent().add(label2);
                        popup.setAnchorX(900);
                        popup.setAnchorY(600);
                        popup.show(stage);
                        popup.setAutoHide(true);
                    }
                    else if (response.startsWith("Wrong")){
                        Popup popup = new Popup();
                        Label label2 = new Label("Password is wrong.");
                        label2.setTextFill(Color.web("red"));
                        label2.setFont(Font.font(20));
                        popup.getContent().add(label2);
                        popup.setAnchorX(900);
                        popup.setAnchorY(600);
                        popup.show(stage);
                        popup.setAutoHide(true);
                    }
                    else{
                        try {
                            stage.setTitle(username);
                            new MainMenu().start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });



        Button button2 = new Button("Exit");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    LoadUsers.saveAllUsers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });



        Button button3 = new Button("Play as guest");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setTitle("Admin");
                try {
                    new MainMenu().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        HBox hBox5 = new HBox(button1, button2, button3);



        Label label2 = new Label("Dont have an account yet?");
        label2.setTextFill(Color.web("red"));
        Button button = new Button("SIGNUP");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Signup().start(stage);
            }
        });

        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(
                label,
                hBox,
                label1,
                hBox1,
                hBox5,
                label2,
                button);
        root.getChildren().addAll(vBox);
        // create a image
        Image image = new Image(getClass().getResource("/Sample/IMG/pack2.jpg").toExternalForm());
        width = image.getWidth();
        height = image.getHeight();


        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);
        root.setBackground(background);









        stage = primaryStage;
        Scene scene = new Scene(root,400,600);
        primaryStage.setScene(scene);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.show();
        primaryStage.setTitle("Login Menu");
        primaryStage.setAlwaysOnTop(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}