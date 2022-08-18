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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class StartNewGame extends Application {
    Stage stage;
    StackPane root = new StackPane();
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        String username = stage.getTitle();
        stage.setTitle("New Game");


        VBox vBox = new VBox();


        Button button = new Button("Start game");
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
                if (User.allUsers.get(username).getSelectedMaze() == -1){
                    Popup popup = new Popup();
                    Label label2 = new Label("You did not select any maze");
                    label2.setTextFill(Color.web("red"));
                    label2.setFont(Font.font(15));
                    popup.getContent().add(label2);
                    popup.setAnchorX(800);
                    popup.setAnchorY(600);
                    popup.show(stage);
                    popup.setAutoHide(true);
                }
                else{
                    stage.setTitle(username);
                    try {
                        Battlefield battlefield = new Battlefield();
                        battlefield.stage = stage;
                        battlefield.userName = username;
                        battlefield.how = "new";
                        battlefield.init();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });












        Button button1 = new Button("Select Maze");
        button1.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400)," +
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
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setTitle(username);
                try {
                    new SelectMaze().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });













        Button button2 = new Button("Set PacMan Life");
        button2.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400)," +
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
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Popup popup = new Popup();
                VBox vBox1 = new VBox();

                Label label2 = new Label("Choose pacman life.");
                label2.setTextFill(Color.web("white"));
                label2.setFont(Font.font(20));

                MenuItem menuItem1 = new MenuItem("2");
                menuItem1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Main.setPackManLife("set packman life 2", username);
                    }
                });
                MenuItem menuItem2 = new MenuItem("3");
                menuItem2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Main.setPackManLife("set packman life 3", username);
                    }
                });
                MenuItem menuItem3 = new MenuItem("4");
                menuItem3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Main.setPackManLife("set packman life 4", username);
                    }
                });
                MenuItem menuItem4 = new MenuItem("5");
                menuItem4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Main.setPackManLife("set packman life 5", username);
                    }
                });

                MenuButton menuButton = new MenuButton("Options", null, menuItem1, menuItem2, menuItem3, menuItem4);


                Image imageForButton = new Image(getClass().getResource("/Sample/IMG/pack5.png").toExternalForm());
                ImageView imageView = new ImageView(imageForButton);

                menuButton.setGraphic(imageView);

                vBox1.getChildren().addAll(label2, menuButton);

                popup.getContent().add(vBox1);
                popup.setAnchorX(900);
                popup.setAnchorY(600);
                popup.show(stage);
                popup.setAutoHide(true);
            }
        });












        Button button3 = new Button("Back");
        button3.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400)," +
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
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    stage.setTitle(username);
                    new MainMenu().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });












        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(button,
                button1,
                button2,
                button3);


        root.getChildren().addAll(vBox);

        // create a image
        Image image = new Image(getClass().getResource("/Sample/IMG/pack2.jpg").toExternalForm());
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
