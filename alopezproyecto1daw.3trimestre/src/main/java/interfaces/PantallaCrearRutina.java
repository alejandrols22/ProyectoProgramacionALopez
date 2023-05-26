package interfaces;

import enums.Categoria;
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
import java.util.List;
import java.util.Map;

public class PantallaCrearRutina extends JFrame {
    private static final String CONFIG_FILE = "bdconfig.ini";

    public class Ejercicio {
        private String nombre;
        private float caloriasPorMinuto;
        private int duracion;
        private Categoria categoria;

        public Ejercicio(String nombre, float caloriasPorMinuto, int duracion, Categoria categoria) {
            this.nombre = nombre;
            this.caloriasPorMinuto = caloriasPorMinuto;
            this.duracion = duracion;
            this.categoria = categoria;
        }

        public String getNombre() {
            return nombre;
        }

        public float getCaloriasPorMinuto() {
            return caloriasPorMinuto;
        }

        public int getDuracion() {
            return duracion;
        }

        public Categoria getCategoria() {
            return categoria;
        }
    }

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

    private Map<Categoria, List<Ejercicio>> getEjercicios() {
        Map<Categoria, List<Ejercicio>> ejerciciosPorCategoria = new HashMap<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT e.nombre, ej.calorias_quemadas_por_minuto, ej.duracion, ej.categoria FROM Ejercicio ej JOIN Entidad e ON ej.entidadId = e.id");

            while (resultSet.next()) {
                String nombreEjercicio = resultSet.getString("nombre");
                float caloriasPorMinuto = resultSet.getFloat("calorias_quemadas_por_minuto");
                int duracion = resultSet.getInt("duracion");
                Categoria categoria = Categoria.valueOf(resultSet.getString("categoria").toUpperCase());

                Ejercicio ejercicio = new Ejercicio(nombreEjercicio, caloriasPorMinuto, duracion, categoria);

                if (ejerciciosPorCategoria.containsKey(categoria)) {
                    ejerciciosPorCategoria.get(categoria).add(ejercicio);
                } else {
                    List<Ejercicio> ejercicios = new ArrayList<>();
                    ejercicios.add(ejercicio);
                    ejerciciosPorCategoria.put(categoria, ejercicios);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return ejerciciosPorCategoria;
    }

    private List<Ejercicio> seleccionarEjerciciosParaObjetivo(float caloriasObjetivo) {
        Map<Categoria, List<Ejercicio>> ejerciciosPorCategoria = getEjercicios();
        List<Ejercicio> ejerciciosSeleccionados = new ArrayList<>();

        // Create a copy of the available exercises to avoid modifying the original list
        Map<Categoria, List<Ejercicio>> availableEjerciciosPorCategoria = new HashMap<>(ejerciciosPorCategoria);

        while (caloriasObjetivo > 0) {
            // Select a random category from the available categories
            List<Categoria> categorias = new ArrayList<>(availableEjerciciosPorCategoria.keySet());
            Categoria categoria = categorias.get((int) (Math.random() * categorias.size()));

            // Check if there are available exercises in the selected category
            List<Ejercicio> ejercicios = availableEjerciciosPorCategoria.get(categoria);
            if (ejercicios.isEmpty()) {
                // If no exercises are available in the category, remove it from the available categories
                availableEjerciciosPorCategoria.remove(categoria);
                continue;
            }

            // Select a random exercise from the category
            Ejercicio ejercicio = ejercicios.get((int) (Math.random() * ejercicios.size()));

            // Check if the exercise duration can fit within the remaining calories
            int duracionEjercicio = ejercicio.getDuracion();
            float caloriasEjercicio = ejercicio.getCaloriasPorMinuto() * duracionEjercicio;
            if (caloriasEjercicio <= caloriasObjetivo) {
                ejerciciosSeleccionados.add(ejercicio);
                caloriasObjetivo -= caloriasEjercicio;
            }

            // Remove the selected exercise from the available exercises
            ejercicios.remove(ejercicio);
            if (ejercicios.isEmpty()) {
                // If all exercises in the category have been selected, remove the category from the available categories
                availableEjerciciosPorCategoria.remove(categoria);
            }
        }

        return ejerciciosSeleccionados;
    }

    private void initComponents() {
        JLabel labelCaloriasObjetivo = new JLabel("Calorías objetivo:");
        JTextField textFieldCaloriasObjetivo = new JTextField(10);

        JButton calcularButton = new JButton("Crear rutina");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float caloriasObjetivo = Float.parseFloat(textFieldCaloriasObjetivo.getText());

                List<Ejercicio> ejerciciosSeleccionados = seleccionarEjerciciosParaObjetivo(caloriasObjetivo);

                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);

                for (Ejercicio ejercicio : ejerciciosSeleccionados) {
                    textArea.append(ejercicio.getNombre() + " - " + ejercicio.getDuracion() + " minutos\n");
                }

                JScrollPane scrollPane = new JScrollPane(textArea);
                getContentPane().add(scrollPane, BorderLayout.CENTER);
            }
        });

        JPanel opcionesPanel = new JPanel(new GridLayout(1, 2));
        opcionesPanel.add(labelCaloriasObjetivo);
        opcionesPanel.add(textFieldCaloriasObjetivo);

        getContentPane().add(opcionesPanel, BorderLayout.NORTH);
        getContentPane().add(calcularButton, BorderLayout.SOUTH);
    }

    public void mostrarInterfaz() {
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Pantalla Crear Rutina");
            initComponents();
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }
}