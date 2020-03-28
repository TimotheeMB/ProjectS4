import java.util.LinkedList;

public class Person extends Entity{
    public Point[] initPos;
    public Point[] pos;
    public LinkedList<Point> target;

    public Person(Point center, Point target, Room room, int signature) {
        super(room,signature);
        this.target= new LinkedList<Point>();
        this.target.add(target);
        this.pos = around(center);
        this.initPos = copies(pos);
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
            System.out.println(room.map[pos[i].x][pos[i].y]);
            if(room.map[pos[i].x][pos[i].y]==0||!lookAround){
                double distance = pos[i].distance(target.getLast());
                if (distance < minDistance) {
                    minDistance = distance;
                    nextCenter = pos[i];
                }
            }else{
                System.out.println("J'ai vu quelqu'un");
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

    public Point[] copies(Point[] x){
        Point [] r=new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            r[i]=x[i];
        }
        return r;
    }

    public void computeMyPathway() {
        removePrint();
        while (!pos[0].equals(target.getFirst())) {
            System.out.println("je calcule mon traget");
            pos = nextPos(false);
            int sign = room.map[pos[0].x][pos[0].y];
            if (sign != 0 && sign % 2 == 0) {// if there is an obstacle
                Obstacle obs=room.obstacles.get(sign / 2 - 1);
                double minDist = pos[0].distance(obs.allPoints()[0]);
                int pointToReach=0;
                for (int i = 1; i < 4 ; i++) {
                    if(pos[0].distance(obs.allPoints()[i])<minDist){
                        minDist=pos[0].distance(obs.allPoints()[i]);
                        pointToReach=i;
                    }
                }
                target.add(obs.allPoints()[pointToReach]); //my target is point A of the obstacle on my way (c'est pas ce qu'on veut mais c'est un début)
            }
        }
        pos=copies(initPos);
    }
}
