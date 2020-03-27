public class Obstacle extends Entity {

    // attributes
    public Point pointA  ;
    public Point pointC ;

    //constructor
    public Obstacle (Point A, Point C, int[][] map, int signature) {
        super(signature);
        this.pointA = A ;
        this.pointC = C ;
        this.addPrint(map);
    }

    public int length (){
        return (pointC.x-pointA.x) ;
    }

    public int height (){
        return (pointC.y-pointA.y) ;
    }

    public void addPrint (int [][] map){
        for (int i = this.pointA.x ; i<=this.pointC.x ; i++){
            for (int j = this.pointA.y ; j<=this.pointC.y ; j++){
                map [i][j] = signature;
            }
        }
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB (){
        Point pointB = new Point (this.pointC.x, this.pointA.y);
        return pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public Point getPointD (){
        Point pointD = new Point (this.pointA.x, this.pointC.y);
        return pointD;
    }
}
