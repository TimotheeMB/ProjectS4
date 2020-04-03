import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayPanel extends JPanel implements MouseListener {

    public Simulation simulation;
    public boolean waitAddPerson;
    public boolean waitAddExit;
    public boolean waitAddObstacle;
    public Point beginningObstacle;


    public DisplayPanel(Simulation simulation) {
        this.simulation=simulation;
        waitAddExit = false;
        waitAddObstacle = false;
        waitAddPerson = false;
        addMouseListener(this);
    }

    //The real display of the simulation
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,simulation.room.SIZE,simulation.room.SIZE);
        for (int x = 0; x < simulation.room.SIZE; x++) {
            for (int y = 0; y < simulation.room.SIZE; y++) {
                int signature = simulation.room.map[x][y];
                if(signature!=0){
                    if(signature==-1) {
                        Color beautygreen = new Color (80,210,120);
                        g.setColor(beautygreen);
                    }else if(signature%2==0){
                        Color beautyred = new Color (235,20,80);
                        g.setColor(beautyred);
                    }else {
                        Color beautyblue = new Color (70,10,150);
                        g.setColor(beautyblue);
                    }
                    g.fillRect(x,y,1,1);
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        if(waitAddPerson) {
            simulation.room.addPerson(new Point(e.getX(), e.getY()));
        }else if (waitAddObstacle) {
            this.beginningObstacle = new Point(e.getX(),e.getY());
        }else if (waitAddExit) {
            simulation.room.addExit(new Point(e.getX(), e.getY()));
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (waitAddObstacle) {
            simulation.room.addObstacle(this.beginningObstacle, new Point(e.getX(), e.getY()));
        }
    }

    public void mouseExited (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseClicked (MouseEvent e){}

}
