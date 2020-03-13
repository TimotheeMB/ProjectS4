public class Obstacle<x1, y1, x2, y2> {

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

    //width of the rectangle on x
    public int length (){
        return (x2 - x1) ;
    }

    //width of the rectangle on x
    public int height (){
        return (y2 - y1) ;
    }

    //set unavailable the rectangle occupied by the obstacle
    public boolean [][] availableSpace (int x1, int y1, int x2, int y2) {
        boolean[][] available = new boolean [this.length()][this.height()] ;
        for (int i = x1 ; i<=x2 ; i++){
            for (int j = y1 ; j<=y2 ; j++){
                available[i][j] = false;
            }
        }
        return available;
    }

    //create an obstacle (a dark rectangular shape)
    /*public void actionPerformed (ActionEvent e){
        fillrectangle(x1, y1, this.length(), this.height());
    }*/
}











