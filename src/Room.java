import java.util.ArrayList;

public class Room {
    final int SIZE;
    public int [][] map;
    public ArrayList<Person> persons;
    ArrayList<Obstacle> obstacles;
    int signaturePerson;
    int signatureObstacle;
    public Exit exit ;


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
        System.out.println("j'ajoute une personne");
        persons.add(new Person(center,exit.exitLocation,this,signaturePerson));
        System.out.println("persons.size()="+persons.size());
        System.out.println("signature ="+signaturePerson);
        signaturePerson+=2;
    }

    public void addObstacle(Point a, Point b){
        System.out.println("j'ajoute un obstacle");
        obstacles.add(new Obstacle(a,b,this,signatureObstacle));
        System.out.println("obstacles.size()="+obstacles.size());
        System.out.println("signature ="+signatureObstacle);
        signatureObstacle+=2;
    }

    public void addExit(Point e){
        if(exit.exitLocation!=null){
            exit.removePrint();
        }
        System.out.println("j'ajoute une sortie");
        this.exit = new Exit (e, this);
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
