
import java.io.InputStream;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Square implements GameTile{
    public Point point;
    public Image image;
    public ImageView imageView;
    public int type;
    Class<?> clazz = this.getClass();
    public Square(int i,double a,double b){
        type = i;
        switch (i) {
            // grass
            case 0: {
                InputStream input = clazz.getResourceAsStream("/image/grass.png");
                image = new Image(input);
                imageView = new ImageView(image);
                break;
            }
            // road
            case 1: {
                InputStream input = clazz.getResourceAsStream("/image/road.png");
                image = new Image(input);
                imageView = new ImageView(image);
                break;
            }
            case 2:{
                InputStream input = clazz.getResourceAsStream("/image/normaltower.png");
                image = new Image(input);
                imageView = new ImageView(image);
                break;
            }
            // remove
            case 3:{
                InputStream input = clazz.getResourceAsStream("/image/bin.jpg");
                image = new Image(input);
                imageView = new ImageView(image);
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                break;
            }
            // nTower
            case 4:{
                InputStream input = clazz.getResourceAsStream("/image/spaceShips_006.png");
                image = new Image(input);
                imageView = new ImageView(image);
                //imageView.setFitWidth(32);
                //imageView.setFitHeight(32);
                break;
            }
            // mTower
            case 5:{
                InputStream input = clazz.getResourceAsStream("/image/spaceShips_005.png");
                image = new Image(input);
                imageView = new ImageView(image);
                //imageView.setFitWidth(50);
                //imageView.setFitHeight(50);
                break;
            }
            // sTower
            case 6:{
                InputStream input = clazz.getResourceAsStream("/image/spaceShips_007.png");
                image = new Image(input);
                imageView = new ImageView(image);
                //imageView.setFitWidth(50);
                //imageView.setFitHeight(50);
                break;
            }
            //health
            case 7:{
                InputStream input = clazz.getResourceAsStream("/image/health.png");
                image = new Image(input);
                imageView = new ImageView(image);
                break;
            }
            //sniper
            case 8:{
                InputStream input = clazz.getResourceAsStream("/image/oneshot.png");
                image = new Image(input);
                imageView = new ImageView(image);
                break;
            }
            // start
            case 9:{
                InputStream input = clazz.getResourceAsStream("/image/start.png");
                image = new Image(input);
                imageView = new ImageView(image);
                break;
            }
            // finish
            case 10:{
                InputStream input = clazz.getResourceAsStream("/image/road.png");
                image = new Image(input);
                imageView = new ImageView(image);
                break;
            }
        }
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setX(a);
        imageView.setY(b);
        point = new Point(a*width+width/2,b*height+height/2);
    }
    public void setType(int t){
        type = t;
    }

    @Override
    public void setPoint(Point m) {
        point = new Point(m.x,m.y);
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public void loadImage(Group root) {
        root.getChildren().add(imageView);
    }
}
