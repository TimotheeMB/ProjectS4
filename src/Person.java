import java.util.LinkedList;

public class Person extends Entity{
    public Point[] pos;
    public LinkedList<Point> target;
    public Room room;

    public Person(Point center, Point target, Room room, int signature) {
        super(signature);
        this.room=room;
        this.target.add(target);
        this.pos = around(center);
        addPrint();
    }

    public void move(){
        removePrint();
        this.pos = nextPos(true);
        addPrint();
    }

    public Point[] nextPos(boolean lookAround){
        Point nextCenter=pos[0];
        double minDistance=pos[0].distance(target.getLast());
        for (int i = 1; i < 20 ; i++) {
            if(room.map[pos[i].x][pos[i].y]==0||lookAround==false){
                double distance = pos[i].distance(target.getLast());
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
            room.map[point.x][point.y]=signature;
        }
    }

    public void removePrint(){
        for (Point point:pos) {
            room.map[point.x][point.y]=0;
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

    public void computeMyPathway() {
        pos=nextPos(false);
        for (Point p: pos) {
            int sign=room.map[p.x][p.y];
            if(sign%2==0) {// if there is an obstacle
                target.add(room.obstacles.get(sign/2-1).pointA); /*my target is point A of the obstacle on my way
                                                             (c'est pas ce qu'on veut mais c'est un début)*/
            }
        }
    }
}
