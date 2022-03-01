package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.services;

import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions.ControlledException;

import java.awt.*;
import java.awt.event.InputEvent;

public class RobotService {

    private static final int BUTTON4_DOWN_MASK = 1<<14;
    private static final int BUTTON5_DOWN_MASK = 1<<15;

    private final LogService logService;
    private final Robot robot;

    public RobotService(LogService logService) throws AWTException {
        this.logService = logService;
        this.robot = new Robot();
    }

    public void keyDown(int code) throws ControlledException {
        this.logService.log(RobotService.class, "Executing key down \"" + code + "\".");
        try {
            this.robot.keyPress(code);
        } catch (IllegalArgumentException exception) {
            throw new ControlledException("The key code \"" + code + "\" is not valid.");
        }
    }

    public void keyUp(int code) throws ControlledException {
        this.logService.log(RobotService.class, "Executing key up \"" + code + "\".");
        try {
            this.robot.keyRelease(code);
        } catch (IllegalArgumentException exception) {
            throw new ControlledException("The key code \"" + code + "\" is not valid.");
        }
    }

    public void wait(int ms) throws ControlledException {
        this.logService.log(RobotService.class, "Waiting \"" + ms + "\" milliseconds.");
        try {
            this.robot.delay(ms);
        } catch (IllegalArgumentException exception) {
            throw new ControlledException("The wait duration of \"" + ms + "\" milliseconds is not valid.");
        }
    }

    public void mouseScrollWheel(int notches) throws ControlledException {
        this.logService.log(RobotService.class, "Executing mouse scroll wheel notches \"" + notches + "\".");
        try {
            this.robot.mouseWheel(notches);
        } catch (IllegalArgumentException exception) {
            throw new ControlledException("Invalid number of mouse scroll wheel notches \"" + notches + "\".");
        }
    }

    public void mouseButtonLeftPress() {
        this.logService.log(RobotService.class, "Executing mouse button left press.");
        this.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void mouseButtonLeftRelease() {
        this.logService.log(RobotService.class, "Executing mouse button left release.");
        this.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void mouseButtonMiddlePress() {
        this.logService.log(RobotService.class, "Executing mouse button middle press.");
        this.robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
    }

    public void mouseButtonMiddleRelease() {
        this.logService.log(RobotService.class, "Executing mouse button middle release.");
        this.robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }

    public void mouseButtonRightPress() {
        this.logService.log(RobotService.class, "Executing mouse button right press.");
        this.robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void mouseButtonRightRelease() {
        this.logService.log(RobotService.class, "Executing mouse button right release.");
        this.robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void mouseButton4Press() {
        this.logService.log(RobotService.class, "Executing mouse button 4 press.");
        this.robot.mousePress(BUTTON4_DOWN_MASK);
    }

    public void mouseButton4Release() {
        this.logService.log(RobotService.class, "Executing mouse button 4 release.");
        this.robot.mouseRelease(BUTTON4_DOWN_MASK);
    }

    public void mouseButton5Press() {
        this.logService.log(RobotService.class, "Executing mouse button 5 press.");
        this.robot.mousePress(BUTTON5_DOWN_MASK);
    }

    public void mouseButton5Release() {
        this.logService.log(RobotService.class, "Executing mouse button 5 release.");
        this.robot.mouseRelease(BUTTON5_DOWN_MASK);
    }

    public void mouseMove(int x, int y) {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point b = pointerInfo.getLocation();

        int currentX = (int) b.getX();
        int currentY = (int) b.getY();

        this.logService.log(RobotService.class, "Current mouse position is \"" + currentX + ":" + currentY + "\".");

        int newX = currentX + x;
        int newY = currentY + y;

        this.logService.log(RobotService.class, "Executing mouse movement by \"" + x + ":" + y + "\" to position \"" + newX + ":" + newY + "\".");

        this.robot.mouseMove(newX, newY);
    }

    public void mousePosition(int x, int y) {
        this.logService.log(RobotService.class, "Executing mouse position to \"" + x + ":" + y + "\".");
        this.robot.mouseMove(x, y);
    }

}
