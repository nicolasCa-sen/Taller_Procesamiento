package Presentation;

import Control.Control;
import GUI.Login;
import Logic.Client;
import Logic.Modules;

public class Main {

    public static void main(String[] args) {
        Login principal = new Login();
        new Control(principal).launchWindow();

        Modules modules = new Modules();
        modules.addRow(new Client("Numar", 123), "Consulta médica general");
        modules.addRow(new Client("Juan", 564), "Consulta con especialista en cardiología");
        modules.addRow(new Client("Pedro", 412), "Consulta con especialista en traumatología");
        modules.listGeneral();
        modules.listCardio();
        modules.listTraum();
        modules.Modul1();
        modules.Modul2();
        modules.Modul3();
    }
}
