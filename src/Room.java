import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Room implements Serializable {
    final int SIZE;
    public int [][][] map;
    public ArrayList<Person> persons;
    ArrayList<Obstacle> obstacles;
    ArrayList<Exit> exits;
    final int INFINITY=Integer.MAX_VALUE;

    boolean panic=false;


    public Room(int size) {
        exits=new ArrayList<Exit>();
        persons=new ArrayList<Person>();
        obstacles=new ArrayList<Obstacle>();
        SIZE = size;
        map = new int[SIZE][SIZE][2];
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
                map[i][j][0]=0;
                map[i][j][1]=INFINITY;
            }
        }
    }

    public void addPerson(Point center){
        persons.add(new Person(center,this));
    }

    public void addObstacle(Point a, Point b){
        obstacles.add(new Obstacle(a,b,this));
    }

    public void addExit(Point e){
        Exit exit = new Exit (e, this);
        this.exits.add(exit);
    }

    public void nextStep(){
        for (Person p: persons) {
            p.nextStep();
        }
    }

    public void dijkstra(Exit exit){
        PriorityQueue<ValuedPoint> priority = new PriorityQueue<ValuedPoint>( new ValuedPointComparator() );
        priority.add( new ValuedPoint(exit.position[0], 0) );
        setDist(exit.position[0],0);
        while( !priority.isEmpty() ){
            Point source = priority.poll();
            for (int i = 0; i <source.around(false).length ; i++) {
                try {
                    Point p = source.around(false)[i];
                    if(signAt(p)!=2) {
                        int new_cost = distAt(source);
                        if (i % 2 == 0) {
                            new_cost += 10;
                        } else {
                            new_cost += 15;
                        }
                        if (new_cost < distAt(p)) {
                            setDist(p, new_cost);
                            priority.offer(new ValuedPoint(p, new_cost));
                        }
                    }
                } catch (Exception e) {}
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
    public boolean emptyAround(Point p) {
        for (Point d: p.around(true)) {
            try{
                if(signAt(d)!=0){
                    return false;
                }
            }catch (Exception e){}

        }
        return true;
    }
}
