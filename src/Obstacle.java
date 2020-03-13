public class Obstacle {

    // attributes
    public int [] angle1 ;
    public int [] angle2 ;


    //constructor
    public Obstacle (int angle1[], int angle2[]) {
        this.angle1[] = angle1[] ;
        this.angle2[] = angle2[] ;
    }

    public int [][] volumeObstacle (int angle1 [], int angle2[]) {
        int [][] volume = new int [][] ;
        volume [0][0] = {angle1[0], angle2[0]} ;
        volume [1][1] = {angle1[1], angle2[1]} ;
        return volume ;
    }






}
