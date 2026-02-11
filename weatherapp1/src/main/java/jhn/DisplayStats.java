package jhn;


import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.awt.*;


public class DisplayStats {


    public DisplayStats(LocalDate date, Weather weather, JFrame parentFrame) {

        JPanel statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setVisible(true);
        parentFrame.add(statsPanel);


    }

    
}
