import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Simulation implements Serializable, ActionListener {

    /*Entities*/
    public ArrayList<Person> persons;
    public ArrayList<Obstacle> obstacles;
    public ArrayList<Exit> exits;

    /*Map*/
    public int width;
    public int height;
    public int [][][] map;// the map of the "room" of the simulation
    public final int INFINITY=Integer.MAX_VALUE;

    /*Time*/
    public Timer timer;
    public long time;
    public final int NORMAL_STEP_DURATION=72;//Corresponds to a speed of 10km/h

    /*panic*/
    public boolean panic;//If true all the persons in the simulation we behave like crazy

    public Simulation(boolean askSize) {
        /*Entities*/
        exits= new ArrayList<>();
        persons= new ArrayList<>();
        obstacles= new ArrayList<>();

        /*Map*/
        width=500;//default size: 50m
        height=500;
        if(askSize){ //A pop-up window will appear to ask the size of the new simulation room
            boolean correctAnswer = false;
            while (!correctAnswer){
                String size = JOptionPane.showInputDialog(null, "Choose the size of your room in m^2 (in the form WidthxHeight ex: 20x10) : ", "Parametrization", JOptionPane.QUESTION_MESSAGE);//the pop-up itself
                if(size!=null&&!size.equals("")){//If real size given
                    String[] dimension = {"",""};
                    int k = 0;
                    //before the 'x' will be dimension[0] (width) and after dimention[1] (height)
                    for (int i = 0; i < size.length(); i++) {
                        if (size.charAt(i) == 'x' || size.charAt(i) == 'X' || size.charAt(i) =='*') {
                            k++;
                        } else {
                            dimension[k] += size.charAt(i);
                        }
                    }
                    correctAnswer = true;
                    try {
                        width = Integer.parseInt(dimension[0]) * 10;//convert meters in number of cases
                        if(dimension[1].equals("")){//if only one dimention given make a square room
                            height = width;
                        }else {
                            height = Integer.parseInt(dimension[1]) * 10;
                        }
                    }catch (Exception e){//If the answer is not valid
                        System.out.println("Not valid answer");
                        correctAnswer = false;
                    }
                }else{//If no answer make the room with default size
                    correctAnswer = true;
                }
            }
        }
        map = new int[width][height][2];
        for (int i = 0; i <width; i++) {
            for (int j = 0; j <height ; j++) {
                map[i][j][0]=0;
                map[i][j][1]=INFINITY;
            }
        }
        /*Time*/
        this.timer = new Timer(NORMAL_STEP_DURATION,this);
        this.time=0;
    }


    /*Main methods*/

    public void nextStep(){
        for (Person p : persons) {
            p.nextStep();
        }
    }

    /**famous algorithm used here to compute the distance to the closest exit at every point on the map,
     * so that every person in the simulation can find his/her way avoiding obstacles...
     * and still be free to avoid other persons because they will not have a precise path to follow
     * just a kind of guideline (the knowledge of the distance to the exit at every points)
     */
    public void dijkstra(){
        //Initialize the distance to the closest exit at infinity every where
        for (int i = 0; i <width; i++) {
            for (int j = 0; j <height ; j++) {
                map[i][j][1]=INFINITY;
            }
        }
        //Then for each exit...
        for (Exit exit:exits) {
            PriorityQueue<ValuedPoint> priority = new PriorityQueue<>(new ValuedPointComparator());//This will store the points to be examined by order of distance
            priority.add( new ValuedPoint(exit.position[0], 0) );// We start with the exit, with a distance 0
            setDist(exit.position[0],0);//We store the distance in the map
            while( !priority.isEmpty() ){//While there is still points to analyze
                Point source = priority.poll();//We pick the closest point (call it A)
                for (int i = 0; i <source.around(false).length ; i++) {//We look at every points around that point (call them B_i)
                    Point p = source.around(false)[i];
                    if(inBounds(p)&&signAt(p)!=2) {//If B_i is in the map and with no obstacles on it
                        //we will compute the length of the path passing by the point A
                        int newDist = distAt(source);//It is equal to the distance of point A to the exit + ...
                        if (i % 2 == 0) {
                            newDist += 10;// ... 1*10 if B_i as a "side" in common with A
                        } else {
                            newDist += 14;//~sqrt(2)*10 if B_i as a "vertex" in common with A
                        }
                        if (newDist < distAt(p)) {//If the path passing by A is closer than the previously computed one (Infinity if no previously computed path)
                            setDist(p, newDist);//We update the closest distance to the exit
                            priority.offer(new ValuedPoint(p, newDist));//And we put it in the list to examined, so that we can take it as a point A
                        }
                    }
                }
            }
        }
        //by doing that for each exit we will, obviously, replace the distance of a point only if the exit is closer to that point than the previous ones...
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
        this.panic=panic;
    }


    /*Tests*/
    public boolean inBounds(Point p){
        return (p.x>=0 && p.x<width && p.y>=0 && p.y<height);
    }
    public boolean emptyAround(Point p) {
        for (Point d: p.around(true)) {
            if(!inBounds(d)||signAt(d)!=0) {
                return false;
            }
        }
        return true;
    }
    public boolean isRunning(){
        return timer.isRunning();
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
    public void speedTimes(double factor){
        timer.setDelay((int)(timer.getDelay()*(1/factor)));
    }
    public void actionPerformed(ActionEvent e) {
        time += NORMAL_STEP_DURATION; // On incrémente le temps
        nextStep();
    }

}
