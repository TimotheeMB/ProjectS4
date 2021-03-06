import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayPanel extends JPanel implements MouseListener{

    public Window window;

    Color beautyRed = new Color (200,20,70);
    Color beautyBlue = new Color (70,50,140);
    Color beautyGreen = new Color(80, 210, 120);

    Point beginningObstacle;

    public DisplayPanel(Window window) {
        this.window=window;
        setBackground(Color.black);
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        if(window.drawDistanceToExit){
            for (int x = 0; x <window.simulation.width ; x+=5) {
                for (int y = 0; y <window.simulation.height ; y+=5) {
                    int distance =window.simulation.distAt(new Point(x,y));
                    try {
                        g.setColor(new Color((int)(distance *0.02), (int)(255- distance *0.02), 100));
                    }catch (Exception e){
                        g.setColor(new Color(255, 0, 100));
                    }
                    g.fillRect((int) (x * scaleX()), (int) (y * scaleY()), (int) (scaleX()*5+1), (int) (scaleY()*5+1));
                }
            }
        }
        if(window.drawEquidistant){
            for (int x = 0; x <window.simulation.width ; x++) {
                for (int y = 0; y <window.simulation.height ; y++) {
                    int sign =window.simulation.distAt(new Point(x,y));
                    if(sign%200<10&&sign%200>0){
                        g.setColor(Color.black);
                        g.fillRect((int) (x * scaleX()), (int) (y * scaleY()), (int) (scaleX() + 1), (int) (scaleY() + 1));
                    }
                }
            }
        }
        g.setColor(window.drawDistanceToExit? Color.white : beautyRed);
        for (Person brian : window.simulation.persons) {
            if(window.simulation.distAt(brian.position[0])>40) {
                g.fillOval((int) (brian.position[0].x * scaleX() - 2.5 * scaleX()), (int) (brian.position[0].y * scaleY() - 2.5 * scaleY()), (int) (5 * scaleX()), (int) (5 * scaleY()));
            }
        }
        g.setColor(beautyBlue);
        for (Obstacle obstacle:window.simulation.obstacles
             ) {
            g.fillRect((int)((obstacle.vertices[0].x)*scaleX()),(int)((obstacle.vertices[0].y)*scaleY()),(int)((obstacle.length())*scaleX()),(int)((obstacle.height())*scaleY()));
        }
        g.setColor(beautyGreen);
        for(Exit exit : window.simulation.exits) {
            g.fillOval((int) (exit.position[0].x * scaleX() - 2.5 * scaleX()), (int) (exit.position[0].y * scaleY() - 2.5 * scaleY()), (int) (5 * scaleX()), (int) (5 * scaleY()));
        }

    }

    public void mousePressed(MouseEvent e) {
        Point clicked=new Point((int)(e.getX()/scaleX()), (int)(e.getY()/scaleY()));
        if(window.wait.get(window.choicesPan.person)) {
            window.simulation.addPerson(clicked);
        }else if (window.wait.get(window.choicesPan.obstacle)) {
            this.beginningObstacle = clicked;
        }else if (window.wait.get(window.choicesPan.exit)) {
            window.simulation.addExit(clicked);
            if(!window.simulation.isRunning()&&(window.drawDistanceToExit ||window.drawEquidistant)){
                window.simulation.dijkstra();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (window.wait.get(window.choicesPan.obstacle)) {
            window.simulation.addObstacle(this.beginningObstacle, new Point((int)(e.getX()/scaleX()), (int)(e.getY()/scaleY())));
            if(!window.simulation.isRunning()&&(window.drawDistanceToExit ||window.drawEquidistant)){
                window.simulation.dijkstra();
            }
        }
    }

    public void mouseExited (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseClicked (MouseEvent e){}


    /*Scale*/
    public double scaleX(){
        return (getWidth()/(double)window.simulation.width);
    }
    public double scaleY(){
        return (getHeight()/(double)window.simulation.height);
    }


}
