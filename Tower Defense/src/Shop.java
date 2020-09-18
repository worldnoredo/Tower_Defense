import javafx.scene.Group;


public class Shop {
    public Square remove = new Square(3,0*Square.width,9*Square.height);
    public Square nTower = new Square(4,1*Square.width,9*Square.height);
    public Square mTower = new Square(5,2*Square.width,9*Square.height);
    public Square sTower = new Square(6,3*Square.width,9*Square.height);
    public Square health = new Square(7,4*Square.width,9*Square.height);
    public Square sniper = new Square(8,5*Square.width,9*Square.height);
    //public
    public void load(Group root){
        root.getChildren().addAll(remove.imageView,nTower.imageView,sTower.imageView,mTower.imageView,health.imageView,sniper.imageView);
    }
}
