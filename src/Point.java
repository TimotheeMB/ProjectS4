public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point B){
        return Math.sqrt((B.x-this.x)*(B.x-this.x)+(B.y-this.y)*(B.y-this.y));
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean equals(Point a) {
        return (this.x==a.x && this.y==a.y);
    }
}
