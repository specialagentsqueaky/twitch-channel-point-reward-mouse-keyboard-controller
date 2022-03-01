package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.services;

public class LogService {

    public void log(Class c, String message) {
        System.out.println("[" + c.getSimpleName() + "] " + message);
    }

    public void exception(Exception exception) {
        exception.printStackTrace();
    }

}
