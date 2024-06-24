package config;

import org.aeonbits.owner.ConfigFactory;

public class Project {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    public static boolean isWebMobile() {//
        int horizontalSize = 0;
           try {
               horizontalSize = Integer.parseInt(config.browserSize().split("x")[0]);
            }
            catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        return horizontalSize <= 980;
    }

    //public static boolean isRemoteWebDriver() {
        //return !config.remoteDriverUrl().equals("https://shop.tastycoffee.ru/");
    //}

    //public static boolean isVideoOn() {
      //  return !config.videoStorage().equals("");
    //}
}
