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
            room.map[point.x][point.y] = signature;
        }
    };

    public void removePrint(){
        for (Point point:position) {
            room.map[point.x][point.y] = 0;
        }
    };

    public Point[] around(Point p){
        return new Point[]{
                p,
                new Point(p.x + 1, p.y),
                new Point(p.x + 1, p.y - 1),
                new Point(p.x, p.y - 1),
                new Point(p.x - 1, p.y - 1),
                new Point(p.x - 1, p.y),
                new Point(p.x - 1, p.y + 1),
                new Point(p.x, p.y + 1),
                new Point(p.x + 1, p.y + 1),
                new Point(p.x + 2, p.y + 1),
                new Point(p.x + 2, p.y),
                new Point(p.x + 2, p.y-1),
                new Point(p.x + 1, p.y-2),
                new Point(p.x, p.y-2),
                new Point(p.x-1, p.y-2),
                new Point(p.x-2, p.y-1),
                new Point(p.x-2, p.y),
                new Point(p.x-2, p.y+1),
                new Point(p.x-1, p.y+2),
                new Point(p.x, p.y+2),
                new Point(p.x+1, p.y+2)
        };
    }

}
