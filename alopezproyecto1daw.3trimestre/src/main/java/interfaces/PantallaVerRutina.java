package interfaces;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PantallaVerRutina extends JFrame {
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

    private Object[][] getData() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            try {
                connection = getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT re.*, ent.nombre, ej.categoria " +
                    "FROM Rutina_Ejercicio re " +
                    "JOIN Ejercicio ej ON re.ejercicioId = ej.id " +
                    "JOIN Entidad ent ON ej.entidadId = ent.id " +
                    "LIMIT 0, 1000");

            // Obtener el número de columnas del resultado de la consulta
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Excluir las columnas "rutinaId" y "ejercicioId" de los nombres de columna
            String[] columnNames = new String[columnCount - 2];
            int columnIndex = 0;
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                if (!columnName.equals("rutinaId") && !columnName.equals("ejercicioId")) {
                    columnNames[columnIndex] = columnName;
                    columnIndex++;
                }
            }

            // Obtener el número de filas del resultado de la consulta
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // Crear una matriz de objetos para almacenar los datos
            Object[][] data = new Object[rowCount][columnCount - 2];

            // Rellenar la matriz de datos con los resultados de la consulta
            int rowIndex = 0;
            while (resultSet.next()) {
                columnIndex = 0;
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    if (!columnName.equals("rutinaId") && !columnName.equals("ejercicioId")) {
                        data[rowIndex][columnIndex] = resultSet.getObject(i);
                        columnIndex++;
                    }
                }
                rowIndex++;
            }

            return data;
        } finally {
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

    public PantallaVerRutina() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            Object[][] data = getData();

            
            getContentPane().setBackground(new Color(50, 50, 50));

            String[] columnNames = {"duracion", "nombre", "categoria"};

            JTable rutinasTable = new JTable(new RutinasTableModel(data, columnNames));
            JScrollPane scrollPane = new JScrollPane(rutinasTable);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            
            JButton volverButton = new JButton("Volver");
            volverButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                    pantallaPrincipal.setVisible(true);
                    dispose();
                }
            });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(volverButton);
            getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las rutinas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class RutinasTableModel extends AbstractTableModel {
        private Object[][] data;
        private String[] columnNames;

        public RutinasTableModel(Object[][] data, String[] columnNames) {
            this.data = data;
            this.columnNames = columnNames;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaVerRutina pantalla = new PantallaVerRutina();
            pantalla.setVisible(true);
        });
    }
}


