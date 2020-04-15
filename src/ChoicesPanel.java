import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ChoicesPanel extends JPanel implements ActionListener, ItemListener {

    Color beautyGreenBlue = new Color (120,250,180);
    HashMap<JButton, String> text;

    public Window window;

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

    public TextComponent instructions;// Display of the explanations
    public JLabel timing;// Display of the time of the simulation

    public double vx;

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

        roomChoice = new JComboBox<>(new String[]{"+ New simulation", "Your simulation", "The beurk", "A classroom"});
        roomChoice.setSelectedIndex(0);
        roomChoice.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(roomChoice, gbc);

        gbc.insets = new Insets(0, 5, 0, 5);
        panic = new JCheckBox("Panic mode");
        panic.setSelected(false);
        panic.setBackground(beautyGreenBlue);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 0.01;
        add(panic,gbc);

        equi = new JCheckBox("Display Equipotentials");
        equi.setSelected(false);
        equi.setBackground(beautyGreenBlue);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(equi,gbc);

        color = new JCheckBox("Display distance to the exit");
        color.setSelected(false);
        color.setBackground(beautyGreenBlue);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(color,gbc);

        //How to play
        gbc.insets = new Insets(5, 5, 5, 5);
        Font e = new Font( "Cambria", Font.PLAIN, 18);
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

        save = new JButton("Save the current simulation as \"Your simulation\"",new ImageIcon("Icons/save24.png"));
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

        speed = new JButton(new ImageIcon("Icons/forward.png"));
        speed.setLayout(null);
        gbc.gridx = 3;
        gbc.gridy = 10;
        add(speed,gbc);

        pause = new JButton(new ImageIcon("Icons/pause.png"));
        pause.setLayout(null);
        pause.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 10;
        add(pause,gbc);

        text=new HashMap<>();
        text.put(this.person,"To add a person, you just have\nto click somewhere in the simulation,\na point representing that person\nwill appear.");
        text.put(this.obstacle,"You can create rectangular shaped\nobstacles.To do so, you will give\n2 vertices, press your mouse,\nand release it where you want");
        text.put(this.exit,"As for adding a person\njust click somewhere on the simulation\nto add the exit.");

        addListener();
    }

    public void addListener() {
        person.addActionListener(this);
        obstacle.addActionListener(this);
        exit.addActionListener(this);
        roomChoice.addItemListener(this);
        roomChoice.addActionListener(this);
        panic.addItemListener(this);
        equi.addItemListener(this);
        color.addItemListener(this);
        save.addActionListener(this);
        restart.addActionListener(this);
        start.addActionListener(this);
        slow.addActionListener(this);
        speed.addActionListener(this);
        pause.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        //If we press start...
        if (e.getSource() == start) {
            instructions.setText("Computing paths...");
            window.simulation.dijkstra();
            instructions.setText("The simulation is running");
            window.simulation.start();//...we start the simulation
            start.setVisible(false);
            pause.setVisible(true);
        }

        //If we press pause...
        else if (e.getSource() == pause) {
            window.simulation.pause();
            start.setVisible(true);
            pause.setVisible(false);

        }

        else if (e.getSource() == slow) {
            window.simulation.speedTimes(0.5);
            vx *= 0.5;
            instructions.setText("x"+vx);
        }

        else if (e.getSource() == speed) {
            window.simulation.speedTimes(1.5);
            vx *= 1.5;
            instructions.setText("x"+vx);
        }

        else if (e.getSource() == restart) {
            window.simulation.restart();
        }

        else if (e.getSource() == roomChoice){
            if (roomChoice.getSelectedItem() == "Your simulation"){
                window.setSimulation("Rooms/UserDefined.ser");
            }else if(roomChoice.getSelectedItem() == "A classroom") {
                window.setSimulation("Rooms/Classroom.ser");
            }else if(roomChoice.getSelectedItem() == "+ New simulation"){
                System.out.println("new");
                window.setSimulation(new Simulation(500,500));
            }else if(roomChoice.getSelectedItem() == "The beurk"){
                window.setSimulation("Rooms/Beurk.ser");
            }
        }

        else if (e.getSource() == save){
            try {
                FileOutputStream fs = new FileOutputStream("Rooms/UserDefined.ser");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(window.simulation); // 3
                os.close();
            } catch (Exception et) {
                et.printStackTrace();
            }
            instructions.setText("Simulation saved ;)");
        }
        else{
            window.wait.forEach((button,bool)->{
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

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == color) {
            window.drawColor = (e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == equi) {
            window.drawEqui = (e.getStateChange() == ItemEvent.SELECTED);
        }
        else if (e.getSource() == panic){
            window.simulation.setPanic(e.getStateChange() == ItemEvent.SELECTED);
        }
    }
}
