
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;
import java.util.Timer;


public class MachineTower implements Tower{
    public Line laze = new Line();
    public int attackrange;
    public double attackspeed;
    public double attackdamage;
    public double bulletspeed;
    public int level = 1;
    public int gold = 50;
    public Timer reload = new Timer();
    Image image;
    Image imagede;
    ImageView imageViewde;
    ImageView imageview ;
    Image bulletimage;
    Class<?> clazz = this.getClass();
    public Point point;
    private Circle circle = new Circle();
    public MachineTower(int a, int b, GameField gameField, Group root) {
        attackdamage = 0.2;
        attackrange = 150;
        attackspeed = 0.03;
        bulletspeed = 0.05;

        InputStream inputde = clazz.getResourceAsStream("/image/mde.png");
        imagede = new Image(inputde,64,64,false,true);
        imageViewde = new ImageView(imagede);
        imageViewde.setX(a*width);
        imageViewde.setY(b*height);

        InputStream input = clazz.getResourceAsStream("/image/spaceShips_005.png");
        image = new Image(input);

        imageview = new ImageView(image);
        imageview.setFitHeight(50);
        imageview.setFitWidth(50);
        imageview.setX(a * width + 8);
        imageview.setY(b * height + 8);
        gameField.gameField[a][b].setType(5);
        point = new Point(a * width, b * height);

        loadImage(root);
        laze.setStartX(a*height+height/2);
        laze.setStartY(b*width+width/2);
        laze.setStrokeWidth(5);
        laze.setStroke(Color.RED);
        fire(root, gameField);
        gameField.base.golddecrease(gold);


        circle.setRadius(attackrange);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.TRANSPARENT);
        circle.setStrokeWidth(1);
        circle.setCenterX(a*64+32);
        circle.setCenterY(b*64+32);
        root.getChildren().add(circle);
    }
    public void upgrade(){
        attackdamage += 2;
        attackrange += 200;
        level += 1;
        gold *= 2;
        InputStream input = clazz.getResourceAsStream("/image/road.png");
        image = new Image(input);
        imageview.setImage(image);
    }
    // mouse event

    @Override
    public int getAttackRange() {
        return attackrange;
    }

    @Override
    public void setPoint(Point m) {

    }

    @Override
    public Point getPoint() {

        return point;
    }
    public Circle getCircle(){
        return circle;
    }

    @Override
    public ImageView getImagede() {
        return imageViewde;
    }

    @Override
    public void loadImage(Group root) {
        root.getChildren().addAll(imageViewde,imageview);
    }
    @Override
    public void fire(Group root,GameField gamefield){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),event -> {
            int i=0;
            if (gamefield.towerList[(int)(point.x/64)][(int)(point.y/64)] == null) reload.cancel();
            while (gamefield.enemyList.size()!= 0 && i < gamefield.enemyList.size() && this.getPoint().distance(
                    new Point(gamefield.enemyList.get(i).getImageView().getTranslateX(),gamefield.enemyList.get(i).getImageView().getTranslateY())) > this.getAttackRange()) i++;
            if (i != gamefield.enemyList.size() && gamefield.enemyList.size()!= 0) {
                root.getChildren().add(laze);
                laze.setEndX(gamefield.enemyList.get(i).getImageView().getTranslateX()+20);
                laze.setEndY(gamefield.enemyList.get(i).getImageView().getTranslateY()+20);
                gamefield.enemyList.get(i).takeDamage(this.attackdamage,gamefield,root);

            }
        }),new KeyFrame(Duration.seconds(attackspeed),event -> {
            root.getChildren().remove(laze);
        }));
        timeline.play();
        timeline.setOnFinished(event -> {
            if (!root.getChildren().contains(imageview)) timeline.stop(); else timeline.play();
        });

    }
    @Override
    public double getBulletSpeed(){
        return bulletspeed;
    }

    @Override
    public Image getBulletImage() {
        return bulletimage;
    }
    @Override
    public int getGold(){
        return gold;
    }
    @Override
    public ImageView getImageView() {
        return imageview;
    }
    public double getAttackDamage(){
        return attackdamage;
    }
}

