package Logic;

import Logic.Client;
import Logic.Consultation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class processes implements Runnable{


    private ArrayList<Consultation> array;

    private int seg;

    public processes(ArrayList<Consultation> array, int seg) {
        this.array = array;
        this.seg = seg;
    }

    @Override
    public void run(){
        for (int i = 0; i <array.size() ; i++) {
            if (!array.get(i).isAtencion()){
                //System.out.println(i);
                array.get(i).setAtencion(true);

                System.out.println(array.get(i).getCliente().getName());
                LocalTime timeNow = LocalTime.now();
                String timeChain = timeNow.getHour() + ":" + timeNow.getMinute() + ":" + timeNow.getSecond();
                System.out.println(timeChain);
                //System.out.println(client.getId());
                try{
                    Thread.sleep(seg*1000);

                }catch (InterruptedException e){
                    throw  new RuntimeException(e);
                }

            }
        }

    }
}
