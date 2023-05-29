package interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PantallaVerRecetas extends JFrame {
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

    private JTable createTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            try {
                connection = getConnection();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Receta");

            // Crea el modelo de tabla no editable
            TableModel tableModel = new NonEditableTableModel();

            // Agrega los datos de las recetas a la tabla
            while (resultSet.next()) {
                String alimentos = resultSet.getString("alimentos");
                ((NonEditableTableModel) tableModel).addRow(new Object[]{alimentos});
            }

            // Crea la tabla con el modelo de datos no editable
            JTable table = new JTable(tableModel);
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);
            table.setRowSelectionAllowed(true);
            table.setColumnSelectionAllowed(false);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setFillsViewportHeight(true);

            // Ajusta el ancho de la columna "Alimentos"
            table.getColumnModel().getColumn(0).setPreferredWidth((int) (table.getColumnModel().getTotalColumnWidth() * 0.9));

            return table;
        } finally {
            // Cierra los recursos
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void initComponents() throws IOException {
        try {
            // Crea la tabla de recetas
            JTable recetasTable = createTable();
            recetasTable.setForeground(Color.WHITE);

            // Crea el JScrollPane para agregar la tabla
            JScrollPane scrollPane = new JScrollPane(recetasTable);
            scrollPane.setBackground(new Color(50, 50, 50));
            recetasTable.setBackground(new Color(50, 50, 50));

            // Agrega el JScrollPane al JFrame
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            // Crea el botón para volver a PantallaPrincipal
            JButton backButton = new JButton("Volver a PantallaPrincipal");
            backButton.addActionListener(e -> {
                new PantallaPrincipal().setVisible(true);
                dispose(); // Cierra la ventana actual
            });
            getContentPane().add(backButton, BorderLayout.SOUTH); // Agrega el botón debajo de la tabla

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las recetas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarInterfaz() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception e) {
                e.printStackTrace();
            }

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Pantalla Ver Recetas");
            try {
                initComponents();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Establece la ventana en pantalla completa
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }

    public class NonEditableTableModel extends DefaultTableModel {
        public NonEditableTableModel() {
            super(new Object[][] {}, new String[] { "Alimentos" });
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public static void main(String[] args) {
        PantallaVerRecetas pantalla = new PantallaVerRecetas();
        pantalla.mostrarInterfaz();
    }
}



