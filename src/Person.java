public class Person extends Entity{
    public Point[] pos;
    public Point target;
    public int[][] map;

    public Person(int[][] map,Point pos, Point target,int signature) {
        super(signature);
        this.map=map;
        this.target = target;
        this.pos = new Point[]{
                                pos,
                                new Point(pos.x + 1, pos.y),
                                new Point(pos.x + 1, pos.y - 1),
                                new Point(pos.x, pos.y - 1),
                                new Point(pos.x - 1, pos.y - 1),
                                new Point(pos.x - 1, pos.y),
                                new Point(pos.x - 1, pos.y + 1),
                                new Point(pos.x, pos.y + 1),
                                new Point(pos.x + 1, pos.y + 1),
                                new Point(pos.x + 2, pos.y + 1),
                                new Point(pos.x + 2, pos.y),
                                new Point(pos.x + 2, pos.y-1),
                                new Point(pos.x + 1, pos.y-2),
                                new Point(pos.x, pos.y-2),
                                new Point(pos.x-1, pos.y-2),
                                new Point(pos.x-2, pos.y-1),
                                new Point(pos.x-2, pos.y),
                                new Point(pos.x-2, pos.y+1),
                                new Point(pos.x-1, pos.y+2),
                                new Point(pos.x, pos.y+2),
                                new Point(pos.x+1, pos.y+2)
        };
    }

    public void move(){
        for (Point point:pos) {
            map[point.x][point.y]=0;
        }
        Point nextPos=nextPos();
        this.pos = new Point[]{
                nextPos,
                new Point(nextPos.x + 1, nextPos.y),
                new Point(nextPos.x + 1, nextPos.y - 1),
                new Point(nextPos.x, nextPos.y - 1),
                new Point(nextPos.x - 1, nextPos.y - 1),
                new Point(nextPos.x - 1, nextPos.y),
                new Point(nextPos.x - 1, nextPos.y + 1),
                new Point(nextPos.x, nextPos.y + 1),
                new Point(nextPos.x + 1, nextPos.y + 1),
                new Point(nextPos.x + 2, nextPos.y + 1),
                new Point(nextPos.x + 2, nextPos.y),
                new Point(nextPos.x + 2, nextPos.y-1),
                new Point(nextPos.x + 1, nextPos.y-2),
                new Point(nextPos.x, nextPos.y-2),
                new Point(nextPos.x-1, nextPos.y-2),
                new Point(nextPos.x-2, nextPos.y-1),
                new Point(nextPos.x-2, nextPos.y),
                new Point(nextPos.x-2, nextPos.y+1),
                new Point(nextPos.x-1, nextPos.y+2),
                new Point(nextPos.x, nextPos.y+2),
                new Point(nextPos.x+1, nextPos.y+2)
        };
        for (Point point:pos) {
            map[point.x][point.y]=signature;
        }
    }

    public Point nextPos(){
        Point nextPos=pos[0];
        double minDistance=pos[0].distance(target);
        for (int i = 1; i < 20 ; i++) {
            if(map[pos[i].x][pos[i].y]==0){
                double distance = pos[i].distance(target);
                if (distance < minDistance) {
                    minDistance = distance;
                    nextPos = pos[i];
                    System.out.println(i);
                }
            }
        }
        return nextPos;

    }

}
