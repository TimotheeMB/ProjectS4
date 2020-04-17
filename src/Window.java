import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Window extends JFrame implements ActionListener{

    /* === ATTRIBUTES === */

    //The simulation to manage
    public Simulation simulation;

    //Time management
    public int displayInterval;//The display will refresh every ...ms
    public Timer timer;//thanks to that timer

    //Panel
    public DisplayPanel displayPan;// Display of the simulation
    public ChoicesPanel choicesPan;
    public JPanel total;

    //booleans used across the window
    public boolean drawEqui;
    public boolean drawColor;
    HashMap<JButton, Boolean> wait;


    /* === CONSTRUCTOR === */
    public Window(){

        //Default simulation
        setSimulation(new Simulation(false));

        //Window initialization
        this.setTitle("Crowd Simulator");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //The panel that contains everything
        total = new JPanel();
        this.add(total);
        //Create and initiate the layout manager for the total panel
        GridBagLayout totalLayout = new GridBagLayout();
        total.setLayout(totalLayout);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.ipady = gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.weighty= 1;

        //Panel to display simulation
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0.85;
        displayPan = new DisplayPanel(this);
        total.add(displayPan, gc);

        //Panel for choices
        gc.gridx = 2;
        gc.gridy = 0;
        gc.weightx = 0.15;
        choicesPan = new ChoicesPanel(this);
        total.add(choicesPan, gc);

        //booleans for selected button
        wait=new HashMap<>();
        wait.put(this.choicesPan.person,false);
        wait.put(this.choicesPan.obstacle,false);
        wait.put(this.choicesPan.exit,false);

        //Timer
        displayInterval = 50;
        timer = new Timer(displayInterval, this); // Timer creation
        timer.start();

        this.setVisible(true);

        setSimulation(new Simulation(true));
    }

    public void setSimulation(String fileName){
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            simulation = (Simulation) ois.readObject();
            ois.close();
        } catch (Exception eu) {
            eu.printStackTrace();
        }
    }
    public void setSimulation(Simulation simulation){
        this.simulation=simulation;
    }

    public void actionPerformed(ActionEvent e) {
        //Conversion of the time in base 60
        int timeInMin = (int) simulation.time / 60000;
        int timeInSec = (int) simulation.time / 1000 - timeInMin * 60;


        //Each "DisplayInterval" ms...
        if (e.getSource() == timer) {

            //...we display the time
            choicesPan.timing.setText("Time = " + timeInMin + " : " + timeInSec);

            //...and we refresh the display
            displayPan.repaint();
        }
    }

    public static void main(String[] args) {
        new Window();
    }
}