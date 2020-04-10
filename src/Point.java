public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
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

    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
