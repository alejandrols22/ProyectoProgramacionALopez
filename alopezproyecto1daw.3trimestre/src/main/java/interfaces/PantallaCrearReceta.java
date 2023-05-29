package interfaces;

import org.json.JSONObject;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
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

        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 50, 50));

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);

        for (String tipo : alimentosPorTipo.keySet()) {
            ArrayList<String> alimentos = alimentosPorTipo.get(tipo);

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String alimento : alimentos) {
                comboBoxModel.addElement(alimento);
            }

            JComboBox<String> alimentosComboBox = new JComboBox<>(comboBoxModel);
            alimentosComboBox.setPreferredSize(new Dimension(200, 30));

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

        JPanel opcionesPanel = new JPanel(new GridLayout(4, 2));
        opcionesPanel.setBackground(new Color(50, 50, 50));
        opcionesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelCaloriasReceta = new JLabel("Calorías de la receta:");
        labelCaloriasReceta.setForeground(Color.WHITE);
        JTextField textFieldCaloriasReceta = new JTextField(10);
        textFieldCaloriasReceta.setPreferredSize(new Dimension(200, 30));
        textFieldCaloriasReceta.setDocument(new NumericDocument());

        JLabel labelPorcentajeProteinas = new JLabel("Porcentaje Proteínas:");
        labelPorcentajeProteinas.setForeground(Color.WHITE);
        JSpinner spinnerPorcentajeProteinas = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        JLabel labelPorcentajeCarbohidratos = new JLabel("Porcentaje Carbohidratos:");
        labelPorcentajeCarbohidratos.setForeground(Color.WHITE);
        JSpinner spinnerPorcentajeCarbohidratos = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        JLabel labelPorcentajeGrasas = new JLabel("Porcentaje Grasas:");
        labelPorcentajeGrasas.setForeground(Color.WHITE);
        JSpinner spinnerPorcentajeGrasas = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        opcionesPanel.add(labelCaloriasReceta);
        opcionesPanel.add(textFieldCaloriasReceta);
        opcionesPanel.add(labelPorcentajeProteinas);
        opcionesPanel.add(spinnerPorcentajeProteinas);
        opcionesPanel.add(labelPorcentajeCarbohidratos);
        opcionesPanel.add(spinnerPorcentajeCarbohidratos);
        opcionesPanel.add(labelPorcentajeGrasas);
        opcionesPanel.add(spinnerPorcentajeGrasas);

        JButton calcularButton = new JButton("Calcular Comida");
        calcularButton.setBackground(Color.WHITE);
        calcularButton.setForeground(Color.BLACK);
        calcularButton.setPreferredSize(new Dimension(400, 40));
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float caloriasReceta = Float.parseFloat(textFieldCaloriasReceta.getText());
                int porcentajeProteinas = (int) spinnerPorcentajeProteinas.getValue();
                int porcentajeCarbohidratos = (int) spinnerPorcentajeCarbohidratos.getValue();
                int porcentajeGrasas = (int) spinnerPorcentajeGrasas.getValue();

                int sumaPorcentajes = porcentajeProteinas + porcentajeCarbohidratos + porcentajeGrasas;
                if (sumaPorcentajes != 100) {
                    JOptionPane.showMessageDialog(PantallaCrearReceta.this,
                            "La suma de los porcentajes debe ser igual a 100. Por favor, inténtelo de nuevo.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    mostrarResultadoComida(caloriasReceta, porcentajeProteinas, porcentajeCarbohidratos, porcentajeGrasas);
                }
            }
        });

        JButton botonPrincipal = new JButton("Ir a Pantalla Principal");
        botonPrincipal.setBackground(Color.WHITE);
        botonPrincipal.setForeground(Color.BLACK);
        botonPrincipal.setPreferredSize(new Dimension(400, 40));
        botonPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                pantallaPrincipal.setVisible(true);
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(50, 50, 50));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(calcularButton);
        buttonPanel.add(botonPrincipal);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(50, 50, 50));
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(opcionesPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void mostrarResultadoComida(float caloriasReceta, int porcentajeProteinas, int porcentajeCarbohidratos,
                                        int porcentajeGrasas) {
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
        resultadoTextArea.setBackground(new Color(50, 50, 50));
        resultadoTextArea.setForeground(Color.WHITE);
        resultadoTextArea.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton guardarRecetaButton = new JButton("Guardar Receta");
        guardarRecetaButton.setBackground(Color.WHITE);
        guardarRecetaButton.setForeground(Color.BLACK);
        guardarRecetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarReceta(caloriasProteinas, caloriasCarbohidratos, caloriasGrasas);
            }
        });

        JPanel resultadoPanel = new JPanel(new BorderLayout());
        resultadoPanel.setBackground(new Color(50, 50, 50));
        resultadoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        resultadoPanel.add(resultadoTextArea, BorderLayout.CENTER);
        resultadoPanel.add(guardarRecetaButton, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, resultadoPanel, "Resultado de la Comida", JOptionPane.PLAIN_MESSAGE);
    }

    private void insertarReceta(float caloriasProteinas, float caloriasCarbohidratos, float caloriasGrasas) {
        try (Connection connection = getConnection()) {
            String alimentosJson = new JSONObject(alimentosSeleccionadosPorTipo).toString();

            String query = "INSERT INTO Receta (entidadId, caloriasProteinas, caloriasCarbohidratos, caloriasGrasas, alimentos) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 1);  // Reemplaza el valor 1 por el valor correcto de entidadId
            statement.setFloat(2, caloriasProteinas);
            statement.setFloat(3, caloriasCarbohidratos);
            statement.setFloat(4, caloriasGrasas);
            statement.setString(5, alimentosJson);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Receta insertada correctamente", "Receta Guardada", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar la receta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarInterfaz() {
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Pantalla Crear Receta");
            initComponents();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
        });
    }

    private class NumericDocument extends PlainDocument {
        private int minValue;
        private int maxValue;

        public NumericDocument() {
            this.minValue = 0;
            this.maxValue = Integer.MAX_VALUE;
        }

        public NumericDocument(int minValue, int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            try {
                int value = Integer.parseInt(getText(0, getLength()) + str);
                if (value >= minValue && value <= maxValue) {
                    super.insertString(offset, str, attr);
                }
            } catch (NumberFormatException e) {
                // Ignore non-numeric input
            }
        }
    }
}



