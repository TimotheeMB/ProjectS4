import java.io.Serializable;

public class Point implements Serializable {
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
            /* |----|----|----|----|----|
               |    | 14 | 13 | 12 |    |
               |----|----|----|----|----|
               | 15 | 4  | 3  | 2  | 11 |
               |----|----|----|----|----|
               | 16 | 5  | 0  | 1  | 10 |
               |----|----|----|----|----|
               | 17 | 6  | 7  | 8  | 9  |
               |----|----|----|----|----|
               |    | 18 | 19 | 20 |    |
               |----|----|----|----|----| */
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
            /* |-----|-----|-----|
               |  4  |  3  |  2  |
               |-----|-----|-----|
               |  5  |  0  |  1  |
               |-----|-----|-----|
               |  6  |  7  |  8  |
               |-----|-----|-----| */
        }
    }

    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
