public class Obstacle {

    // attributes
    public int x1 ;
    public int y1 ;
    public int x2 ;
    public int y2 ;

    //constructor
    public Obstacle (int x1, int y1, int x2, int y2) {
        this.x1 = x1 ;
        this.y1 = y1 ;
        this.x2 = x2 ;
        this.y2 = y2 ;
    }

    public int length (){
        return (x2-x1) ;
    }

    public int height (){
        return (y2-y1) ;
    }

    public boolean [][] isAvailable (){
        boolean [][] available = new boolean [this.length()][this.height()] ;
        for (int i = this.x1 ; i<=this.x2 ; i++){
            for (int j = this.y1 ; j<=this.y2 ; j++){
                available [i][j] = false ;
            }
        }
        return available ;
    }

}
