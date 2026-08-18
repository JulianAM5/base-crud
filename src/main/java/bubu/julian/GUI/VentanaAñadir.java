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
 * VentanaAñadir
 */
public class VentanaAñadir extends BorderPane {

    Controlador controlador;

    public VentanaAñadir(Controlador controlador) {
        this.controlador = controlador;
        setId("ventanas");
    }

    public void mostrarAñadirPersona() {
        VBox holder = new VBox();

        Label nombreLabel = new Label("Nombre");
        TextField nombreField = new TextField();

        Label direccionLabel = new Label("Direccion");
        TextField direccionField = new TextField();

        HBox botones = new HBox();
        Button confirmarButton = new Button("Confirmar");
        Button cancelarButton = new Button("Cancelar");

        confirmarButton.getStyleClass().addAll("button-phone", "button-confirm");
        cancelarButton.getStyleClass().addAll("button-phone", "button-cancel");

        confirmarButton.setOnAction(e -> {
            if(controlador.solicitarAñadirPersona(nombreField.getText(), direccionField.getText())) {
                nombreField.clear();
                direccionField.clear();
            }
        });

        cancelarButton.setOnAction(e -> {
            controlador.cerrarVentana(this);
        });

        botones.getChildren().addAll(confirmarButton, cancelarButton);
        botones.setSpacing(40);;
        botones.setAlignment(Pos.CENTER);

        holder.setSpacing(20);
        holder.getChildren().addAll(nombreLabel, nombreField, direccionLabel, direccionField, botones);
        holder.setAlignment(Pos.CENTER);
        setAlignment(holder, Pos.CENTER);

        setCenter(holder);
    }

    public void mostrarModificarPersona(int personaId) {
        VBox holder = new VBox();

        Label nombreLabel = new Label("Nombre");
        TextField nombreField = new TextField();

        Label direccionLabel = new Label("Direccion");
        TextField direccionField = new TextField();

        HBox botones = new HBox();
        Button confirmarButton = new Button("Confirmar");
        Button cancelarButton = new Button("Cancelar");

        confirmarButton.setOnAction(e -> {
            if(controlador.solicitarModificarPersona(personaId, nombreField.getText(), direccionField.getText())) {
                nombreField.clear();
                direccionField.clear();
            }
        });

        cancelarButton.setOnAction(e -> {
            controlador.cerrarVentana(this);
        });

        botones.getChildren().addAll(confirmarButton, cancelarButton);
        botones.setSpacing(40);;
        botones.setAlignment(Pos.CENTER);

        holder.setSpacing(20);
        holder.getChildren().addAll(nombreLabel, nombreField, direccionLabel, direccionField, botones);
        holder.setAlignment(Pos.CENTER);
        setAlignment(holder, Pos.CENTER);

        setCenter(holder);
    }
}
