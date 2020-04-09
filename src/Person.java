public class Person extends Entity{

    public Point[] initPosition;

    /* ================================================ */
    /* CONSTRUCTOR */
    public Person(Point center, Point target, Room room, int signature) {

        /*I initialize my attributes*/
        super(room,signature);
        this.position = around(center);
        this.initPosition = position;

        /*I put myself in the room*/
        addPrint();
    }


    /* ================================================ */
    /* METHODS */

    public void nextStep(){
        System.out.println("next step");
        removePrint();//I disappear from my last position

        if(room.panic){
            Point randomPosition = position[(int)(Math.random()*20)];
            if(emptyAround(randomPosition)){
                position = around(randomPosition);
            }
        }else {
            int minDist=room.distAt(position[0]);
            int index=0;
            for (int i = 0; i <position.length ; i++) {
                if (emptyAround(position[i])) {
                    System.out.println(room.distAt(position[i]));
                    if (room.distAt(position[i]) < minDist) {
                        minDist = room.distAt(position[i]);
                        index = i;
                    }
                }
            }
            position= around(position[index]);
        }
        System.out.println("new pos"+position[0]);
        addPrint();//I appear in my new position
    }

    public boolean emptyAround(Point p) {
        for (Point d: around(p)) {
            try{
                if(room.signAt(d)!=0){
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
            room.setSign(point,signature);
        }
    }

}
