package bubu.julian.GUI;

import bubu.julian.Controlador;
import bubu.julian.EnlaceBD;
import bubu.julian.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * MenuPrincipal
 */
public class MenuPrincipal extends BorderPane {

    Controlador controlador;

    public MenuPrincipal(Controlador controlador) {
        this.controlador = controlador;
        setCenter(crearTablaPersonas());
        setBottom(crearBotonAñadir());
    }

    private TableView crearTablaPersonas() {
        TableView<Persona> tabla = new TableView<Persona>();

        TableColumn<Persona, Integer> colId = new TableColumn<>();
        TableColumn<Persona, String> colNombre = new TableColumn<>();
        TableColumn<Persona, String> colDireccion = new TableColumn<>();

        colId.setText("ID");
        colNombre.setText("Nombre");
        colDireccion.setText("Dirección");

        colId.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Persona, String>("direccion"));

        tabla.getColumns().addAll(colId, colNombre, colDireccion);
        tabla.setItems(FXCollections.observableArrayList(controlador.recuperarPersonas()));

        return tabla;
    }

    private Button crearBotonAñadir() {
        Button añadirButton = new Button("Añadir");
        
        setAlignment(añadirButton, Pos.CENTER);

        añadirButton.setOnAction(e -> {
            
        });

        return añadirButton;
    }
}
