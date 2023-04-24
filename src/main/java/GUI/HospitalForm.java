package GUI;

import Control.Control;
import Logic.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Optional;

public class HospitalForm extends JFrame {

    // Atributos de la clase
    private static final int FORM_WIDTH = 854;
    private static final int FORM_HEIGHT = 480;

    private final Color PANEL_COLOR  = new Color(188, 227, 255);
    private final Color BUTTON_COLOR  = new Color(77, 205, 255);

    private final int BUTTON_WIDTH  = 500;
    private final int BUTTON_HEIGHT  = 40;
    private final int BUTTON_FONT_SIZE = 20;

    private final String LOGIN_BUTTON_TEXT = "Ingresar";
    private final String[] PROCEDURE_TYPES  = {"Consulta médica general", "Consulta con especialista en cardiología",
            "Consulta con especialista en traumatología", "Consulta con especialista en oftalmología",
            "Consulta con especialista en dermatología"};
    private final String[] MODULES  = {"Módulo 1", "Módulo 2", "Módulo especialista en cardiología",
            "Módulo especialista en traumatología", "Módulo especialista en oftalmología",
            "Módulo especialista en dermatología"};

    private Login principal;
    private Control control = new Control(principal);

    // Componentes para el formulario de inicio sesión
    private JPanel panelForm = new JPanel();
    private JPanel panelLoginButton = new JPanel();
    private JPanel panelClientInformation = new JPanel();

    private JTextField nameField = new JTextField(20);
    private JTextField identificationField = new JTextField(20);
    private JButton loginButton = new JButton(LOGIN_BUTTON_TEXT);

    private JComboBox<String> procedureComboBox = new JComboBox<>(PROCEDURE_TYPES);
    private JComboBox<String> moduleComboBox = new JComboBox<>(MODULES);

    // Componentes para la información de los módulos y sus respectivos turnos
    private JPanel panelModules = new JPanel();
    private JPanel[] panelModuleButtons = new JPanel[MODULES.length];
    private JPanel[] panelShifts = new JPanel[MODULES.length];

    private JTextField[][] shiftFields = new JTextField[MODULES.length][3];
    private JButton[] moduleButtons = new JButton[MODULES.length];


    public HospitalForm(Login principal) {
        super("Hospital");
        this.principal = principal;
        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(PANEL_COLOR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        launchPanels();
    }

    public void launchPanels() {
        setupFormPanel();
        setupModulesPanel();
    }

    public void setupFormPanel() {
        configPanel(panelForm, new FlowLayout());

        configPanel(panelClientInformation, new GridLayout(2, 2, 10, 5));
        nameField.setEditable(false);
        identificationField.setEditable(false);

        Optional<Client> clientOptional = control.findClientById(principal.getClient().getId());

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            nameField.setHorizontalAlignment(SwingConstants.CENTER);
            nameField.setText("Nombre - " + client.getName());
            identificationField.setHorizontalAlignment(SwingConstants.CENTER);
            identificationField.setText("Identificación - " + String.valueOf(client.getId()));
        }

        panelClientInformation.add(nameField);
        panelClientInformation.add(procedureComboBox);
        panelClientInformation.add(identificationField);
        panelClientInformation.add(moduleComboBox);

        configPanel(panelLoginButton, new FlowLayout(FlowLayout.RIGHT));
        configButton(loginButton, 250, 55, 24);
        addButtonToPanel(loginButton, panelLoginButton, e -> {
        });

        panelForm.add(panelClientInformation);
        panelForm.add(panelLoginButton);
        this.add(panelForm, BorderLayout.NORTH);
    }


    public void setupModulesPanel() {
        configPanel(panelModules, new GridLayout(6, 1));
        char letter = 'A';
        for (int i = 0; i < 6; i++) {

            panelModuleButtons[i] = new JPanel();
            panelShifts[i] = new JPanel();

            shiftFields[i][0] = new JTextField(letter + " - 00", 1);
            shiftFields[i][1] = new JTextField(letter + " - 01", 1);
            shiftFields[i][2] = new JTextField("00:10", 1);

            moduleButtons[i] = new JButton();

            configPanel(panelModuleButtons[i], new FlowLayout(FlowLayout.CENTER, 10, 5));
            configButton(moduleButtons[i], BUTTON_WIDTH,  BUTTON_HEIGHT, BUTTON_FONT_SIZE);
            addButtonToPanel(moduleButtons[i], panelModuleButtons[i], e -> {

            });

            configPanel(panelShifts[i], new GridLayout(2, 3, 5, 0));
            shiftFields[i][0].setHorizontalAlignment(SwingConstants.CENTER);
            shiftFields[i][1].setHorizontalAlignment(SwingConstants.CENTER);
            shiftFields[i][2].setHorizontalAlignment(SwingConstants.CENTER);
            shiftFields[i][0].setEditable(false);
            shiftFields[i][1].setEditable(false);
            shiftFields[i][2].setEditable(false);

            panelShifts[i].add(new JLabel("Turno actual:"));
            panelShifts[i].add(shiftFields[i][0]);
            panelShifts[i].add(shiftFields[i][2]);
            panelShifts[i].add(new JLabel("Turno siguiente:"));
            panelShifts[i].add(shiftFields[i][1]);
            panelShifts[i].add(new JLabel("Tiempo restante"));
            panelModuleButtons[i].add(panelShifts[i]);
            panelModules.add(panelModuleButtons[i]);

            letter++;
        }
        moduleButtons[0].setText("Consultorio 1");
        moduleButtons[1].setText("Consultorio 2");
        moduleButtons[2].setText("Consulta con especialista en cardiología");
        moduleButtons[3].setText("Consulta con especialista en traumatología");
        moduleButtons[4].setText("Consulta con especialista en oftalmología");
        moduleButtons[5].setText("Consulta con especialista en dermatología");

        this.add(panelModules, BorderLayout.CENTER);
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
