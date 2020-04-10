public class Person extends Entity{

    public Point[] initPosition;

    /* ================================================ */
    /* CONSTRUCTOR */
    public Person(Point center, Room room) {

        /*I initialize my attributes*/
        super(room,1);
        this.position = center.around(true);
        this.initPosition = position;

        /*I put myself in the room*/
        addPrint();
    }


    /* ================================================ */
    /* METHODS */

    public void nextStep(){
        removePrint();//I disappear from my last position

        if(room.panic){
            Point randomPosition = position[(int)(Math.random()*20)];
            if(emptyAround(randomPosition)){
                position = randomPosition.around(true);
            }
        }else {
            int minDist=room.distAt(position[0]);
            int index=0;
            for (int i = 0; i <position.length ; i++) {
                if (emptyAround(position[i])) {
                    if (room.distAt(position[i]) < minDist) {
                        minDist = room.distAt(position[i]);
                        index = i;
                    }
                }
            }
            position= position[index].around(true);
        }
        addPrint();//I appear in my new position
    }

    public boolean emptyAround(Point p) {
        for (Point d: p.around(true)) {
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
