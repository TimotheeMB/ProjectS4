import javax.swing.*;
import java.awt.*;

public class ChoicesPanel extends JPanel {

    Color beautyGreenBlue = new Color (120,250,180);

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

    public Listener listener;

    public ChoicesPanel() {
        setBackground(beautyGreenBlue);

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

        roomChoice = new JComboBox<>(new String[]{"+ New room", "Your room", "The beurk", "A classroom"});
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

        save = new JButton("Save the current room as \"Your room\"",new ImageIcon("Icons/save24.png"));
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

    }

    public void addListener(Listener l){
        person.addActionListener(l);
        obstacle.addActionListener(l);
        exit.addActionListener(l);
        roomChoice.addItemListener(l);
        roomChoice.addActionListener(l);
        panic.addActionListener(l);
        equi.addActionListener(l);
        color.addActionListener(l);
        save.addActionListener(l);
        restart.addActionListener(l);
        start.addActionListener(l);
        slow.addActionListener(l);
        speed.addActionListener(l);
        pause.addActionListener(l);
    }
}
