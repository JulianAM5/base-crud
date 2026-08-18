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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * MenuPrincipal
 */
public class MenuPrincipal extends BorderPane {

    private Controlador controlador;

    private String telefonoSeleccionado;
    private int idPersonaSeleccionada;

    public MenuPrincipal(Controlador controlador) {
        this.controlador = controlador;
        setTop(crearTitulo());
        setCenter(crearTablaPersonas());
        setRight(crearTablaTelefonos(-1));
        idPersonaSeleccionada = -1;
    }
    
    public void actualizar() {
        setCenter(crearTablaPersonas());
        setRight(crearTablaTelefonos(-1));
        idPersonaSeleccionada = -1;
    }

    private VBox crearTablaPersonas() {
        VBox holder = new VBox();
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
                idPersonaSeleccionada = seleccionado.getId();
            }
        });


        tabla.getColumns().addAll(colId, colNombre, colDireccion);
        tabla.setItems(FXCollections.observableArrayList(controlador.recuperarPersonas()));

        HBox buttonHolder = new HBox();
        buttonHolder.setAlignment(Pos.CENTER);
        buttonHolder.setSpacing(30);
        Button añadirButton = new Button("Añadir");
        Button modificarButton = new Button("Modificar");
        Button eliminarButton = new Button("Eliminar");
        buttonHolder.getChildren().addAll(añadirButton, modificarButton, eliminarButton);


        añadirButton.setId("button_pos");
        eliminarButton.setId("button_neg");
        modificarButton.setId("button_mod");

        añadirButton.getStyleClass().addAll("button-phone", "button-confirm");
        eliminarButton.getStyleClass().addAll("button-phone", "button-cancel");
        modificarButton.getStyleClass().addAll("button-phone", "button-blue");

        añadirButton.setOnAction(e -> {
            controlador.mostrarVentanaAñadir();
        });

        modificarButton.setOnAction(e -> {
            if (idPersonaSeleccionada == -1) { return; }

            controlador.mostrarVentanaModificar(idPersonaSeleccionada);
        });

        eliminarButton.setOnAction(e -> {
            controlador.solicitarEliminarPersona(idPersonaSeleccionada);
        });

        setAlignment(tabla, Pos.CENTER);
        holder.getChildren().addAll(tabla, buttonHolder);
        holder.setAlignment(Pos.CENTER);
        holder.setSpacing(10);
        return holder;
    }

    private VBox crearTablaTelefonos(int personaId) {
        VBox holder = new VBox();
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

        tabla.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
            if (seleccionado != null) {
                telefonoSeleccionado = seleccionado.getNumTelefono();
            }
        });

        Button añadirButton = new Button("Añadir");
        añadirButton.setId("button_pos");

        Button eliminarButton = new Button("Eliminar");
        eliminarButton.setId("button_neg");

        HBox buttonHolder = new HBox();
        buttonHolder.getChildren().addAll(añadirButton, eliminarButton);
        buttonHolder.setAlignment(Pos.CENTER);
        buttonHolder.setSpacing(30);


        añadirButton.getStyleClass().addAll("button-phone", "button-confirm");
        eliminarButton.getStyleClass().addAll("button-phone", "button-cancel");

        añadirButton.setOnAction(e -> {
            if (idPersonaSeleccionada == -1) { return; }

            controlador.mostrarVentanaAñadirTelefono(personaId);
        });

        eliminarButton.setOnAction(e -> {
            if (telefonoSeleccionado.isEmpty()) { return; }

            controlador.solicitarEliminarTelefonoAPersona(personaId, telefonoSeleccionado);
        });

        setAlignment(tabla, Pos.CENTER);
        holder.setAlignment(Pos.CENTER);
        holder.setSpacing(10);
        holder.getChildren().addAll(tabla, buttonHolder);

        return holder;
    }

    private Label crearTitulo() {
        Label titulo = new Label("Consulta de Personas");

        setAlignment(titulo, Pos.CENTER);

        return titulo;
    }
}
