
import javafx.animation.PathTransition;
import javafx.scene.shape.Polyline;
import java.io.*;
import java.net.URL;
import java.util.*;

public class GameStage {
    public Polyline road = new Polyline();
    public PathTransition transition = new PathTransition();
    public Square[][] squareList = new Square[GameField.column-2][GameField.line-1];
    ArrayList<Integer> squareX = new ArrayList<>();
    ArrayList<Integer> squareY = new ArrayList<>();
    public PathTransition path = new PathTransition();
    public Square start;
    public Square finish;
    public GameStage(int i) throws Exception {
        try {

            for (int j = 0; j < GameField.column-2; j++)
                for (int k = 0; k < GameField.line-1; k++) squareList[j][k] = new Square(0,j*Square.width,k*Square.height);
            // get road
            String url = System.getProperty("user.dir");
            File input = new File(url + "/src/stage/stageroad1.txt");
            Scanner sc = new Scanner(input);
            sc.useLocale(Locale.US);
            while (sc.hasNextInt()) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                squareX.add(a);
                squareY.add(b);
                if (squareX.size() == 1 )
                    squareList[a][b] = new Square(1,a*Square.width,b*Square.height);
                else {
                    if (squareX.get(squareX.size()-2) == a) {
                        if (squareY.get(squareY.size()-2) < b)
                            for (int j = squareY.get(squareY.size()-2); j <= b; j++)
                                squareList[a][j] = new Square(1,a*Square.width,j*Square.height);
                        else for (int j = b;j < squareY.get(squareY.size()-2); j++)
                                squareList[a][j] = new Square(1,a*Square.width,j*Square.height);

                    }
                    else {
                        if (squareX.get(squareX.size()-2) < a)
                            for (int j = squareX.get(squareX.size()-2)+1; j <= a; j++)
                                squareList[j][b] = new Square(1,j*Square.width,b*Square.height);
                        else for (int j = a; j < squareX.get(squareX.size()-2); j++)
                                squareList[j][b] = new Square(1,j*Square.width,b*Square.height);
                    }
                }

            }
            finish = new Square(10,squareX.get(squareX.size()-1)*Square.width,squareY.get(squareY.size()-1)*Square.height);
            start = new Square(9,squareX.get(0)*Square.width,squareY.get(0)*Square.height);
            Double[] pointRoad = new Double[squareX.size() * 2];
            for (int j = 0; j < squareX.size(); j++) {
                pointRoad[j * 2] = (double)squareX.get(j)*Square.width+Enemy.height/2;
                pointRoad[j * 2 + 1] = (double)squareY.get(j)*Square.height+Enemy.width/2;
            }
            road.getPoints().addAll(pointRoad);

            path.setPath(road);

        } catch (FileNotFoundException e){
            System.out.print(e.getMessage());
        }
    }



}
