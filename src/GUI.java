import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    public long time;
    public int interval;
    public Timer t;
    public JLabel timing;
    /*public GUI () {
        this.setTitle(" Welcome to our Crowd Simulator");
        this.setSize(1500,800);
        this.setLocation(200,20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel choicesPan = new JPanel();
        choicesPan.setBounds(1165,10,300, 720);
        choicesPan.setLayout(null);
        choicesPan.setBackground(Color.cyan);

        JPanel totalPan = new JPanel();
        totalPan.setBounds(0,0,1500,800);
        totalPan.setLayout(null);
        totalPan.add(choicesPan);
        this.add(totalPan);

        JComboBox choosePersonNumber = new JComboBox();
        choosePersonNumber.setBounds(10, 10, 280 , 70);
        choosePersonNumber.setLayout(null);
        choicesPan.add(choosePersonNumber);

        JButton person = new JButton("Add a person");
        person.setBounds(10, 10, 280, 70);
        person.setLayout(null);
        choicesPan.add(person);

        JButton obstacle = new JButton("Add an obstacle");
        obstacle.setBounds(10, 90, 280, 70);
        obstacle.setLayout(null);
        choicesPan.add(obstacle);

        this.setVisible(true);
    } */

    public GUI (int interval){

        this.setTitle(" Welcome to our Crowd Simulator");
        this.setSize(1500,800);
        this.setLocation(200,20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel choicesPan = new JPanel();
        choicesPan.setBounds(1165,10,300, 720);
        choicesPan.setLayout(null);
        choicesPan.setBackground(Color.cyan);

        JPanel totalPan = new JPanel();
        totalPan.setBounds(0,0,1500,800);
        totalPan.setLayout(null);
        totalPan.add(choicesPan);
        this.add(totalPan);

        /* JComboBox choosePersonNumber = new JComboBox();
        choosePersonNumber.setBounds(10, 10, 280 , 70);
        choosePersonNumber.setLayout(null);
        choicesPan.add(choosePersonNumber);*/

        JButton person = new JButton("Add a person");
        person.setBounds(10, 10, 280, 70);
        person.setLayout(null);
        choicesPan.add(person);

        JButton obstacle = new JButton("Add an obstacle");
        obstacle.setBounds(10, 90, 280, 70);
        obstacle.setLayout(null);
        choicesPan.add(obstacle);

        JButton start = new JButton("Start simulation!!!");
        start.setBounds(10, 640, 280, 70);
        start.setLayout(null);
        start.addActionListener(this);
        choicesPan.add(start);

        Font f = new Font("Caliban", Font.BOLD, 20);

        timing = new JLabel(" ");
        timing.setBounds(10, 560, 280, 70);
        timing.setLayout(null);
        timing.setFont(f);
        choicesPan.add(timing);

        this.setVisible(true);

        this.interval=interval;
        t = new Timer(interval, this); // Création du timer
        time = 0; // On initialise le temps à 0

    }
    // Méthode exécutée à chaque réveil du Timer

    public void actionPerformed(ActionEvent e) {
        t.start(); // On lance le chrono
        time+=interval; // On incrémente le temps
        System.out.println("Start since "+time+" ms");
        int timeInMin = (int) time/60000;
        int timeInSec = (int) time/1000 - timeInMin*60;
        timing.setText("Time = "+timeInMin+" : " + timeInSec);
    }
}
