package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class RowCustomer extends JFrame {

    private static final int FORM_WIDTH = 854;
    private static final int FORM_HEIGHT = 480;
    private static final String[] columnNames = {"Identificación", "Nombre", "Estado", "Tiempo restante"};
    private static final Object[][] data = {{"123456", "Juan Pérez", "En espera", "10:30"},
            {"789012", "María Gómez", "Atendido", "11:15"}};

    private JPanel infomationPanel;
    private JPanel tablePanel;

    private JTextField surgeryField;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton backButton;

    public RowCustomer() {
        super("Fila Clientes");
        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        infomationPanel = new JPanel();
        configPanel(infomationPanel, new FlowLayout(FlowLayout.LEFT));

        surgeryField = new JTextField("Consultorio", 20);
        surgeryField.setPreferredSize(new Dimension(180, 30));
        surgeryField.setHorizontalAlignment(SwingConstants.CENTER);
        infomationPanel.add(surgeryField);

        backButton = new JButton("Regresar");
        configButton(backButton, 250, 30, 15);
        addButtonToPanel(backButton, infomationPanel, e -> {});

        tablePanel = new JPanel();
        configPanel(tablePanel, new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        table = new JTable(data, columnNames);
        table.setDefaultEditor(Object.class, null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().setBackground(new Color(188, 227, 255));
        this.add(infomationPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
    }

    private void configPanel(JPanel panel, LayoutManager layout) {
        panel.setLayout(layout);
        panel.setBackground(new Color(188, 227, 255));
    }

    private void configButton(JButton button, int width, int height, int sizeFont) {
        button.setBackground(new Color(77, 205, 255));
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, sizeFont));
        button.setPreferredSize(new Dimension(width, height));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void addButtonToPanel(JButton button, JPanel panel, ActionListener listener) {
        button.addActionListener(listener);
        panel.add(button);
    }
}
