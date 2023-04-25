package Presentation;

import Control.LoginControl;
import GUI.Login;
import Logic.Client;
import Logic.ConsultationProcess;
import Logic.Modules;

public class Main {

    public static void main(String[] args) {
        Login principal = new Login();
        new LoginControl(principal).launchWindow();

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
