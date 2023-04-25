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

    public void turno( Client client, Integer t_Consult){



        switch (t_Consult) {
            case 0:
                cons_Gene.add(new Consultation(client,cons_Gene.size()+1));
                break;
            case 1:
                cons_Cardi.add(new Consultation(client,cons_Cardi.size()+1));
                break;
            case 2 :
                cons_Trauma.add(new Consultation(client,cons_Trauma.size()+1));
                break;
            case 3 :
                cons_Oftam.add(new Consultation(client,cons_Oftam.size()+1));
                break;
            case 4 :
                cons_Dermat.add(new Consultation(client,cons_Dermat.size()+1));
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
