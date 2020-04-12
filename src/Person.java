public class Person extends Entity{

    public Point[] initPosition;

    boolean panic;

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

        if(panic){
            Point randomPosition = position[(int)(Math.random()*20)];
            if(room.emptyAround(randomPosition)){
                position = randomPosition.around(true);
            }
        }else {
            int minDist=room.distAt(position[0]);
            int index=0;
            for (int i = 0; i <position.length ; i++) {
                if (room.emptyAround(position[i])) {
                    if (room.distAt(position[i]) < minDist) {
                        minDist = room.distAt(position[i]);
                        index = i;
                    }
                }
            }
            position= position[index].around(true);
            if(room.distAt(position[0])==Integer.MAX_VALUE){
                panic = true;
            }
        }
        addPrint();//I appear in my new position
    }
}
