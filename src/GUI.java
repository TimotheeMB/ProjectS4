import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    public GUI () {
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

        this.setVisible(true);
    }

}
