import java.util.ArrayList;

public class Person extends Entity{

    /*** Hello ! My name is Brian and I am an instance of this class.
     * I will explain you what I do, so that we can get to know each other a bit more ;)
     */

    /* ================================================ */
    /* These are my attributes :*/

    //I've got a position in the room

    public Point[] initPosition;//and I remember where I started from

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
        this.position = around(center);
        this.initPosition = position;
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
        if(position[0].distance(currentTarget())<10){//If I reached my target
            targetIndex++;//I switch to the new one
        }
        if(room.panic){
            Point randomPosition = position[(int)(Math.random()*20)];
            if(emptyAround(randomPosition)){
                position = around(randomPosition);
            }
        }else {
            position = around(findCloserPoint(position, currentTarget(), true, false));//I compute my new position
        }
        addPrint();//I appear in my new position
    }

    //I think about my pathway
    public void computeMyPathway() {
    System.out.println("=== COMPUTE MY PATHWAY ===");
        Obstacle currentObs=null;
        Point [] possibleTargets=new Point[4];
        int sign;
        int lastSign=0;
        int nbStep=0;

        removePrint();
        while (!position[0].equals(finalTarget)) {

            nbStep++;
            if (nbStep > 100000) {
                System.out.println("I give up it's too complicated\nI'm gonna die..... ");
                break;
            }

            if (position[0].equals(currentTarget())) {
                System.out.println("next target");
                targetIndex++;
            }

            position = around(findCloserPoint(position, currentTarget(), false, false));

            sign=0;
            for (Point p: position) {
                if (room.mapAt(p)!=0){
                    sign = room.mapAt(p);
                }
            }



            // if there is an obstacle
            if (sign != 0 && sign % 2 == 0) {
                System.out.println("I touch obstacle #" + (sign / 2 - 1));
                if (sign != lastSign) {//if it a different obstacle
                    if (!currentTarget().equals(finalTarget)) {
                        targets.remove(targetIndex);
                    }
                    System.out.println("It's a new one");
                    currentObs = room.obstacles.get(sign / 2 - 1);
                    System.arraycopy(currentObs.vertices, 0, possibleTargets, 0, possibleTargets.length);

                }


                Point pointToReach = findCloserPoint(possibleTargets, position[0], true, true);
                System.out.println("J'ajoute une target :" + pointToReach);
                targets.add(pointToReach);
                lastSign = sign;
                targetIndex = 0;
                position = initPosition;
            }
        }
        targetIndex=0;
        position=initPosition;
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

    public boolean emptyAround(Point p) {
        for (Point d: around(p)) {
            try{
                if(room.map[d.x][d.y]!=0){
                    return false;
                }
            }catch (Exception e){}

        }
        return true;
    }

    public void addPersonalSpace() {
        Point[] ps = new Point[]{
                new Point(position[0].x+5, position[0].y+0),
                new Point(position[0].x+5, position[0].y-1),
                new Point(position[0].x+5, position[0].y-2),
                new Point(position[0].x+4, position[0].y-3),
                new Point(position[0].x+3, position[0].y-4),
                new Point(position[0].x+2, position[0].y-5),
                new Point(position[0].x+1, position[0].y-5),
                new Point(position[0].x+0, position[0].y-5),
                new Point(position[0].x-1, position[0].y-5),
                new Point(position[0].x-2, position[0].y-5),
                new Point(position[0].x-3, position[0].y-4),
                new Point(position[0].x-4, position[0].y-3),
                new Point(position[0].x-5, position[0].y-2),
                new Point(position[0].x-5, position[0].y-1),
                new Point(position[0].x-5, position[0].y-0),
                new Point(position[0].x-5, position[0].y+1),
                new Point(position[0].x-5, position[0].y+2),
                new Point(position[0].x-4, position[0].y+3),
                new Point(position[0].x-3, position[0].y+4),
                new Point(position[0].x-2, position[0].y+5),
                new Point(position[0].x-1, position[0].y+5),
                new Point(position[0].x+0, position[0].y+5),
                new Point(position[0].x+1, position[0].y+5),
                new Point(position[0].x+2, position[0].y+5),
                new Point(position[0].x+3, position[0].y+4),
                new Point(position[0].x+4, position[0].y+3),
                new Point(position[0].x+5, position[0].y+2),
                new Point(position[0].x+5, position[0].y+1),
        };
        for (Point point:ps) {
            room.map[point.x][point.y] = signature;
        }
    }

}
