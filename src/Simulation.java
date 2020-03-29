import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements ActionListener {
    Room room;
    Timer timer;
    long time;
    int StepDuration;

    public Simulation(Room room, int StepDuration) {
        this.room = room;
        this.StepDuration = StepDuration;
        this.timer = new Timer(StepDuration,this);
        this.time=0;
    }

    public void start(){
        System.out.println("======== ComputePathway ========");
        room.computePathways();
        System.out.println("======== Move ========");
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time += StepDuration; // On incrémente le temps
        room.nextStep();
    }
}
