package Control;

import GUI.Login;
import Logic.Client;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Control {

    private static final String FILE_NAME = "database.json";
    private static final Gson gson = new Gson();
    private List<Client> clientList;
    private Login login;

    public Control(Login principal) {
        this.login = principal;
        loadClientList();
    }

    public void launchWindow() {
        new Login().setVisible(true);
    }

    private void loadClientList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            Client[] clients = gson.fromJson(reader, Client[].class);
            clientList = new ArrayList<>(Arrays.asList(clients));
        } catch (IOException e) {
            showErrorDialog("Error al leer o escribir la base de datos.", "Error de lectura/escritura");
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            showErrorDialog("Error al leer la base de datos JSON.", "Error de lectura");
            throw new RuntimeException(e);
        }
    }

    public boolean idExists(String identification) {
        if (!isValidIdentificationFormat(identification)) {
            return false;
        }

        int id = Integer.parseInt(identification);

        Optional<Client> optionalClient = findClientById(id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            showWelcomeMessage(client);
            return true;
        }

        String name = askForName();

        if (name == null) {
            return false;
        }

        if (!isValidNameFormat(name)) {
            showErrorDialog("El nombre no debe contener números.", "Nombre no válido");
            return false;
        }

        Client newClient = new Client(name, id);
        clientList.add(newClient);
        saveClientList();

        return true;
    }

    private void saveClientList() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(clientList, writer);
        } catch (IOException e) {
            showErrorDialog("Error al guardar los datos en la base de datos.", "Error de escritura");
            throw new RuntimeException(e);
        }
    }

    public Optional<Client> findClientById(int id) {
        return clientList.stream()
                .filter(client -> client.getId() == id)
                .findFirst();
    }

    private String askForName() {
        String htmlStyle = """
                    <html>
                        <head>
                            <style>
                                h2 {
                                    font-size: 1.5rem;
                                    margin: 0.25rem 0;
                                    text-align: center;
                                }
                                hr {
                                    margin: 0.25rem 0 1rem;
                                    border: none;
                                    border-top: 1px solid #ccc;
                                }
                                p {
                                    font-size: 1rem;
                                    margin: 0.25rem 0;
                                }
                                .center {
                                    display: flex;
                                    justify-content: center;
                                }
                            </style>
                        </head>
                        <body style='width: 350px; padding: 20px;'>
                            <div class='center'>
                                <h2>Agregar nombre a la base de datos</h2>
                                <hr>
                            </div>
                            <div>
                                <p>Ingresa tu nombre completo para agregarlo a nuestra base de datos:</p>
                            </div>
                        </body>
                    </html>
                """;

        while (true) {
            String name = JOptionPane.showInputDialog(null, htmlStyle, "Identificación no encontrada", JOptionPane.WARNING_MESSAGE);
            if (name == null) {
                return null;
            }
            name = name.trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "<html><h2>El nombre no puede estar vacío.</h2></html>",
                        "Nombre no válido", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            return name;
        }
    }

    private boolean isValidIdentificationFormat(String identification) {
        if (!identification.matches("\\d{3}")) {
            JOptionPane.showMessageDialog(null, "<html><h2>La identificación debe ser un número de 3 dígitos.</h2></html>",
                    "Identificación no válida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isValidNameFormat(String name) {
        return name.matches("\\D+");
    }

    private void showErrorDialog(String message, String title) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "<html><h2>" + message + "</h2></html>", title, JOptionPane.ERROR_MESSAGE));
    }

    private void showWelcomeMessage(Client client) {
        String userName = client.getName();
        String message = String.format("<html><h2>Bienvenido de vuelta, %s!</h2></html>", userName);
        JOptionPane.showMessageDialog(null, message, "Identificación encontrada", JOptionPane.INFORMATION_MESSAGE);
    }
}
