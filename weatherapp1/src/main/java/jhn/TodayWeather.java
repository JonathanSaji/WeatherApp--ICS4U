package jhn;
import javax.swing.*;

import java.awt.event.*;

import java.awt.*;

public class TodayWeather extends JFrame implements MouseListener {

	public JPanel todayPanel;

    TodayWeather(JFrame parentFrame) {

		todayPanel = new JPanel(new GridBagLayout());
		todayPanel.setBackground(Color.BLACK);
		parentFrame.add(todayPanel);


		JLabel zero = new JLabel();
        labelCreator(zero, "12 AM", 300, 75,
        Color.WHITE, new Font("Monospaced", Font.PLAIN, 24),true,0,0);

		JLabel one = new JLabel();
        labelCreator(one, "1 AM", 300, 75,
        Color.WHITE, new Font("Monospaced", Font.PLAIN, 24),true,1,1);

		JLabel two = new JLabel();
        labelCreator(two, "2 AM", 300, 75,
        Color.WHITE, new Font("Monospaced", Font.PLAIN, 24),true,0,2);

		JLabel three = new JLabel();
        labelCreator(three, "3 AM", 300, 75,
        Color.WHITE, new Font("Monospaced", Font.PLAIN, 24),true,1,3);
		
		



    }

	public void labelCreator(JLabel label, String text,  int width, int height,Color textColor, Font font, boolean addMouseListener,int gridy,int gridx) {
        label = new JLabel(text, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(width, height));
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        label.setForeground(textColor);
        label.setFont(font);
        label.setBackground(Color.DARK_GRAY);
        if(addMouseListener) {
            label.addMouseListener(this);
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;   // Always in the same column to stay centered
        gbc.gridy = gridy; // Increments to move down
        gbc.insets = new Insets(10, 50, 10, 50); // Adds 10 pixels of space above and below
        
        todayPanel.add(label, gbc);

        }


	@Override
	public void mousePressed(MouseEvent e) {
		// Invoked when a mouse button has been pressed on a component


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Invoked when a mouse button has been released on a component
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Invoked when the mouse enters a component 
		if (e.getComponent() instanceof JLabel) {
        JLabel label = (JLabel) e.getComponent();
        label.setOpaque(true);
        label.setForeground(Color.YELLOW);
        }
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Invoked when the mouse exits a component
		if (e.getComponent() instanceof JLabel) {
        JLabel label = (JLabel) e.getComponent();
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
		}
    }


    @Override
    public void mouseClicked(MouseEvent e) {
       
    }
}
