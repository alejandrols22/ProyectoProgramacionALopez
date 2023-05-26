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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Receta");

            // Crea el modelo de tabla no editable
            TableModel tableModel = new NonEditableTableModel();
            ((NonEditableTableModel) tableModel).addColumn("ID");
            ((NonEditableTableModel) tableModel).addColumn("Entidad ID");
            ((NonEditableTableModel) tableModel).addColumn("Calorias Proteinas");
            ((NonEditableTableModel) tableModel).addColumn("Calorias Carbohidratos");
            ((NonEditableTableModel) tableModel).addColumn("Calorias Grasas");
            ((NonEditableTableModel) tableModel).addColumn("Alimentos");

            // Agrega los datos de las recetas a la tabla
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int entidadId = resultSet.getInt("entidadId");
                float caloriasProteinas = resultSet.getFloat("caloriasProteinas");
                float caloriasCarbohidratos = resultSet.getFloat("caloriasCarbohidratos");
                float caloriasGrasas = resultSet.getFloat("caloriasGrasas");
                String alimentos = resultSet.getString("alimentos");

                ((NonEditableTableModel) tableModel).addRow(new Object[]{id, entidadId, caloriasProteinas, caloriasCarbohidratos, caloriasGrasas, alimentos});
            }

            // Crea la tabla con el modelo de datos no editable
            JTable table = new JTable(tableModel);
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);
            table.setRowSelectionAllowed(true);
            table.setColumnSelectionAllowed(false);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setFillsViewportHeight(true);

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

            // Crea el JScrollPane para agregar la tabla
            JScrollPane scrollPane = new JScrollPane(recetasTable);

            // Agrega el JScrollPane al JFrame
            getContentPane().add(scrollPane, BorderLayout.CENTER);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }

    public class NonEditableTableModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}


