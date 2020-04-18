import java.io.Serializable;

public abstract class Entity implements Serializable {
    public Simulation simulation;// In which simulation it is
    public int signature;// A value that the Entity will put in the map to mark its position
    public Point[] position;

    public Entity(Simulation simulation, int signature) {
        this.simulation = simulation;
        this.signature = signature;
    }

    //Mark the position in the map of the simulation
    public void addPrint(){
        for (Point point:position) {
            simulation.setSign(point,signature);
        }
    }

    //Remove it
    public void removePrint(){
        for (Point point:position) {
            simulation.setSign(point,0);
        }
    }

}
