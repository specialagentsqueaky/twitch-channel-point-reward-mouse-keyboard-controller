package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.utils;

import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions.ControlledException;

import java.util.HashMap;
import java.util.Map;

abstract public class KeyCodeUtil {

    public static int getCode(String key) throws ControlledException {

        Map<String, Integer> codes = new HashMap<>();
        codes.put("0", 48);
        codes.put("1", 49);
        codes.put("2", 50);
        codes.put("3", 51);
        codes.put("4", 52);
        codes.put("5", 53);
        codes.put("6", 54);
        codes.put("7", 55);
        codes.put("8", 56);
        codes.put("9", 57);
        codes.put("A", 65);
        codes.put("ACCEPT", 30);
        codes.put("ADD", 107);
        codes.put("AGAIN", 65481);
        codes.put("ALL_CANDIDATES", 256);
        codes.put("ALPHANUMERIC", 240);
        codes.put("ALT", 18);
        codes.put("ALT_GRAPH", 65406);
        codes.put("AMPERSAND", 150);
        codes.put("ASTERISK", 151);
        codes.put("AT", 512);
        codes.put("B", 66);
        codes.put("BACK_QUOTE", 192);
        codes.put("BACK_SLASH", 92);
        codes.put("BACK_SPACE", 8);
        codes.put("BEGIN", 65368);
        codes.put("BRACELEFT", 161);
        codes.put("BRACERIGHT", 162);
        codes.put("C", 67);
        codes.put("CANCEL", 3);
        codes.put("CAPS_LOCK", 20);
        codes.put("CIRCUMFLEX", 514);
        codes.put("CLEAR", 12);
        codes.put("CLOSE_BRACKET", 93);
        codes.put("CODE_INPUT", 258);
        codes.put("COLON", 513);
        codes.put("COMMA", 44);
        codes.put("COMPOSE", 65312);
        codes.put("CONTEXT_MENU", 525);

        codes.put("CONTROL", 17);
        codes.put("CTRL", 17);

        codes.put("CONVERT", 28);
        codes.put("COPY", 65485);
        codes.put("CUT", 65489);
        codes.put("D", 68);
        codes.put("DEAD_ABOVEDOT", 134);
        codes.put("DEAD_ABOVERING", 136);
        codes.put("DEAD_ACUTE", 129);
        codes.put("DEAD_BREVE", 133);
        codes.put("DEAD_CARON", 138);
        codes.put("DEAD_CEDILLA", 139);
        codes.put("DEAD_CIRCUMFLEX", 130);
        codes.put("DEAD_DIAERESIS", 135);
        codes.put("DEAD_DOUBLEACUTE", 137);
        codes.put("DEAD_GRAVE", 128);
        codes.put("DEAD_IOTA", 141);
        codes.put("DEAD_MACRON", 132);
        codes.put("DEAD_OGONEK", 140);
        codes.put("DEAD_SEMIVOICED_SOUND", 143);
        codes.put("DEAD_TILDE", 131);
        codes.put("DEAD_VOICED_SOUND", 142);
        codes.put("DECIMAL", 110);
        codes.put("DELETE", 127);
        codes.put("DIVIDE", 111);
        codes.put("DOLLAR", 515);
        codes.put("DOWN", 40);
        codes.put("E", 69);
        codes.put("END", 35);
        codes.put("ENTER", 10);
        codes.put("EQUALS", 61);
        codes.put("ESCAPE", 27);
        codes.put("EURO_SIGN", 516);
        codes.put("EXCLAMATION_MARK", 517);
        codes.put("F", 70);
        codes.put("F1", 112);
        codes.put("F10", 121);
        codes.put("F11", 122);
        codes.put("F12", 123);
        codes.put("F13", 61440);
        codes.put("F14", 61441);
        codes.put("F15", 61442);
        codes.put("F16", 61443);
        codes.put("F17", 61444);
        codes.put("F18", 61445);
        codes.put("F19", 61446);
        codes.put("F2", 113);
        codes.put("F20", 61447);
        codes.put("F21", 61448);
        codes.put("F22", 61449);
        codes.put("F23", 61450);
        codes.put("F24", 61451);
        codes.put("F3", 114);
        codes.put("F4", 115);
        codes.put("F5", 116);
        codes.put("F6", 117);
        codes.put("F7", 118);
        codes.put("F8", 119);
        codes.put("F9", 120);
        codes.put("FINAL", 24);
        codes.put("FIND", 65488);
        codes.put("FULL_WIDTH", 243);
        codes.put("G", 71);
        codes.put("GREATER", 160);
        codes.put("H", 72);
        codes.put("HALF_WIDTH", 244);
        codes.put("HELP", 156);
        codes.put("HIRAGANA", 242);
        codes.put("HOME", 36);
        codes.put("I", 73);
        codes.put("INPUT_METHOD_ON_OFF", 263);
        codes.put("INSERT", 155);
        codes.put("INVERTED_EXCLAMATION_MARK", 518);
        codes.put("J", 74);
        codes.put("JAPANESE_HIRAGANA", 260);
        codes.put("JAPANESE_KATAKANA", 259);
        codes.put("JAPANESE_ROMAN", 261);
        codes.put("K", 75);
        codes.put("KANA", 21);
        codes.put("KANA_LOCK", 262);
        codes.put("KANJI", 25);
        codes.put("KATAKANA", 241);
        codes.put("KP_DOWN", 225);
        codes.put("KP_LEFT", 226);
        codes.put("KP_RIGHT", 227);
        codes.put("KP_UP", 224);
        codes.put("L", 76);
        codes.put("LEFT", 37);
        codes.put("LEFT_PARENTHESIS", 519);
        codes.put("LESS", 153);
        codes.put("M", 77);
        codes.put("META", 157);
        codes.put("MINUS", 45);
        codes.put("MODECHANGE", 31);
        codes.put("MULTIPLY", 106);
        codes.put("N", 78);
        codes.put("NONCONVERT", 29);
        codes.put("NUM_LOCK", 144);
        codes.put("NUMBER_SIGN", 520);

        codes.put("NUMPAD0", 96);
        codes.put("NUMPAD1", 97);
        codes.put("NUMPAD2", 98);
        codes.put("NUMPAD3", 99);
        codes.put("NUMPAD4", 100);
        codes.put("NUMPAD5", 101);
        codes.put("NUMPAD6", 102);
        codes.put("NUMPAD7", 103);
        codes.put("NUMPAD8", 104);
        codes.put("NUMPAD9", 105);

        codes.put("O", 79);
        codes.put("OPEN_BRACKET", 91);
        codes.put("P", 80);
        codes.put("PAGE_DOWN", 34);
        codes.put("PAGE_UP", 33);
        codes.put("PASTE", 65487);
        codes.put("PAUSE", 19);
        codes.put("PERIOD", 46);
        codes.put("PLUS", 521);
        codes.put("PREVIOUS_CANDIDATE", 257);

        codes.put("PRINTSCREEN", 154);
        codes.put("PRINT_SCREEN", 154);
        codes.put("PRTSC", 154);

        codes.put("PROPS", 65482);
        codes.put("Q", 81);
        codes.put("QUOTE", 222);
        codes.put("QUOTEDBL", 152);
        codes.put("R", 82);
        codes.put("RIGHT", 39);
        codes.put("RIGHT_PARENTHESIS", 522);
        codes.put("ROMAN_CHARACTERS", 245);
        codes.put("S", 83);

        codes.put("SCROLLLOCK", 145);
        codes.put("SCROLL_LOCK", 145);
        codes.put("SCRLK", 145);

        codes.put("SEMICOLON", 59);
        codes.put("SEPARATER", 108);
        codes.put("SEPARATOR", 108);
        codes.put("SHIFT", 16);
        codes.put("SLASH", 47);
        codes.put("SPACE", 32);
        codes.put("STOP", 65480);
        codes.put("SUBTRACT", 109);
        codes.put("T", 84);
        codes.put("TAB", 9);
        codes.put("U", 85);
        codes.put("UNDEFINED", 0);
        codes.put("UNDERSCORE", 523);
        codes.put("UNDO", 65483);
        codes.put("UP", 38);
        codes.put("V", 86);
        codes.put("W", 87);
        codes.put("WINDOWS", 524);
        codes.put("X", 88);
        codes.put("Y", 89);
        codes.put("Z", 90);

        Integer code = codes.get(key);

        if (code == null) {
            throw new ControlledException("Invalid key \"" + key + "\".");
        }

        return code;

    }

}
