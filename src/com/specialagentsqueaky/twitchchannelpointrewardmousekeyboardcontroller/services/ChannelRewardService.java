package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.services;

import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions.ControlledException;
import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions.ScriptException;
import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.utils.KeyCodeUtil;
import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.utils.RandomUtil;
import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.utils.StringUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelRewardService {

    private final LogService logService;
    private final RobotService robotService;

    public ChannelRewardService(LogService logService, RobotService robotService) {
        this.logService = logService;
        this.robotService = robotService;
    }

    public void perform(String user, String rewardID) throws Exception {

        this.logService.log(ChannelRewardService.class, "\"" + user + "\" just redeemed reward \"" + rewardID + "\".");

        // First try and see if we find a reward instruction file
        File file = new File(rewardID + ".txt");
        if (!file.exists()) {
            this.logService.log(ChannelRewardService.class, "Could not find the reward instruction file \"" + file + "\". Ignoring reward claim.");
            return;
        }

        this.logService.log(ChannelRewardService.class, "Found reward instruction file \"" + file + "\". Reading it.");
        String contents = FileUtils.readFileToString(file, "UTF-8");
        String[] rawInstructions = contents.split("\n");

        List<String> trimmedInstructions = new ArrayList<>();

        // Trim all instructions
        for (String instruction : rawInstructions) {
            instruction = instruction.trim();
            if (StringUtil.isEmpty(instruction)) {
                continue;
            }
            trimmedInstructions.add(instruction);
        }

        // Trim all instructions
        this.logService.log(ChannelRewardService.class, "Will try and find all markers in the script.");
        Map<String, Integer> markers = new HashMap<>();
        for (int i = 0; i < trimmedInstructions.size(); i++) {
            String instruction = trimmedInstructions.get(i);
            this.checkForMarker(i, instruction, markers);
        }
        this.logService.log(ChannelRewardService.class, "Found " + markers.size() + " markers: " + markers);

        this.logService.log(ChannelRewardService.class, "Will now try and perform all other instructions in the script.");
        for (int i = 0; i < trimmedInstructions.size(); i++) {
            String instruction = trimmedInstructions.get(i);
            PerformInstructionResponse response = this.performInstruction(i, instruction);
            if (response == null) {
                continue;
            }
            if (response.endScript()) {
                this.logService.log(ChannelRewardService.class, "Ending script.");
                break;
            }
            String jumpToMarker = response.getJumpToMarker();
            if (!StringUtil.isEmpty(jumpToMarker)) {
                Integer jumpToRowLine = markers.get(jumpToMarker);
                if (jumpToRowLine == null) {
                    throw new ScriptException(i, "The script wanted to jump to the marker \"" + jumpToMarker + "\" but that does not seem to exist.");
                }
                this.log(i, "The script wants to jump to row line " + jumpToRowLine + ".");
                if (jumpToRowLine < i) {
                    throw new ScriptException(i, "We are on row " + i + ", but the script wanted to jump to row " + jumpToRowLine + " which is prohibited to prevent endless loops.");
                }
                i = jumpToRowLine;
            }
        }

        this.logService.log(ChannelRewardService.class, "All instructions for reward \"" + file + "\" done.");

    }

    private void checkForMarker(int rowLine, String instruction, Map<String, Integer> markers) throws ControlledException {
        this.log(rowLine, "Found instruction \"" + instruction + "\". Will try and see if it is a marker.");
        List<String> keywords = this.getKeywords(instruction);

        String actionKeyword = this.getString(keywords, "main keyword", 0);

        if (!"MARKER".equals(actionKeyword)) {
            this.log(rowLine, "The instruction \"" + instruction + "\" is not a marker.");
            return;
        }

        this.log(rowLine, "The instruction \"" + instruction + "\" seems to be a marker.");

        String label = this.getString(keywords, "label", 1);
        this.log(rowLine, "Found the marker \"" + label + "\".");
        if (markers.get(label) != null) {
            throw new ControlledException("Found duplicated marker \"" + label + "\". Markers has to be unique.");
        }
        this.log(rowLine, "Found the marker \"" + label + "\" on line " + rowLine + ".");
        markers.put(label, rowLine);

    }

    private PerformInstructionResponse performInstruction(int rowLine, String instruction) throws ControlledException {
        this.logService.log(ChannelRewardService.class, "Found instruction \"" + instruction + "\". Will try and execute it.");

        if (instruction.startsWith("#")) {
            this.log(rowLine, "\"" + instruction + "\" seems to be a comment. Ignoring that row.");
            return null;
        }

        List<String> keywords = this.getKeywords(instruction);

        String actionKeyword = getString(keywords, "main keyword", 0);
        this.logService.log(ChannelRewardService.class, "Action keyword is \"" + actionKeyword + "\". Will try and execute that specific instruction.");

        switch (actionKeyword) {
            case "KEY_PRESS": {
                this.performKeyPress(keywords);
                return null;
            }
            case "WAIT": {
                this.performWait(keywords);
                return null;
            }
            case "MOUSE_SCROLL_WHEEL": {
                this.performScrollWheel(keywords);
                return null;
            }
            case "MOUSE_BUTTON_LEFT": {
                this.performMouseButtonLeft(keywords);
                return null;
            }
            case "MOUSE_BUTTON_MIDDLE": {
                this.performMouseButtonMiddle(keywords);
                return null;
            }
            case "MOUSE_BUTTON_RIGHT": {
                this.performMouseButtonRight(keywords);
                return null;
            }
            case "MOUSE_BUTTON_4": {
                this.performMouseButton4(keywords);
                return null;
            }
            case "MOUSE_BUTTON_5": {
                this.performMouseButton5(keywords);
                return null;
            }
            case "MOUSE_MOVE": {
                this.performMouseMove(keywords);
                return null;
            }
            case "MOUSE_POSITION": {
                this.performMousePosition(keywords);
                return null;
            }
            case "MARKER": {
                this.performMarker(rowLine, keywords);
                return null;
            }
            case "GOTO": {
                String selectedLabel = this.performGoto(rowLine, keywords);
                if (!StringUtil.isEmpty(selectedLabel)) {
                    return new PerformInstructionResponse(false, selectedLabel);
                }
                return null;
            }
            case "RETURN": {
                this.log(rowLine, "Found \"RETURN\". Will send signal to end script.");
                return new PerformInstructionResponse(true, null);
            }
        }

        throw new ControlledException("Don't know how to execute instruction \"" + instruction + "\".");

    }

    private List<String> getKeywords(String instruction) {
        List<String> keywords = new ArrayList<>();
        for (String k : instruction.split(" ")) {
            if (StringUtil.isEmpty(k.trim())) {
                continue;
            }
            keywords.add(k);
        }
        return keywords;
    }

    private int getInteger(List<String> keywords, String label, int index) throws ControlledException {
        String value = this.getString(keywords, label, index);
        return parseInt(value);
    }

    private String getString(List<String> keywords, String label, int index) throws ControlledException {
        if (keywords.size() - 1 < index) {
            throw new ControlledException("Could not find the \"" + label + "\" from instructions \"" + keywords + "\".");
        }
        return keywords.get(index);
    }

    private int parseInt(String s) throws ControlledException {
        try {
            return Integer.parseInt(s, 10);
        } catch (NumberFormatException exception) {
            throw new ControlledException("Cannot convert \"" + s + "\" into a number.");
        }
    }

    private void performKeyPress(List<String> keywords) throws ControlledException {

        List<Integer> codes = new ArrayList<>();

        int duration = this.getInteger(keywords, "duration", 1);

        for (int i = 2; i < keywords.size(); i++) {
            int code = KeyCodeUtil.getCode(keywords.get(i));
            codes.add(code);
        }

        for (int code : codes) {
            this.robotService.keyDown(code);
        }

        // This is required while testing OBS scenes
        this.robotService.wait(duration);

        for (int code : codes) {
            this.robotService.keyUp(code);
        }

    }

    private void performWait(List<String> keywords) throws ControlledException {
        int duration = this.getInteger(keywords, "duration", 1);
        this.robotService.wait(duration);
    }

    private void performScrollWheel(List<String> keywords) throws ControlledException {
        int notches = this.getInteger(keywords, "notches", 1);
        this.robotService.mouseScrollWheel(notches);
    }

    private void performMouseButtonLeft(List<String> keywords) throws ControlledException {
        int durationMS = this.getInteger(keywords, "duration", 1);
        this.robotService.mouseButtonLeftPress();
        this.robotService.wait(durationMS);
        this.robotService.mouseButtonLeftRelease();
    }

    private void performMouseButtonMiddle(List<String> keywords) throws ControlledException {
        int durationMS = this.getInteger(keywords, "duration", 1);
        this.robotService.mouseButtonMiddlePress();
        this.robotService.wait(durationMS);
        this.robotService.mouseButtonMiddleRelease();
    }

    private void performMouseButtonRight(List<String> keywords) throws ControlledException {
        int durationMS = this.getInteger(keywords, "duration", 1);
        this.robotService.mouseButtonRightPress();
        this.robotService.wait(durationMS);
        this.robotService.mouseButtonRightRelease();
    }

    private void performMouseButton4(List<String> keywords) throws ControlledException {
        int durationMS = this.getInteger(keywords, "duration", 1);
        this.robotService.mouseButton4Press();
        this.robotService.wait(durationMS);
        this.robotService.mouseButton4Release();
    }

    private void performMouseButton5(List<String> keywords) throws ControlledException {
        int durationMS = this.getInteger(keywords, "duration", 1);
        this.robotService.mouseButton5Press();
        this.robotService.wait(durationMS);
        this.robotService.mouseButton5Release();
    }

    private void performMouseMove(List<String> keywords) throws ControlledException {
        int x = this.getInteger(keywords, "x", 1);
        int y = this.getInteger(keywords, "y", 2);
        this.robotService.mouseMove(x, y);
    }

    private void performMousePosition(List<String> keywords) throws ControlledException {
        int x = this.getInteger(keywords, "x", 1);
        int y = this.getInteger(keywords, "y", 2);
        this.robotService.mousePosition(x, y);
    }

    private void performMarker(int rowLine, List<String> keywords) throws ControlledException {
        String label = this.getString(keywords, "label", 1);
        this.log(rowLine, "Found the marker \"" + label + "\".");
    }

    private String performGoto(int rowLine, List<String> keywords) throws ControlledException {

        List<String> possibleMarkers = new ArrayList<>();

        for (int i = 1; i < keywords.size(); i++) {
            String code = keywords.get(i);
            possibleMarkers.add(code);
        }

        if (possibleMarkers.isEmpty()) {
            throw new ControlledException("Could not find any labels for the GOTO \"" + keywords + "\".");
        }

        this.log(rowLine, "Found the possible markers to goto \"" + possibleMarkers + "\". Selecting a random one.");

        String selectedLabel = RandomUtil.random(possibleMarkers);

        this.log(rowLine, "The label \"" + selectedLabel + "\" was selected.");

        return selectedLabel;

    }

    private void log(int rowLine, String message) {
        this.logService.log(ChannelRewardService.class, "[LINE" + rowLine + "] " + message);
    }

    public static class PerformInstructionResponse {

        private final boolean endScript;
        private final String jumpToMarker;

        public PerformInstructionResponse(boolean endScript, String jumpToMarker) {
            this.endScript = endScript;
            this.jumpToMarker = jumpToMarker;
        }

        public boolean endScript() {
            return this.endScript;
        }

        public String getJumpToMarker() {
            return this.jumpToMarker;
        }

    }

}
