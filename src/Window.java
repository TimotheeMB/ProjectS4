import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    public boolean drawEquidistant;
    public boolean drawDistanceToExit;
    HashMap<JButton, Boolean> wait;


    /* === CONSTRUCTOR === */
    public Window(){

        //Default simulation
        chargeSimulation(new Simulation(false));

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

        chargeSimulation(new Simulation(true));
    }

    public void chargeSimulation(String fileName){ //this will charge the simulation with this given file path.
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            simulation = (Simulation) ois.readObject();
            ois.close();
        } catch (Exception eu) {
            eu.printStackTrace();
        }
    }
    public void chargeSimulation(Simulation simulation){
        this.simulation=simulation;
    }

    public void saveSimulation(){
        //A pop-up to ask the name of the simulation to save
        String nameSimulation =  JOptionPane.showInputDialog(null, "Choose a name for this simulation : ", "Save the simulation!", JOptionPane.QUESTION_MESSAGE);
        if (nameSimulation != null){//If there is a name
            //A pop-up just to confirm the name
            JOptionPane.showMessageDialog(null, "You chose to name your simulation: " + nameSimulation, "Name your simulation", JOptionPane.INFORMATION_MESSAGE);
            simulation.timer.setDelay(simulation.NORMAL_STEP_DURATION);//We don't want to save the simulation going fast
            try {
                FileOutputStream fs = new FileOutputStream("Simulations/"+nameSimulation+".ser");//we will save the simulation in the folder Simulations, in a file with its name
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(simulation); // 3
                os.close();
                if(((DefaultComboBoxModel) choicesPan.simulationChoice.getModel()).getIndexOf(nameSimulation)==-1){//If not already in the comboBox
                    choicesPan.simulationChoice.addItem(nameSimulation);//We add the new simulation it the comboBox
                }
                choicesPan.instructions.setText("Simulation saved");
            } catch (Exception et) {
                et.printStackTrace();
            }
        }
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