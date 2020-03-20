import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

    // attributes
    public int DisplayInterval;
    public Timer timer;
    public JLabel timing;
    public Simulation simulation;
    JButton person;
    JButton obstacle;
    JButton start;
    JButton exit;

    // constructor
    public GUI(Simulation simulation, int DisplayInterval) {

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

        person = new JButton("Add a person");
        person.setBounds(10, 10, 280, 70);
        person.setLayout(null);
        person.addActionListener(this);
        choicesPan.add(person);

        obstacle = new JButton("Add an obstacle");
        obstacle.setBounds(10, 90, 280, 70);
        obstacle.setLayout(null);
        obstacle.addActionListener(this);
        choicesPan.add(obstacle);

        exit = new JButton("Add exit");
        exit.setBounds(10, 170, 280, 70);
        exit.setLayout(null);
        exit.addActionListener(this);
        choicesPan.add(exit);

        start = new JButton("Start simulation!!!");
        start.setBounds(10, 640, 280, 70);
        start.setLayout(null);
        start.addActionListener(this);
        choicesPan.add(start);


        Font f = new Font("Calibri", Font.BOLD, 20);
        timing = new JLabel(" ");
        timing.setBounds(10, 560, 280, 70);
        timing.setLayout(null);
        timing.setFont(f);
        choicesPan.add(timing);

        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            simulation.start();     //If we press start, we start the simulation
        }
        if (e.getSource() == timer) {
            int timeInMin = (int) simulation.time / 60000;      //each 10ms we update the time display
            int timeInSec = (int) simulation.time / 1000 - timeInMin * 60;
            timing.setText("Time = " + timeInMin + " : " + timeInSec);
            repaint();
        }
    }
}