import java.util.LinkedList;

public class Room {
    final int SIZE;
    boolean [][] map;
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
        map[p.pos[0]][p.pos[1]]=true;
    }

    public void addObstacle(Obstacle o){
        obstacles.add(o);
        //map...=true;
    }

    public void nextStep(){
        for (Person p : persons) {
            map[p.pos[0]][p.pos[1]]=false;
            p.move();
            map[p.pos[0]][p.pos[1]]=true;
        }
    }


}
