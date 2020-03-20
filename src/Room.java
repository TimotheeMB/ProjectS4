import java.util.LinkedList;

public class Room {
    final int SIZE;
    public boolean [][] map;
    LinkedList<Person> persons;
    LinkedList<Obstacle> obstacles;

    public Room(int size) {
        persons=new LinkedList<Person>();
        SIZE = size;
        map = new boolean[SIZE][SIZE];
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
                map[i][j]=false;
            }
        }
    }

    public void addPerson(Person p){
        persons.add(p);
        map[p.pos.x][p.pos.y]=true;
    }

    public void addObstacle(Obstacle o){
        obstacles.add(o);
        //map...=true;
    }

    public void nextStep(){
        for (Person p : persons) {
            map[p.pos.x][p.pos.y]=false;
            p.move();
            map[p.pos.x][p.pos.y]=true;
        }
    }


    public void computePathways() {
    }
}
