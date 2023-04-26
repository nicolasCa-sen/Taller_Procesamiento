package Logic;

import java.util.List;

public class Rows {

    private RowSystem rowSystem;

    public Rows() {
        this.rowSystem = new RowSystem();
    }

    public void addRow(Client client, String consultationType) {
        rowSystem.addClient(client, consultationType);
    }

    public List<Consultation> listGeneral() {
        return rowSystem.getConsultationsMap().get("Consulta médica general");
    }

    public List<Consultation> listCardio() {
        return rowSystem.getConsultationsMap().get("Consulta con especialista en cardiología");
    }

    public List<Consultation> listTraum() {
        return rowSystem.getConsultationsMap().get("Consulta con especialista en traumatología");
    }

    public List<Consultation> listOftamo() {
        return rowSystem.getConsultationsMap().get("Consulta con especialista en oftalmología");
    }

    public List<Consultation> listDermato() {
        return rowSystem.getConsultationsMap().get("Consulta con especialista en dermatología");
    }

}
