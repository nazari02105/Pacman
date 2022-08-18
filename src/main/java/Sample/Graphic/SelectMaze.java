package Sample.Graphic;

import Sample.Controller.Main;
import Sample.Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SelectMaze extends Application {
    Stage stage;
    ScrollPane root = new ScrollPane();
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        String username = stage.getTitle();
        stage.setTitle("Select Maze");



        String mazes = Main.selectingBoard(username);
        String[] arrayMazes = mazes.split("\\*");

        GridPane gridPane = new GridPane();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        VBox vBox = new VBox();

        for (int i = 0; i<arrayMazes.length; ++i){
            String[] maze = arrayMazes[i].split("\n");
            for (int j = 0; j<17; ++j){
                String line = maze[j];
                for (int k = 0; k<21; ++k){
                    Character x = line.charAt(k);
                    if (x.equals('#')){
                        Image image = new Image(getClass().getResource("/Sample/IMG/wall.jpg").toExternalForm());
                        ImageView imageView = new ImageView(image);
                        gridPane.add(imageView, i*21+k+i, j);
                    }
                    else{
                        Image image = new Image(getClass().getResource("/Sample/IMG/wall2.jpg").toExternalForm());
                        ImageView imageView = new ImageView(image);
                        gridPane.add(imageView, i*21+k+i, j);
                    }
                }
            }
        }



        for (int i = 0; i<arrayMazes.length; ++i){
            Button button = new Button("number" + String.valueOf(i+1));
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    User.allUsers.get(username).setSelectedMaze(finalI);
                    try {
                        stage.setTitle(username);
                        new StartNewGame().start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            hBox.getChildren().add(button);
        }


        Button button = new Button("New Maze");
        button.setStyle("-fx-background-color: #000000," +
                "linear-gradient(#7ebcea, #2f4b8f)," +
                "linear-gradient(#426ab7, #263e75)," +
                "linear-gradient(#395cab, #223768);" +
                "-fx-background-insets: 0,1,2,3;" +
                "-fx-background-radius: 3,2,2,2;" +
                "-fx-padding: 12 30 12 30; -fx-text-fill: white;" +
                "-fx-font-size: 12px;");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    stage.setTitle(username);
                    new SelectNewMaze().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button button1 = new Button("Back");
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
                try {
                    stage.setTitle(username);
                    new StartNewGame().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Button button2 = new Button("Build Maze");
        button2.setStyle("-fx-background-color: #000000," +
                "linear-gradient(#7ebcea, #2f4b8f)," +
                "linear-gradient(#426ab7, #263e75)," +
                "linear-gradient(#395cab, #223768);" +
                "-fx-background-insets: 0,1,2,3;" +
                "-fx-background-radius: 3,2,2,2;" +
                "-fx-padding: 12 30 12 30; -fx-text-fill: white;" +
                "-fx-font-size: 12px;");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BuildMazeByUser buildMazeByUser = new BuildMazeByUser();
                buildMazeByUser.username = username;
                try {
                    buildMazeByUser.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        hBox2.getChildren().addAll(button, button2, button1);



        hBox.setSpacing(500);

        vBox.getChildren().addAll(gridPane, hBox, hBox1, hBox2);



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
        root.setContent(vBox);
        root.setPannable(true);




        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(500);
        stage.show();
        stage.setAlwaysOnTop(true);



    }
}
