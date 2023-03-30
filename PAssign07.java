package portfolio;

/**
 * File:PAssign07.java
 * Class: CSCI 1302
 * Assignment #: 06
 * Author: Alex Perez
 * Created on:  Oct 30, 2022
 * Last modified on: Nov 2, 2022
 * Description: Using JavaFX, I created an alarm home security system
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

class HomeSecurity extends KeyPadPane {

	// data member
	private boolean unlocked = false;

	// Textfield instance
	TextField input = new TextField();

	// default contructor
	public HomeSecurity() {

		// keyPadPane instance
		KeyPadPane pane = new KeyPadPane();

		// button styling
		btnBlank1.setText("C");
		btnBlank2.setText("<");
		btnBlank1.setStyle("-fx-background-color: green");
		btnBlank2.setStyle("-fx-background-color: yellow");

		// calls the event handling method
		registerEventHandlers();
	}

	@Override
	protected void registerEventHandlers() {

		// check if we are using phone layout
		ArrayList<Button> currList = new ArrayList<Button>();
		currList = listButtons;

		// variable
		String key = "1234";

		// for loop: retrives button input from keypad, clears textfield if < is clicked and if c is clicked and matches with code, alarm unlocks if not alarm is locked
		for (int i = 0; i < currList.size(); i++) {
			if (currList.get(i).getText().equals("C")) {
				btnBlank1.setOnAction(e -> {
					if (input.getText().equals(key)) {
						input.setText("DISABLED!");
					} else {
						input.setText("LOCKED!");
					}
				});

			} else if (currList.get(i).getText().equals("<")) {
				btnBlank2.setOnAction(e -> {
					input.setText(" ");
				});
			} else {
				currList.get(i).setOnAction(e -> {
					input.setText(input.getText() + ((Button)e.getSource()).getText());
				});
			}
		}

		// free up memory
		currList = null;
	}

	// text fields accessor
	public TextField getInput() {
		return input;
	}

	// text fields mutator
	public void setInput(TextField input) {
		this.input = input;
	}
}

public class PAssign07 extends Application {

	Circle redCir = new Circle(7);
	Circle greenCir = new Circle(7);
	HomeSecurity alarm = new HomeSecurity();
	TextField input = new TextField();

	@Override // overrides start method in the Application class
	public void start(Stage primaryStage) {

		// input is set to alarm
		alarm.setInput(input);

		//alarm.setAlignment(Pos); // set alignment to CENTER right
		alarm.setVgap(5); // set vertical gap to 5
		alarm.setHgap(5); // set horizontal to 5
		alarm.setPadding(new Insets(30)); // set padding to 10

		// Circle styling
		redCir.setFill(Color.DARKRED);
		greenCir.setFill(Color.DARKGREEN);

		// VBox 
		VBox vbox = new VBox();
		
		// add circles to vbox
		vbox.getChildren().add(greenCir);
		vbox.getChildren().add(redCir);

		// vbox styling
		vbox.setAlignment(Pos.TOP_LEFT);
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(5);

		// hbox
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(vbox);
		input.setMinSize(46, 20);
		hbox.getChildren().add(input);

		// gridpane
		GridPane gridpane = new GridPane();
		gridpane.add(hbox,0,0);
		gridpane.add(alarm,1, 0);

		// gridpan styling
		gridpane.setHgap(50);
		gridpane.setStyle("-fx-background-color: Darkgray; -fx-border-color: black; -fx-border-style: solid; -fx-border-width: 20px");

		// Scene instance 
		Scene scene = new Scene(gridpane,300,200);
		primaryStage.setResizable(false); // set stage to non resizable
		primaryStage.setTitle("Home Security System"); // set title
		primaryStage.setScene(scene); // add scene to stage
		primaryStage.show(); // display stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}

