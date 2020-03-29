import java.util.ArrayList;

public class Person extends Entity{
    public Point[] initPos;
    public Point[] pos;
    public Point finalTarget;
    public ArrayList<Point> targets;
    int targetIndex;
    Obstacle currentObs=null;
    boolean first=false;


    public Person(Point center, Point target, Room room, int signature) {
        super(room,signature);
        this.finalTarget=target;
        this.targets= new ArrayList<Point>();
        targetIndex=0;
        this.pos = around(center);
        this.initPos = copies(pos);

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

        Point nextCenter=pos[0];
        double minDistance=pos[0].distance(currentTarget());
        for (int i = 1; i < 20 ; i++) {
            if(room.map[pos[i].x][pos[i].y]==0) {
                double distance = pos[i].distance(currentTarget());
                if (distance < minDistance) {
                    minDistance = distance;
                    nextCenter = pos[i];
                }
            }
        }

        pos=around(nextCenter);

        addPrint();

    }

    public Point[] nextPos(boolean lookAround){

        if(pos[0].equals(targets.get(targetIndex))) {
            if (lookAround) {
                targetIndex++;
            } else {
                targetIndex = 0;
                if (first) {
                    currentObs.addPrint();
                    first = false;
                }
            }
            System.out.println("je passe à la cible suivante");
        }

        Point nextCenter=pos[0];
        double minDistance=pos[0].distance(targets.get(targetIndex));
        for (int i = 1; i < 20 ; i++) {
            //System.out.println(room.map[pos[i].x][pos[i].y]);
            if(room.map[pos[i].x][pos[i].y]==0||!lookAround){
                double distance = pos[i].distance(targets.get(targetIndex));
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
        int sign;
        int lastSign=0;

        removePrint();

        while (!pos[0].equals(finalTarget)) {

            if(pos[0].equals(currentTarget())){
                targetIndex++;
                if (first) {
                    currentObs.addPrint();
                    first = false;
                }
            }

            Point nextCenter=pos[0];
            double minDistance=pos[0].distance(currentTarget());
            for (int i = 1; i < 20 ; i++) {
                double distance = pos[i].distance(currentTarget());
                if (distance < minDistance) {
                    minDistance = distance;
                    nextCenter = pos[i];
                }
            }

            pos = around(nextCenter);





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
                    first=true;
                    System.out.println("il est #" + (sign / 2 - 1) + " dans la liste");
                    possibleTargets=copies(currentObs.allPoints());
                }


                currentObs.removePrint();

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

                targets.add(new Point(possibleTargets[pointToReach].x,possibleTargets[pointToReach].y)); //my target is point A of the obstacle on my way (c'est pas ce qu'on veut mais c'est un début)
                possibleTargets[pointToReach]=new Point(10000,10000); //we "delete" the point from the possible targets
                //targetIndex=targets.size()-1;
                System.out.println("j'ai ajouter un nouvelle cible à ma liste'");
                System.out.println("My new target: "+targets.get(targetIndex));

                lastSign=sign;
            }
        }

        if(currentObs!=null) {
            currentObs.addPrint();
        }
        targetIndex=0;
        pos=copies(initPos);
        addPrint();
    }

    public Point currentTarget(){
        if (targetIndex>=targets.size()){
            return finalTarget;
        }else{
            return targets.get(targetIndex);
        }
    }
}
