public class Person extends Entity{
    public Point[] pos;
    public Point target;
    public int[][] map;

    public Person(int[][] map,Point center, Point target,int signature) {
        super(signature);
        this.map=map;
        this.target = target;
        this.pos = around(center);
        for (Point point:this.pos) {
            map[point.x][point.y]=signature;
        }
    }

    public void move(){
        removePrint();
        this.pos = nextPos();
        addPrint();
    }

    public Point[] nextPos(){
        Point nextCenter=pos[0];
        double minDistance=pos[0].distance(target);
        for (int i = 1; i < 20 ; i++) {
            if(map[pos[i].x][pos[i].y]==0){
                double distance = pos[i].distance(target);
                if (distance < minDistance) {
                    minDistance = distance;
                    nextCenter = pos[i];
                    System.out.println(i);
                }
            }
        }
        return around(nextCenter);
    }

    public void addPrint(){
        for (Point point:pos) {
            map[point.x][point.y]=signature;
        }
    }

    public void removePrint(){
        for (Point point:pos) {
            map[point.x][point.y]=0;
        }
    }

    public Point[] around(Point p){
        return new Point[]{
                p,
                new Point(p.x + 1, p.y),
                new Point(p.x + 1, p.y - 1),
                new Point(p.x, p.y - 1),
                new Point(p.x - 1, p.y - 1),
                new Point(p.x - 1, p.y),
                new Point(p.x - 1, p.y + 1),
                new Point(p.x, p.y + 1),
                new Point(p.x + 1, p.y + 1),
                new Point(p.x + 2, p.y + 1),
                new Point(p.x + 2, p.y),
                new Point(p.x + 2, p.y-1),
                new Point(p.x + 1, p.y-2),
                new Point(p.x, p.y-2),
                new Point(p.x-1, p.y-2),
                new Point(p.x-2, p.y-1),
                new Point(p.x-2, p.y),
                new Point(p.x-2, p.y+1),
                new Point(p.x-1, p.y+2),
                new Point(p.x, p.y+2),
                new Point(p.x+1, p.y+2)
        };
    }

}
