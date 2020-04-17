import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.HashMap;

public class ChoicesPanel extends JPanel implements ActionListener, ItemListener {
//Attributes

    Color beautyGreenBlue = new Color (120,250,180);

    //Stock the text of the instructions corresponding to the button we click on.
    HashMap<JButton, String> text;

    //The window in which the panel will be displayed.
    public Window window;

    //Buttons
    public JButton person;
    public JButton obstacle;
    public JButton start;
    public JButton exit;
    public JButton pause;
    public JButton save;
    public JButton restart;
    public JButton fast;
    public JButton slow;

    //Check Boxes
    public JCheckBox panic;
    public JCheckBox equidistant;
    public JCheckBox distanceToExit;

    //Combo Box to choose the simulation
    public JComboBox<String> simulationChoice;

    public TextComponent instructions;// Display of the explanations
    public JLabel timing;// Display of the time of the simulation

    public double vx; //To display the speed

    public ChoicesPanel(Window window) {

        setBackground(beautyGreenBlue);
        vx = 1;
        this.window = window;

        //Create and initiate the layout manager for the Choices Panel
        GridBagLayout choicesLayout = new GridBagLayout();
        setLayout(choicesLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridwidth = 4;

        //Buttons to create the condition of the simulation
        person = new JButton("    Add persons   ",new ImageIcon("Icons/user.png"));
        person.setLayout(null);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(person, gbc);

        obstacle = new JButton("   Add obstacles",new ImageIcon("Icons/wall.png"));
        obstacle.setLayout(null);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(obstacle,gbc);

        exit = new JButton("       Add exits     ",new ImageIcon("Icons/exit.png"));
        exit.setLayout(null);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(exit,gbc);

        simulationChoice = new JComboBox<>();
        simulationChoice.addItem("+ New simulation");
        File simulationsDirectory=new File("Simulations"); //where the saved simulations are
        File[] simulations=simulationsDirectory.listFiles();//Put all the simulations in a array of files
        for(File simulation: simulations){
            simulationChoice.addItem(simulation.getName().replace(".ser",""));
        }
        simulationChoice.setSelectedIndex(0);
        simulationChoice.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(simulationChoice, gbc);

        gbc.insets = new Insets(0, 5, 0, 5);
        panic = new JCheckBox("Panic mode");
        panic.setSelected(false);
        panic.setBackground(beautyGreenBlue);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 0.01;
        add(panic,gbc);

        equidistant = new JCheckBox("Display Equipotentials");
        equidistant.setSelected(false);
        equidistant.setBackground(beautyGreenBlue);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(equidistant,gbc);

        distanceToExit = new JCheckBox("Display distance to the exit");
        distanceToExit.setSelected(false);
        distanceToExit.setBackground(beautyGreenBlue);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(distanceToExit,gbc);

        //Display information
        gbc.insets = new Insets(5, 5, 5, 5);
        Font e = new Font( "Cambrian", Font.BOLD, 12);
        instructions = new TextArea (10, 10);
        instructions.setFont(e);
        instructions.setEditable(false);
        instructions.setBackground(beautyGreenBlue);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1;
        gbc.weighty = 0.15;
        add(instructions, gbc);

        //Display simulation time
        Font f = new Font("Calibri", Font.BOLD, 20);
        timing = new JLabel(" ");
        timing.setLayout(null);
        timing.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        add(timing, gbc);

        //Button to save a simulation
        save = new JButton("Save the current simulation",new ImageIcon("Icons/save24.png"));
        save.setLayout(null);
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(save,gbc);

        //Start, pause, speed up, slow down, restart buttons for the simulation
        restart = new JButton(new ImageIcon("Icons/refresh.png"));
        restart.setLayout(null);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        add(restart,gbc);

        start = new JButton(new ImageIcon("Icons/play.png"));
        start.setLayout(null);
        gbc.gridx = 1;
        gbc.gridy = 10;
        add(start,gbc);

        slow = new JButton(new ImageIcon("Icons/next.png"));
        slow.setLayout(null);
        gbc.gridx = 2;
        gbc.gridy = 10;
        add(slow,gbc);

        fast = new JButton(new ImageIcon("Icons/forward.png"));
        fast.setLayout(null);
        gbc.gridx = 3;
        gbc.gridy = 10;
        add(fast,gbc);

        pause = new JButton(new ImageIcon("Icons/pause.png"));
        pause.setLayout(null);
        pause.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 10;
        add(pause,gbc);

        text=new HashMap<>();
        text.put(this.person,"To add a person, you just have\nto click somewhere in the simulation,\na point representing that person\nwill appear.");
        text.put(this.obstacle,"You can create rectangular shaped\nobstacles.To do so, you will give\n2 vertices, press your mouse,\nand release it where you want");
        text.put(this.exit,"Just click somewhere on the simulation\nto add the exit.");

        addListener();
    }

    private void restart() {
        //booleans for selected button
        text.forEach((button,bool)->{
            window.wait.put(button,false);
            button.setEnabled(true);
            button.setBackground(new JButton().getBackground());
        });

        distanceToExit.setSelected(false);
        panic.setSelected(false);
        equidistant.setSelected(false);

        fast.setEnabled(true);
        slow.setEnabled(true);

        vx=1;

        start.setVisible(true);
        pause.setVisible(false);
    }

    //Add a listener to the needed components
    public void addListener() {
        person.addActionListener(this);
        obstacle.addActionListener(this);
        exit.addActionListener(this);
        simulationChoice.addItemListener(this);
        simulationChoice.addActionListener(this);
        panic.addItemListener(this);
        equidistant.addItemListener(this);
        distanceToExit.addItemListener(this);
        save.addActionListener(this);
        restart.addActionListener(this);
        start.addActionListener(this);
        slow.addActionListener(this);
        fast.addActionListener(this);
        pause.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {

        if(vx*0.5<0.1){
            slow.setEnabled(false);
        }else if (vx*1.5>20){
            fast.setEnabled(false);
        }else{
            slow.setEnabled(true);
            fast.setEnabled(true);
        }

        //If we press start, pause, restart, slow, fast...
        if (e.getSource() == start) {
            instructions.setText("Computing paths...");
            window.simulation.dijkstra();
            instructions.setText("The simulation is running");
            window.simulation.start();//...we start the simulation
            start.setVisible(false);
            pause.setVisible(true);
            person.setEnabled(false);
            obstacle.setEnabled(false);
            exit.setEnabled(false);
        }

        else if (e.getSource() == pause) {
            window.simulation.pause();
            start.setVisible(true);
            pause.setVisible(false);
            person.setEnabled(true);
            obstacle.setEnabled(true);
            exit.setEnabled(true);
        }

        else if (e.getSource() == slow) {
            window.simulation.speedTimes(0.5);
            vx *= 0.5;
            instructions.setText("x"+vx);
        }

        else if (e.getSource() == fast) {
            window.simulation.speedTimes(1.5);
            vx *= 1.5;
            instructions.setText("x"+vx);
        }

        else if (e.getSource() == restart) {
            window.simulation.restart();
        }

        //If we choose a simulation
        else if (e.getSource() == simulationChoice){
            if(simulationChoice.getSelectedItem() == "+ New simulation"){
                window.chargeSimulation(new Simulation(true));
                instructions.setText("new simulation charged");
            }else{
                instructions.setText("The simulation is loading");
                window.chargeSimulation("Simulations/"+ simulationChoice.getSelectedItem()+".ser");
                instructions.setText(simulationChoice.getSelectedItem()+" charged");
            }
            this.restart();
        }

        //If we press save
        else if (e.getSource() == save){
            window.saveSimulation();
        }

        //If we press person, obstacle or exit
        else{
            text.forEach((button,bool)->{
                if(button==e.getSource()){
                    button.setBackground(beautyGreenBlue);
                    window.wait.put(button,true);
                    instructions.setText(text.get(button));
                }else {
                    button.setBackground(new JButton().getBackground());
                    window.wait.put(button,false);
                }
            });
        }
    }

    /* Check Boxes */
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == distanceToExit) {
            window.drawDistanceToExit = (e.getStateChange() == ItemEvent.SELECTED);
            if(!window.simulation.isRunning()) {
                window.simulation.dijkstra();
            }
        }
        else if (e.getSource() == equidistant) {
            window.drawEquidistant = (e.getStateChange() == ItemEvent.SELECTED);
            if(!window.simulation.isRunning()) {
                window.simulation.dijkstra();
            }
        }
        else if (e.getSource() == panic){
            window.simulation.setPanic(e.getStateChange() == ItemEvent.SELECTED);
        }
    }
}
