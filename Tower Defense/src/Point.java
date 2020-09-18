public class Point {
    public double x;
    public double y;
    public Point(double a,double b){
        this.x = a;
        this.y = b;
    }
    public double distance(Point other){
        double a = other.x - x;
        double b = other.y - y;
        return Math.sqrt(a * a + b * b);
    }
}
