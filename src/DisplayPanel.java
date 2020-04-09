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

    @Override
    public void paint(Graphics g) {

        g.setColor(beautyred);
        for (Person kevin: simulation.room.persons
             ) {
            g.fillOval((int)(kevin.position[0].x*scaleX()-2.5*scaleX()),(int)(kevin.position[0].y*scaleY()-2.5*scaleY()),(int)(5*scaleX()),(int)(5*scaleY()));
        }
        g.setColor(beautyblue);
        for (Obstacle bob:simulation.room.obstacles
             ) {
            g.fillRect((int)((bob.vertices[0].x)*scaleX()),(int)((bob.vertices[0].y)*scaleY()),(int)((bob.length())*scaleX()),(int)((bob.height())*scaleY()));
        }
        g.setColor(beautygreen);
        for(Exit exit : simulation.room.exits) {
            try {
                g.fillOval((int) (exit.position[0].x * scaleX() - 2.5 * scaleX()), (int) (exit.position[0].y * scaleY() - 2.5 * scaleY()), (int) (5 * scaleX()), (int) (5 * scaleY()));
            } catch (Exception e) {
            }
        }

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
