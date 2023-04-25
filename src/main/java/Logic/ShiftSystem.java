package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShiftSystem {

    private Map<String, List<Consultation>> consultationsMap = new HashMap<>();

    public ShiftSystem() {
        consultationsMap.put("Consulta médica general", new ArrayList<>());
        consultationsMap.put("Consulta con especialista en cardiología", new ArrayList<>());
        consultationsMap.put("Consulta con especialista en traumatología", new ArrayList<>());
        consultationsMap.put("Consulta con especialista en oftalmología", new ArrayList<>());
        consultationsMap.put("Consulta con especialista en dermatología", new ArrayList<>());
    }

    public void addClient(Client client, String shiftConsultation) {
        List<Consultation> consultations = consultationsMap.get(shiftConsultation);
        if (consultations != null) {
            consultations.add(new Consultation(client, consultations.size() + 1));
        }
    }

    public Map<String, List<Consultation>> getConsultationsMap() {
        return consultationsMap;
    }
}
