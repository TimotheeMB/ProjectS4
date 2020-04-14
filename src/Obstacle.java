public class Obstacle extends Entity {

    // attributes
    Point[] vertices;

    //constructor
    public Obstacle(Point one, Point two, Simulation simulation) {
        super(simulation, 2);
        if ((one.x < two.x) && (one.y < two.y)) {
            vertices = new Point[]{new Point(one.x,one.y), new Point(two.x, one.y),new Point(two.x,two.y), new Point(one.x, two.y)};
        } else if ((one.x > two.x) && (one.y > two.y)) {
            vertices = new Point[]{new Point(two.x,two.y), new Point(one.x, two.y),new Point(one.x,one.y), new Point(two.x, one.y)};
        } else if ((one.x < two.x)&& (one.y > two.y)) {
            vertices = new Point[]{new Point(one.x,two.y), new Point(two.x,two.y), new Point(two.x, one.y), new Point(two.x,two.y)};
        }else{
            vertices = new Point[]{new Point(two.x, one.y), new Point(one.x,one.y), new Point(one.x, two.y), new Point(two.x,two.y)};
        }



        this.position = new Point[(length())*(height())];
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
        return (vertices[2].x - vertices[0].x + 1);
    }

    public int height() {
        return (vertices[2].y - vertices[0].y + 1);
    }
}
