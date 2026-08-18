package bubu.julian;

import java.util.ArrayList;

import bubu.julian.GUI.AñadirTelefonoGUI;
import bubu.julian.GUI.MenuPrincipal;
import bubu.julian.GUI.VentanaAñadir;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Controlador
 */
public class Controlador {

    private EnlaceBD enlaceBD;
    private StackPane root;
    private MenuPrincipal menu;

    public Controlador() {
        enlaceBD = new EnlaceBD();
        enlaceBD.ConectarAServidor();

        root = new StackPane();

        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() {
        menu = new MenuPrincipal(this);
        root.getChildren().add(menu);
    }

    public void mostrarVentanaAñadir() {
        VentanaAñadir ventana = new VentanaAñadir(this);
        ventana.mostrarAñadirPersona();
        root.getChildren().add(ventana);
    }

    public void mostrarVentanaModificar(int personaId) {
        VentanaAñadir ventana = new VentanaAñadir(this);
        ventana.mostrarModificarPersona(personaId);
        root.getChildren().add(ventana);
    }

    public void mostrarVentanaAñadirTelefono(int personaId) {
        root.getChildren().add(new AñadirTelefonoGUI(this, personaId));
    }

    public void cerrarVentana(Pane pane) {
        root.getChildren().remove(pane);
    }

    public boolean solicitarAñadirPersona(String nombre, String direccion) {
        if (nombre.isEmpty() || direccion.isEmpty()) { return false; }

        if (!enlaceBD.AñadirPersona(nombre, direccion)) { return false; }
        
        menu.actualizar();
        return true;
    }

    public boolean solicitarModificarPersona(int id, String nombre, String direccion) {
        return true;
    }

    public boolean solicitarEliminarPersona(int id) {
        if (!enlaceBD.EliminarPersona(id)) { return false; }

        menu.actualizar();
        return true;
    }

    public boolean solicitarEliminarTelefonoAPersona(int personaId, String numTelefono) {
        if(!enlaceBD.EliminarTelefonoAPersona(personaId, numTelefono)) { return false; }

        menu.actualizar();
        return true;
    }

    public boolean solicitarAñadirTelefonoAPersona(int personaId, String numTelefono) {
        if (numTelefono.isEmpty()) { return false; }

        if (!enlaceBD.AñadirTelefonoAPersona(personaId, numTelefono)) { return false; }

        menu.actualizar();
        return true;
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
