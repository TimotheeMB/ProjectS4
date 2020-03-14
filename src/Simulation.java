import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements ActionListener {
    Room room;
    Timer timer;
    GUI GUI;

    public Simulation(Room room, int interval) {
        this.room = room;
        this.timer = new Timer(interval,this);
    }

    public void start(){
        room.computePathways();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==GUI.start){
            start();//We start the simulation
            GUI.t.start(); // On lance le chrono
        }
        room.nextStep();
    }
}
