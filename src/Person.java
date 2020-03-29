import java.util.ArrayList;
import java.util.LinkedList;

public class Person extends Entity{
    public Point[] initPos;
    public Point[] pos;
    public ArrayList<Point> target;
    int currentTarget;
    Obstacle currentObs=null;
    boolean first=false;


    public Person(Point center, Point target, Room room, int signature) {
        super(room,signature);
        this.target= new ArrayList<Point>();
        currentTarget=0;
        this.target.add(target);
        this.pos = around(center);
        this.initPos = copies(pos);
        addPrint();
    }

    public void move(){

        System.out.println("This is my target: "+target.get(currentTarget));
        System.out.println("This is my pos: "+pos[0]);

        removePrint();

        this.pos = nextPos(true);

        addPrint();

    }

    public Point[] nextPos(boolean lookAround){

        if(pos[0].equals(target.get(currentTarget))){
            if(currentTarget==0){
                System.out.println("=====>  JE SUIS ARRIVE  <=======");
            }else {
                if(lookAround) {
                    currentTarget--;
                }else{
                    currentTarget=0;
                }
                System.out.println("je passe à la cible suivante");
                if(first) {
                    currentObs.addPrint();
                    first=false;
                }
            }
        };

        Point nextCenter=pos[0];
        double minDistance=pos[0].distance(target.get(currentTarget));
        for (int i = 1; i < 20 ; i++) {
            //System.out.println(room.map[pos[i].x][pos[i].y]);
            if(room.map[pos[i].x][pos[i].y]==0||!lookAround){
                double distance = pos[i].distance(target.get(currentTarget));
                if (distance < minDistance) {
                    minDistance = distance;
                    nextCenter = pos[i];
                }
            }else{
                //System.out.println("J'ai vu quelqu'un");
            }
        }
        return around(nextCenter);
    }

    public void addPrint(){
        for (Point point:pos) {
            room.map[point.x][point.y]+=signature;
        }
    }

    public void removePrint(){
        for (Point point:pos) {
            room.map[point.x][point.y]= room.map[point.x][point.y]-signature;
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
        Point [] possibleTargets=new Point[4];
        int nbtargets=0;
        int sign;
        int lastSign=0;

        removePrint();
        while (!pos[0].equals(target.get(0))) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pos = nextPos(false);
            System.out.println("My imaginary position: "+pos[0]);
            sign = room.map[pos[0].x][pos[0].y];
            System.out.println("J'imagine que je suis sur une case: "+sign);
            if (sign != 0 && sign % 2 == 0) {// if there is an obstacle
                if(sign!=lastSign) {
                    System.out.println("il y a un obstacle");
                    currentObs = room.obstacles.get(sign / 2 - 1);
                    currentObs.removePrint();
                    first=true;
                    System.out.println("il est #" + (sign / 2 - 1) + " dans la liste");
                    possibleTargets=copies(currentObs.allPoints());
                }
                double minDist = pos[0].distance(possibleTargets[0]);
                System.out.println("minDist: "+minDist);
                int pointToReach=0;
                System.out.println("PTR: "+pointToReach);
                for (int i = 1; i < 4 ; i++) {
                    System.out.println("dist"+pos[0].distance(possibleTargets[i]));
                    if(pos[0].distance(possibleTargets[i])<minDist){
                        minDist=pos[0].distance(possibleTargets[i]);
                        System.out.println("minDist: "+minDist);
                        pointToReach=i;
                        System.out.println("PTR: "+pointToReach);
                    }
                }
                target.add(new Point(possibleTargets[pointToReach].x,possibleTargets[pointToReach].y)); //my target is point A of the obstacle on my way (c'est pas ce qu'on veut mais c'est un début)
                possibleTargets[pointToReach]=new Point(10000,10000);
                //currentTarget++;
                nbtargets++;
                currentTarget=nbtargets;
                System.out.println("j'ai ajouter un nouvelle cible à ma liste'");
                System.out.println("My new target: "+target.get(currentTarget));

                lastSign=sign;
            }
        }
        pos=copies(initPos);
        addPrint();
        currentTarget=nbtargets;
    }
}
