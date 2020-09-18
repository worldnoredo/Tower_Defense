
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.File;
import java.io.InputStream;
import java.nio.file.LinkOption;
import java.util.concurrent.atomic.AtomicInteger;


public class test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();

        Scene sc = new Scene(root,GameField.column*Square.width,GameField.line*Square.height);
        //primaryStage.setScene(sc);
        BUTTON button = new BUTTON();
        button.loadbutton(primaryStage,root,sc);

        Class<?> clazz = this.getClass();

        InputStream inputnde = clazz.getResourceAsStream("/image/nde.png");
        Image imagende = new Image(inputnde,64,64,false,true);
        ImageView imageViewnde = new ImageView(imagende);
        imageViewnde.setX(64);
        imageViewnde.setY(9*64);

        InputStream inputmde = clazz.getResourceAsStream("/image/mde.png");
        Image imagemde = new Image(inputmde,64,64,false,true);
        ImageView imageViewmde = new ImageView(imagemde);
        imageViewmde.setX(2*64);
        imageViewmde.setY(9*64);

        InputStream inputsde = clazz.getResourceAsStream("/image/sde.png");
        Image imagesde = new Image(inputsde,64,64,false,true);
        ImageView imageViewsde = new ImageView(imagesde);
        imageViewsde.setX(3*64);
        imageViewsde.setY(9*64);

        root.getChildren().addAll(imageViewmde,imageViewnde,imageViewsde);

        GameField game1 = new GameField(1,root);;
        // load mapimageview


        // load shop

        //load transition


        //mouse handler
        AtomicInteger type = new AtomicInteger(0);
        EventHandler<MouseEvent> mEvent = event -> {
            int a = (int)(event.getX()/GameTile.width);
            int b = (int)(event.getY()/GameTile.height);
            // normal
            if ( type.get()  == 0) {
                // buy normaltower choose
                if (a == 1 && b == 9) {
                    if (game1.base.gold < 50) game1.addtext("Not enough gold");
                    else {
                        type.set(4);
                        game1.addtext("Buy Normal Tower");
                        for (Square[] m : game1.gameField)
                            for (Square i : m)
                                if (i.type == 0)
                                    i.imageView.setEffect(new Shadow(BlurType.THREE_PASS_BOX, Color.GREEN, 10));

                    }
                }
                // buy mTower choose
                else if (a == 2 && b == 9 ) {
                    if (game1.base.gold < 50)  game1.addtext("Not Enough gold");
                    else {
                        type.set(5);
                        game1.addtext("Buy Machine Tower");
                        for (Square[] m : game1.gameField)
                            for (Square i : m)
                                if (i.type == 0)
                                    i.imageView.setEffect(new Shadow(BlurType.THREE_PASS_BOX, Color.GREEN, 10));

                    }
                }
                //buy sTower choose
                else if (a == 3 && b == 9 ) {
                    if (game1.base.gold < 50)  game1.addtext("Not Enough gold");
                    else {
                        type.set(6);
                        game1.addtext("Buy Sniper Tower");
                        for (Square[] m : game1.gameField)
                            for (Square i : m)
                                if (i.type == 0)
                                    i.imageView.setEffect(new Shadow(BlurType.THREE_PASS_BOX, Color.GREEN, 10));

                    }
                }
                //sell tower choose
                else if (a == 0 && b == 9) {
                    type.set(3);
                    game1.addtext("Sell Tower");
                    for (Tower[] m : game1.towerList) for (Tower i : m) if (i != null) i.getImageView().setEffect(new GaussianBlur());
                }
                // buy health
                else if (a == 4 && b == 9) {
                    if (game1.base.gold < 200)  game1.addtext("Not Enough gold");
                    else {
                        game1.base.buyHealth();
                        game1.addtext("Health + 3. Gold - 200");
                        String music = "src/buy.wav";
                        Media media = new Media(new File(music).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                    }
                }
                else if (a == 5 && b == 9){
                    if (game1.base.gold < 50)  game1.addtext("Not Enough gold");
                    else {
                        type.set(8);
                        game1.addtext("Choose an Enemy");
                        String music = "src/buy.wav";
                        Media media = new Media(new File(music).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                    }
                }
            }
            // sell tower
            else if (type.get() == 3){
                if (a < 18 && b < 9)
                    if (game1.gameField[a][b].type == 4 || game1.gameField[a][b].type == 5 || game1.gameField[a][b].type == 6) {
                        game1.gameField[a][b].setType(0);
                        root.getChildren().removeAll(game1.towerList[a][b].getImageView(),
                                                        game1.towerList[a][b].getImagede(),
                                                        game1.towerList[a][b].getCircle());
                        if (button.getSetup()) {
                            String music = "src/buy.wav";
                            Media media = new Media(new File(music).toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.play();
                        }
                        game1.base.goldincrease(game1.towerList[a][b].getGold()/2);
                        game1.addtext("sell tower: gold+" + String.valueOf(game1.towerList[a][b].getGold()/2));
                        game1.towerList[a][b] = null;
                        System.gc();
                    } else  game1.addtext("Cancel");
                else  game1.addtext("Cancel");
                type.set(0);
                for (Tower[] m : game1.towerList) for (Tower i : m) if (i != null) i.getImageView().setEffect(null);
            }
            // build normal tower
            else if (type.get() == 4){
                if (a < 18 && b < 9)
                    if (game1.gameField[a][b].type == 0){
                        game1.towerList[a][b] = new NormalTower(a,b,game1,root);
                        game1.addtext("Buy Normal Tower: gold - 50");
                        if (button.getSetup()) {
                            String music = "src/buy.wav";
                            Media media = new Media(new File(music).toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.play();
                        }
                    }
                else  game1.addtext("Cancel");
                else  game1.addtext("Cancel");
                type.set(0);
                for (Square[] x : game1.gameField) for (Square i : x)  i.imageView.setEffect(null);
            }
            // build MachineTower
            else if (type.get() == 5){
                if (a < 18 && b < 9)
                    if (game1.gameField[a][b].type == 0){
                        game1.towerList[a][b] = new MachineTower(a,b,game1,root);
                        game1.addtext("Buy Machine Tower: gold - 50");
                        if (button.getSetup()) {
                        String music = "src/buy.wav";
                        Media media = new Media(new File(music).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                        }
                    }
                else  game1.addtext("Cancel");
                else  game1.addtext("Cancel");
                type.set(0);
                for (Square[] x : game1.gameField) for (Square i : x)  i.imageView.setEffect(null);
            }
            // build snipertower
            else if (type.get() == 6){
                if (a < 18 && b < 9)
                    if (game1.gameField[a][b].type == 0){
                        game1.towerList[a][b] = new SniperTower(a,b,game1,root);
                        game1.addtext("Buy Sniper Tower: gold - 50");
                        if (button.getSetup()) {
                            String music = "src/buy.wav";
                            Media media = new Media(new File(music).toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.play();
                        }
                    }
                else  game1.addtext("Cancel");
                else  game1.addtext("Cancel");
                type.set(0);
                for (Square[] x : game1.gameField) for (Square i : x)  i.imageView.setEffect(null);
            }
            //one shot enemy
            else if (type.get() == 8) {
                int i = 0;
                while (i < game1.enemyList.size() && event.getTarget() != game1.enemyList.get(i).getImageView()) i++;
                if (i < game1.enemyList.size()) {
                    game1.enemyList.get(i).takeDamage(100,game1,root);
                    game1.base.golddecrease(50);
                }
                else  game1.addtext("Cancel");
                type.set(0);
            }
            System.out.println(type.get());
        };
        sc.setOnMouseClicked(mEvent);
        primaryStage.setTitle("test");
        primaryStage.show();
    }



    public static void main(String[] args){
        launch(args);
    }
}
