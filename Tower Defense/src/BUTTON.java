import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;

public class BUTTON {
    private Boolean setup = true;
    private Button NewGame = new Button("NEW GAME");
    private Button buttotQuit = new Button("PAUSE");
    private Button LoadGame = new Button("LOAD GAME");
    private Button Options = new Button("OPTIONS");
    private Button Exit = new Button("EXIT");
    private Button soundon= new Button("ON SOUND");
    private Button soundoff= new Button("OFF SOUND");
    private Button menu = new Button("MENU");
    private Button Continue = new Button("CONTINUE");
    private Button sOn = new Button();
    private Button sOff = new Button();
    public void loadbutton(Stage stage, Group root, Scene scene) {
        Pane rootmenu = new Pane();
        Class<?> clazz = this.getClass();
        Scene sceneMenu = new Scene(rootmenu,1280,640);

        NewGame.setLayoutX(550);
        NewGame.setLayoutY(300);
        NewGame.setPrefSize(200,45);
        NewGame.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        NewGame.setOnAction(actionEvent -> {
            if (setup) {
            String music = "src/newgame.mp3";
            Media media = new Media(new File(music).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            }
            stage.setScene(scene);
        });


        LoadGame.setLayoutX(550);
        LoadGame.setLayoutY(350);
        LoadGame.setPrefSize(200,45);
        LoadGame.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        LoadGame.setOnAction(actionEvent -> {
            if (setup) {
            String music = "src/button.mp3";
            Media media = new Media(new File(music).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            }
            stage.setScene(scene);
        });


        Options.setLayoutX(550);
        Options.setLayoutY(400);
        Options.setPrefSize(200,45);
        Options.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");


        Exit.setLayoutX(550);
        Exit.setLayoutY(450);
        Exit.setPrefSize(200,45);
        Exit.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Exit.setOnAction(actionEvent -> {
            if (setup) {
                String music = "src/button.mp3";
                Media media = new Media(new File(music).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
            stage.close();
        });





        InputStream input = clazz.getResourceAsStream("image/menu.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        rootmenu.getChildren().addAll(imageView,NewGame,LoadGame,Options,Exit);


        soundon.setLayoutX(7*64);
        soundon.setLayoutY(5*64);
        soundon.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");


        Button soundoff= new Button("OFF SOUND");
        soundoff.setLayoutX(11*64);
        soundoff.setLayoutY(5*64);
        soundoff.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        soundoff.setOnAction(actionEvent -> {
            setup = false;
            rootmenu.getChildren().removeAll(soundoff,soundon);
            rootmenu.getChildren().addAll(NewGame,LoadGame,Options,Exit);
        });

        soundon.setOnAction(actionEvent -> {
            String music = "src/button.mp3";
            Media media = new Media(new File(music).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            setup = true;
            rootmenu.getChildren().removeAll(soundoff,soundon);
            rootmenu.getChildren().addAll(NewGame,LoadGame,Options,Exit);
        });



        Options.setOnAction(actionEvent -> {
            if (setup) {
                String music = "src/button.mp3";
                Media media = new Media(new File(music).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
            rootmenu.getChildren().removeAll(NewGame,LoadGame,Options,Exit);
            rootmenu.getChildren().addAll(soundon,soundoff);
        });

        InputStream inputon = clazz.getResourceAsStream("/image/on.png");
        Image imageon = new Image(inputon,64,64,false,true);
        ImageView imageViewOn = new ImageView(imageon);
        sOn.setGraphic(imageViewOn);
        sOn.setPrefSize(64,64);
        sOn.setLayoutX(550);
        sOn.setLayoutY(400);
        sOn.setOnAction(actionEvent -> {
            setup = true;
            String music = "src/button.mp3";
            Media media = new Media(new File(music).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            root.getChildren().removeAll(Continue,menu,sOn,sOff);
        });


        InputStream inputoff = clazz.getResourceAsStream("/image/off.png");
        Image imageoff = new Image(inputoff,64,64,false,true);
        ImageView imageViewOff = new ImageView(imageoff);
        sOff.setGraphic(imageViewOff);
        sOff.setPrefSize(64,64);
        sOff.setLayoutX(670);
        sOff.setLayoutY(400);
        sOff.setOnAction(actionEvent -> {
            setup = false;
            root.getChildren().removeAll(Continue,menu,sOn,sOff);
        });

        buttotQuit.setLayoutX(18*64);
        buttotQuit.setLayoutY(9*64);
        buttotQuit.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        buttotQuit.setOnAction(actionEvent -> {
            if (setup) {
                String music = "src/button.mp3";
                Media media = new Media(new File(music).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
            root.getChildren().addAll(menu,Continue,sOn,sOff);
        });
        root.getChildren().addAll(buttotQuit);

        menu.setLayoutX(550);
        menu.setLayoutY(350);
        menu.setPrefSize(200,45);
        menu.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        menu.setOnAction(actionEvent -> {
            if (setup) {
                String music = "src/button.mp3";
                Media media = new Media(new File(music).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
            stage.setScene(sceneMenu);
            root.getChildren().removeAll(Continue,menu,sOn,sOff);
        });
        Continue.setPrefSize(200,45);
        Continue.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Continue.setLayoutX(550);
        Continue.setLayoutY(300);
        Continue.setOnAction(actionEvent -> {
            if (setup) {
                String music = "src/button.mp3";
                Media media = new Media(new File(music).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
            root.getChildren().removeAll(Continue,menu,sOn,sOff);
        });


        stage.setScene(sceneMenu);
    }

    public Button getNewGame() {
        return NewGame;
    }
    public Button getButtotQuit() {
        return buttotQuit;
    }
    public Button getLoadGame() {
        return LoadGame;
    }
    public Button getOptions() {
        return Options;
    }
    public Button getExit() {
        return Exit;
    }
    public Button getSoundon() {
        return soundon;
    }
    public Button getSoundoff() {
        return soundoff;
    }
    public Boolean getSetup() {
        return setup;
    }
}                                                                                                     
                                                                                                      