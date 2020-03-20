import java.util.LinkedList;

public class Room {
    final int SIZE;
    public int [][] map;
    LinkedList<Entity> entities;
    int signature;

    public Room(int size) {
        signature=1;
        entities=new LinkedList<Entity>();
        SIZE = size;
        map = new int[SIZE][SIZE];
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
                map[i][j]=0;
            }
        }
    }

    public void addPerson(Point pos){
        entities.add(new Person(map,pos,new Point(0,0),signature));
        signature++;
    }

    public void addObstacle(Point a, Point b){
        entities.add(new Obstacle(a,b,signature));
        signature++;
        //map...=true;
    }

    public void nextStep(){
        for (Entity e : entities) {
            if(e instanceof Person){
                Person p=(Person)e;
                p.move();
                System.out.println("I move");
            }
        }
    }


    public void computePathways() {
    }
}
