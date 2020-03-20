import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class DisplayPanel extends JPanel {

    public Simulation simulation;
    public boolean waitAddObstacle;
    public boolean waitAddPerson;
    public boolean waitAddExit;

    public DisplayPanel(Simulation simulation) {
        this.simulation=simulation;
        waitAddExit = false;
        waitAddObstacle = false;
        waitAddPerson = false;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,simulation.room.SIZE,simulation.room.SIZE);
        g.setColor(Color.black);
        for (int x = 0; x < simulation.room.SIZE; x++) {
            for (int y = 0; y < simulation.room.SIZE; y++) {
                if(simulation.room.map[x][y]!=0){
                    g.fillRect(x,y,5,5);
                }
            }
        }
    }
}
