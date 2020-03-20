public class Obstacle {

    // attributes
    public Point pointA  ;
    public Point pointB ;

    //constructor
    public Obstacle (Point A, Point B) {
        this.pointA = A ;
        this.pointB = B ;
    }

    public int length (){
        return (pointB.x-pointA.x) ;
    }

    public int height (){
        return (pointB.y-pointA.y) ;
    }

    public void isAvailable (boolean [][] map){
        for (int i = this.pointA.x ; i<=this.pointB.x ; i++){
            for (int j = this.pointA.y ; j<=this.pointB.y ; j++){
                map [i][j] = false ;
            }
        }
    }

}
