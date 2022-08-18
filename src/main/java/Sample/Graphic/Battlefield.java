package Sample.Graphic;

import Sample.Controller.BattleField;
import Sample.Controller.Main;
import Sample.Controller.ThreadForBattlefield;
import Sample.Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.Objects;


public class Battlefield extends Application {
    public Stage stage;
    public String userName;
    BattleField battleField;
    public GridPane gridPane = new GridPane();
    int pakManLife;
    int score;
    public String how;

    //sounds fields
    public boolean eatingScoreSound = false;
    public boolean eatingEnergy = false;
    public boolean pacmanEat = false;
    public boolean soulEat = false;
    boolean soundPlay = true;


    @Override
    public void init() throws Exception {
        if (how.equalsIgnoreCase("new")){
            User.allUsers.get(userName).setSavingTheGame(null);
        }
        battleField = new BattleField(User.allUsers.get(userName));
        battleField.battlefield = this;
        pakManLife = battleField.packManLifePoint;
        score = battleField.score;

        battleField.runTheGame(how);
        this.start(stage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //starting threads
        ThreadForBattlefield threadForBattlefield = new ThreadForBattlefield();
        threadForBattlefield.battleField = battleField;
        threadForBattlefield.start();

        battleField.start();




        //initialize nodes
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane();



        //adding video
        String path = Objects.requireNonNull(getClass().getResource("/Sample/Video/first.mp4")).toExternalForm();

        //Instantiating Media class
        Media media = new Media(path);

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Instantiating MediaView class
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(1000);
        mediaView.setFitWidth(1000);

        //by setting this property to true, the Video will be played
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);



        //adding music
        //main music
        String path2 = Objects.requireNonNull(getClass().getResource("/Sample/Music/main.wav")).toExternalForm();
        //Instantiating Media class
        Media media2 = new Media(path2);
        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
        //by setting this property to true, the audio will be played
        mediaPlayer2.setAutoPlay(true);
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);

        //eating score music
        AudioClip audioClip = new AudioClip(getClass().getResource("/Sample/Music/score.wav").toExternalForm());

        //eating energy music
        AudioClip audioClip2 = new AudioClip(getClass().getResource("/Sample/Music/energy.wav").toExternalForm());

        //pacman eat music
        AudioClip audioClip3 = new AudioClip(getClass().getResource("/Sample/Music/pacmanEat.wav").toExternalForm());

        //soul eat music
        AudioClip audioClip4 = new AudioClip(getClass().getResource("/Sample/Music/soulsEat.wav").toExternalForm());

        //goodbye music
        AudioClip audioClip5 = new AudioClip(getClass().getResource("/Sample/Music/goodbye.mp3").toExternalForm());





        //adding label
        Label label = new Label("Pacman life point:" + pakManLife + "   -   " + "Score:" + score);
        label.setFont(Font.font(20));
        label.setTextFill(Color.web("white"));
        label.setAlignment(Pos.CENTER);


