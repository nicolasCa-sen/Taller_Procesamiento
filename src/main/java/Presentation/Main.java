package Presentation;

import Control.Control;
import GUI.Login;

public class Main {

    public static void main(String[] args) {
        Login principal = new Login();
        new Control(principal).launchWindow();
    }
}
