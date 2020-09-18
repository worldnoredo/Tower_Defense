
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;


interface Enemy extends GameEntity {
    public final int width = 64;
    public final int height = 64;
    public void takeDamage(double x,GameField gameField, Group root);
    public ImageView getImageView();
    public void loadpath(GameField gameField,Group root);
    public int getDamage();
    public int getGold();
    public Circle getTarget(double time);
    public double getrate();
}
