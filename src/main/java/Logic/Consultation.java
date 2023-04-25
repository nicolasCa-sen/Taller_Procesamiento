package Logic;

public class Consultation {

    private Client client;
    private int shift;
    private boolean status;

    public Consultation(Client Client, int turno) {
        this.client = Client;
        this.shift = turno;
        this.status = false;
    }

    public int getShift() {
        return shift;
    }

    public Client getCliente() {
        return client;
    }

    public void setCliente(Client Client) {
        this.client = Client;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
