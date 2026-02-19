package jhn.ui;

import javax.swing.*;
import jhn.API.Weather;
import jhn.run.WeatherApp;
import jhn.ui.uiComponents.WeatherIcons;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

public class TimeDivider extends JFrame implements MouseListener {

    JPanel background;
    JLabel morningJLabel, beforenoonJLabel, afternoonJLabel, nightJLabel;
    WeatherIcons weatherIcons;
    JFrame parentFrame;
    Weather weather;
    LocalDate date;

    public TimeDivider(JFrame parentFrame, Weather weather, LocalDate date) {
        this.parentFrame = parentFrame;
        this.weather = weather;
        this.date = date;
        weatherIcons = new WeatherIcons(weather);

        // Background panel with animated gif support
        background = new JPanel(null) {
            private final ImageIcon icon = new ImageIcon(
                    WeatherApp.getBackgroundHandler().getBackgroundPath());

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (icon.getImage() != null) {
                    g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        background.setBounds(0, 0, 1920, 1080);
        parentFrame.add(background);

        // Initialize all four time-period panels
        morningJLabel = new JLabel();
        beforenoonJLabel = new JLabel();
        afternoonJLabel = new JLabel();
        nightJLabel = new JLabel();

        // Position 0 = top-left, 1 = top-right, 2 = bottom-left, 3 = bottom-right
        timeLabelCreator(morningJLabel, 0);
        timeLabelCreator(beforenoonJLabel, 1);
        timeLabelCreator(afternoonJLabel, 2);
        timeLabelCreator(nightJLabel, 3);

        // --- Morning (00:00 – 06:00) ---
        String degreeString = weather.getDegreeString();
        statsLabel(new JLabel(), morningJLabel, "Morning", 0, false, null);
        statsLabel(new JLabel(), morningJLabel, "Temp: " + weather.getLOWVal(date, weather.getTemperatures(), 0, 6)
                + " -> " + weather.getHIGHVal(date, weather.getTemperatures(), 0, 6) + degreeString, 2, false, null);
        statsLabel(new JLabel(), morningJLabel,
                "Humidity: " + weather.getAverageVal(date, weather.getHumidity(), 0, 6) + "%", 3, false, null);
        statsLabel(new JLabel(), morningJLabel,
                "Wind Speed: " + weather.getAverageVal(date, weather.getWindSpeed(), 0, 6) + " km/h", 4, false, null);
        statsLabel(new JLabel(), morningJLabel, null, 5, true, weatherIcons.getIconForCloud(date, 0, 6));

        // --- Before Noon (06:00 – 12:00) ---
        statsLabel(new JLabel(), beforenoonJLabel, "Before Noon", 0, false, null);
        statsLabel(new JLabel(), beforenoonJLabel, "Temp: " + weather.getLOWVal(date, weather.getTemperatures(), 6, 12)
                + " -> " + weather.getHIGHVal(date, weather.getTemperatures(), 6, 12) + degreeString, 2, false, null);
        statsLabel(new JLabel(), beforenoonJLabel,
                "Humidity: " + weather.getAverageVal(date, weather.getHumidity(), 6, 12) + "%", 3, false, null);
        statsLabel(new JLabel(), beforenoonJLabel,
                "Wind Speed: " + weather.getAverageVal(date, weather.getWindSpeed(), 6, 12) + " km/h", 4, false, null);
        statsLabel(new JLabel(), beforenoonJLabel, null, 5, true, weatherIcons.getIconForCloud(date, 6, 12));

        // --- Afternoon (12:00 – 18:00) ---
        statsLabel(new JLabel(), afternoonJLabel, "Afternoon", 0, false, null);
        statsLabel(new JLabel(), afternoonJLabel, "Temp: " + weather.getLOWVal(date, weather.getTemperatures() , 12, 18)
                + " -> " + weather.getHIGHVal(date, weather.getTemperatures(), 12, 18) + degreeString, 2, false, null);
        statsLabel(new JLabel(), afternoonJLabel,
                "Humidity: " + weather.getAverageVal(date, weather.getHumidity(), 12, 18) + "%", 3, false, null);
        statsLabel(new JLabel(), afternoonJLabel,
                "Wind Speed: " + weather.getAverageVal(date, weather.getWindSpeed(), 12, 18) + " km/h", 4, false, null);
        statsLabel(new JLabel(), afternoonJLabel, null, 5, true, weatherIcons.getIconForCloud(date, 12, 18));

        // --- Night (18:00 – 24:00) ---
        statsLabel(new JLabel(), nightJLabel, "Night", 0, false, null);
        statsLabel(new JLabel(), nightJLabel, "Temp: " + weather.getLOWVal(date, weather.getTemperatures(), 18, 24)
                + " -> " + weather.getHIGHVal(date, weather.getTemperatures(), 18, 24) + degreeString, 2, false, null);
        statsLabel(new JLabel(), nightJLabel,
                "Humidity: " + weather.getAverageVal(date, weather.getHumidity(), 18, 24) + "%", 3, false, null);
        statsLabel(new JLabel(), nightJLabel,
                "Wind Speed: " + weather.getAverageVal(date, weather.getWindSpeed(), 18, 24) + " km/h", 4, false, null);
        statsLabel(new JLabel(), nightJLabel, null, 5, true, weatherIcons.getIconForCloud(date, 18, 24));

        // MENU button centred on screen
        JLabel menu = new JLabel("MENU", SwingConstants.CENTER);
        menu.setBounds(WeatherApp.getMiddleX(200), WeatherApp.getMiddleY(100), 200, 100);
        menu.setFont(new Font("Monospaced", Font.BOLD, 38));
        menu.setForeground(Color.BLACK);
        menu.addMouseListener(this);
        background.add(menu);

        parentFrame.revalidate();
        parentFrame.repaint();
    }

    /**
     * Creates and positions a time-period panel in a 2x2 grid.
     *
     * @param label    The JLabel to configure as a panel.
     * @param position 0 = top-left (Morning), 1 = top-right (Before Noon),
     *                 2 = bottom-left (Afternoon), 3 = bottom-right (Night)
     */
    public void timeLabelCreator(JLabel label, int position) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        label.setOpaque(true);
        label.setLayout(new GridBagLayout());

        int panelWidth = 400;
        int panelHeight = 500;
        int centerX = WeatherApp.getMiddleX(panelWidth);
        int centerY = WeatherApp.getMiddleY(panelHeight);

        switch (position) {
            case 0:
                label.setBounds(centerX - (int)(panelWidth * 1.8), centerY, panelWidth, panelHeight); // Morning
                break;
            case 1:
                label.setBounds(centerX - (int)(panelWidth / 1.5), centerY, panelWidth, panelHeight); // Before Noon
                break;
            case 2:
                label.setBounds(centerX +(int)(panelWidth / 1.5), centerY, panelWidth, panelHeight); // Afternoon
                break;
            case 3:
                label.setBounds(centerX + (int)(panelWidth * 1.8), centerY, panelWidth, panelHeight); // Night
                break;
            default:
        }

        label.addMouseListener(this);
        background.add(label);
    }

    /**
     * Adds a stat row (text or icon) to a time-period panel.
     *
     * @param label         The JLabel to add.
     * @param panel         The target time-period panel.
     * @param text          The text to display (ignored when icon = true).
     * @param gridy         The GridBagLayout row position.
     * @param icon          Whether to display an image icon instead of text.
     * @param imageFilePath Path to the icon image (used when icon = true).
     */
    public void statsLabel(JLabel label, JLabel panel, String text, int gridy, boolean icon, String imageFilePath) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;

        if (icon) {
            label.setIcon(new ImageIcon(imageFilePath));
        } else {
            label = new JLabel(text);
        }
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Monospaced", Font.BOLD, 28));
        panel.add(label, gbc);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() instanceof JLabel) {
            JLabel label = (JLabel) e.getComponent();
            if (label.getText().equals("MENU")) {
                background.setVisible(false);
                WeatherApp.getMenu().setPanel();
            } else if (label == morningJLabel) {
                background.setVisible(false);
                new DisplayMorning(parentFrame, weather, date,background);
            } else if (label == beforenoonJLabel) {
                background.setVisible(false);
                new DisplayBeforeNoon(parentFrame, weather, date,background);
            } else if (label == afternoonJLabel) {
                background.setVisible(false);
                new DisplayAfternoon(parentFrame, weather, date,background);
            } else if (label == nightJLabel) {
                background.setVisible(false);
                new DisplayNight(parentFrame, weather, date,background);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent() instanceof JLabel) {
            JLabel label = (JLabel) e.getComponent();
            if (label.getText().equals("MENU")) {
                label.setForeground(Color.RED);
                label.setBackground(Color.BLACK);
            } else {
                label.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent() instanceof JLabel) {
            JLabel label = (JLabel) e.getComponent();
            if (label.getText().equals("MENU")) {
                label.setForeground(Color.BLACK);
                label.setOpaque(false);
            } else {
                label.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }


    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         JFrame frame = new JFrame();
    //         new TimeDivider(frame, new Weather(45, -75), LocalDate.now());
    //         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    //         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //         frame.setLayout(null);
    //         frame.setVisible(true);
    //     });
    // }
}