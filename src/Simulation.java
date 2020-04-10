import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements ActionListener {
    Room room;
    Timer timer;
    long time;
    int stepDuration;

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

    public void stop(){
        timer.stop();
    }

    public void restart(){
        for (Person Brian:room.persons) {
            Brian.position=Brian.initPosition;
        }
    }

    public void speedUp(){
        stepDuration= (int) (stepDuration*0.5);
        timer=new Timer(stepDuration,this);
    }

    public void slowDown(){
        stepDuration= (int) (stepDuration*1.5);
        timer=new Timer(stepDuration,this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time += stepDuration; // On incrémente le temps
        room.nextStep();
    }
}
