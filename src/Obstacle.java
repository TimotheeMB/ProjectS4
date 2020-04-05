public class Obstacle extends Entity {

    // attributes
    Point[] vertices;

    //constructor
    public Obstacle(Point one, Point two, Room room, int signature) {
        super(room, signature);
        if ((one.x < two.x) && (one.y < two.y)) {
            vertices = new Point[]{one, new Point(two.x, one.y), two, new Point(one.x, two.y)};
        } else if ((one.x > two.x) && (one.y > two.y)) {
            vertices = new Point[]{two, new Point(one.x, two.y), one, new Point(two.x, one.y)};
        } else if ((one.x < two.x)&& (one.y > two.y)) {
            vertices = new Point[]{new Point(one.x, two.y), two, new Point(two.x, one.y), one};
        }else {
            vertices = new Point[]{new Point(two.x, one.y), one, new Point(one.x, two.y), two};
        }



        this.position = new Point[(length() + 1) * (height() + 1)];
        int index = 0;
        for (int i = this.vertices[0].x; i <= this.vertices[2].x; i++) {
            for (int j = this.vertices[0].y; j <= this.vertices[2].y; j++) {
                position[index] = new Point(i, j);
                index++;
            }
        }

        addPrint();
    }

    public int length() {
        return (vertices[2].x - vertices[0].x);
    }

    public int height() {
        return (vertices[2].y - vertices[0].y);
    }
}
