package bubu.julian;

import java.util.ArrayList;

import bubu.julian.GUI.MenuPrincipal;
import javafx.scene.layout.StackPane;

/**
 * Controlador
 */
public class Controlador {

    private EnlaceBD enlaceBD;
    private StackPane root;

    public Controlador() {
        enlaceBD = new EnlaceBD();
        enlaceBD.ConectarAServidor();

        root = new StackPane();

        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() {
        root.getChildren().add(new MenuPrincipal(this));
    }

    public void mostrarVentanaPersona() {

    }

    public boolean solicitarAñadirPersona(int personaId) {
        return false;
    }

    public boolean solicitarEliminarPersona(int personaId) {
        return false;
    }

    public boolean solicitarAñadirTelefonoAPersona(int personaId, String telefono) {
        return false;
    }

    public ArrayList<Persona> recuperarPersonas() {
        return enlaceBD.RecuperarPersonas();
    }

    public ArrayList<Telefono> recuperarTelefonosDePersona(int personaId) {
        return enlaceBD.RecuperarTelefonosDePersona(personaId);
    }

    public void terminarAplicacion() {
        enlaceBD.CerrarConexion();
        System.exit(0);
    }


    public StackPane getRoot() {
        return root;
    }
}
