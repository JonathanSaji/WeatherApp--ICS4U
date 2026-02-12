package jhn;


import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.awt.*;


public class DisplayStats {

    private JPanel statsPanel;



    public DisplayStats(LocalDate date, Weather weather, JFrame parentFrame,int hourSelected) {

        statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setVisible(true);
        parentFrame.add(statsPanel);

        String WeatherStats[] = {
            "Humidity: " + weather.getHumidity(date, hourSelected),
            "Dew Point: " + weather.getDewPoint(date, hourSelected, WeatherApp.getJsonHandler().getBoolean("celcius")),
            "Apparent Temperature: " + weather.getApparentTemp(date, hourSelected, WeatherApp.getJsonHandler().getBoolean("celcius")),
            "Precipitation: " + weather.getPrecipitation(date, hourSelected),
            "Rain: " + weather.getRain(date, hourSelected),
            "Showers: " + weather.getShowers(date, hourSelected),
            "Snowfall: " + weather.getSnowfall(date, hourSelected),
            "Pressure MSL: " + weather.getPressureMsl(date, hourSelected),
            "Surface Pressure: " + weather.getSurfacePressure(date, hourSelected),
            "Cloud Cover: " + weather.getCloudCover(date, hourSelected),
            "Wind Speed: " + weather.getWindSpeed(date, hourSelected),
            "Soil Temperature: " + weather.getSoilTemp(date, hourSelected, WeatherApp.getJsonHandler().getBoolean("celcius"))
        };

        for (int i = 1; i <= 4; i++) {
            int offset = (i - 1) * 6; // Cleaner logic for index
            for (int j = 0; j < 6; j++) {
                int currentIndex = j + offset;
                System.out.println(date);
                labelCreator(new JLabel(), WeatherStats[currentIndex], j, i - 1, 500, 200);
            }
        }

    }

      public void labelCreator(JLabel label, String text, int gridy, int gridx, int width, int height) {

        label = new JLabel(text, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(width,height));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Monospaced", Font.TYPE1_FONT, 48));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; 
        gbc.gridy = gridy; 
        gbc.insets = new Insets(10, 0, 0, 10); // Spacing between "cards"
        gbc.fill = GridBagConstraints.BOTH;

        statsPanel.add(label, gbc);

       
    }


    
}
