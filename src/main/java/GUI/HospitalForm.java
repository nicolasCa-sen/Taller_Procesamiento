package GUI;

import Control.Control;
import Logic.Client;
import Logic.CountDownThread;
import Logic.Rows;
import Logic.ThreadCompleteListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HospitalForm extends JFrame {

    private static final int FORM_WIDTH = 1000;
    private static final int FORM_HEIGHT = 480;

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

    private final int BUTTON_WIDTH = 500;
    private final int BUTTON_HEIGHT = 40;
    private final int BUTTON_FONT_SIZE = 20;

    private boolean isThreadRunning = false;
    private CountDownThread countDownThread;
    private final int shiftsText[] = {0, 0, 0, 0, 0, 0};
    private final String[] timeText = {"10", "10", "15", "25", "30", "45"};
    private final String[] textos = {"00:", "00:", "00:", "00:", "00:", "00:"};
    private final ActionListener[] moduleButtonActions = new ActionListener[6];
    private Map<Integer, Integer> shiftsTextMap = new HashMap<>();

    private Login principal;
    private Rows rows;
    private Control control = new Control(principal);

    private JPanel panelForm = new JPanel();
    private JTextField nameField = new JTextField(20);
    private JTextField identificationField = new JTextField(20);
    private JComboBox<String> procedureComboBox = new JComboBox<>(PROCEDURE_TYPES);
    private JComboBox<String> moduleComboBox = new JComboBox<>(MODULES);
    private JButton loginButton = new JButton(LOGIN_BUTTON_TEXT);
    private JButton backButton = new JButton(BACK_BUTTON_TEXT);
    private JPanel panelLoginButton = new JPanel();
    private JPanel panelClientInformation = new JPanel();

    private JPanel panelModules = new JPanel();
    private JPanel[] panelModuleButtons = new JPanel[MODULES.length];
    private JPanel[] panelShifts = new JPanel[MODULES.length];
    private JTextField[][] shiftFields = new JTextField[MODULES.length][3];
    private JButton[] moduleButtons = new JButton[MODULES.length];

    public HospitalForm(Login principal, Rows rows) {
        super("Hospital");
        this.principal = principal;
        this.rows = rows;
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

    private void updateShiftFields(int moduleIndex, int shiftIncrement, JTextField[] shiftFields) {
        shiftsTextMap.put(moduleIndex, shiftsTextMap.getOrDefault(moduleIndex, 0) + shiftIncrement);
        char letter = (char) ('A' + moduleIndex);
        shiftFields[0].setText(letter + " - " + String.format("%2d", shiftsTextMap.get(moduleIndex)));
        shiftFields[1].setText(letter + " - " + String.format("%2d", shiftsTextMap.get(moduleIndex) + 1));
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
            if (isThreadRunning) {
                JOptionPane.showMessageDialog(null, "Estás dentro de un consultorio en este momento");
                return;
            }

            int indexModule = moduleComboBox.getSelectedIndex();
            int indexProcedure = procedureComboBox.getSelectedIndex();
            int selectedShiftField = switch (indexProcedure) {
                case 1 -> 2;
                case 2 -> 3;
                case 3 -> 4;
                case 4 -> 5;
                default -> indexModule;
            };

            countDownThread = new CountDownThread(shiftFields[selectedShiftField][2]);
            countDownThread.addThreadCompleteListener(new ThreadCompleteListener() {
                @Override
                public void notifyOfThreadComplete(Thread thread) {
                    isThreadRunning = false;
                }
            });
            countDownThread.start();
            isThreadRunning = true;

            if (indexProcedure == 0) {
                control.getModules().addRow(principal.getClient(), PROCEDURE_TYPES[0]);
            } else {
                control.getModules().addRow(principal.getClient(), PROCEDURE_TYPES[indexProcedure]);
            }

            if (indexProcedure >= 1 && indexProcedure <= 4) {
                updateShiftFields(indexProcedure + 1, 1, shiftFields[indexProcedure + 1]);
            } else {
                updateShiftFields(indexModule, 1, shiftFields[indexModule]);
            }
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
            shiftFields[i][0] = new JTextField(letter + " - " + shiftsText[i], 1);
            shiftFields[i][1] = new JTextField(letter + " - " + (shiftsText[i] + 1), 1);
            shiftFields[i][2] = new JTextField(timeText[i]);
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
        new RowCustomer(principal, index, control.getModules()).setVisible(true);
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
