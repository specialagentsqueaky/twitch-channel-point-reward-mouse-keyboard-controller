package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.services;

import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions.ControlledException;
import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.utils.StringUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Properties;

public class ConfigurationService {

    private final LogService logService;
    private final Properties properties;

    public ConfigurationService(LogService logService) throws Exception {
        this.logService = logService;
        this.properties = new Properties();
        this.init();
    }

    private void init() throws Exception {

        File file = new File("config.txt");

        this.logService.log(ConfigurationService.class, "Loading configuration file \"" + file + "\".");
        this.properties.load(FileUtils.openInputStream(file));

    }

    public String getBroadcasterID() throws Exception {
        return this.getValue("channelID");
    }

    private String getValue(String key) throws ControlledException {
        String value = this.getValueOptional(key);
        if (StringUtil.isEmpty(value)) {
            throw new ControlledException("Could not read the value for key \"" + key + "\" from the config file.");
        }
        return value;
    }

    private String getValueOptional(String key) {
        String value = this.properties.getProperty(key);
        this.logService.log(ConfigurationService.class, "Read value \"" + value + "\" for the configuration key \"" + key + "\".");
        return value;
    }

}
