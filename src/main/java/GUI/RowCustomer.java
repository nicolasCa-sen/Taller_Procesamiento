package GUI;

import Control.LogicControl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class RowCustomer extends JFrame {

    private static final int FORM_WIDTH = 854;
    private static final int FORM_HEIGHT = 480;
    private static final String[] columnNames = {"Identificación", "Nombre", "Estado", "Tiempo restante"};
    private Login principal;
    private LogicControl logicControl;

    private JPanel infomationPanel;
    private JPanel tablePanel;

    private JTextField surgeryField;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton backButton;

    public RowCustomer(Login principal, int option) {
        super("Fila Clientes");
        this.principal = principal;
        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createInformationPanel(option);
        createTablePanel();

        getContentPane().setBackground(new Color(188, 227, 255));
        this.add(infomationPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
    }

    private void createInformationPanel(int option) {
        infomationPanel = new JPanel();
        configPanel(infomationPanel, new FlowLayout(FlowLayout.LEFT));

        surgeryField = new JTextField(30);
        surgeryField.setPreferredSize(new Dimension(180, 30));
        surgeryField.setHorizontalAlignment(SwingConstants.CENTER);
        surgeryField.setEditable(false);
        switch (option) {
            case 0:
                surgeryField.setText("Consultorio 1");

                break;
            case 1:
                surgeryField.setText("Consultorio 2");
                break;
            case 2:
                surgeryField.setText("Consulta con especialista en cardiología");
                break;
            case 3:
                surgeryField.setText("Consulta con especialista en traumatología");
                break;
            case 4:
                surgeryField.setText("Consulta con especialista en oftalmología");
                break;
            case 5:
                surgeryField.setText("Consulta con especialista en dermatología");
                break;
        }
        infomationPanel.add(surgeryField);

        createBackButton();
    }

    private void createBackButton() {
        backButton = new JButton("Regresar");
        configButton(backButton, 250, 30, 15);
        addButtonToPanel(backButton, infomationPanel, e -> {
            new HospitalForm(principal).setVisible(true);
            dispose();
        });
    }

    private void createTablePanel() {
        tablePanel = new JPanel();
        configPanel(tablePanel, new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        table = new JTable(new Object[][]{}, columnNames);
        table.setDefaultEditor(Object.class, null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void configPanel(JPanel panel, LayoutManager layout) {
        panel.setLayout(layout);
        panel.setBackground(new Color(188, 227, 255));
    }

    private void configButton(JButton button, int width, int height, int fontSize) {
        button.setBackground(new Color(77, 205, 255));
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
