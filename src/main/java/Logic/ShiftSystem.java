package Logic;

import java.util.ArrayList;


public class ShiftSystem {

    private ArrayList<Consultation> cons_Gene = new ArrayList<>();
    private ArrayList<Consultation> cons_Cardi = new ArrayList<>();
    private ArrayList<Consultation> cons_Trauma = new ArrayList<>();
    private ArrayList<Consultation> cons_Oftam = new ArrayList<>();
    private ArrayList<Consultation> cons_Dermat = new ArrayList<>();

    public ShiftSystem() {
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
                cons_Gene.add(new Consultation(t_Consult,new Client(nombre,identificacion),cons_Gene.size()));
                break;
            case 2:
                cons_Cardi.add(new Consultation(t_Consult,new Client(nombre,identificacion),cons_Cardi.size()));
                break;
            case 3 :
                cons_Trauma.add(new Consultation(t_Consult,new Client(nombre,identificacion),cons_Trauma.size()));
                break;
            case 4 :
                cons_Oftam.add(new Consultation(t_Consult,new Client(nombre,identificacion),cons_Oftam.size()));
                break;
            case 5 :
                cons_Dermat.add(new Consultation(t_Consult,new Client(nombre,identificacion),cons_Dermat.size()));
                break;
            default:
        }


    }
    public ArrayList<Consultation> getCons_Gene() {
        return cons_Gene;
    }

    public ArrayList<Consultation> getCons_Cardi() {
        return cons_Cardi;
    }

    public ArrayList<Consultation> getCons_Trauma() {
        return cons_Trauma;
    }

    public ArrayList<Consultation> getCons_Oftam() {
        return cons_Oftam;
    }

    public ArrayList<Consultation> getCons_Dermat() {
        return cons_Dermat;
    }
}
