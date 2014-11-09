package edu.up.math.simplexsolver.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SimplexSolverApplication extends Application {

	private SimplexSolverForm form;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		form = new SimplexSolverForm();
		
		BorderPane root = new BorderPane(form.getPanel());
		Scene scene = new Scene(root, 600, 400);
		
		primaryStage.setTitle("Gigi Complex - Calculadora Simplex");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
