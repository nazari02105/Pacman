package Sample.Graphic;

import Sample.Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class BuildMazeByUser extends Application {
    public String username;
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        String[][] board = new String[17][21];

        for (int i = 0; i<17; ++i){
            for (int j = 0; j<21; ++j){
                if (i == 0 || i == 16 || j == 0 || j == 20){
                    board[i][j] = "#";
                    Button button = new Button("wall");
                    button.setTextFill(Paint.valueOf("white"));
                    gridPane.add(button, j, i);
                    button.setStyle("-fx-background-color: blue");
                }
                else{
                    board[i][j] = " ";
                    Button button = new Button("road");
                    button.setTextFill(Paint.valueOf("white"));
                    button.setMinWidth(30);
                    gridPane.add(button, j, i);
                    button.setStyle("-fx-background-color: black");
                    int finalI = i;
                    int finalJ = j;
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (button.getText().equals("wall")){
                                board[finalI][finalJ] = " ";
                                button.setText("road");
                                button.setStyle("-fx-background-color: black");
                            }
                            else{
                                board[finalI][finalJ] = "#";
                                button.setText("wall");
                                button.setStyle("-fx-background-color: blue");
                            }
                        }
                    });
                }
            }
        }


        Button button = new Button("finish");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                User.allUsers.get(username).addToMazes(board);
                User.allUsers.get(username).setSelectedMaze(User.allUsers.get(username).getMazes().size() - 1);
                stage.setTitle(username);
                try {
                    new StartNewGame().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        hBox.getChildren().add(button);


        vBox.getChildren().addAll(gridPane, hBox);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
        stage.setHeight(600);
        stage.setWidth(1200);


    }


}
