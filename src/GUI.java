import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

    /* === ATTRIBUTES === */

    //The simulation to manage
    public Simulation simulation;

    //Time management
    public int DisplayInterval;//The display will refresh every ...ms
    public Timer timer;//thanks to that timer

    //Display
    public JLabel timing;// Display of the time of the simulation
    public DisplayPanel displayPan;//Display of the simulation

    //Buttons
    public JButton person;
    public JButton obstacle;
    public JButton start;
    public JButton exit;
    public JButton stop;

    /* === CONSTRUCTOR === */
    public GUI(Simulation simulation, int DisplayInterval) {

        //The simulation to manage
        this.simulation = simulation;

        //Time management
        this.DisplayInterval = DisplayInterval;
        timer = new Timer(DisplayInterval, this); // Timer creation
        timer.start();

        //Window initialization
        this.setTitle(" Welcome to our Crowd Simulator");
        this.setSize(1070, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Panel for choices
        JPanel choicesPan = new JPanel();
        choicesPan.setBounds(740, 10, 300, 720);
        choicesPan.setLayout(null);
        choicesPan.setBackground(Color.cyan);

        //Panel to display simulation
        displayPan = new DisplayPanel(simulation);
        displayPan.setBounds(10, 10, 720, 720);
        displayPan.setLayout(null);
        displayPan.setBackground(Color.white);

        //Global panel
        JPanel totalPan = new JPanel();
        totalPan.setBounds(0, 0, 1050, 800);
        totalPan.setLayout(null);
        totalPan.add(choicesPan);
        totalPan.add(displayPan);
        this.add(totalPan);

        //all the buttons
        person = new JButton("Add a person");
        person.setBounds(10, 10, 280, 70);
        person.setBackground(Color.white);
        person.setLayout(null);
        person.addActionListener(this);
        choicesPan.add(person);

        obstacle = new JButton("Add an obstacle");
        obstacle.setBounds(10, 90, 280, 70);
        obstacle.setBackground(Color.white);
        obstacle.setLayout(null);
        obstacle.addActionListener(this);
        choicesPan.add(obstacle);

        exit = new JButton("Add exit");
        exit.setBounds(10, 170, 280, 70);
        exit.setBackground(Color.white);
        exit.setLayout(null);
        exit.addActionListener(this);
        choicesPan.add(exit);

        start = new JButton("Start simulation!!!");
        start.setBounds(10, 640, 280, 70);
        start.setLayout(null);
        start.addActionListener(this);
        choicesPan.add(start);

        stop = new JButton("Stop simulation");
        stop.setBounds(10, 640, 280, 70);
        stop.setLayout(null);
        stop.addActionListener(this);
        stop.setVisible(false);
        choicesPan.add(stop);

        //How to play


        //Display simulation time
        Font f = new Font("Calibri", Font.BOLD, 20);
        timing = new JLabel(" ");
        timing.setBounds(10, 560, 280, 70);
        timing.setLayout(null);
        timing.setFont(f);
        choicesPan.add(timing);


        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        //Conversion of the time in base 60
        int timeInMin = (int) simulation.time / 60000;
        int timeInSec = (int) simulation.time / 1000 - timeInMin * 60;

        //If we press start...
        if (e.getSource() == start) {
            simulation.start();//...we start the simulation
            person.setVisible(false);
            obstacle.setVisible(false);
            exit.setVisible(false);
            start.setVisible(false);
            stop.setVisible(true);

        }

        //If we press stop...
        if (e.getSource() == stop) {
            timer.stop();
            timing.setText("The simulation lasted "+ timeInMin + " : " + timeInSec);
            start.setVisible(true);
        }

        //If we press add person...
        if (e.getSource() == person) {
            displayPan.waitAddPerson = !displayPan.waitAddPerson;
            displayPan.waitAddObstacle=false;
            displayPan.waitAddExit=false;
            JOptionPane.showMessageDialog(this,"To add a person, you just have to click somewhere in the room, and a point representing that person will appear.");
        }

        //If we press add obstacle...
        if (e.getSource() == obstacle) {
            displayPan.waitAddObstacle = !displayPan.waitAddObstacle;
            displayPan.waitAddPerson=false;
            displayPan.waitAddExit=false;
            JOptionPane.showMessageDialog(this,"You can create rectangular shaped obstacles. To do so, you will give 2 vertices, press your mouse, and release it where you want");
        }

        //If we press add exit...
        if (e.getSource() == exit) {
            displayPan.waitAddExit = !displayPan.waitAddExit;
            displayPan.waitAddObstacle=false;
            displayPan.waitAddPerson=false;
            JOptionPane.showMessageDialog(this,"You cannot add an exit for the moment... Our developers do their best ;)");
        }

        //Each "DisplayInterval" ms...
        if (e.getSource() == timer) {

            //...we display the time
            timing.setText("Time = " + timeInMin + " : " + timeInSec);

            //...we change the color of buttons if needed
            if (displayPan.waitAddPerson) {
                person.setBackground(Color.cyan);
            }else {
                person.setBackground(Color.white);
            }
            if (displayPan.waitAddObstacle) {
                obstacle.setBackground(Color.cyan);
            }else {
                obstacle.setBackground(Color.white);
            }
            if (displayPan.waitAddExit) {
                exit.setBackground(Color.cyan);
            }else {
                exit.setBackground(Color.white);
            }

            //...and we refresh the display
            repaint();
        }
    }
}