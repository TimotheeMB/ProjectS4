import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Listener implements ActionListener, ItemListener, MouseListener {

    //Time management
    public int DisplayInterval;//The display will refresh every ...ms
    public Timer timer;//thanks to that timer

    public DisplayPanel display;
    public ChoicesPanel choices;
    public Simulation simulation;

    HashMap<JButton, Boolean> wait;
    HashMap<JButton, String> text;

    public Point beginningObstacle;

    public Listener(int displayInterval, DisplayPanel display, ChoicesPanel choices, Simulation simulation) {
        DisplayInterval = displayInterval;
        this.display = display;
        this.choices = choices;
        this.simulation = simulation;

        wait=new HashMap<>();
        wait.put(this.choices.person,false);
        wait.put(this.choices.obstacle,false);
        wait.put(this.choices.exit,false);

        text=new HashMap<>();
        text.put(this.choices.person,"To add a person, you just have\nto click somewhere in the simulation,\na point representing that person\nwill appear.");
        text.put(this.choices.obstacle,"You can create rectangular shaped\nobstacles.To do so, you will give\n2 vertices, press your mouse,\nand release it where you want");
        text.put(this.choices.exit,"As for adding a person\njust click somewhere on the simulation\nto add the exit.");

        timer = new Timer(DisplayInterval, this); // Timer creation
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Conversion of the time in base 60
        int timeInMin = (int) simulation.time / 60000;
        int timeInSec = (int) simulation.time / 1000 - timeInMin * 60;
        double vx= (double) simulation.NORMAL_STEP_DURATION/simulation.stepDuration;

        //Each "DisplayInterval" ms...
        if (e.getSource() == timer) {

            //...we display the time
            choices.timing.setText("Time = " + timeInMin + " : " + timeInSec);

            //...and we refresh the display
            display.repaint();
        }
        //If we press start...
        else if (e.getSource() == choices.start) {
            choices.instructions.setText("Computing paths...");
            simulation.dijkstra();
            choices.instructions.setText("The simulation is running");
            simulation.start();//...we start the simulation
            choices.start.setVisible(false);
            choices.pause.setVisible(true);
        }

        //If we press pause...
        else if (e.getSource() == choices.pause) {
            simulation.pause();
            choices.start.setVisible(true);
            choices.pause.setVisible(false);

        }

        else if (e.getSource() == choices.slow) {
            simulation.speedTimes(0.5);
            choices.instructions.setText("The simulation is running at a speed : Vx"+vx);
        }

        else if (e.getSource() == choices.speed) {
            simulation.speedTimes(1.5);
            choices.instructions.setText("The simulation is running at a speed : Vx"+vx);
        }

        else if (e.getSource() == choices.restart) {
            simulation.restart();
        }

        else if (e.getSource() == choices.roomChoice){
            if (choices.roomChoice.getSelectedItem() == "Your simulation"){
                setSimulation("Rooms/UserDefined.ser");
            }else if(choices.roomChoice.getSelectedItem() == "A classroom") {
                setSimulation("Rooms/Classroom.ser");
            }else if(choices.roomChoice.getSelectedItem() == "+ New simulation"){
                setSimulation(new Simulation(500,500));
            }else if(choices.roomChoice.getSelectedItem() == "The beurk"){
                setSimulation("Rooms/Beurk.ser");
            }
        }

        else if (e.getSource() == choices.save){
            try {
                FileOutputStream fs = new FileOutputStream("Rooms/UserDefined.ser");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(simulation); // 3
                os.close();
            } catch (Exception et) {
                et.printStackTrace();
            }
            choices.instructions.setText("Simulation saved ;)");
        }
        else{
            wait.forEach((button,bool)->{
                if(button==e.getSource()){
                    button.setBackground(choices.beautyGreenBlue);
                    wait.put(button,true);
                    choices.instructions.setText(text.get(button));
                }else {
                    button.setBackground(new JButton().getBackground());
                    wait.put(button,false);
                }
            });
        }

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == choices.color) {
            display.drawColor = (e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == choices.equi) {
            display.drawEqui = (e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == choices.panic){
            simulation.setPanic(e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == choices.roomChoice){
            System.out.println(e.getItem()+"selected");
        }
    }

    public void mousePressed(MouseEvent e) {
        Point clicked=new Point((int)(e.getX()/display.scaleX()), (int)(e.getY()/display.scaleY()));
        if(wait.get(choices.person)) {
            simulation.addPerson(clicked);
        }else if (wait.get(choices.obstacle)) {
            this.beginningObstacle = clicked;
        }else if (wait.get(choices.exit)) {
            simulation.addExit(clicked);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (wait.get(choices.obstacle)) {
            simulation.addObstacle(this.beginningObstacle, new Point((int)(e.getX()/display.scaleX()), (int)(e.getY()/display.scaleY())));
        }
    }

    public void mouseExited (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseClicked (MouseEvent e){}

    public void setSimulation(String fileName){
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            simulation = (Simulation) ois.readObject(); // 4
            ois.close();
        } catch (Exception eu) {
            eu.printStackTrace();
        }
    }
    public void setSimulation(Simulation simulation){
        this.simulation=simulation;
    }

}
