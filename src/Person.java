public class Person {
    public Point pos;
    public Point target;
    public Point[] around;

    public Person(Point pos, Point target) {
        this.pos = pos;
        this.target = target;
        around = new Point[]{new Point(pos.x + 1, pos.y),
                            new Point(pos.x + 1, pos.y - 1),
                            new Point(pos.x, pos.y - 1),
                            new Point(pos.x - 1, pos.y - 1),
                            new Point(pos.x - 1, pos.y),
                            new Point(pos.x - 1, pos.y + 1),
                            new Point(pos.x, pos.y + 1),
                            new Point(pos.x + 1, pos.y + 1)};
    }

    public void move(){
        this.pos=around[chooseDirection()];
        around = new Point[]{new Point(pos.x + 1, pos.y),
                            new Point(pos.x + 1, pos.y - 1),
                            new Point(pos.x, pos.y - 1),
                            new Point(pos.x - 1, pos.y - 1),
                            new Point(pos.x - 1, pos.y),
                            new Point(pos.x - 1, pos.y + 1),
                            new Point(pos.x, pos.y + 1),
                            new Point(pos.x + 1, pos.y + 1)};
    }

    public int chooseDirection(){
        int direction=0;
        double minDistance=around[0].distance(target);
        for (int i = 1; i < 8 ; i++) {
            double distance=around[i].distance(target);
            if(distance<minDistance){
                minDistance=distance;
                direction=i;
            }
        }
        return direction;

    }

}
