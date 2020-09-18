import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;

public class FastEnemy implements Enemy{
    public final double fullhealth = 15;
    public final double fullhealthwidth = 40;
    public Image image ;
    public int speed;
    public double health;
    public int gold;
    public int armor;
    public int damage;
    public Point point;
    public ImageView imageView = new ImageView();
    public PathTransition path = new PathTransition();
    public PathTransition path1 = new PathTransition();
    public PathTransition path2 = new PathTransition();
    public PathTransition future;
    public Circle target = new Circle(10,Color.TRANSPARENT);
    public Rectangle rectangle1 = new Rectangle();
    public Rectangle rectangle2 = new Rectangle();
    public FastEnemy(GameField gameField, Group root) throws Exception {
        speed = 15;
        health = 15;
        gold = 25;
        armor = 2;
        damage = 1;
        point = new Point(0,0);
        Class<?> clazz = this.getClass();
        InputStream input = clazz.getResourceAsStream("/image/fastenemy.png");
        image = new Image(input,40,40,false,true);
        imageView.setImage(image);
        imageView.setX(point.x);
        imageView.setY(point.y);
        rectangle1.setX(0);
        rectangle1.setY(0);
        rectangle1.setWidth(fullhealthwidth);
        rectangle1.setHeight(3);
        rectangle1.setFill(Color.RED);
        rectangle2.setX(0);
        rectangle2.setY(0);
        rectangle2.setWidth(fullhealthwidth);
        rectangle2.setHeight(3);
        rectangle2.setFill(Color.GREEN);

        loadImage(root);
        loadpath(gameField,root);

    }
    @Override
    public void setPoint(Point m) {
        point.x = m.x;
        point.y = m.y;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public void loadImage(Group root){
        root.getChildren().addAll(imageView,rectangle1,rectangle2,target);
    }

    @Override
    public void takeDamage(double x,GameField gameField,Group root) {
        health = health - x*10/(double)(10+armor);
        double width = fullhealthwidth*health/fullhealth;
        rectangle2.setWidth(width);
        if (health < 0) health = 0;
        if (health == 0) {
            gameField.enemyList.remove(this);
            path.stop();
            root.getChildren().removeAll(imageView,rectangle1,rectangle2,target);
            gameField.base.goldincrease(gold);
            gameField.addtext("gold + " + gold);
        }
    }
    @Override
    public ImageView getImageView(){
        return imageView;
    }
    @Override
    public void loadpath(GameField gameField,Group root){
        path.setPath(gameField.path.getPath());
        path.setDuration(Duration.seconds(speed));
        path.setNode(imageView);
        path.play();
        path1.setPath(gameField.path.getPath());
        path1.setDuration(Duration.seconds(speed));
        path1.setNode(rectangle1);
        path1.play();
        path2.setPath(gameField.path.getPath());
        path2.setDuration(Duration.seconds(speed));
        path2.setNode(rectangle2);
        path2.play();
        path.setOnFinished(event -> {
            root.getChildren().removeAll(imageView,rectangle1,rectangle2,target);
            path1.stop();
            path2.stop();
            future.stop();
            gameField.base.enemyescape(this);
            gameField.enemyList.remove(this);
        });
        future = new PathTransition(path.getDuration(),path.getPath(),target);
    }
    @Override
    public int getDamage(){
        return damage;
    }
    @Override
    public int getGold(){
        return gold;
    }
    @Override
    public Circle getTarget(double time){
        future.play();
        future.jumpTo(path.getCurrentTime().add(new Duration(time*1000)));
        future.pause();
        return  target;
    }
    @Override
    public double getrate() {
        return path.getCurrentTime().toSeconds()/speed;
    }

}
