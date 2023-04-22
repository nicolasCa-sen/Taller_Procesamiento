package Logic;

public class consult_General {

    private static  String  nombre = "Consulta General";

    private cliente cliente;
    private int turno;
    private String descripcion;

    public consult_General(Logic.cliente cliente, int turno, String descripcion) {
        this.cliente = cliente;
        this.turno = turno;
        this.descripcion = descripcion;
    }

    public int getTurno() {
        return turno;
    }

    public Logic.cliente getCliente() {
        return cliente;
    }

    public void setCliente(Logic.cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
