public class Obstacle extends Entity {

    /* Attributes */
    Point[] vertices;

    /* Constructor */
    //Two points will be given by the user, to define the (rectangle) obstacle
    public Obstacle(Point one, Point two, Simulation simulation) {
        super(simulation, 2);


        if ((one.x <= two.x) && (one.y <= two.y)) { //upper left to lower right corner
            vertices = new Point[]{new Point(one.x,one.y), new Point(two.x, one.y),new Point(two.x,two.y), new Point(one.x, two.y)};
        } else if ((one.x >= two.x) && (one.y >= two.y)) {//lower right to upper left corner
            vertices = new Point[]{new Point(two.x,two.y), new Point(one.x, two.y),new Point(one.x,one.y), new Point(two.x, one.y)};
        } else if ((one.x <= two.x)&& (one.y >= two.y)) {//lower left to upper right corner
            vertices = new Point[]{new Point(one.x,two.y), new Point(two.x,two.y), new Point(two.x, one.y), new Point(two.x,two.y)};
        }else{//upper right to lower left corner
            vertices = new Point[]{new Point(two.x, one.y), new Point(one.x,one.y), new Point(one.x, two.y), new Point(two.x,two.y)};
        }


        //define the position
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
