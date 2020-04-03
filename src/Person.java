import java.util.ArrayList;

public class Person extends Entity{

    /*** Hello ! My name is Brian and I am an instance of this class.
     * I will explain you what I do, so that we can get to know each other a bit more ;)
     */

    /* ================================================ */
    /* These are my attributes :*/

    //I've got a position in the room
    public Point[] pos;
    public Point[] initPos;//and I remember where I started from

    //I've got some Targets
    public Point finalTarget;//My real goal: the exit
    public ArrayList<Point> targets;//My intermediate targets, to avoid obstacles
    int targetIndex;// That's what I use to remember my current target


    /* ================================================ */
    /* This is my constructor :*/
    public Person(Point center, Point target, Room room, int signature) {

        /*I initialize my attributes*/
        super(room,signature);
        //Position
        this.pos = around(center);
        this.initPos = pos;
        //Targets
        this.finalTarget=target;
        this.targets= new ArrayList<>();
        targetIndex=0;

        /*I put myself in the room*/
        addPrint();
    }


    /* ================================================ */
    /* These are my methods :*/

    //I make one step
    public void move(){

        removePrint();//I disappear from my last position
        if(pos[0].distance(currentTarget())<10){//If I reached my target
            targetIndex++;//I switch to the new one
        }
        pos=around(findCloserPoint(pos,currentTarget(),true,false));//I compute my new position
        addPrint();//I appear in my new position
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
            if(!emptyPoint||emptyAround(points[i])) {
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
            room.map[point.x][point.y] -= signature;
        }
    }

    public boolean emptyAround(Point p) {
        for (Point d: around(p)) {
            if(room.map[d.x][d.y]!=0){
                return false;
            }
        }
        return true;
    }

    public Point[] personalSpace() {
        return new Point[]{
                new Point(pos[0].x+5, pos[0].y+0),
                new Point(pos[0].x+5, pos[0].y-1),
                new Point(pos[0].x+5, pos[0].y-2),
                new Point(pos[0].x+4, pos[0].y-3),
                new Point(pos[0].x+3, pos[0].y-4),
                new Point(pos[0].x+2, pos[0].y-5),
                new Point(pos[0].x+1, pos[0].y-5),
                new Point(pos[0].x+0, pos[0].y-5),
                new Point(pos[0].x-1, pos[0].y-5),
                new Point(pos[0].x-2, pos[0].y-5),
                new Point(pos[0].x-3, pos[0].y-4),
                new Point(pos[0].x-4, pos[0].y-3),
                new Point(pos[0].x-5, pos[0].y-2),
                new Point(pos[0].x-5, pos[0].y-1),
                new Point(pos[0].x-5, pos[0].y-0),
                new Point(pos[0].x-5, pos[0].y+1),
                new Point(pos[0].x-5, pos[0].y+2),
                new Point(pos[0].x-4, pos[0].y+3),
                new Point(pos[0].x-3, pos[0].y+4),
                new Point(pos[0].x-2, pos[0].y+5),
                new Point(pos[0].x-1, pos[0].y+5),
                new Point(pos[0].x+0, pos[0].y+5),
                new Point(pos[0].x+1, pos[0].y+5),
                new Point(pos[0].x+2, pos[0].y+5),
                new Point(pos[0].x+3, pos[0].y+4),
                new Point(pos[0].x+4, pos[0].y+3),
                new Point(pos[0].x+5, pos[0].y+2),
                new Point(pos[0].x+5, pos[0].y+1),

        };
    }
}
