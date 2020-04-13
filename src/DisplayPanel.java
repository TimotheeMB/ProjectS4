import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayPanel extends JPanel{

    public Simulation simulation;
    public boolean drawEqui;
    public boolean drawColor;
    Color beautyRed = new Color (250,100,70);
    Color beautyBlue = new Color (70,50,140);
    Color beautyGreen = new Color(80, 210, 120);

    public DisplayPanel(Simulation simulation) {
        this.simulation=simulation;
        setBackground(Color.black);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        if(drawColor){
            for (int x = 0; x <simulation.room.WIDTH ; x+=5) {
                for (int y = 0; y <simulation.room.HEIGHT ; y+=5) {
                    int sign =simulation.room.distAt(new Point(x,y));
                    try {
                        g.setColor(new Color((int)(sign*0.02), (int)(255-sign*0.02), 255));
                    }catch (Exception e){
                        g.setColor(new Color(255, 0, 255));
                    }
                    g.fillRect((int) (x * scaleX()), (int) (y * scaleY()), (int) (scaleX()*5+1), (int) (scaleY()*5+1));
                }
            }
        }
        if(drawEqui){
            for (int x = 0; x <simulation.room.WIDTH ; x++) {
                for (int y = 0; y <simulation.room.HEIGHT ; y++) {
                    int sign =simulation.room.distAt(new Point(x,y));
                    if(sign%200==0){
                        try {
                            g.setColor(Color.black);
                            g.fillRect((int) (x * scaleX()), (int) (y * scaleY()), (int) (scaleX() + 1), (int) (scaleY() + 1));
                        }catch (Exception e){}
                    }
                }
            }
        }
        g.setColor(beautyRed);
        for (Person kevin: simulation.room.persons
             ) {
            g.fillOval((int)(kevin.position[0].x*scaleX()-2.5*scaleX()),(int)(kevin.position[0].y*scaleY()-2.5*scaleY()),(int)(5*scaleX()),(int)(5*scaleY()));
        }
        g.setColor(beautyBlue);
        for (Obstacle bob:simulation.room.obstacles
             ) {
            g.fillRect((int)((bob.vertices[0].x)*scaleX()),(int)((bob.vertices[0].y)*scaleY()),(int)((bob.length())*scaleX()),(int)((bob.height())*scaleY()));
        }
        g.setColor(beautyGreen);
        for(Exit exit : simulation.room.exits) {
            try {
                g.fillOval((int) (exit.position[0].x * scaleX() - 2.5 * scaleX()), (int) (exit.position[0].y * scaleY() - 2.5 * scaleY()), (int) (5 * scaleX()), (int) (5 * scaleY()));
            } catch (Exception e) {
            }
        }

    }


    public double scaleX(){
        return (getWidth()/(double)simulation.room.WIDTH);
    }

    public double scaleY(){
        return (getHeight()/(double)simulation.room.HEIGHT);
    }


}
