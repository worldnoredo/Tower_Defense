import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;

public class Bullet {
    public Bullet(Tower tower,Group root){
        path.setDuration(Duration.seconds(tower.getBulletSpeed()));
        imageView = new ImageView(tower.getBulletImage());
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setPreserveRatio(true);
        imageView.setX(tower.getPoint().x+Tower.width/2);
        imageView.setY(tower.getPoint().y+Tower.height/2);
        path.setNode(imageView);

    }
    public Image image;
    public ImageView imageView;
    public PathTransition path = new PathTransition();
    public void load(Tower tower,Enemy enemy,Group root,GameField gameField) {
        root.getChildren().add(imageView);
        path.setPath(new Line(tower.getPoint().x+32,tower.getPoint().y+32,
                enemy.getTarget(tower.getBulletSpeed()).getTranslateX(),enemy.getTarget(tower.getBulletSpeed()).getTranslateY()));
        path.play();
        path.setOnFinished(event1 -> {
                enemy.takeDamage(tower.getAttackDamage(),gameField,root);
            root.getChildren().remove(imageView);
        });
    }
}
