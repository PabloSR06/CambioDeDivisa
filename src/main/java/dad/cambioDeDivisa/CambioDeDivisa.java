package dad.cambioDeDivisa;

import comboBox.Alumno;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDeDivisa extends Application {
	
	private TextField dineroText;
	private TextField salidaText;
	
	private ComboBox<Divisa> DivisaEntrada;
	private ComboBox<Divisa> DivisaSalida;
	
	private Button cambiarButton;
	

	@Override
	public void start(Stage primaryStage) throws Exception {

		dineroText = new TextField();
		dineroText.setMaxWidth(70);
		
		salidaText = new TextField();
		salidaText.setMaxWidth(70);
		salidaText.setEditable(false);
		
		Divisa euro = new Divisa("Euro", 1.0);
		Divisa libra = new Divisa("Libra", 0.8873);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);
		
		DivisaEntrada = new ComboBox<>();
		DivisaEntrada.getItems().addAll(euro, libra, dolar, yen);
		DivisaEntrada.getSelectionModel().select(euro);
		
		DivisaSalida = new ComboBox<>();
		DivisaSalida.getItems().addAll(euro, libra, dolar, yen);
		DivisaSalida.getSelectionModel().select(yen);

		cambiarButton = new Button("Cambiar");
		cambiarButton.setOnAction(e -> onCambiarButtonAction(e));
		
		HBox entradaHbox = new HBox(5, dineroText, DivisaEntrada);
		entradaHbox.setAlignment(Pos.CENTER);
		
		HBox salidaHbox = new HBox(5, salidaText, DivisaSalida);
		salidaHbox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5, entradaHbox, salidaHbox, cambiarButton);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root , 250, 200);
		
		primaryStage.setTitle("Cambio Divisa");
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	
	}

	private void onCambiarButtonAction(ActionEvent e) {

		Divisa origen = DivisaEntrada.getSelectionModel().selectedItemProperty().get();
		Divisa destino = DivisaSalida.getSelectionModel().selectedItemProperty().get();
		
		salidaText.textProperty().setValue(
				String.valueOf(
						destino.fromEuro(
								origen.toEuro(
										Double.parseDouble(
												dineroText.getText())))));

	}

	public static void main(String[] args) {
		launch(args);
	}

}
