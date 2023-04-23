package Logic;

import java.util.ArrayDeque;
import java.util.ArrayList;


public class sistema_Turnos {

    private ArrayList<consult> cons_Gene = new ArrayList<>();
    private ArrayList<consult> cons_Cardi = new ArrayList<>();
    private ArrayList<consult> cons_Trauma = new ArrayList<>();
    private ArrayList<consult> cons_Oftam = new ArrayList<>();
    private ArrayList<consult> cons_Dermat = new ArrayList<>();

    public sistema_Turnos() {
    }

    public void turno(String nombre, int identificacion, String t_Consult){
        int form = 0;

        if (t_Consult.equals("Consulta general")) {
            form = 1;
        }
        if (t_Consult.equals("Consulta cardiologia")) {
            form = 2;
        }
        if (t_Consult.equals("Consulta traumatologia")) {
            form = 3;
        }
        if (t_Consult.equals("Consulta oftalmologia")) {
            form = 4;
        }
        if (t_Consult.equals("Consulta dermatologia")) {
            form = 5;
        }
        switch (form) {
            case 1:
                cons_Gene.add(new consult(t_Consult,new cliente(nombre,identificacion),cons_Gene.size()));
                break;
            case 2:
                cons_Cardi.add(new consult(t_Consult,new cliente(nombre,identificacion),cons_Cardi.size()));
                break;
            case 3 :
                cons_Trauma.add(new consult(t_Consult,new cliente(nombre,identificacion),cons_Trauma.size()));
                break;
            case 4 :
                cons_Oftam.add(new consult(t_Consult,new cliente(nombre,identificacion),cons_Oftam.size()));
                break;
            case 5 :
                cons_Dermat.add(new consult(t_Consult,new cliente(nombre,identificacion),cons_Dermat.size()));
                break;
            default:
        }


    }
    public ArrayList<consult> getCons_Gene() {
        return cons_Gene;
    }

    public ArrayList<consult> getCons_Cardi() {
        return cons_Cardi;
    }

    public ArrayList<consult> getCons_Trauma() {
        return cons_Trauma;
    }

    public ArrayList<consult> getCons_Oftam() {
        return cons_Oftam;
    }

    public ArrayList<consult> getCons_Dermat() {
        return cons_Dermat;
    }
}
