import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayPanel extends JPanel implements MouseListener {

    public Simulation simulation;
    public boolean waitAddPerson;
    public boolean waitAddExit;
    public boolean waitAddObstacle1 ;


    public DisplayPanel(Simulation simulation) {
        this.simulation=simulation;
        waitAddExit = false;
        waitAddObstacle1 = false;
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

    @Override

    public void mousePressed(MouseEvent e) {
            /*System.out.println("vous avez appuyé") ;*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /*System.out.println("vous avez relaché") ;*/
    }

    public void mouseExited (MouseEvent e){
        /*System.out.println("la souris est sortie") ;*/
    }

    public void mouseEntered (MouseEvent e){
        /*System.out.println("la souris est rentrée") ;*/
    }

    public void mouseClicked (MouseEvent e){
        if (waitAddObstacle1 == true) {
            System.out.println("vous avez cliqué") ;
        }
    }

}
