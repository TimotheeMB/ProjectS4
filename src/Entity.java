public abstract class Entity {
    public Room room;
    public int signature;
    public Point[] position;

    public Entity(Room room, int signature) {
        this.room = room;
        this.signature = signature;
    }

    public Entity() {} //default

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
