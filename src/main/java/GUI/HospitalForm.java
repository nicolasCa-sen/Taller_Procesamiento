package GUI;

import Control.Control;
import Logic.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Optional;

public class HospitalForm extends JFrame {

    // Atributos de la clase
    private static final int FORM_WIDTH = 1000;
    private static final int FORM_HEIGHT = 480;

    private final int BUTTON_WIDTH = 500;
    private final int BUTTON_HEIGHT = 40;
    private final int BUTTON_FONT_SIZE = 20;

    private final Color PANEL_COLOR = new Color(188, 227, 255);
    private final Color BUTTON_COLOR = new Color(77, 205, 255);

    private final String LOGIN_BUTTON_TEXT = "Ingresar";
    private final String BACK_BUTTON_TEXT = "Regresar";

    private final String[] PROCEDURE_TYPES = {
            "Consulta médica general",
            "Consulta con especialista en cardiología",
            "Consulta con especialista en traumatología",
            "Consulta con especialista en oftalmología",
            "Consulta con especialista en dermatología"
    };

    private final String[] MODULES = {
            "Módulo 1",
            "Módulo 2",
            "Módulo especialista en cardiología",
            "Módulo especialista en traumatología",
            "Módulo especialista en oftalmología",
            "Módulo especialista en dermatología"
    };

    private final String[] buttonTexts = {
            "Consultorio 1",
            "Consultorio 2",
            "Consulta con especialista en cardiología",
            "Consulta con especialista en traumatología",
            "Consulta con especialista en oftalmología",
            "Consulta con especialista en dermatología"
    };

    private final String[] textos = {"00:10", "00:10", "00:15", "00:25", "00:30", "01:00"};
    private final ActionListener[] moduleButtonActions = new ActionListener[6];

    private Login principal;
    private Control control = new Control(principal);

    // Componentes para el formulario de inicio sesión
    private JPanel panelForm = new JPanel();
    private JPanel panelLoginButton = new JPanel();
    private JPanel panelClientInformation = new JPanel();
    private JTextField nameField = new JTextField(20);
    private JTextField identificationField = new JTextField(20);
    private JButton loginButton = new JButton(LOGIN_BUTTON_TEXT);
    private JButton backButton = new JButton(BACK_BUTTON_TEXT);
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
        // Configuración del panel principal
        configPanel(panelForm, new FlowLayout());

        // Configuración del panel de información del cliente
        configPanel(panelClientInformation, new GridLayout(2, 2, 10, 5));
        nameField.setEditable(false);
        identificationField.setEditable(false);

        // Se busca el cliente en el sistema y se setean los datos del cliente
        Optional<Client> clientOptional = control.findClientById(principal.getClient().getId());
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            nameField.setHorizontalAlignment(SwingConstants.CENTER);
            nameField.setText("Nombre - " + client.getName());
            identificationField.setHorizontalAlignment(SwingConstants.CENTER);
            identificationField.setText("Identificación - " + String.valueOf(client.getId()));
        }

        // Configuración de los comboBoxes
        moduleComboBox.removeAllItems();
        addProcedureComboBoxListener();

        // Se agregan los componentes al panel de información del cliente
        panelClientInformation.add(nameField);
        panelClientInformation.add(procedureComboBox);
        panelClientInformation.add(identificationField);
        panelClientInformation.add(moduleComboBox);

        // Configuración del panel de botones de inicio de sesión
        configPanel(panelLoginButton, new FlowLayout(FlowLayout.RIGHT));

        // Configuración del botón de ingreso
        configButton(loginButton, 125, 55, 24);
        addButtonToPanel(loginButton, panelLoginButton, e -> {
            // TODO: Código para ingresar un cliente a la fila
        });

        // Configuración del botón de regresar
        configButton(backButton, 125, 55, 24);
        addButtonToPanel(backButton, panelLoginButton, e -> {
            new Login().setVisible(true);
            dispose();
        });

        // Se agregan los paneles al panel principal
        panelForm.add(panelClientInformation);
        panelForm.add(panelLoginButton);
        this.add(panelForm, BorderLayout.NORTH);
    }


    private void addProcedureComboBoxListener() {
        procedureComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int procedureIndex = procedureComboBox.getSelectedIndex();

                // Actualizar los valores del JComboBox de módulos según las condiciones
                if (procedureIndex == 0) {
                    moduleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Módulo 1", "Módulo 2"}));
                } else if (procedureIndex == 1) {
                    moduleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Módulo especialista en cardiología"}));
                } else if (procedureIndex == 2) {
                    moduleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Módulo especialista en traumatología"}));
                } else if (procedureIndex == 3) {
                    moduleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Módulo especialista en oftalmología"}));
                } else {
                    moduleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Módulo especialista en dermatología"}));
                }
            }
        });
    }


    public void setupModulesPanel() {
        configPanel(panelModules, new GridLayout(6, 1));
        char letter = 'A';

        // Crear y configurar los campos de texto de cada turno
        for (int i = 0; i < shiftFields.length; i++) {
            shiftFields[i][0] = new JTextField(letter + " - 00", 1);
            shiftFields[i][1] = new JTextField(letter + " - 01", 1);
            shiftFields[i][2] = new JTextField(textos[i], 1);
            shiftFields[i][0].setHorizontalAlignment(SwingConstants.CENTER);
            shiftFields[i][1].setHorizontalAlignment(SwingConstants.CENTER);
            shiftFields[i][2].setHorizontalAlignment(SwingConstants.CENTER);
            shiftFields[i][0].setEditable(false);
            shiftFields[i][1].setEditable(false);
            shiftFields[i][2].setEditable(false);
            letter++;
        }

        // Crear y configurar los paneles de cada módulo
        for (int i = 0; i < 6; i++) {
            moduleButtons[i] = new JButton(buttonTexts[i]);
            panelModuleButtons[i] = new JPanel();
            panelShifts[i] = new JPanel();
            configPanel(panelModuleButtons[i], new FlowLayout(FlowLayout.CENTER, 10, 5));
            configButton(moduleButtons[i], BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_FONT_SIZE);
            initButtonsSurgery();
            addButtonToPanel(moduleButtons[i], panelModuleButtons[i], moduleButtonActions[i]);
            configPanel(panelShifts[i], new GridLayout(2, 3, 5, 0));
            panelShifts[i].add(new JLabel("Turno actual:"));
            panelShifts[i].add(shiftFields[i][0]);
            panelShifts[i].add(shiftFields[i][2]);
            panelShifts[i].add(new JLabel("Turno siguiente:"));
            panelShifts[i].add(shiftFields[i][1]);
            panelShifts[i].add(new JLabel("Tiempo restante:"));
            panelModuleButtons[i].add(panelShifts[i]);
            panelModules.add(panelModuleButtons[i]);
        }

        this.add(panelModules, BorderLayout.CENTER);
    }

    public void initButtonsSurgery() {
        for (int i = 0; i < 6; i++) {
            int index = i;
            moduleButtonActions[i] = e -> openRowCustomer(index);
        }
    }

    private void openRowCustomer(int index) {
        new RowCustomer(principal, index).setVisible(true);
        dispose();
    }

    private void configPanel(JPanel panel, LayoutManager layout) {
        panel.setLayout(layout);
        panel.setBackground(PANEL_COLOR);
    }

    private void configButton(JButton button, int width, int height, int fontSize) {
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setPreferredSize(new Dimension(width, height));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void addButtonToPanel(JButton button, JPanel panel, ActionListener listener) {
        button.addActionListener(listener);
        panel.add(button);
    }

}
