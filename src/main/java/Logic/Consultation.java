package Logic;

public class Consultation {

    private   String  nombre ;

    private Client Client;
    private int turno;
    private boolean atencion;

    public Consultation(String nombre, Client Client, int turno) {
        this.nombre = nombre;
        this.Client = Client;
        this.turno = turno;
        this.atencion = false;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTurno() {
        return turno;
    }

    public Client getCliente() {
        return Client;
    }

    public void setCliente(Client Client) {
        this.Client = Client;
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
