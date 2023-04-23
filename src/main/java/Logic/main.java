package Logic;

public class main {

    public static void main(String[] args) {
        sistema_Turnos st = new sistema_Turnos();

        st.turno("juan",428956,"Consulta general");
        st.turno("pepe",428956,"Consulta cardiologia");
        st.turno("carlos",428956,"Consulta traumatologia");
        st.turno("julio",428956,"Consulta oftalmologia");
        st.turno("manuel",428956,"Consulta dermatologia");
        st.turno("julian",428956,"Consulta general");
        st.turno("pedro",428956,"Consulta general");
        st.turno("santiago",428956,"Consulta general");
        st.turno("ana",428956,"Consulta general");

        System.out.println(st.getCons_Gene().size());
        System.out.println(st.getCons_Cardi().size());
        System.out.println("============================================");
        System.out.println("consulta general");
        for (int i = 0; i <st.getCons_Gene().size() ; i++) {
            System.out.println(st.getCons_Gene().get(i).getCliente().getNombre());
        }
        System.out.println("============================================");
        for (int i = 0; i <st.getCons_Gene().size() ; i++) {
            st.getCons_Gene().get(i).setAtencion(true);
        }
        st.turno("juan",428956,"Consulta general");
        st.turno("nico",428956,"Consulta general");
        st.turno("numar",428956,"Consulta general");
        st.turno("juan",428956,"Consulta general");
        st.turno("juan",428956,"Consulta general");
        System.out.println("============================================");
        System.out.println("consulta general");
        for (int i = 0; i <st.getCons_Gene().size() ; i++) {
            if (!st.getCons_Gene().get(i).isAtencion()){
                System.out.println(st.getCons_Gene().get(i).getCliente().getNombre());
            }

        }








    }
}
