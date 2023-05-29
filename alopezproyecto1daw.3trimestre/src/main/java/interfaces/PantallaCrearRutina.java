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
import java.util.List;
import java.util.Random;

public class PantallaCrearRutina extends JFrame {
    private static final String CONFIG_FILE = "bdconfig.ini";
    private JPanel centerPanel;
    private boolean rutinaCreada = false;

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

    private List<Ejercicio> getEjercicios() {
        List<Ejercicio> ejercicios = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT e.nombre, a.calorias_quemadas_por_minuto, a.duracion, a.categoria FROM Ejercicio a JOIN Entidad e ON a.entidadId = e.id");
            while (resultSet.next()) {
                String nombreEjercicio = resultSet.getString("nombre");
                float caloriasPorMinuto = resultSet.getFloat("calorias_quemadas_por_minuto");
                int duracion = resultSet.getInt("duracion");
                Categoria categoria = Categoria.valueOf(resultSet.getString("categoria").toUpperCase());

                Ejercicio ejercicio = new Ejercicio(nombreEjercicio, caloriasPorMinuto, duracion, categoria);
                ejercicios.add(ejercicio);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return ejercicios;
    }

    private List<Ejercicio> seleccionarEjerciciosParaObjetivo(float caloriasObjetivo) {
        List<Ejercicio> ejerciciosDisponibles = getEjercicios();
        List<Ejercicio> ejerciciosSeleccionados = new ArrayList<>();
        Random random = new Random();

        while (caloriasObjetivo > 0) {
            if (ejerciciosDisponibles.isEmpty()) {
                break;
            }

            int index = random.nextInt(ejerciciosDisponibles.size());
            Ejercicio ejercicio = ejerciciosDisponibles.get(index);

            int duracionEjercicio = ejercicio.getDuracion();
            float caloriasEjercicio = ejercicio.getCaloriasPorMinuto() * duracionEjercicio;

            if (caloriasEjercicio <= caloriasObjetivo) {
                ejerciciosSeleccionados.add(ejercicio);
                caloriasObjetivo -= caloriasEjercicio;
            }

            ejerciciosDisponibles.remove(index);
        }

        return ejerciciosSeleccionados;
    }

    private void insertarRutinaEnBaseDatos(List<Ejercicio> ejercicios) {
        try (Connection connection = getConnection()) {

            // Insertar la rutina en la tabla Rutina
            PreparedStatement rutinaStatement = connection.prepareStatement("INSERT INTO Rutina VALUES (DEFAULT)");
            rutinaStatement.executeUpdate();

            // Obtener el ID de la rutina recién insertada
            Statement lastIdStatement = connection.createStatement();
            ResultSet lastIdResult = lastIdStatement.executeQuery("SELECT LAST_INSERT_ID()");
            int rutinaId = 0;
            if (lastIdResult.next()) {
                rutinaId = lastIdResult.getInt(1);
            }

            // Insertar los ejercicios de la rutina en la tabla Rutina_Ejercicio
            for (Ejercicio ejercicio : ejercicios) {

                PreparedStatement ejercicioStatement = connection.prepareStatement(
                        "INSERT INTO Rutina_Ejercicio (rutinaId, duracion) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ejercicioStatement.setInt(1, rutinaId);
                ejercicioStatement.setFloat(2, ejercicio.getDuracion());
                ejercicioStatement.executeUpdate();

                // Obtener el ejercicioId generado automáticamente
                ResultSet generatedKeys = ejercicioStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int ejercicioId = generatedKeys.getInt(1);
                    System.out.println("Ejercicio insertado con ejercicioId: " + ejercicioId);
                } else {
                    System.out.println("No se pudo obtener el ejercicioId generado automáticamente");
                }
            }

            JOptionPane.showMessageDialog(this, "La rutina se ha guardado correctamente.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la rutina. Por favor, inténtalo de nuevo.");
        }
    }

    private void mostrarEjerciciosSeleccionados(float caloriasObjetivo) {
        JButton volverButton = new JButton("Volver a la Pantalla Principal");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                pantallaPrincipal.setVisible(true);
                dispose();  // Cierra esta ventana
            }
        });

        List<Ejercicio> ejerciciosSeleccionados = seleccionarEjerciciosParaObjetivo(caloriasObjetivo);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (Ejercicio ejercicio : ejerciciosSeleccionados) {
            textArea.append(ejercicio.getNombre() + " - " + ejercicio.getDuracion() + " minutos\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton guardarButton = new JButton("Guardar rutina");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarRutinaEnBaseDatos(ejerciciosSeleccionados);
            }
        });

        if (rutinaCreada) {
            getContentPane().add(guardarButton, BorderLayout.SOUTH);
        }

        getContentPane().add(volverButton, BorderLayout.WEST);

        guardarButton.setBackground(Color.GRAY); // Establecer fondo gris al botón "Guardar rutina"
        volverButton.setBackground(Color.GRAY); // Establecer fondo gris al botón "Volver a la Pantalla Principal"

        // Establecer el color de texto blanco para los botones
        guardarButton.setForeground(Color.WHITE);
        volverButton.setForeground(Color.WHITE);

        revalidate();
        repaint();
    }

    private void initComponents() {
        JLabel labelCaloriasObjetivo = new JLabel("Calorías objetivo:");
        JTextField textFieldCaloriasObjetivo = new JTextField(10);

        JButton calcularButton = new JButton("Crear rutina");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float caloriasObjetivo = Float.parseFloat(textFieldCaloriasObjetivo.getText());

                getContentPane().removeAll(); // Eliminar los componentes anteriores
                mostrarEjerciciosSeleccionados(caloriasObjetivo);

                rutinaCreada = true; // Cambiar el estado de rutinaCreada a true
            }
        });

        // Panel superior
        JPanel opcionesPanel = new JPanel(new BorderLayout());
        opcionesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        opcionesPanel.setBackground(Color.GRAY); // Establecer el color de fondo gris

        opcionesPanel.add(labelCaloriasObjetivo, BorderLayout.WEST);
        opcionesPanel.add(textFieldCaloriasObjetivo, BorderLayout.CENTER);
        opcionesPanel.add(calcularButton, BorderLayout.EAST);

        // Panel central para mostrar ejercicios
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(50, 50, 50)); // Establecer el color de fondo gris oscuro

        // Contenedor principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.GRAY); // Establecer el color de fondo gris
        getContentPane().add(opcionesPanel, BorderLayout.NORTH);
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        JButton volverButton = new JButton("Volver a la Pantalla Principal");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                pantallaPrincipal.setVisible(true);
                dispose();  // Cierra esta ventana
            }
        });

        getContentPane().add(volverButton, BorderLayout.WEST);

       

        revalidate();
        repaint();
    }

    public void mostrarInterfaz() {
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Pantalla Crear Rutina");
            initComponents();
            pack();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }
}


