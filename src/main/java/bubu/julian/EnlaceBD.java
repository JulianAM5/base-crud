package bubu.julian;

import java.sql.*;

public class EnlaceBD {
    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "superpassword";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public EnlaceBD() {

    }

    public void ConectarAServidor() {

        try {
            // 1. Registrar el driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. Establecer la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void CerrarConexion() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
