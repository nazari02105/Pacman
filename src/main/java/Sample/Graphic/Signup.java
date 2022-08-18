package Sample.Graphic;

import Sample.Controller.Main;
import Sample.Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;


public class Signup extends Application  {
    private StackPane root = new StackPane();
    private Stage stage;
    double width;
    double height;

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();


        Label label = new Label("Your Username");
        label.setTextFill(Color.web("black"));
        TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        HBox hBox = new HBox();
        hBox.getChildren().add(textField);

        Label label1 = new Label("Your Password");
        label1.setTextFill(Color.web("black"));
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(10);
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(passwordField);


        Button button = new Button("SIGNUP");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = textField.getText();
                String password = passwordField.getText();
                if (username.equals("") || password.equals("")) {
                    Popup popup = new Popup();
                    Label label2 = new Label("Fields can\n not\n" +
                            "be empty");
                    label2.setTextFill(Color.web("red"));
                    label2.setFont(Font.font(20));
                    popup.getContent().add(label2);
                    popup.setAnchorX(700);
                    popup.setAnchorY(400);
                    popup.show(stage);
                    popup.setAutoHide(true);
                }
                else{
                    String response = "";
                    try {
                        response = Main.signup("user signup --username " + username + " --password " + password);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (response.startsWith("User with")){
                        Popup popup = new Popup();
                        Label label2 = new Label("User with\n this\n" +
                                "id exists.");
                        label2.setTextFill(Color.web("red"));
                        label2.setFont(Font.font(20));
                        popup.getContent().add(label2);
                        popup.setAnchorX(700);
                        popup.setAnchorY(400);
                        popup.show(stage);
                        popup.setAutoHide(true);
                    }
                    else{
                        Popup popup = new Popup();
                        Label label2 = new Label("User created\n" +
                                "successfully.");
                        label2.setTextFill(Color.web("green"));
                        label2.setFont(Font.font(20));
                        popup.getContent().add(label2);
                        popup.setAnchorX(700);
                        popup.setAnchorY(400);
                        popup.show(stage);
                        popup.setAutoHide(true);
                        new LoginMenu().start(stage);
                    }
                }
            }
        });


        Button button1 = new Button("Back");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new LoginMenu().start(stage);
            }
        });

        HBox hBox2 = new HBox(button, button1);



        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(
                label,
                hBox,
                label1,
                hBox1,
                hBox2);
        root.getChildren().addAll(vBox);
        // create a image
        Image image = new Image(getClass().getResource("/Sample/IMG/pack3.jpg").toExternalForm());
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
        primaryStage.setTitle("Signup Menu");
        primaryStage.setAlwaysOnTop(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}