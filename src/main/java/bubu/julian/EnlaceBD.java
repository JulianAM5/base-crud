package bubu.julian;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.ObservableList;

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

    public ArrayList<Persona> RecuperarPersonas() {
        try {
            ArrayList<Persona> personas = new ArrayList<>();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Personas");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                Persona persona = new Persona(id, nombre, direccion);

                personas.add(persona);
            }

            return personas;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public ArrayList<Telefono> RecuperarTelefonosDePersona(int personaId) {
        try {
            ArrayList<Telefono> telefonos = new ArrayList<>();
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT telefono FROM Telefonos WHERE personaId = " + personaId);

            while (rs.next()) {
                String numTelefono = rs.getString("telefono");
                telefonos.add(new Telefono(personaId, numTelefono));
            }

            return telefonos;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public boolean AñadirPersona(String nombre, String direccion) {
        try {
            String sqlInstruccion = "INSERT INTO Personas (nombre, direccion) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlInstruccion);

            ps.setString(1, nombre);
            ps.setString(2, direccion);
            
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public boolean AñadirTelefonoAPersona(int personaId, String numTelefono) {
        try {
            String sqlInstruccion = "INSERT INTO Telefonos (personaId, telefono) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlInstruccion);

            ps.setInt(1, personaId);
            ps.setString(2, numTelefono);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
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
