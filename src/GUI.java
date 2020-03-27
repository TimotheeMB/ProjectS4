import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

    // attributes
    public int DisplayInterval;
    public Timer timer;
    public JLabel timing;
    public Simulation simulation;
    public DisplayPanel disp;
    JButton person;
    JButton obstacle;
    JButton start;
    JButton exit;
    JButton stop;
    // constructor
    public GUI(Simulation simulation, int DisplayInterval) {

        disp = new DisplayPanel(simulation);
        this.simulation = simulation;
        this.DisplayInterval = DisplayInterval;
        timer = new Timer(DisplayInterval, this); // Timer creation
        timer.start();

        //Window initialization
        this.setTitle(" Welcome to our Crowd Simulator");
        this.setSize(1070, 800);
        this.setLocation(200, 20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel for choices
        JPanel choicesPan = new JPanel();
        choicesPan.setBounds(740, 10, 300, 720);
        choicesPan.setLayout(null);
        choicesPan.setBackground(Color.cyan);

        //Panel to display simulation
        DisplayPanel displayPan = new DisplayPanel(simulation);
        displayPan.setBounds(10, 10, 720, 720);
        displayPan.setLayout(null);
        displayPan.setBackground(Color.white);

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

        //Display timer
        Font f = new Font("Calibri", Font.BOLD, 20);
        timing = new JLabel(" ");
        timing.setBounds(10, 560, 280, 70);
        timing.setLayout(null);
        timing.setFont(f);
        choicesPan.add(timing);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int timeInMin = (int) simulation.time / 60000;      //each 10ms we update the time display
        int timeInSec = (int) simulation.time / 1000 - timeInMin * 60;

        if (e.getSource() == start) {
            simulation.start();//If we press start, we start the simulation
            person.setVisible(false);
            obstacle.setVisible(false);
            exit.setVisible(false);
            stop.setVisible(true);
            start.setVisible(false);
        }
        if (e.getSource() == stop) {
            timer.stop();
            timing.setText("The simulation lasted "+ timeInMin + " : " + timeInSec);
            start.setVisible(true);
        }
        if (e.getSource() == timer) {
            timing.setText("Time = " + timeInMin + " : " + timeInSec);
            repaint();
            if (!disp.waitAddPerson) {
                person.setBackground(Color.white);
            }
            if (!disp.waitAddObstacle) {
                obstacle.setBackground(Color.white);
            }
            if (!disp.waitAddExit) {
                exit.setBackground(Color.white);
            }
        }
        if (e.getSource() == person) {
            disp.waitAddPerson = true;
            simulation.room.addPerson(new Point(100,100));
            person.setBackground(Color.red);
        }
        if (e.getSource() == obstacle) {
            disp.waitAddObstacle = true;
            obstacle.setBackground(Color.red);
        }
        if (e.getSource() == exit) {
            disp.waitAddExit = true;
            exit.setBackground(Color.red);
        }
    }
}