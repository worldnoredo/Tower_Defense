import javafx.scene.Group;

public interface GameEntity {
    public void setPoint(Point m);
    public Point getPoint();
    public void loadImage(Group root);
}
