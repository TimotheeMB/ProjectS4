public abstract class Entity {
    public Room room;
    public int signature;

    public Entity(Room room, int signature) {
        this.room = room;
        this.signature = signature;
    }

    public abstract void addPrint();

    public abstract void removePrint();
}
