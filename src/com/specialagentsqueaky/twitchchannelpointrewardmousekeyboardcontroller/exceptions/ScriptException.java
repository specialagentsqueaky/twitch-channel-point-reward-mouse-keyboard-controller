package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions;

public class ScriptException extends Exception {

    public ScriptException(int rowLine, String message) {
        super("[LINE" + rowLine + "] " + message);
    }

}
