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
    Color beautyred = new Color (250,100,70);
    Color beautyblue = new Color (70,50,140);
    Color beautygreen = new Color(80, 210, 120);

    public DisplayPanel(Simulation simulation) {
        this.simulation=simulation;
        waitAddExit = false;
        waitAddObstacle = false;
        waitAddPerson = false;
        addMouseListener(this);
    }

    /*The old display of the simulation
    public void paint(Graphics g) {
        for (int x = 0; x < simulation.room.SIZE; x++) {
            for (int y = 0; y < simulation.room.SIZE; y++) {
                int signature = simulation.room.map[x][y];
                if(signature!=0){
                    if(signature==-1) {
                        g.setColor(beautygreen);
                    }else if(signature%2==0){
                        g.setColor(beautyblue);
                    }else {
                        g.setColor(beautyred);
                    }
                    g.fillRect((int)(x*scaleX()),(int)(y*scaleY()),(int)scaleX()+1,(int)scaleY()+1);
                }
            }
        }
    }
     */


    @Override
    public void paint(Graphics g) {

        g.setColor(beautyred);
        for (Person kevin: simulation.room.persons
             ) {
            g.fillOval((int)(kevin.position[0].x*scaleX()),(int)(kevin.position[0].y*scaleY()),(int)(5*scaleX()),(int)(5*scaleY()));
        }
        g.setColor(beautyblue);
        for (Obstacle bob:simulation.room.obstacles
             ) {
            g.fillRect((int)((bob.vertices[0].x+5)*scaleX()),(int)((bob.vertices[0].y+5)*scaleY()),(int)((bob.length()-10)*scaleX()),(int)((bob.height()-10)*scaleY()));
        }
        g.setColor(beautygreen);
        try {
            g.fillOval((int) (simulation.room.exit.position[0].x * scaleX()), (int) (simulation.room.exit.position[0].y * scaleY()), (int) (5 * scaleX()), (int) (5 * scaleY()));
        }catch (Exception e){ }

    }

    public void mousePressed(MouseEvent e) {
        Point clicked=new Point((int)(e.getX()/scaleX()), (int)(e.getY()/scaleY()));
        if(waitAddPerson) {
            simulation.room.addPerson(clicked);
        }else if (waitAddObstacle) {
            this.beginningObstacle = clicked;
        }else if (waitAddExit) {
            simulation.room.addExit(clicked);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (waitAddObstacle) {
            simulation.room.addObstacle(this.beginningObstacle, new Point((int)(e.getX()/scaleX()), (int)(e.getY()/scaleY())));
        }
    }

    public void mouseExited (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseClicked (MouseEvent e){}


    public double scaleX(){
        return (getWidth()/(double)simulation.room.SIZE);
    }

    public double scaleY(){
        return (getHeight()/(double)simulation.room.SIZE);
    }


}
