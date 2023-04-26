package Logic;

import javax.swing.*;

public class CountDownThread extends Thread {

    private JTextField textField;
    private boolean isThreadRunning = false;

    public CountDownThread(JTextField textField) {
        this.textField = textField;
    }

    public void run() {
        int timeInit = Integer.parseInt(textField.getText().trim());
        int time = Integer.parseInt(textField.getText().trim());
        while (time > 0) {
            time--;
            textField.setText(String.format("%02d", time));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isThreadRunning = false;
                textField.setText(Integer.toString(timeInit));
            }
        }
    }

    public void addThreadCompleteListener(final ThreadCompleteListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listener.notifyOfThreadComplete(CountDownThread.this);
            }
        }).start();
    }
}
