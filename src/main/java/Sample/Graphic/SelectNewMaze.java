package Sample.Graphic;

import Sample.Controller.Main;
import Sample.Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SelectNewMaze extends Application {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        String username = stage.getTitle();


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        String[][] maze = Main.generateNewMaze(username);


        for (int i = 0; i<17; ++i){
            for (int j = 0; j<21; ++j){
                if (maze[i][j].equals("#")){
                    Image image = new Image(getClass().getResource("/Sample/IMG/wall.jpg").toExternalForm());
                    ImageView imageView = new ImageView(image);
                    gridPane.add(imageView, i, j);
                }
                else{
                    Image image = new Image(getClass().getResource("/Sample/IMG/wall2.jpg").toExternalForm());
                    ImageView imageView = new ImageView(image);
                    gridPane.add(imageView, i, j);
                }
            }
        }

        Label label = new Label("do you like this?");

        Button button = new Button("Yes");
        button.setStyle("-fx-background-color: linear-gradient(#008000, #228B28);" +
                "-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                User.allUsers.get(username).addToMazes(maze);
                User.allUsers.get(username).setSelectedMaze(User.allUsers.get(username).getMazes().size() - 1);
                try {
                    stage.setTitle(username);
                    new StartNewGame().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Button button1 = new Button("No");
        button1.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);" +
                "-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new SelectNewMaze().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        hBox.getChildren().addAll(button, button1);



        vBox.getChildren().addAll(gridPane, label, hBox);


        // create a image
        Image image = new Image(getClass().getResource("/Sample/IMG/yellow.jpg").toExternalForm());
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

        vBox.setBackground(background);



        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setWidth(1300);
        stage.setHeight(600);
        stage.show();
        stage.setAlwaysOnTop(true);


    }
}
