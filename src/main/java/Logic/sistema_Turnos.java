package Logic;

import java.util.ArrayDeque;

public class sistema_Turnos {

    public void Turno(String nombre,int identificacion, String descripcion){
        switch (1) {
            case 1:
                ArrayDeque<consult_General> cons_Gene = new ArrayDeque<>();
                cons_Gene.add(new consult_General(new cliente(nombre,identificacion),cons_Gene.size(),descripcion));
                break;
            case 2:
                break;
            case 3 :
                break;
            default:
        }

    }
}
