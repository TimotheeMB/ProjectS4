import java.util.ArrayList;


public class Room {
    final int SIZE;
    public int [][][] map;
    public ArrayList<Person> persons;
    ArrayList<Obstacle> obstacles;
    int signaturePerson;
    int signatureObstacle;
    public Exit exit ;
    final int INFINITY=1000000;

    boolean panic=false;


    public Room(int size) {
        exit=new Exit();//default exit
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
        dijkstra();
    }

    public void nextStep(){
        for (Person p: persons) {
            p.nextStep();
        }
    }

    public void computePathways() {
        if(!panic){
            for (Person p: persons) {
                p.computeMyPathway();
            }
        }
    }

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

    public void dijkstra(){
        int nbUnvisited=SIZE*SIZE;

        for (Obstacle obstacle: obstacles) {
            for (Point point:obstacle.position) {
                markVisited(point);
                nbUnvisited--;
            }
        }

        setDist(exit.position[0],0);

        while (nbUnvisited>0){
            System.out.println(nbUnvisited);
            int minDist=INFINITY;
            Point act=new Point(0,0);
            for (int x = 0; x <SIZE ; x++) {
                for (int y = 0; y <SIZE ; y++) {
                    Point p = new Point(x,y);
                    if (distAt(p)<minDist){
                        act=p;
                    }
                }
            }

            markVisited(act);
            nbUnvisited--;

            for (Point p:act.around()) {
                try {
                    if (!visitedAt(p)) {
                        int alt = distAt(act) + 1;
                        if (alt < distAt(p)) {
                            setDist(p, alt);
                        }
                    }
                }catch (Exception e){}
            }
        }
    }
}
