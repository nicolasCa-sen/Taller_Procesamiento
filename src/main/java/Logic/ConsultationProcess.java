package Logic;

import java.util.List;

public class ConsultationProcess implements Runnable {

    private List<Consultation> consultationArray;
    private int secondsToWait;

    public ConsultationProcess(List<Consultation> consultationArray, int secondsToWait) {
        this.consultationArray = consultationArray;
        this.secondsToWait = secondsToWait;
    }

    @Override
    public void run() {
        synchronized (consultationArray) {
            for (Consultation currentConsultation : consultationArray) {
                if (!currentConsultation.isStatus()) {
                    currentConsultation.setStatus(true);
                    System.out.println(currentConsultation.getShift());
                    try {
                        java.lang.Thread.sleep(secondsToWait * 1000);
                    } catch (InterruptedException e) {
                        System.err.println("Error al esperar " + secondsToWait + " segundos: " + e.getMessage());
                        java.lang.Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
