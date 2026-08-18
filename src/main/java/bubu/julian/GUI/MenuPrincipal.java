package bubu.julian.GUI;

import bubu.julian.Controlador;
import bubu.julian.EnlaceBD;
import bubu.julian.Persona;
import bubu.julian.Telefono;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        setTop(crearTitulo());
        setCenter(crearTablaPersonas());
        setRight(crearTablaTelefonos(-1));
        setBottom(crearBotonAñadir());
    }
    
    public void actualizar() {
        setCenter(crearTablaPersonas());
        setRight(crearTablaTelefonos(-1));
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
        
        colId.setPrefWidth(30);
        colNombre.setPrefWidth(400);
        colDireccion.setPrefWidth(400);
        tabla.setPrefWidth(830);
        tabla.setMaxWidth(830);


        tabla.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
            if (seleccionado != null) {
                setRight(crearTablaTelefonos(seleccionado.getId()));
            }
        });


        tabla.getColumns().addAll(colId, colNombre, colDireccion);
        tabla.setItems(FXCollections.observableArrayList(controlador.recuperarPersonas()));

        setAlignment(tabla, Pos.CENTER);
        return tabla;
    }

    private TableView crearTablaTelefonos(int personaId) {
        TableView<Telefono> tabla = new TableView<Telefono>();

        TableColumn<Telefono, String> colTelefono = new TableColumn<>();

        colTelefono.setText("Telefono");

        colTelefono.setCellValueFactory(new PropertyValueFactory<Telefono, String>("numTelefono"));

        colTelefono.setPrefWidth(300);
        tabla.setPrefWidth(300);
        tabla.setMaxWidth(300);

        tabla.getColumns().addAll(colTelefono);

        if (personaId != -1) {
            tabla.setItems(FXCollections.observableArrayList(controlador.recuperarTelefonosDePersona(personaId)));
        }

        setAlignment(tabla, Pos.CENTER);
        return tabla;
    }

    private Button crearBotonAñadir() {
        Button añadirButton = new Button("Añadir");
        
        setAlignment(añadirButton, Pos.CENTER);

        añadirButton.setOnAction(e -> {
            controlador.mostrarVentanaAñadir();
        });

        return añadirButton;
    }

    private Label crearTitulo() {
        Label titulo = new Label("Consulta de Personas");

        setAlignment(titulo, Pos.CENTER);

        return titulo;
    }
}
