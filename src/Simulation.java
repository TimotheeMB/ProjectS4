import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Simulation implements Serializable, ActionListener {

    /*Entities*/
    ArrayList<Person> persons;
    ArrayList<Obstacle> obstacles;
    ArrayList<Exit> exits;

    /*Map*/
    final int WIDTH;
    final int HEIGHT;
    public int [][][] map;
    final int INFINITY=Integer.MAX_VALUE;

    /*Time*/
    Timer timer;
    long time;
    final int NORMAL_STEP_DURATION=72;//Corresponds to a speed of 10km/h



    public Simulation(int width, int height) {
        /*Entities*/
        exits= new ArrayList<>();
        persons= new ArrayList<>();
        obstacles= new ArrayList<>();

        /*Map*/
        WIDTH = width;
        HEIGHT = height;
        map = new int[WIDTH][HEIGHT][2];
        for (int i = 0; i <WIDTH; i++) {
            for (int j = 0; j <HEIGHT ; j++) {
                map[i][j][0]=0;
            }
        }

        /*Time*/
        this.timer = new Timer(NORMAL_STEP_DURATION,this);
        this.time=0;
    }


    /*Main methods*/

    public void nextStep(){
        for (Person p: persons) {
            p.nextStep();
        }
    }

    //famous algorithm used here to compute the distance to the closest exit at every point on the map
    public void dijkstra(){
        for (int i = 0; i <WIDTH; i++) {
            for (int j = 0; j <HEIGHT ; j++) {
                map[i][j][1]=INFINITY;
            }
        }
        for (Exit exit:exits) {
            PriorityQueue<ValuedPoint> priority = new PriorityQueue<>(new ValuedPointComparator());
            priority.add( new ValuedPoint(exit.position[0], 0) );
            setDist(exit.position[0],0);
            while( !priority.isEmpty() ){
                Point source = priority.poll();
                for (int i = 0; i <source.around(false).length ; i++) {
                    Point p = source.around(false)[i];
                    if(inBounds(p)&&signAt(p)!=2) {
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
                }
            }
        }

    }


    /*Add Entities*/
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

    /*Setters and Getters*/
    public int signAt(Point p){
        return map[p.x][p.y][0];
    }
    public void setSign(Point p,int sign){
        if(inBounds(p)) {
            map[p.x][p.y][0] = sign;
        }
    }
    public int distAt(Point p){
        return map[p.x][p.y][1];
    }
    public void setDist(Point p,int dist){
        map[p.x][p.y][1]=dist;
    }
    public void setPanic(boolean panic){
        for (Person p:persons) {
            p.panic=panic;
        }
    }


    /*Tests*/
    public boolean inBounds(Point p){
        return (p.x>=0 && p.x<WIDTH && p.y>=0 && p.y<HEIGHT);
    }
    public boolean emptyAround(Point p) {
        for (Point d: p.around(true)) {
            if(!inBounds(d)||signAt(d)!=0) {
                return false;
            }
        }
        return true;
    }


    /*Time*/
    public void start(){
        timer.start();
    }
    public void pause(){
        timer.stop();
    }
    public void restart(){
        for (Person Brian:persons) {
            Brian.removePrint();
            Brian.position=Brian.initPosition;
        }
        time=0;
    }
    public void speedPlus(double step){
        timer.setDelay((int)(timer.getDelay()*(1/step)));
    }
    public void actionPerformed(ActionEvent e) {
        time += NORMAL_STEP_DURATION; // On incrémente le temps
        nextStep();
    }

}
