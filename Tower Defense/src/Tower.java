import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;


public interface Tower extends GameTile{
    public double getAttackDamage();
    public int getAttackRange();
    public double getBulletSpeed();
    public Image getBulletImage();
    public void fire(Group root, GameField gameField);
    public int getGold();
    public ImageView getImageView();
    public Circle getCircle();
    public ImageView getImagede();
}
