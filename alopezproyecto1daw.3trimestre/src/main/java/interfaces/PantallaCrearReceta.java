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

public class PantallaCrearReceta extends JFrame {
    private static final String CONFIG_FILE = "bdconfig.ini";

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

                // Verificar si ya existe la lista de alimentos para el tipo actual
                if (alimentosPorTipo.containsKey(tipoAlimento)) {
                    // Agregar el nombre del alimento a la lista existente
                    alimentosPorTipo.get(tipoAlimento).add(nombreAlimento + " (" + calorias + " calorías, " + cantidad + " gramos)");
                } else {
                    // Crear una nueva lista para el tipo de alimento y agregar el nombre del alimento
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

            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String alimento : alimentos) {
                listModel.addElement(alimento);
            }

            JList<String> alimentosList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(alimentosList);

            panel.add(scrollPane);
        }

        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel labelComidas = new JLabel("Cantidad de comidas:");
        JTextField textFieldComidas = new JTextField(10);

        JLabel labelCalorias = new JLabel("Calorías diarias:");
        JTextField textFieldCalorias = new JTextField(10);

        JLabel labelPorcentajeProteinas = new JLabel("Porcentaje Proteínas:");
        JTextField textFieldPorcentajeProteinas = new JTextField(10);

        JLabel labelPorcentajeCarbohidratos = new JLabel("Porcentaje Carbohidratos:");
        JTextField textFieldPorcentajeCarbohidratos = new JTextField(10);

        JLabel labelPorcentajeGrasas = new JLabel("Porcentaje Grasas:");
        JTextField textFieldPorcentajeGrasas = new JTextField(10);

        JPanel opcionesPanel = new JPanel(new GridLayout(6, 2));
        opcionesPanel.add(labelComidas);
        opcionesPanel.add(textFieldComidas);
        opcionesPanel.add(labelCalorias);
        opcionesPanel.add(textFieldCalorias);
        opcionesPanel.add(labelPorcentajeProteinas);
        opcionesPanel.add(textFieldPorcentajeProteinas);
        opcionesPanel.add(labelPorcentajeCarbohidratos);
        opcionesPanel.add(textFieldPorcentajeCarbohidratos);
        opcionesPanel.add(labelPorcentajeGrasas);
        opcionesPanel.add(textFieldPorcentajeGrasas);

        getContentPane().add(opcionesPanel, BorderLayout.NORTH);

        JButton calcularButton = new JButton("Calcular Comidas");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidadComidas = Integer.parseInt(textFieldComidas.getText());
                float caloriasDiarias = Float.parseFloat(textFieldCalorias.getText());
                float porcentajeProteinas = Float.parseFloat(textFieldPorcentajeProteinas.getText());
                float porcentajeCarbohidratos = Float.parseFloat(textFieldPorcentajeCarbohidratos.getText());
                float porcentajeGrasas = Float.parseFloat(textFieldPorcentajeGrasas.getText());

                calcularPosiblesComidas(cantidadComidas, caloriasDiarias, porcentajeProteinas, porcentajeCarbohidratos, porcentajeGrasas);
            }
        });

        getContentPane().add(calcularButton, BorderLayout.SOUTH);
    }

    private void calcularPosiblesComidas(int cantidadComidas, float caloriasDiarias, float porcentajeProteinas, float porcentajeCarbohidratos, float porcentajeGrasas) {
        Map<String, ArrayList<String>> alimentosPorTipo = obtenerAlimentosPorTipo();

        for (int i = 1; i <= cantidadComidas; i++) {
            System.out.println("Comida " + i + ":");

            float caloriasComida = caloriasDiarias / cantidadComidas;
            float caloriasProteinas = caloriasComida * porcentajeProteinas / 100;
            float caloriasCarbohidratos = caloriasComida * porcentajeCarbohidratos / 100;
            float caloriasGrasas = caloriasComida * porcentajeGrasas / 100;

            System.out.println("Proteínas: " + caloriasProteinas + " calorías");
            System.out.println("Carbohidratos: " + caloriasCarbohidratos + " calorías");
            System.out.println("Grasas: " + caloriasGrasas + " calorías");

            for (String tipo : alimentosPorTipo.keySet()) {
                ArrayList<String> alimentos = alimentosPorTipo.get(tipo);
                int indiceAlimento = (int) (Math.random() * alimentos.size());
                String alimento = alimentos.get(indiceAlimento);

                String[] parts = alimento.split("\\(");
                String nombreAlimento = parts[0].trim();
                System.out.println("nombreAlimento: " + nombreAlimento);

                parts = parts[1].split("calorías,");
                float caloriasAlimento = Float.parseFloat(parts[0].trim());
                System.out.println("caloriasAlimento: " + caloriasAlimento);

                parts = parts[1].split("gramos\\)");
                float gramosAlimento = Float.parseFloat(parts[0].trim());
                System.out.println("gramosAlimento: " + gramosAlimento);

                float gramosNecesarios = 0;

                switch(tipo) {
                    case "proteina":
                        System.out.println("Calculando gramos para proteina");
                        System.out.println("Calorias Proteinas: " + caloriasProteinas);
                        System.out.println("Ratio Calorias/Gramos alimento: " + (caloriasAlimento / gramosAlimento));
                        gramosNecesarios = (caloriasProteinas * gramosAlimento) / caloriasAlimento;
                        break;
                    case "carbohidrato":
                        System.out.println("Calculando gramos para carbohidrato");
                        System.out.println("Calorias Carbohidratos: " + caloriasCarbohidratos);
                        System.out.println("Ratio Calorias/Gramos alimento: " + (caloriasAlimento / gramosAlimento));
                        gramosNecesarios = (caloriasCarbohidratos * gramosAlimento) / caloriasAlimento;
                        break;
                    case "grasa":
                        System.out.println("Calculando gramos para grasa");
                        System.out.println("Calorias Grasas: " + caloriasGrasas);
                        System.out.println("Ratio Calorias/Gramos alimento: " + (caloriasAlimento / gramosAlimento));
                        gramosNecesarios = (caloriasGrasas * gramosAlimento) / caloriasAlimento;
                        break;
                }
                System.out.println("aaaa" + caloriasGrasas);
                System.out.println("aaaa" + gramosAlimento);
                System.out.println("aaaa" + caloriasAlimento);
                System.out.println("Tipo: " + tipo + " - Alimento: " + nombreAlimento);
                System.out.println("gramosNecesarios: " + gramosNecesarios);
            }

            System.out.println();
        }
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