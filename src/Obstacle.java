public class Obstacle extends Entity {

    // attributes
    public Point pointA  ;
    public Point pointB ;

    //constructor
    public Obstacle (Point A, Point B,int[][] map,int signature) {
        super(signature);
        this.pointA = A ;
        this.pointB = B ;
        this.isAvailable(map);
    }

    public int length (){
        return (pointB.x-pointA.x) ;
    }

    public int height (){
        return (pointB.y-pointA.y) ;
    }

    public void isAvailable (int [][] map){
        for (int i = this.pointA.x ; i<=this.pointB.x ; i++){
            for (int j = this.pointA.y ; j<=this.pointB.y ; j++){
                map [i][j] = signature;
            }
        }
    }

}
