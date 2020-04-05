import java.util.ArrayList;

public class Room {
    final int SIZE;
    public int [][] map;
    public ArrayList<Person> persons;
    ArrayList<Obstacle> obstacles;
    int signaturePerson;
    int signatureObstacle;
    public Exit exit ;


    boolean panic=false;


    public Room(int size) {
        exit=new Exit();//default exit
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
        persons.add(new Person(center,exit.position[0],this,signaturePerson));
        signaturePerson+=2;
    }

    public void addObstacle(Point a, Point b){
        obstacles.add(new Obstacle(a,b,this,signatureObstacle));
        signatureObstacle+=2;
    }

    public void addExit(Point e){
        if(exit.position[0]!=null){
            exit.removePrint();
        }
        this.exit = new Exit (e, this);
    }

    public void nextStep(){
        for (Person p: persons) {
            p.move();
        }
    }

    public void computePathways() {
        if(!panic){
            for (Person p: persons) {
                p.computeMyPathway();
            }
        }
        for (Obstacle o:obstacles) {
            o.addPrint();
        }
    }
}
