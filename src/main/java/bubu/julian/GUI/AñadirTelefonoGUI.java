package bubu.julian.GUI;


import bubu.julian.Controlador;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * AñadirTelefonoGUI
 */
public class AñadirTelefonoGUI extends BorderPane {

    private Controlador controlador;
    private int personaId;

    public AñadirTelefonoGUI(Controlador controlador, int personaId) {
        this.controlador = controlador;
        this.personaId = personaId;

        setId("ventanas");
        mostrarAñadirNuevoTelefono();
    }

    private void mostrarAñadirNuevoTelefono() {
        VBox holder = new VBox();

        Label telLabel = new Label("Telefono");
        TextField telField = new TextField();

        HBox buttonHolder = new HBox();
        Button confirmarButton = new Button("Confirmar");
        Button cancelarButton = new Button("Cancelar");
        confirmarButton.getStyleClass().addAll("button-phone", "button-confirm");
        cancelarButton.getStyleClass().addAll("button-phone", "button-cancel");

        confirmarButton.setOnAction(e -> {
            if(controlador.solicitarAñadirTelefonoAPersona(personaId, telField.getText())) {
                telField.clear();
            }
        });

        cancelarButton.setOnAction(e -> {
            controlador.cerrarVentana(this);
        });

        buttonHolder.getChildren().addAll(confirmarButton, cancelarButton);
        buttonHolder.setAlignment(Pos.CENTER);
        buttonHolder.setSpacing(40);

        holder.setAlignment(Pos.CENTER);
        holder.setSpacing(20);
        holder.getChildren().addAll(telLabel, telField, buttonHolder);
        setCenter(holder);
    }
}
