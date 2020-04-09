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

    public Point[] around(boolean large){
        if(large) {
            return new Point[]{
                    this,
                    new Point(this.x + 1, this.y),
                    new Point(this.x + 1, this.y - 1),
                    new Point(this.x, this.y - 1),
                    new Point(this.x - 1, this.y - 1),
                    new Point(this.x - 1, this.y),
                    new Point(this.x - 1, this.y + 1),
                    new Point(this.x, this.y + 1),
                    new Point(this.x + 1, this.y + 1),
                    new Point(this.x + 2, this.y + 1),
                    new Point(this.x + 2, this.y),
                    new Point(this.x + 2, this.y-1),
                    new Point(this.x + 1, this.y-2),
                    new Point(this.x, this.y-2),
                    new Point(this.x-1, this.y-2),
                    new Point(this.x-2, this.y-1),
                    new Point(this.x-2, this.y),
                    new Point(this.x-2, this.y+1),
                    new Point(this.x-1, this.y+2),
                    new Point(this.x, this.y+2),
                    new Point(this.x+1, this.y+2)
            };
        }else{
            return new Point[]{
                    new Point(this.x + 1, this.y),
                    new Point(this.x + 1, this.y - 1),
                    new Point(this.x, this.y - 1),
                    new Point(this.x - 1, this.y - 1),
                    new Point(this.x - 1, this.y),
                    new Point(this.x - 1, this.y + 1),
                    new Point(this.x, this.y + 1),
                    new Point(this.x + 1, this.y + 1),
            };
        }
    }
}
