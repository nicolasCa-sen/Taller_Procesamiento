package Logic;

public class consult {

    private   String  nombre ;

    private cliente cliente;
    private int turno;
    private boolean atencion;

    public consult(String nombre, Logic.cliente cliente, int turno) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.turno = turno;
        this.atencion = false;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public boolean isAtencion() {
        return atencion;
    }

    public void setAtencion(boolean atencion) {
        this.atencion = atencion;
    }
}