        //setting buttons
        Button button = new Button("Pause");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                battleField.canSoulsMove = false;
            }
        });

        Button button1 = new Button("Exit");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                audioClip5.play();
                battleField.isGameFinished = true;
                threadForBattlefield.finished = 0;
                String response = Main.afterGame(battleField, userName);
                Popup popup = new Popup();
                Label label2 = new Label(response);
                label2.setTextFill(Color.web("orange"));
                label2.setFont(Font.font(20));
                popup.getContent().add(label2);
                popup.setAnchorX(550);
                popup.setAnchorY(650);
                popup.show(stage);
                popup.setAutoHide(true);
                stage.setTitle(userName);
                mediaPlayer2.stop();
                try {
                    new MainMenu().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button button2 = new Button("Mute");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (soundPlay){
                    mediaPlayer2.stop();
                    soundPlay = false;
                }
                else{
                    mediaPlayer2.play();
                    soundPlay = true;
                }
            }
        });


        //adding buttons to hbox
        HBox hBox = new HBox();
        hBox.getChildren().addAll(button, button1, button2);
        hBox.setAlignment(Pos.CENTER);


        //initializing gridpane
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i<17; ++i){
            for (int j = 0; j<21; ++j){
                switch (battleField.maze[i][j]) {
                    case " ": {
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/wall2.jpg")).toExternalForm());
                        ImageView imageView = new ImageView(image);
                        gridPane.add(imageView, j, i);
                        break;
                    }
                    case "#": {
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/wall.jpg")).toExternalForm());
                        ImageView imageView = new ImageView(image);
                        gridPane.add(imageView, j, i);
                        break;
                    }
                    case "@": {
                        //adding video
                        String path5 = Objects.requireNonNull(getClass().getResource("/Sample/Video/pacLeft.mp4")).toExternalForm();

                        //Instantiating Media class
                        Media media5 = new Media(path5);

                        //Instantiating MediaPlayer class
                        MediaPlayer mediaPlayer5 = new MediaPlayer(media5);

                        //Instantiating MediaView class
                        MediaView mediaView5 = new MediaView(mediaPlayer5);
                        mediaView5.setFitHeight(20);
                        mediaView5.setFitWidth(30);

                        //by setting this property to true, the Video will be played
                        mediaPlayer5.setAutoPlay(true);
                        mediaPlayer5.setMute(true);
                        mediaPlayer5.setCycleCount(MediaPlayer.INDEFINITE);
                        gridPane.add(mediaView5, j, i);
                        break;
                    }
                    case "-": {
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/score.jpg")).toExternalForm());
                        ImageView imageView = new ImageView(image);
                        gridPane.add(imageView, j, i);
                        break;
                    }
                    case "%": {
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/energy.jpg")).toExternalForm());
                        ImageView imageView = new ImageView(image);
                        gridPane.add(imageView, j, i);
                        break;
                    }
                    case "&":
                        if (battleField.currentX1 == j && battleField.currentY1 == i) {
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/soul1.png")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        } else if (battleField.currentX2 == j && battleField.currentY2 == i) {
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/soul2.png")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        } else if (battleField.currentX3 == j && battleField.currentY3 == i) {
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/soul3.png")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        } else if (battleField.currentX4 == j && battleField.currentY4 == i) {
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/soul4.png")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        }
                        break;
                }
            }
        }


        //working on nodes
        vBox.getChildren().addAll(label, gridPane, hBox);
        stackPane.getChildren().addAll(mediaView, vBox);
        Scene scene = new Scene(stackPane);


        //arrow keys
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                //because whenever pacman moves, souls should start moving
                battleField.canSoulsMove = true;
                //getting arrow keys
                String code = event.toString();
                String[] seperate = code.split("code");
                String mainCode = "";
                for (int i = 0; i < seperate[1].length(); ++i) {
                    if (seperate[1].charAt(i) != ' ' && seperate[1].charAt(i) != '=' && seperate[1].charAt(i) != ']')
                        mainCode += String.valueOf(seperate[1].charAt(i));
                }
                //setting arrow keys
                if (mainCode.equalsIgnoreCase("up"))
                    mainCode = "w";
                else if (mainCode.equalsIgnoreCase("down"))
                    mainCode = "s";
                else if (mainCode.equalsIgnoreCase("left"))
                    mainCode = "a";
                else if (mainCode.equalsIgnoreCase("right"))
                    mainCode = "d";
                battleField.jahat = mainCode;
                //updating gridpane
                gridPane.getChildren().clear();
                pakManLife = battleField.packManLifePoint;
                score = battleField.score;
                //checking if game is over
                //sounds
                if (pakManLife == 0){
                    mediaPlayer2.stop();
                    audioClip5.play();
                    threadForBattlefield.finished = 0;
                    String response = Main.afterGame(battleField, userName);


                    Popup popup = new Popup();
                    Label label2 = new Label(response);
                    label2.setTextFill(Color.web("orange"));
                    label2.setFont(Font.font(20));
                    popup.getContent().add(label2);
                    popup.setAnchorX(550);
                    popup.setAnchorY(650);
                    popup.show(stage);
                    popup.setAutoHide(true);

                    try {
                        stage.setTitle(userName);
                        new StartNewGame().start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //updating gridpane
                label.setText("Pacman life point:" + pakManLife + "   -   " + "Score:" + score);
                label.setFont(Font.font(20));
                for (int i = 0; i<17; ++i){
                    for (int j = 0; j<21; ++j){
                        if (battleField.maze[i][j].equals(" ")){
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/wall2.jpg")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        }
                        else if (battleField.maze[i][j].equals("#")){
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/wall.jpg")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        }
                        else if (battleField.maze[i][j].equals("@") && mainCode.equalsIgnoreCase("d")){
                            System.out.println("hehe");
                            //adding video
                            String path5 = Objects.requireNonNull(getClass().getResource("/Sample/Video/pacRight.mp4")).toExternalForm();

                            //Instantiating Media class
                            Media media5 = new Media(path5);

                            //Instantiating MediaPlayer class
                            MediaPlayer mediaPlayer5 = new MediaPlayer(media5);

                            //Instantiating MediaView class
                            MediaView mediaView5 = new MediaView(mediaPlayer5);
                            mediaView5.setFitHeight(20);
                            mediaView5.setFitWidth(25);

                            //by setting this property to true, the Video will be played
                            mediaPlayer5.setAutoPlay(true);
                            mediaPlayer5.setMute(true);
                            mediaPlayer5.setCycleCount(MediaPlayer.INDEFINITE);
                            gridPane.add(mediaView5, j, i);
                        }
                        else if (battleField.maze[i][j].equals("@") && mainCode.equalsIgnoreCase("s")){
                            //adding video
                            String path5 = Objects.requireNonNull(getClass().getResource("/Sample/Video/pacDown.mp4")).toExternalForm();

                            //Instantiating Media class
                            Media media5 = new Media(path5);

                            //Instantiating MediaPlayer class
                            MediaPlayer mediaPlayer5 = new MediaPlayer(media5);

                            //Instantiating MediaView class
                            MediaView mediaView5 = new MediaView(mediaPlayer5);
                            mediaView5.setFitHeight(20);
                            mediaView5.setFitWidth(25);

                            //by setting this property to true, the Video will be played
                            mediaPlayer5.setAutoPlay(true);
                            mediaPlayer5.setMute(true);
                            mediaPlayer5.setCycleCount(MediaPlayer.INDEFINITE);
                            gridPane.add(mediaView5, j, i);
                        }
                        else if (battleField.maze[i][j].equals("@") && mainCode.equalsIgnoreCase("w")){
                            //adding video
                            String path5 = Objects.requireNonNull(getClass().getResource("/Sample/Video/pacUp.mp4")).toExternalForm();

                            //Instantiating Media class
                            Media media5 = new Media(path5);

                            //Instantiating MediaPlayer class
                            MediaPlayer mediaPlayer5 = new MediaPlayer(media5);

                            //Instantiating MediaView class
                            MediaView mediaView5 = new MediaView(mediaPlayer5);
                            mediaView5.setFitHeight(20);
                            mediaView5.setFitWidth(25);

                            //by setting this property to true, the Video will be played
                            mediaPlayer5.setAutoPlay(true);
                            mediaPlayer5.setMute(true);
                            mediaPlayer5.setCycleCount(MediaPlayer.INDEFINITE);
                            gridPane.add(mediaView5, j, i);
                        }
                        else if (battleField.maze[i][j].equals("@") && mainCode.equalsIgnoreCase("a")){
                            //adding video
                            String path5 = Objects.requireNonNull(getClass().getResource("/Sample/Video/pacLeft.mp4")).toExternalForm();

                            //Instantiating Media class
                            Media media5 = new Media(path5);

                            //Instantiating MediaPlayer class
                            MediaPlayer mediaPlayer5 = new MediaPlayer(media5);

                            //Instantiating MediaView class
                            MediaView mediaView5 = new MediaView(mediaPlayer5);
                            mediaView5.setFitHeight(20);
                            mediaView5.setFitWidth(25);

                            //by setting this property to true, the Video will be played
                            mediaPlayer5.setAutoPlay(true);
                            mediaPlayer5.setMute(true);
                            mediaPlayer5.setCycleCount(MediaPlayer.INDEFINITE);
                            gridPane.add(mediaView5, j, i);
                        }
                        else if (battleField.maze[i][j].equals("-")){
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/score.jpg")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        }
                        else if (battleField.maze[i][j].equals("%")){
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/energy.jpg")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        }
                        else if (battleField.maze[i][j].equals("&") && battleField.canSoulEatPackMan){
                            if (battleField.currentX1 == j && battleField.currentY1 == i) {
                                Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/soul1.png")).toExternalForm());
                                ImageView imageView = new ImageView(image);
                                gridPane.add(imageView, j, i);
                            }
                            else if (battleField.currentX2 == j && battleField.currentY2 == i){
                                Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/soul2.png")).toExternalForm());
                                ImageView imageView = new ImageView(image);
                                gridPane.add(imageView, j, i);
                            }
                            else if (battleField.currentX3 == j && battleField.currentY3 == i){
                                Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/soul3.png")).toExternalForm());
                                ImageView imageView = new ImageView(image);
                                gridPane.add(imageView, j, i);
                            }
                            else if (battleField.currentX4 == j && battleField.currentY4 == i){
                                Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/soul4.png")).toExternalForm());
                                ImageView imageView = new ImageView(image);
                                gridPane.add(imageView, j, i);
                            }
                        }
                        else if (battleField.maze[i][j].equals("&") && !battleField.canSoulEatPackMan){
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("/Sample/IMG/whiteSoul.png")).toExternalForm());
                            ImageView imageView = new ImageView(image);
                            gridPane.add(imageView, j, i);
                        }
                    }
                }
                if (eatingScoreSound && soundPlay){
                    audioClip.play();
                    eatingScoreSound = false;
                }
                if (eatingEnergy && soundPlay){
                    audioClip2.play();
                    eatingEnergy = false;
                }
                if (pacmanEat && soundPlay){
                    audioClip3.play();
                    pacmanEat = false;
                }
                if (soulEat && soundPlay){
                    audioClip4.play();
                    soulEat = false;
                }
            }
        });





        //setting stage stuffs
        stage.setScene(scene);
        stage.setHeight(800);
        stage.setWidth(1000);
        stage.show();
    }
}
