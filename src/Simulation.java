import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements ActionListener {
    Room room;
    Timer timer;
    long time;
    int stepDuration;
    boolean isRunning;

    public Simulation(Room room, int stepDuration) {
        this.room = room;
        this.stepDuration = stepDuration;
        this.timer = new Timer(stepDuration,this);
        this.time=0;
    }

    public void initialize(){
        for (Exit exit:room.exits) {
            room.dijkstra(exit);
        }
    }

    public void start(){
        timer.start();
    }

    public void pause(){
        timer.stop();
    }

    public void restart(){
        for (Person Brian:room.persons) {
            Brian.removePrint();
            Brian.position=Brian.initPosition;
        }
        time=0;
    }

    public void speedTimes(double factor){
        boolean isRunning;
        if(timer.isRunning()){
            timer.stop();
            isRunning=true;
        }else{
            isRunning=false;
        }
        stepDuration= (int) (stepDuration*(1/factor));
        timer=new Timer(stepDuration,this);
        if (isRunning) {
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time += stepDuration; // On incrémente le temps
        room.nextStep();
    }
}
