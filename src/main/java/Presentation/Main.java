package Presentation;

import GUI.Login;
import Control.Control;
import Control.processes;
import Logic.ShiftSystem;

public class Main {

    public static void main(String[] args) {
        /*
        Login principal = new Login();
        new Control(principal).launchWindow();
         */

        ShiftSystem st = new ShiftSystem();

        st.turno("juan",428956,"Consulta general");
        st.turno("pepe",428956,"Consulta cardiologia");
        st.turno("carlos",428956,"Consulta traumatologia");
        st.turno("julio",428956,"Consulta oftalmologia");
        st.turno("manuel",428956,"Consulta dermatologia");
        st.turno("julian",428956,"Consulta general");
        st.turno("pedro",428956,"Consulta general");
        st.turno("santiago",428956,"Consulta general");
        st.turno("ana",428956,"Consulta general");
        st.turno("juan",428956,"Consulta general");
        st.turno("nico",428956,"Consulta general");
        st.turno("numar",428956,"Consulta general");
        st.turno("juan",428956,"Consulta general");
        st.turno("juan",428956,"Consulta general");

        System.out.println(st.getCons_Gene().size());
        System.out.println("============================================");
        System.out.println("consulta general");

        processes pc = new processes();
        pc.recorrido(st.getCons_Gene());
        new Thread(pc).start();

    }
}
