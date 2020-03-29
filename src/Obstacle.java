public class Obstacle extends Entity {

    // attributes
    public Point pointA  ;
    public Point pointC ;

    //constructor
    public Obstacle (Point A, Point C, Room room, int signature) {
        super(room,signature);
        this.pointA = A ;
        this.pointC = C ;
        this.addPrint();
    }

    public int length (){
        return (pointC.x-pointA.x) ;
    }

    public int height (){
        return (pointC.y-pointA.y) ;
    }

    public void addPrint (){
        for (int i = this.pointA.x +5 ; i<=this.pointC.x -5 ; i++){
            for (int j = this.pointA.y +5; j<=this.pointC.y -5 ; j++){
                room.map[i][j] = signature;
            }
        }
    }

    public void setPrint (){
        for (int i = this.pointA.x +5 ; i<=this.pointC.x -5 ; i++){
            for (int j = this.pointA.y +5; j<=this.pointC.y -5 ; j++){
                room.map[i][j] = signature;
            }
        }
    }

    public void removePrint(){
        for (int i = this.pointA.x +5 ; i<=this.pointC.x -5 ; i++){
            for (int j = this.pointA.y +5; j<=this.pointC.y -5 ; j++){
                room.map[i][j] = 0;
            }
        }
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB (){
        return new Point (this.pointC.x, this.pointA.y);
    }

    public Point getPointC() {
        return pointC;
    }

    public Point getPointD (){
        return new Point (this.pointA.x, this.pointC.y);
    }

    public Point[] allPoints(){
        return new Point[]{getPointA(),getPointB(),getPointC(),getPointD()};
    }
}
