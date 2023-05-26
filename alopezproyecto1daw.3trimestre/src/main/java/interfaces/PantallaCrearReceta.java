package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PantallaCrearReceta extends JFrame {
    private static final String CONFIG_FILE = "bdconfig.ini";
    private Map<String, String> alimentosSeleccionadosPorTipo = new HashMap<>();

    private Connection getConnection() throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE));
        String ip = reader.readLine();
        int puerto = Integer.parseInt(reader.readLine());
        String nombreBD = reader.readLine();
        String user = reader.readLine();
        String password = reader.readLine();
        reader.close();

        String url = "jdbc:mysql://" + ip + ":" + puerto + "/" + nombreBD;
        return DriverManager.getConnection(url, user, password);
    }

    private Map<String, ArrayList<String>> obtenerAlimentosPorTipo() {
        Map<String, ArrayList<String>> alimentosPorTipo = new HashMap<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT a.tipo, e.nombre, a.calorias, a.cantidad FROM Alimento a JOIN Entidad e ON a.entidadId = e.id");

            while (resultSet.next()) {
                String tipoAlimento = resultSet.getString("tipo");
                String nombreAlimento = resultSet.getString("nombre");
                float calorias = resultSet.getFloat("calorias");
                float cantidad = resultSet.getFloat("cantidad");

                if (alimentosPorTipo.containsKey(tipoAlimento)) {
                    alimentosPorTipo.get(tipoAlimento).add(nombreAlimento + " (" + calorias + " calorías, " + cantidad + " gramos)");
                } else {
                    ArrayList<String> alimentos = new ArrayList<>();
                    alimentos.add(nombreAlimento + " (" + calorias + " calorías, " + cantidad + " gramos)");
                    alimentosPorTipo.put(tipoAlimento, alimentos);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return alimentosPorTipo;
    }

    private void initComponents() {
        Map<String, ArrayList<String>> alimentosPorTipo = obtenerAlimentosPorTipo();

        JPanel panel = new JPanel(new GridLayout(1, alimentosPorTipo.size()));

        for (String tipo : alimentosPorTipo.keySet()) {
            ArrayList<String> alimentos = alimentosPorTipo.get(tipo);

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String alimento : alimentos) {
                comboBoxModel.addElement(alimento);
            }

            JComboBox<String> alimentosComboBox = new JComboBox<>(comboBoxModel);

            alimentosComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                    String alimentoSeleccionado = (String) comboBox.getSelectedItem();
                    alimentosSeleccionadosPorTipo.put(tipo, alimentoSeleccionado);
                }
            });

            panel.add(alimentosComboBox);
        }

        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel labelCaloriasReceta = new JLabel("Calorías de la receta:");
        JTextField textFieldCaloriasReceta = new JTextField(10);

        JLabel labelPorcentajeProteinas = new JLabel("Porcentaje Proteínas:");
        JTextField textFieldPorcentajeProteinas = new JTextField(10);

        JLabel labelPorcentajeCarbohidratos = new JLabel("Porcentaje Carbohidratos:");
        JTextField textFieldPorcentajeCarbohidratos = new JTextField(10);

        JLabel labelPorcentajeGrasas = new JLabel("Porcentaje Grasas:");
        JTextField textFieldPorcentajeGrasas = new JTextField(10);

        JPanel opcionesPanel = new JPanel(new GridLayout(4, 2));
        opcionesPanel.add(labelCaloriasReceta);
        opcionesPanel.add(textFieldCaloriasReceta);
        opcionesPanel.add(labelPorcentajeProteinas);
        opcionesPanel.add(textFieldPorcentajeProteinas);
        opcionesPanel.add(labelPorcentajeCarbohidratos);
        opcionesPanel.add(textFieldPorcentajeCarbohidratos);
        opcionesPanel.add(labelPorcentajeGrasas);
        opcionesPanel.add(textFieldPorcentajeGrasas);

        getContentPane().add(opcionesPanel, BorderLayout.NORTH);

        JButton calcularButton = new JButton("Calcular Comida");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float caloriasReceta = Float.parseFloat(textFieldCaloriasReceta.getText());
                float porcentajeProteinas = Float.parseFloat(textFieldPorcentajeProteinas.getText());
                float porcentajeCarbohidratos = Float.parseFloat(textFieldPorcentajeCarbohidratos.getText());
                float porcentajeGrasas = Float.parseFloat(textFieldPorcentajeGrasas.getText());

                mostrarResultadoComida(caloriasReceta, porcentajeProteinas, porcentajeCarbohidratos, porcentajeGrasas);
            }
        });

        getContentPane().add(calcularButton, BorderLayout.SOUTH);
    }

    private void mostrarResultadoComida(float caloriasReceta, float porcentajeProteinas, float porcentajeCarbohidratos,
                                        float porcentajeGrasas) {
        StringBuilder resultadoBuilder = new StringBuilder();

        resultadoBuilder.append("Comida:\n\n");

        float caloriasProteinas = caloriasReceta * porcentajeProteinas / 100;
        float caloriasCarbohidratos = caloriasReceta * porcentajeCarbohidratos / 100;
        float caloriasGrasas = caloriasReceta * porcentajeGrasas / 100;

        resultadoBuilder.append("Proteínas: ").append(caloriasProteinas).append(" calorías\n");
        resultadoBuilder.append("Carbohidratos: ").append(caloriasCarbohidratos).append(" calorías\n");
        resultadoBuilder.append("Grasas: ").append(caloriasGrasas).append(" calorías\n");

        for (String tipo : alimentosSeleccionadosPorTipo.keySet()) {
            String alimentoSeleccionado = alimentosSeleccionadosPorTipo.get(tipo);

            String[] parts = alimentoSeleccionado.split("\\(");
            String nombreAlimento = parts[0].trim();

            parts = parts[1].split("calorías,");
            float caloriasAlimento = Float.parseFloat(parts[0].trim());

            parts = parts[1].split("gramos\\)");
            float gramosAlimentoPor100g = Float.parseFloat(parts[0].trim());
            float gramosAlimento = gramosAlimentoPor100g / 100;

            float gramosNecesarios = 0;

            if (tipo.equals("Proteina")) {
                gramosNecesarios = (caloriasProteinas * 100) / caloriasAlimento;
            } else if (tipo.equals("Carbohidrato")) {
                gramosNecesarios = (caloriasCarbohidratos * 100) / caloriasAlimento;
            } else if (tipo.equals("Grasa")) {
                gramosNecesarios = (caloriasGrasas * 100) / caloriasAlimento;
            }

            resultadoBuilder.append(nombreAlimento).append(": ").append(gramosNecesarios).append(" gramos\n");
        }

        resultadoBuilder.append("\n");

        JTextArea resultadoTextArea = new JTextArea(resultadoBuilder.toString());
        resultadoTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Resultado de la Comida", JOptionPane.PLAIN_MESSAGE);
    }

    public void mostrarInterfaz() {
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Pantalla Crear Receta");
            initComponents();
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }
}

