import java.util.ArrayList;

public class Person extends Entity{

    /* === ATTRIBUTES === */

    //Position
    public Point[] initPos;
    public Point[] pos;

    //Targets
    public Point finalTarget;
    public ArrayList<Point> targets;
    int targetIndex;

    /* === CONSTRUCTOR === */
    public Person(Point center, Point target, Room room, int signature) {

        super(room,signature);

        //Position
        this.pos = around(center);
        this.initPos = pos;

        //Targets
        this.finalTarget=target;
        this.targets= new ArrayList<>();
        targetIndex=0;


        addPrint();//so that we can see the person we created
    }

    public void move(){

        removePrint();
        if(pos[0].equals(currentTarget())){
            targetIndex++;
        }
        pos=around(findCloserPoint(pos,currentTarget(),true,false));
        addPrint();
    }

    public void computeMyPathway() {

        Obstacle currentObs=null;
        Point [] possibleTargets=new Point[4];
        int sign;
        int lastSign=0;

        removePrint();

        while (!pos[0].equals(finalTarget)) {

            if(pos[0].equals(currentTarget())){
                targetIndex++;
                currentObs.addPrint();
            }

            pos=around(findCloserPoint(pos,currentTarget(),false,false));

            sign = room.map[pos[0].x][pos[0].y];

            // if there is an obstacle
            if (sign != 0 && sign % 2 == 0) {

                //if it a different obstacle
                if(sign!=lastSign) {
                    currentObs = room.obstacles.get(sign / 2 - 1);
                    possibleTargets=currentObs.allPoints();
                }

                currentObs.removePrint();

                Point pointToReach=findCloserPoint(possibleTargets,pos[0],false,true);

                targets.add(pointToReach);
                lastSign=sign;
            }
        }

        targetIndex=0;
        pos=initPos;
        addPrint();
    }

    public Point findCloserPoint(Point[] points, Point target, boolean emptyPoint, boolean suppressThePoint){

        double smallerDistance=points[0].distance(target); //default value, no check of emptiness
        int index=0;

        for (int i = 1; i < points.length ; i++) {
            if(!emptyPoint||room.map[points[i].x][points[i].y]==0) {
                double distance = points[i].distance(target);
                if (distance < smallerDistance) {
                    smallerDistance = distance;
                    index=i;
                }
            }
        }
        Point r= points[index];
        if(suppressThePoint) {
            points[index] = new Point(10000, 10000);
        }
        return r;
    }

    public Point currentTarget(){
        if (targetIndex>=targets.size()){
            return finalTarget;
        }else{
            return targets.get(targetIndex);
        }
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
}
