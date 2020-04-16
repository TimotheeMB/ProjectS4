import java.io.Serializable;

public abstract class Entity implements Serializable {
    public Simulation simulation;
    public int signature;
    public Point[] position;

    public Entity(Simulation simulation, int signature) {
        this.simulation = simulation;
        this.signature = signature;
    }

    public void addPrint(){
        for (Point point:position) {
            simulation.setSign(point,signature);
        }
    }

    public void removePrint(){
        for (Point point:position) {
            simulation.setSign(point,0);
        }
    }

}
