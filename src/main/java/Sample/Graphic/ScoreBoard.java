package Sample.Graphic;

import Sample.Controller.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreBoard extends Application {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        String username = stage.getTitle();
        stage.setTitle("Score board");

        String scores = Main.showScoreBoard();
        String[] seperate = scores.split("\n");


        PieChart pieChart = new PieChart();

        for (int i = 0; i<seperate.length; ++i){
            if (i == 10)
                break;
            if (seperate.length >= 10){
                pieChart.getData().add(new PieChart.Data(seperate[i], 36));
            }
            else {
                pieChart.getData().add(new PieChart.Data(seperate[i], 360 / seperate.length));
            }
        }

        Button button = new Button("Back");
        button.setAlignment(Pos.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
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

        VBox vbox = new VBox(pieChart, button);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 600, 600);

        stage.setScene(scene);
        stage.setHeight(600);
        stage.setWidth(900);

        primaryStage.show();
    }
}
