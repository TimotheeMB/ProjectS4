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

    //Obstacles
    Obstacle currentObs=null;

    /* === CONSTRUCTOR === */
    public Person(Point center, Point target, Room room, int signature) {

        super(room,signature);

        //Position
        this.pos = around(center);
        this.initPos = copyOf(pos);

        //Targets
        this.finalTarget=target;
        this.targets= new ArrayList<>();
        targetIndex=0;


        addPrint();//so that we can see the person we created
    }

    public void move(){

        System.out.println("This is my targetIndex : "+targetIndex);
        System.out.println("This is my pos: "+pos[0]);

        removePrint();

        if(pos[0].equals(currentTarget())){
            targetIndex++;
            System.out.println("je passe à la cible suivante");
        }

        pos=around(pos[findCloserPoint(pos,currentTarget(),true)]);

        addPrint();

    }

    public void computeMyPathway() {

        Point [] possibleTargets=new Point[4];
        int sign;
        int lastSign=0;

        removePrint();

        while (!pos[0].equals(finalTarget)) {

            if(pos[0].equals(currentTarget())){
                targetIndex++;
                currentObs.addPrint();
            }

            pos=around(pos[findCloserPoint(pos,currentTarget(),false)]);


            System.out.println("My imaginary position: "+pos[0]);
            sign = room.map[pos[0].x][pos[0].y];
            System.out.println("J'imagine que je suis sur une case: "+sign);

            // if there is an obstacle
            if (sign != 0 && sign % 2 == 0) {

                //if it a different obstacle
                if(sign!=lastSign) {
                    if(currentObs!=null) {
                        currentObs.addPrint();
                    }
                    currentObs = room.obstacles.get(sign / 2 - 1);
                    System.out.println("il est #" + (sign / 2 - 1) + " dans la liste");
                    possibleTargets=copyOf(currentObs.allPoints());
                }


                currentObs.removePrint();

                int pointToReach=findCloserPoint(possibleTargets,pos[0],false);

                targets.add(copyOf(possibleTargets[pointToReach])); //my target is point A of the obstacle on my way (c'est pas ce qu'on veut mais c'est un début)
                possibleTargets[pointToReach]=new Point(10000,10000); //we "delete" the point from the possible targets
                System.out.println("j'ai ajouter un nouvelle cible à ma liste'");
                System.out.println("My new target: "+targets.get(targetIndex));

                lastSign=sign;
            }
        }

        if(currentObs!=null) {
            currentObs.addPrint();
        }
        targetIndex=0;
        pos=copyOf(initPos);
        addPrint();
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

    public Point[] copyOf(Point[] x){
        Point [] r=new Point[x.length];
        System.arraycopy(x, 0, r, 0, x.length);
        return r;
    }

    public Point copyOf(Point p){
        Point r=new Point(p.x,p.y);
        return r;
    }

    public int findCloserPoint(Point[] points, Point target, boolean emptyPoint){

        double smallerDistance=points[0].distance(target); //default value, no check of emptiness
        int closerPoint=0;

        for (int i = 1; i < points.length ; i++) {
            if(!emptyPoint||room.map[points[i].x][points[i].y]==0) {
                double distance = points[i].distance(target);
                if (distance < smallerDistance) {
                    smallerDistance = distance;
                    closerPoint=i;
                }
            }
        }

        return closerPoint;
    }

}
