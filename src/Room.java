import java.util.ArrayList;
import java.util.PriorityQueue;


public class Room {
    final int SIZE;
    public int [][][] map;
    public ArrayList<Person> persons;
    ArrayList<Obstacle> obstacles;
    int signaturePerson;
    int signatureObstacle;
    //public Exit exit ;
    ArrayList<Exit> exits;
    final int INFINITY=1000000;

    boolean panic=false;


    public Room(int size) {
        exits=new ArrayList<Exit>();

        signaturePerson=1;
        signatureObstacle=2;
        persons=new ArrayList<Person>();
        obstacles=new ArrayList<Obstacle>();
        SIZE = size;
        map = new int[SIZE][SIZE][3];
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
                map[i][j][0]=0;
                map[i][j][1]=INFINITY;
                map[i][j][2]=0;
            }
        }
    }

    public void addPerson(Point center){
        persons.add(new Person(center,this,signaturePerson));
        signaturePerson+=2;
    }

    public void addObstacle(Point a, Point b){
        obstacles.add(new Obstacle(a,b,this,signatureObstacle));
        signatureObstacle+=2;
    }

    public void addExit(Point e){
        /*if(exit.position[0]!=null){
            exit.removePrint();
            for (int i = 0; i <SIZE; i++) {
                for (int j = 0; j <SIZE ; j++) {
                    map[i][j][1]=INFINITY;
                }
            }
        }
        */

        Exit ex= new Exit (e, this);
        this.exits.add(ex);
        dijkstra(ex);
    }

    public void nextStep(){
        for (Person p: persons) {
            p.nextStep();
        }
    }

    public void dijkstra(Exit exit){
        for (Obstacle obstacle: obstacles) {
            for (Point point:obstacle.position) {
                markVisited(point);
            }
        }
        PriorityQueue<WeightedVertex> priority = new PriorityQueue<WeightedVertex>( new WeightedVertexComparator() );
        priority.add( new WeightedVertex(exit.position[0], 0.0) );
        setDist(exit.position[0],0);
        while( !priority.isEmpty() ){
            Point source = priority.poll().source;
            for (int i = 0; i <source.around().length ; i++) {
                int new_cost =distAt(source);
                if(i%2==0){
                    new_cost+=10;
                }else {
                    new_cost+=14;
                }
                try {
                    if (new_cost < distAt(source.around()[i])&&!visitedAt(source.around()[i])) {
                        setDist(source.around()[i], new_cost);
                        priority.offer(new WeightedVertex(source.around()[i], new_cost));
                    }
                }catch (Exception e){}
            }
        }
    }

    /*Setters and Getters*/
    public int signAt(Point p){
        return map[p.x][p.y][0];
    }
    public void setSign(Point p,int sign){
        map[p.x][p.y][0]=sign;
    }
    public int distAt(Point p){
        return map[p.x][p.y][1];
    }
    public void setDist(Point p,int dist){
        map[p.x][p.y][1]=dist;
    }
    public boolean visitedAt(Point p){
        return (map[p.x][p.y][2]==1);
    }
    public void markVisited(Point p){
        map[p.x][p.y][2]=1;
    }
}
