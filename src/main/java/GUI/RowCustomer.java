package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RowCustomer extends JFrame {

    private static final int FORM_WIDTH = 854;
    private static final int FORM_HEIGHT = 480;
    private final Color PANEL_COLOR = new Color(188, 227, 255);
    private final Color BUTTON_COLOR = new Color(77, 205, 255);

    private JPanel infomationPanel = new JPanel();
    private JPanel tablePanel = new JPanel();

    private JTextField surgeryField = new JTextField();

    private JButton backButton = new JButton("Regresar");

    public RowCustomer(Login principal) {
        super("Fila Clientes");
        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(PANEL_COLOR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        configPanel(tablePanel, new FlowLayout());
    }

    private void configPanel(JPanel panel, LayoutManager layout) {
        panel.setLayout(layout);
        panel.setBackground(PANEL_COLOR);
    }

    private void configButton(JButton button, int widht, int height, int sizeFont) {
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, sizeFont));
        button.setPreferredSize(new Dimension(widht, height));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void addButtonToPanel(JButton button, JPanel panel, ActionListener listener) {
        button.addActionListener(listener);
        panel.add(button);
    }
}
