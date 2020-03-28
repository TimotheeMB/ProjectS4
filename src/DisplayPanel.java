import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayPanel extends JPanel implements MouseListener {

    public Simulation simulation;
    static public boolean waitAddPerson;
    static public boolean waitAddExit;
    static public boolean waitAddObstacle;
    public Point beginningObstacle;


    public DisplayPanel(Simulation simulation) {
        this.simulation=simulation;
        waitAddExit = false;
        waitAddObstacle = false;
        waitAddPerson = false;
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,simulation.room.SIZE,simulation.room.SIZE);
        g.setColor(Color.black);
        for (int x = 0; x < simulation.room.SIZE; x++) {
            for (int y = 0; y < simulation.room.SIZE; y++) {
                if(simulation.room.map[x][y]!=0){
                    g.fillRect(x,y,1,1);
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        if (waitAddObstacle == true) {
            this.beginningObstacle = new Point(e.getX(),e.getY());
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(waitAddPerson==true){
            simulation.room.addPerson(new Point(e.getX(),e.getY()));
        }else if (waitAddObstacle == true) {
            simulation.room.addObstacle(this.beginningObstacle, new Point(e.getX(), e.getY()));
        }
    }

    public void mouseExited (MouseEvent e){}

    public void mouseEntered (MouseEvent e){}

    public void mouseClicked (MouseEvent e){}

}
