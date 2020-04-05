public class Obstacle extends Entity {

    // attributes
    Point[] vertices;

    //constructor
    public Obstacle(Point one, Point two, Room room, int signature) {
        super(room, signature);
        if ((one.x < two.x) && (one.y < two.y)) {
            vertices = new Point[]{new Point(one.x-5,one.y-5), new Point(two.x+5, one.y-5),new Point(two.x+5,two.y+5), new Point(one.x-5, two.y+5)};
        } else if ((one.x > two.x) && (one.y > two.y)) {
            vertices = new Point[]{new Point(two.x-5,two.y-5), new Point(one.x+5, two.y-5),new Point(one.x+5,one.y+5), new Point(two.x-5, one.y+5)};
        } else if ((one.x < two.x)&& (one.y > two.y)) {
            vertices = new Point[]{new Point(one.x-5,two.y-5), new Point(two.x+5,two.y-5), new Point(two.x+5, one.y+5), new Point(two.x-5,two.y+5)};
        }else{
            vertices = new Point[]{new Point(two.x-5, one.y-5), new Point(one.x+5,one.y-5), new Point(one.x+5, two.y+5), new Point(two.x-5,two.y+5)};
        }



        this.position = new Point[(length()-10)*(height()-10)];
        int index = 0;
        for (int i = this.vertices[0].x+5; i <= this.vertices[2].x-5; i++) {
            for (int j = this.vertices[0].y+5; j <= this.vertices[2].y-5; j++) {
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
