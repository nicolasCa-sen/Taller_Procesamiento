package Logic;

import java.time.LocalTime;
import java.util.ArrayList;

public class Modules {

    ShiftSystem st;
    public Modules() {
        st = new ShiftSystem();
    }


    public void addRow(Client client, int id){

        st.turno(client,id);

    }

    public void listGeneral(){
        System.out.println(generalcustomers(st.getCons_Gene()));
    }
    public void listCardio(){
        System.out.println(generalcustomers(st.getCons_Cardi()));
    }
    public void listTraum(){
        System.out.println(generalcustomers(st.getCons_Trauma()));
    }
    public void listOftamo(){
        System.out.println(generalcustomers(st.getCons_Oftam()));
    }
    public void listDermato(){
        System.out.println(generalcustomers(st.getCons_Dermat()));
    }

    private String generalcustomers(ArrayList<Consultation> array){
        String data = "";
        for (int i = 0; i <array.size() ; i++) {
            if (!array.get(i).isAtencion()){
                data = data + "\n"+"\n"+"el turno es "+array.get(i).getTurno()+"\n"+
                        "nombre  : " + array.get(i).getCliente().getName();
            }
        }
        return data;
    }

    public void  Modul1(){
        processes pc = new processes(st.getCons_Gene(),10);
    }
    public void  Modul2(){
        processes pc = new processes(st.getCons_Gene(),10);

    }
    public void  Modul3(){
        processes pc = new processes(st.getCons_Cardi(),15);
    }
    public void  Modul4(){
        processes pc = new processes(st.getCons_Trauma(),25);
    }
    public void  Modul5(){
        processes pc = new processes(st.getCons_Oftam(),30);
    }
    public void  Modul6(){
        processes pc = new processes(st.getCons_Dermat(),60);
    }

}
