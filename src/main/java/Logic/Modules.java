package Logic;

import java.util.List;

public class Modules {

    private ShiftSystem shiftSystem;

    public Modules() {
        this.shiftSystem = new ShiftSystem();
    }

    public void addRow(Client client, String consultationType) {
        shiftSystem.addClient(client, consultationType);
    }

    public void listGeneral() {
        List<Consultation> generalConsultations = shiftSystem.getConsultationsMap().get("Consulta médica general");
        System.out.println(getGeneralCustomers(generalConsultations));
    }

    public void listCardio() {
        List<Consultation> cardioConsultations = shiftSystem.getConsultationsMap().get("Consulta con especialista en cardiología");
        System.out.println(getGeneralCustomers(cardioConsultations));
    }

    public void listTraum() {
        List<Consultation> traumaConsultations = shiftSystem.getConsultationsMap().get("Consulta con especialista en traumatología");
        System.out.println(getGeneralCustomers(traumaConsultations));
    }

    public void listOftamo() {
        List<Consultation> oftamConsultations = shiftSystem.getConsultationsMap().get("Consulta con especialista en oftalmología");
        System.out.println(getGeneralCustomers(oftamConsultations));
    }

    public void listDermato() {
        List<Consultation> dermatConsultations = shiftSystem.getConsultationsMap().get("Consulta con especialista en dermatología");
        System.out.println(getGeneralCustomers(dermatConsultations));
    }

    private String getGeneralCustomers(List<Consultation> consultations) {
        StringBuilder sb = new StringBuilder();
        for (Consultation consultation : consultations) {
            if (!consultation.isStatus()) {
                sb.append("\n\nEl turno es ").append(consultation.getShift())
                        .append("\nNombre: ").append(consultation.getCliente().getName());
            }
        }
        return sb.toString();
    }

    public void iniciarModulo1() {
        List<Consultation> consultas = shiftSystem.getConsultationsMap().get("Consulta médica general");
        ConsultationProcess proceso = new ConsultationProcess(consultas, 10);
        Thread hilo = new Thread(proceso);
        hilo.start();
    }

    public void Modul1() {
        List<Consultation> consultations = shiftSystem.getConsultationsMap().get("Consulta médica general");
        ConsultationProcess process = new ConsultationProcess(consultations, 10);
        Thread thread = new Thread(process);
        thread.start();
    }

    public void Modul2() {
        List<Consultation> consultations = shiftSystem.getConsultationsMap().get("Consulta con especialista en cardiología");
        ConsultationProcess process = new ConsultationProcess(consultations, 10);
        Thread thread = new Thread(process);
        thread.start();
    }

    public void Modul3() {
        List<Consultation> consultations = shiftSystem.getConsultationsMap().get("Consulta con especialista en traumatología");
        ConsultationProcess process = new ConsultationProcess(consultations, 15);
        Thread thread = new Thread(process);
        thread.start();
    }

    public void Modul4() {
        List<Consultation> consultations = shiftSystem.getConsultationsMap().get("Consulta con especialista en oftalmología");
        ConsultationProcess process = new ConsultationProcess(consultations, 25);
        Thread thread = new Thread(process);
        thread.start();
    }

    public void Modul5() {
        List<Consultation> consultations = shiftSystem.getConsultationsMap().get("Consulta con especialista en dermatología");
        ConsultationProcess process = new ConsultationProcess(consultations, 30);
        Thread thread = new Thread(process);
        thread.start();
    }

}
