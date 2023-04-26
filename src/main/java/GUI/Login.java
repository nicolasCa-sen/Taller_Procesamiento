package GUI;

import Control.Control;
import Logic.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

    private Control control = new Control(this);
    private Client client = new Client("", 0);

    private JPanel panelPrincipal = new JPanel();
    private JTextField txtIdentification = new JTextField("Identificación...");
    private JButton btnLogin = new JButton("Ingresar");

    public Login() {
        super("Login");
        setSize(426, 240);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configureMainPanel();
        configureLoginButton();

        add(panelPrincipal);
        pack();
        setTitle("Inicio de Sesión");
    }

    private void configureMainPanel() {
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 240, 240));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel welcomeLabel = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        panelPrincipal.add(welcomeLabel, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel(new GridLayout(2, 1, 10, 10));
        panelCenter.setBackground(new Color(240, 240, 240));

        txtIdentification.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtIdentification.setBackground(Color.WHITE);
        txtIdentification.setForeground(Color.BLACK);
        txtIdentification.setFont(new Font("Arial", Font.PLAIN, 16));
        txtIdentification.setPreferredSize(new Dimension(300, 40));

        panelCenter.add(txtIdentification);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        panelPrincipal.add(panelCenter, BorderLayout.CENTER);

        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelSouth.setBackground(new Color(240, 240, 240));

        panelPrincipal.add(panelSouth, BorderLayout.SOUTH);
    }

    private void configureLoginButton() {
        btnLogin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        btnLogin.setBackground(new Color(192, 192, 192));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setPreferredSize(new Dimension(150, 40));
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(128, 128, 128));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(new Color(192, 192, 192));
            }
        });

        addButtonToPanel(btnLogin, (JPanel) panelPrincipal.getComponent(2), e -> {
            if (control.idExists(txtIdentification.getText())) {
                client.setId(Integer.parseInt(txtIdentification.getText()));
                client.setName(control.findClientById(Integer.parseInt(txtIdentification.getText())).get().getName());
                new HospitalForm(this, control.getModules()).setVisible(true);
                dispose();
            }
        });
    }

    private void addButtonToPanel(JButton button, JPanel panel, ActionListener listener) {
        button.addActionListener(listener);
        panel.add(button);
    }

    public Client getClient() {
        return client;
    }
}
