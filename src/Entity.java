import java.io.Serializable;

public abstract class Entity implements Serializable {
    public Room room;
    public int signature;
    public Point[] position;

    public Entity(Room room, int signature) {
        this.room = room;
        this.signature = signature;
    }

    public void addPrint(){
        for (Point point:position) {
            room.setSign(point,signature);
        }
    };

    public void removePrint(){
        for (Point point:position) {
            room.setSign(point,0);
        }
    };

}
