import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class GameField {
    public static final int column = 20;
    public static final int line = 10;
    public Square start ;
    public Square finish;
    public Square[][] gameField = new Square[column-2][line-1];
    public PathTransition path;
    public Shop shop = new Shop();
    public Base base = new Base();
    public Tower[][] towerList = new Tower[column - 2][line - 1];
    public int wave;
    public int nEnemy;
    public int fEnemy;
    public int tEnemy;
    public ArrayList<Enemy> enemyList = new ArrayList<>();
    public Text text;
    public TextFlow textFlow;
    public BUTTON button = new BUTTON();
    public GameField(int i, Group root) throws Exception {
        GameStage gameStage = new GameStage(i);
        for (int j = 0; j < column - 2; j++) for (int k = 0; k < line - 1; k++) {
                gameField[j][k] = gameStage.squareList[j][k];
                gameField[j][k].loadImage(root);
        }

        text = new Text("\n\n\n\n"+"Get Ready!");
        text.setFont(new Font(25));
        text.setX(0);
        text.setY(9*GameTile.height);
        text.setLineSpacing(7);
        text.setTextOrigin(VPos.BOTTOM);
        text.setWrappingWidth(500);
        root.getChildren().add(text);

        path = gameStage.path;
        start = gameStage.start;
        start.imageView.setBlendMode(BlendMode.SRC_OVER);
        finish = gameStage.finish;
        shop.load(root);
        base.load(root);
        nEnemy = 0;
        fEnemy = 0;
        tEnemy = 0;
        wave = 0;
        loadenemy(root);

        root.setOnMouseMoved(event -> {
            int x = (int)(event.getX()/GameTile.width);
            int y = (int)(event.getY()/GameTile.height);
            if (x < 18 && y < 9) {
                if (towerList[x][y] != null) {
                    towerList[x][y].getCircle().setStroke(Color.RED);
                } else for (Tower[] a : towerList)
                    for (Tower b : a)
                        if (b != null) b.getCircle().setStroke(Color.TRANSPARENT);
            }
        });
    }
    public static Comparator<Enemy> sortenemy = new Comparator<Enemy>() {
        @Override
        public int compare(Enemy o1, Enemy o2) {
            return (o1.getrate() > o2.getrate()) ? -1 : 1;
        }
    };
    public void loadenemy(Group root) throws Exception {
        GameField m = this;
        String url = System.getProperty("user.dir");
        File input = new File(url + "/src/stage/stageenemy1.txt");
        Scanner sc = new Scanner(input);
        sc.useLocale(Locale.US);
        AtomicInteger endtime = new AtomicInteger(0);
        Timeline loadwave = new Timeline(new KeyFrame(Duration.ZERO,event -> {
            nEnemy = sc.nextInt();
            fEnemy = sc.nextInt();
            tEnemy = sc.nextInt();
            endtime.set(Math.max(Math.max(nEnemy*2+1,fEnemy*2+6),tEnemy*2+10));
            Timeline loadnEnemy = new Timeline(new KeyFrame(Duration.ZERO,actionEvent -> {
                try {
                    enemyList.add(new NormalEnemy(m,root));
                    nEnemy--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }),new KeyFrame(Duration.seconds(2)));
            loadnEnemy.setOnFinished(actionEvent -> {
                if (nEnemy!= 0) loadnEnemy.play();
            });
            Timeline loadfEnemy = new Timeline(new KeyFrame(Duration.ZERO,actionEvent -> {
                try {
                    enemyList.add(new FastEnemy(m,root));
                    fEnemy--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }),new KeyFrame(Duration.seconds(2)));
            loadfEnemy.setOnFinished(actionEvent -> {
                if (fEnemy!= 0) loadfEnemy.play();
            });
            Timeline loadtEnemy = new Timeline(new KeyFrame(Duration.ZERO,actionEvent -> {
                try {
                    enemyList.add(new TankEnemy(m,root));
                    tEnemy--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }),new KeyFrame(Duration.seconds(2)));
            loadtEnemy.setOnFinished(actionEvent -> {
                if (tEnemy!= 0) loadtEnemy.play();
            });
            Timeline loadenemy = new Timeline(new KeyFrame(Duration.seconds(0),actionEvent -> {
                if (nEnemy != 0) loadnEnemy.play();
            }),new KeyFrame(Duration.seconds(5),actionEvent -> {
                if (fEnemy != 0) loadfEnemy.play();
            }),new KeyFrame(Duration.seconds(10),actionEvent -> {
                if (tEnemy != 0) loadtEnemy.play();
            }));
            loadenemy.play();
            wave++;
        }),new KeyFrame(Duration.seconds(25),actionEvent -> {
            if (endtime.get() < 25) root.getChildren().add(start.imageView);
        }),new KeyFrame(Duration.seconds(30),actionEvent -> {
            if (endtime.get() >=25 && endtime.get() <30) root.getChildren().add(start.imageView);
        }),new KeyFrame(Duration.seconds(35),actionEvent -> {
            if (endtime.get() >=30 && endtime.get() <35) root.getChildren().add(start.imageView);
        }),new KeyFrame(Duration.seconds(40),actionEvent -> {
            if (endtime.get() >=35 && endtime.get() <40) root.getChildren().add(start.imageView);
        }),new KeyFrame(Duration.seconds(45)));
        loadwave.setOnFinished(event -> {
            if (wave < 10) loadwave.play();
            root.getChildren().remove(start.imageView);
            text.setText("\n\n\nFight");
        });

        Timeline sort = new Timeline(new KeyFrame(Duration.ZERO,actionEvent -> {
            enemyList.sort(sortenemy);
        }),new KeyFrame(Duration.seconds(0.5)));
        sort.setOnFinished(event -> {
            sort.play();
        });
        sort.play();

        EventHandler<MouseEvent> startclick = mouseEvent -> {
            if (wave == 0) {
                loadwave.play();
                root.getChildren().remove(start.imageView);
                text.setText("\n\n\nFight!");
            } else
                loadwave.jumpTo(Duration.INDEFINITE);

        };
        start.imageView.setOnMouseClicked(startclick);
        root.getChildren().add(start.imageView);
    }
    public void addtext(String s){
        System.out.println(text.getText().indexOf('\n'));
        String m = text.getText().substring(text.getText().indexOf('\n')+1);
        text.setText(m + "\n" + s);
    }

}
