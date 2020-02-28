import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    public GUI () {
        this.setTitle(" Welcome to our Crowd Simulator");
        this.setSize(1500,800);
        this.setLocation(200,20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panneauChoix = new JPanel();
        panneauChoix.setBounds(1165,10,300, 720);
        panneauChoix.setLayout(null);
        panneauChoix.setBackground(Color.cyan);



        JPanel panneauGlobal = new JPanel();
        panneauGlobal.setBounds(0,0,1500,800);
        panneauGlobal.setLayout(null);
        panneauGlobal.add(panneauChoix);
        this.add(panneauGlobal);

        this.setVisible(true);
    }

}
