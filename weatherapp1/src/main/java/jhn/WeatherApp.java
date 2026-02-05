package jhn;
public class WeatherApp {
    public static void main(String[] args) throws Exception {
      
        Menu menu = new Menu();
        menu.setVisible(true);
        
        Weather weather = new Weather(45, -75);
        //slot means at what time 
        System.out.println(weather.getWeatherWithTime(0));
    }
}


