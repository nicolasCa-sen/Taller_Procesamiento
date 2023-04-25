package Logic;

public class Consultation {

    private Client Client;
    private int turno;
    private boolean atencion;

    public Consultation(Client Client, int turno) {
        this.Client = Client;
        this.turno = turno;
        this.atencion = false;
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


    public boolean isAtencion() {
        return atencion;
    }

    public void setAtencion(boolean atencion) {
        this.atencion = atencion;
    }
}
