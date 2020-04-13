import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Listener implements ActionListener, ItemListener, MouseListener {

    //Time management
    public int DisplayInterval;//The display will refresh every ...ms
    public Timer timer;//thanks to that timer

    public DisplayPanel display;
    public ChoicesPanel choices;
    public Simulation simulation;

    public boolean waitAddPerson;
    public boolean waitAddExit;
    public boolean waitAddObstacle;

    public Point beginningObstacle;

    public Listener(int displayInterval, DisplayPanel display, ChoicesPanel choices, Simulation simulation) {
        DisplayInterval = displayInterval;
        this.display = display;
        this.choices = choices;
        this.simulation = simulation;
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
            if(choices.timing!=null) {
                choices.timing.setText("Time = " + timeInMin + " : " + timeInSec);
            }

            //...we change the color of buttons if needed
            /*if (display.waitAddPerson) {
                person.setBackground(beautyGreenBlue);
            } else {
                person.setBackground(new JButton().getBackground());
            }
            if (display.waitAddObstacle) {
                obstacle.setBackground(beautyGreenBlue);
            } else {
                obstacle.setBackground(new JButton().getBackground());
            }
            if (display.waitAddExit) {
                exit.setBackground(beautyGreenBlue);
            } else {
                exit.setBackground(new JButton().getBackground());
            }*/

            //...and we refresh the display
            display.repaint();
        }
        //If we press start...
        else if (e.getSource() == choices.start) {
            choices.instructions.setText("Initialization");
            simulation.initialize();
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
            if (choices.roomChoice.getSelectedItem() == "Your room"){
                simulation.setRoom("Rooms/UserDefined.ser");
            }else if(choices.roomChoice.getSelectedItem() == "A classroom") {
                simulation.setRoom("Rooms/Classroom.ser");
            }else if(choices.roomChoice.getSelectedItem() == "+ New room"){
                simulation.setRoom(new Room(500,500));
            }else if(choices.roomChoice.getSelectedItem() == "The beurk"){
                simulation.setRoom("Rooms/Beurk.ser");
            }
        }

        //If we press add person...
        else if (e.getSource() == choices.person) {
            waitAddPerson = !waitAddPerson;
            waitAddObstacle = false;
            waitAddExit = false;
            choices.person.setBackground(choices.beautyGreenBlue);
            choices.instructions.setText( "To add a person, you just have\nto click somewhere in the room,\na point representing that person\nwill appear.");
            /*JOptionPane.showMessageDialog(this, "To add a person, you just have to click somewhere in the room, and a point representing that person will appear.");*/
        }

        //If we press add obstacle...
        else if (e.getSource() == choices.obstacle) {
            waitAddObstacle = !waitAddObstacle;
            waitAddPerson = false;
            waitAddExit = false;
            choices.instructions.setText("You can create rectangular shaped\nobstacles.To do so, you will give\n2 vertices, press your mouse,\nand release it where you want");
            //JOptionPane.showMessageDialog(this, "You can create rectangular shaped obstacles. To do so, you will give 2 vertices, press your mouse, and release it where you want");
        }

        //If we press add exit...
        else if (e.getSource() == choices.exit) {
            waitAddExit = !waitAddExit;
            waitAddObstacle = false;
            waitAddPerson = false;
            choices.instructions.setText("As for adding a person\njust click somewhere on the room\nto add the exit.");
            //JOptionPane.showMessageDialog(this, "You cannot add an exit for the moment... Our develop do their best ;)");
        }

        else if (e.getSource() == choices.save){
            try {
                FileOutputStream fs = new FileOutputStream("Rooms/UserDefined.ser");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(simulation.room); // 3
                os.close();
            } catch (Exception et) {
                et.printStackTrace();
            }
            choices.instructions.setText("Room saved ;)");
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
            simulation.room.setPanic(e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == choices.roomChoice){
            System.out.println(e.getItem()+"selected");
        }
    }

    public void mousePressed(MouseEvent e) {
        Point clicked=new Point((int)(e.getX()/display.scaleX()), (int)(e.getY()/display.scaleY()));
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
            simulation.room.addObstacle(this.beginningObstacle, new Point((int)(e.getX()/display.scaleX()), (int)(e.getY()/display.scaleY())));
        }
    }

    public void mouseExited (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseClicked (MouseEvent e){}

}
