package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller;

import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions.ControlledException;
import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.services.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

    public static String VERSION = "1.2.0";

    public static void main(String[] args) throws Exception {

        try {

            LogService logService = new LogService();

            logService.log(Main.class, "Starting version " + VERSION);

            // Required for log4j
            BasicConfigurator.configure();
            Logger.getRootLogger().setLevel(Level.WARN);

            RobotService robotService = new RobotService(logService);
            ConfigurationService configurationService = new ConfigurationService(logService);
            ChannelRewardService channelRewardService = new ChannelRewardService(logService, robotService);
            TwitchService twitchService = new TwitchService(logService, channelRewardService);

//            String accessToken = configurationService.getBroadcasterID();
            String channelID = configurationService.getBroadcasterID();

            twitchService.start(channelID);

        } catch (ControlledException exception) {
            System.out.println("");
            System.out.println("ERROR: " + exception.getMessage());
            System.out.println("");
        }

    }

}
