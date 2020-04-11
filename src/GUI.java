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

public class GUI extends JFrame implements ActionListener, ItemListener {

    /* === ATTRIBUTES === */

    //The simulation to manage
    public Simulation simulation;

    //Time management
    public int DisplayInterval;//The display will refresh every ...ms
    public Timer timer;//thanks to that timer

    //Display
    public JLabel timing;// Display of the time of the simulation
    public DisplayPanel displayPan;// Display of the simulation
    public TextComponent instructions;// Display of the explanations

    //Buttons
    public JButton person;
    public JButton obstacle;
    public JButton start;
    public JButton exit;
    public JButton pause;
    public JButton save;
    public JButton restart;
    public JButton speed;
    public JButton slow;

    public JCheckBox panic;
    public JCheckBox equi;
    public JCheckBox color;

    public JComboBox<String> roomChoice;

    //Panel
    public JPanel choicesPan;
    public JPanel total;

    /* === CONSTRUCTOR === */
    public GUI(Simulation simulation, int DisplayInterval){

        //The simulation to manage
        this.simulation = simulation;

        //Time management
        this.DisplayInterval = DisplayInterval;
        timer = new Timer(DisplayInterval, this); // Timer creation
        timer.start();

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
        displayPan.setBackground(Color.black);
        total.add(displayPan, gc);

        //Panel for choices
        gc.gridx = 2;
        gc.gridy = 0;
        gc.weightx = 0.25;
        choicesPan = new JPanel();
        Color beautygreenblue = new Color (120,250,180);
        choicesPan.setBackground(beautygreenblue);
        total.add(choicesPan, gc);
        //Create and initiate the layout manager for the Choices Panel
        GridBagLayout choicesLayout = new GridBagLayout();
        choicesPan.setLayout(choicesLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridwidth = 4;

        //Buttons to create the condition of the simulation
        person = new JButton("Add a person",new ImageIcon("Icons/user.png"));
        person.setBackground(Color.white);
        person.setLayout(null);
        person.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        choicesPan.add(person, gbc);

        obstacle = new JButton("Add an obstacle",new ImageIcon("Icons/smart-grid.png"));
        obstacle.setBackground(Color.white);
        obstacle.setLayout(null);
        obstacle.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        choicesPan.add(obstacle,gbc);

        exit = new JButton("Add exit",new ImageIcon("Icons/door.png"));
        exit.setBackground(Color.white);
        exit.setLayout(null);
        exit.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        choicesPan.add(exit,gbc);

        roomChoice = new JComboBox<>(new String[]{"+ New room", "Your saved room", "The beurk", "A classroom"});
        roomChoice.setSelectedIndex(0);
        roomChoice.addItemListener(this);
        roomChoice.addActionListener(this);
        roomChoice.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        choicesPan.add(roomChoice, gbc);

        gbc.insets = new Insets(0, 5, 0, 5);
        panic = new JCheckBox("Panic mode");
        panic.setSelected(false);
        panic.setBackground(beautygreenblue);
        panic.addItemListener(this);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 0.01;
        choicesPan.add(panic,gbc);

        equi = new JCheckBox("Display Equipotentials");
        equi.setSelected(false);
        equi.setBackground(beautygreenblue);
        equi.addItemListener(this);
        gbc.gridx = 0;
        gbc.gridy = 5;
        choicesPan.add(equi,gbc);

        color = new JCheckBox("Display distance to the exit");
        color.setSelected(false);
        color.setBackground(beautygreenblue);
        color.addItemListener(this);
        gbc.gridx = 0;
        gbc.gridy = 6;
        choicesPan.add(color,gbc);

        //How to play
        gbc.insets = new Insets(5, 5, 5, 5);
        Font e = new Font( "Cambria", Font.PLAIN, 18);
        instructions = new TextArea (10, 10);
        instructions.setFont(e);
        instructions.setEditable(false);
        instructions.setBackground(beautygreenblue);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1;
        gbc.weighty = 0.15;
        choicesPan.add(instructions, gbc);

        //Display simulation time
        Font f = new Font("Calibri", Font.BOLD, 20);
        timing = new JLabel(" ");
        timing.setLayout(null);
        timing.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        choicesPan.add(timing, gbc);

        save = new JButton("Save your room",new ImageIcon("Icons/save24.png"));
        save.setLayout(null);
        save.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 9;
        choicesPan.add(save,gbc);

        //Start, pause, speed up, slow down, restart buttons for the simulation

        restart = new JButton(new ImageIcon("Icons/refresh.png"));
        restart.setLayout(null);
        restart.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        choicesPan.add(restart,gbc);

        start = new JButton(new ImageIcon("Icons/play.png"));
        start.setLayout(null);
        start.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 10;
        choicesPan.add(start,gbc);

        slow = new JButton(new ImageIcon("Icons/next.png"));
        slow.setLayout(null);
        slow.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 10;
        choicesPan.add(slow,gbc);

        speed = new JButton(new ImageIcon("Icons/forward.png"));
        speed.setLayout(null);
        speed.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 10;
        choicesPan.add(speed,gbc);

        pause = new JButton(new ImageIcon("Icons/pause.png"));
        pause.setLayout(null);
        pause.addActionListener(this);
        pause.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 10;
        choicesPan.add(pause,gbc);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        //Conversion of the time in base 60
        int timeInMin = (int) simulation.time / 60000;
        int timeInSec = (int) simulation.time / 1000 - timeInMin * 60;
        double vx= (double) simulation.NORMAL_STEP_DURATION/simulation.stepDuration;

        //If we press start...
        if (e.getSource() == start) {
            instructions.setText("Initialization");
            simulation.initialize();
            instructions.setText("The simulation is running");
            simulation.start();//...we start the simulation
            start.setVisible(false);
            pause.setVisible(true);
        }

        //If we press pause...
        else if (e.getSource() == pause) {
            simulation.pause();
            start.setVisible(true);
            pause.setVisible(false);

        }

        else if (e.getSource() == slow) {
            simulation.speedTimes(0.5);
            instructions.setText("The simulation is running at a speed : Vx"+vx);
        }

        else if (e.getSource() == speed) {
            simulation.speedTimes(1.5);
            instructions.setText("The simulation is running at a speed : Vx"+vx);
        }

        else if (e.getSource() == restart) {
            simulation.restart();
        }

        else if (e.getSource() == roomChoice){
            if (roomChoice.getSelectedItem() == "Your saved room"){
                try {
                    FileInputStream fis = new FileInputStream("Rooms/UserDefined.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    simulation.room= (Room) ois.readObject(); // 4
                    ois.close();
                } catch (Exception eu) {
                    eu.printStackTrace();
                }
            }else if(roomChoice.getSelectedItem() == "A classroom") {
                try {
                    FileInputStream fis = new FileInputStream("Rooms/Classroom.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    simulation.room = (Room) ois.readObject(); // 4
                    ois.close();
                } catch (Exception eu) {
                    eu.printStackTrace();
                }
            }
        }

        //If we press add person...
        else if (e.getSource() == person) {
            displayPan.waitAddPerson = !displayPan.waitAddPerson;
            displayPan.waitAddObstacle = false;
            displayPan.waitAddExit = false;
            instructions.setText( "To add a person, you just have\nto click somewhere in the room,\na point representing that person\nwill appear.");
            /*JOptionPane.showMessageDialog(this, "To add a person, you just have to click somewhere in the room, and a point representing that person will appear.");*/
        }

        //If we press add obstacle...
        else if (e.getSource() == obstacle) {
            displayPan.waitAddObstacle = !displayPan.waitAddObstacle;
            displayPan.waitAddPerson = false;
            displayPan.waitAddExit = false;
            instructions.setText("You can create rectangular shaped\nobstacles.To do so, you will give\n2 vertices, press your mouse,\nand release it where you want");
            //JOptionPane.showMessageDialog(this, "You can create rectangular shaped obstacles. To do so, you will give 2 vertices, press your mouse, and release it where you want");
        }

        //If we press add exit...
        else if (e.getSource() == exit) {
            displayPan.waitAddExit = !displayPan.waitAddExit;
            displayPan.waitAddObstacle = false;
            displayPan.waitAddPerson = false;
            instructions.setText("As for adding a person\njust click somewhere on the room\nto add the exit.");
            //JOptionPane.showMessageDialog(this, "You cannot add an exit for the moment... Our develop do their best ;)");
        }

        else if (e.getSource() == save){
            try {
                FileOutputStream fs = new FileOutputStream("Rooms/UserDefined.ser");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(simulation.room); // 3
                os.close();
            } catch (Exception et) {
                et.printStackTrace();
            }
            instructions.setText("Room saved ;)");
        }

        //Each "DisplayInterval" ms...
        else if (e.getSource() == timer) {

            //...we display the time
            if(timing!=null) {
                timing.setText("Time = " + timeInMin + " : " + timeInSec);
            }

            //...we change the color of buttons if needed
            if (displayPan.waitAddPerson) {
                person.setBackground(Color.cyan);
            } else {
                person.setBackground(Color.white);
            }
            if (displayPan.waitAddObstacle) {
                obstacle.setBackground(Color.cyan);
            } else {
                obstacle.setBackground(Color.white);
            }
            if (displayPan.waitAddExit) {
                exit.setBackground(Color.cyan);
            } else {
                exit.setBackground(Color.white);
            }

            //...and we refresh the display
            repaint();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == color) {
            displayPan.drawColor = (e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == equi) {
            displayPan.drawEqui = (e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == panic){
            simulation.room.panic = (e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == roomChoice){
            System.out.println(e.getItem()+"selected");
        }
    }
}