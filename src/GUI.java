import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GUI extends JFrame{

    /* === ATTRIBUTES === */

    //The simulation to manage
    public Simulation simulation;

    //Listener
    public Listener listener;

    //Panel
    public DisplayPanel displayPan;// Display of the simulation
    public ChoicesPanel choicesPan;
    public JPanel total;



    /* === CONSTRUCTOR === */
    public GUI(Simulation simulation, int displayInterval){

        //The simulation to manage
        this.simulation = simulation;

        //Window initialization
        this.setTitle(" Welcome to our Crowd Simulator");
        this.setSize(1070, 740);
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
        gc.weightx = 0.75;
        displayPan = new DisplayPanel(simulation);
        total.add(displayPan, gc);

        //Panel for choices
        gc.gridx = 2;
        gc.gridy = 0;
        gc.weightx = 0.25;
        choicesPan = new ChoicesPanel();
        total.add(choicesPan, gc);

        listener = new Listener(displayInterval,displayPan,choicesPan,simulation);
        choicesPan.addListener(listener);
        displayPan.addMouseListener(listener);

        this.setVisible(true);
    }
}