import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Base {
    public int health;
    public int gold;
    public Rectangle golarectangle;
    public Rectangle healthrectangle;
    public Text goldText = new Text();
    public Text healthText= new Text();
    public StackPane goldstack = new StackPane();
    public StackPane healthstack = new StackPane();
    public Base(){
        health = 20;
        gold = 300;
        updateGold();
        updateHealth();
        golarectangle = new Rectangle(2*GameTile.width, GameTile.height);
        golarectangle.setFill(Color.BROWN);
        goldText.setFont(new Font(40));
        goldstack.getChildren().addAll(golarectangle,goldText);

        healthrectangle = new Rectangle(2*GameTile.width, GameTile.height);
        healthrectangle.setFill(Color.RED);
        healthText.setFont(new Font(40));
        healthstack.getChildren().addAll(healthrectangle,healthText);
    }
    public void load(Group root){
        goldstack.setLayoutX(6*GameTile.width);
        goldstack.setLayoutY(9*GameTile.height);
        healthstack.setLayoutX(8*GameTile.width);
        healthstack.setLayoutY(9*GameTile.height);
        root.getChildren().addAll(goldstack,healthstack);
    }
    //gold void
    public void updateGold(){
        goldText.setText(String.valueOf(gold));
    }
    public void goldincrease(int x){
        gold += x;
        updateGold();
    }
    public void golddecrease(int x){
        gold -= x;
        updateGold();
    }
    // health void
    public void updateHealth(){
        healthText.setText(String.valueOf(health));
    }
    public void enemyescape(Enemy enemy){
        health -= enemy.getDamage();
        updateHealth();
    }
    public void buyHealth(){
        health += 3;
        golddecrease(200);
        updateHealth();
    }
}
