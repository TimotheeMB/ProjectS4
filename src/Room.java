import java.util.ArrayList;
import java.util.LinkedList;

public class Room {
    final int SIZE;
    public int [][] map;
    ArrayList<Person> persons;
    ArrayList<Obstacle> obstacles;
    int signaturePerson;
    int signatureObstacle;

    public Room(int size) {
        signaturePerson=1;
        signatureObstacle=2;
        persons=new ArrayList<Person>();
        obstacles=new ArrayList<Obstacle>();
        SIZE = size;
        map = new int[SIZE][SIZE];
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
                map[i][j]=0;
            }
        }
    }

    public void addPerson(Point center){
        persons.add(new Person(center,new Point(600,111),this,signaturePerson));
        signaturePerson+=2;
    }

    public void addObstacle(Point a, Point b){
        obstacles.add(new Obstacle(a,b,map,signatureObstacle));
        signatureObstacle+=2;
    }

    public void nextStep(){
        for (Person p: persons) {
            p.move();
        }
    }


    public void computePathways() {
        for (Person p: persons) {
            p.computeMyPathway();
        }
    }
}
