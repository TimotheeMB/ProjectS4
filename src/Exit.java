public class Exit extends Entity {

    public Exit (Point position, Simulation simulation){
        super(simulation,0);
        this.position = new Point[]{position};
    }

}