

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;


public class SniperTower implements Tower{
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public int attackrange;
    public double attackspeed;
    public double attackdamage;
    public double bulletspeed;
    public int level = 1;
    public int gold = 50;
    Image image;
    ImageView imageview ;

    Image imagede;
    ImageView imageViewde;

    private Circle circle = new Circle();

    Image bulletimage;
    Class<?> clazz = this.getClass();
    public Point point;
    public SniperTower(int a, int b, GameField gameField, Group root) {
        attackdamage = 2;
        attackrange = 300;
        attackspeed = 5;
        bulletspeed = 0.6;

        InputStream inputde = clazz.getResourceAsStream("/image/sde.png");
        imagede = new Image(inputde,64,64,false,true);
        imageViewde = new ImageView(imagede);
        imageViewde.setX(a*width);
        imageViewde.setY(b*height);

        InputStream input = clazz.getResourceAsStream("/image/spaceShips_007.png");
        image = new Image(input);
        bulletimage = new Image(clazz.getResourceAsStream("/image/bullet.png"));
        imageview = new ImageView(image);
        imageview.setFitHeight(50);
        imageview.setFitWidth(50);
        imageview.setX(a * width + 8);
        imageview.setY(b * height + 8);
        gameField.gameField[a][b].setType(6);
        point = new Point(a * width, b * height);
        fire(root, gameField);
        loadImage(root);
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
            for (Enemy e : gamefield.enemyList) if (point.distance(new Point(e.getImageView().getTranslateX(),e.getImageView().getTranslateY())) < attackrange)
            {
                new Bullet(this,root).load(this,e,root,gamefield);
            }
        }),new KeyFrame(Duration.seconds(this.attackspeed)));
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
