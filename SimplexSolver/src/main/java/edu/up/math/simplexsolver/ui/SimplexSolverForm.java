package edu.up.math.simplexsolver.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import org.apache.commons.math3.optim.linear.UnboundedSolutionException;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import edu.up.math.simplexsolver.LinearProgramingSolver;
import edu.up.math.simplexsolver.Result;

public class SimplexSolverForm {
	
	private LinearProgramingSolver solver;
	private SimplexTypeChooserBar typeBar;
	private TextField txtFunction;
	private TextArea txtSubject;
	private TextField txtSolution;
	private Button btnSolve;
	private GridPane rootPanel;
	
	public SimplexSolverForm() {
		configure();
	}
	
	private void configure() {
		
		solver = new LinearProgramingSolver();
		
		rootPanel = new GridPane();
		rootPanel.setPadding(new Insets(5));
		rootPanel.setVgap(5);
		rootPanel.setHgap(5);
		rootPanel.setAlignment(Pos.CENTER);
		
		typeBar = new SimplexTypeChooserBar();
		GridPane.setColumnSpan(typeBar, 2);
		rootPanel.add(typeBar, 0, 0);
		
		txtFunction = new TextField();
		txtFunction.setPromptText("Exemplo: 100x + 4y + 2z");
		txtFunction.setTooltip(createToolTip("Exemplo: 100x + 4y + 2z"));
		txtFunction.setPrefWidth(400);
		
		rootPanel.add(createLabel("Função:"), 0, 1);
		rootPanel.add(txtFunction, 1, 1);
		
		txtSubject = new TextArea();
		txtSubject.setPrefRowCount(10);
		txtSubject.setPrefColumnCount(100);
		txtSubject.setPrefWidth(400);
		
		rootPanel.add(createLabel("Sujeito á:"), 0, 2);
		rootPanel.add(txtSubject, 1, 2);
		
		btnSolve = new Button("Resolver :)");
		btnSolve.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				solve();
			}
		});
		
		btnSolve.setTooltip(createToolTip("Tomará que de certo!!!"));
		GridPane.setColumnSpan(btnSolve, 2);
		rootPanel.add(btnSolve, 0, 3);
		GridPane.setHalignment(btnSolve, HPos.CENTER);
		
		txtSolution = new TextField();
		txtSolution.setEditable(false);
		txtSolution.setPrefWidth(400);
		
		rootPanel.add(createLabel("Solução:"), 0, 4);
		rootPanel.add(txtSolution, 1, 4);
		
	}
	
	private void solve() {
		try {
			GoalType type = typeBar.getType();
			Result result = solver.solve(type, txtFunction.getText(), txtSubject.getText());
			txtSolution.setText(result.toString());
		} catch (UnboundedSolutionException ex) {
			txtSolution.setText("Não existe solução");
		} catch (Exception ex) {
			txtSolution.setText("Erro ao calcular. Verifique os valores digitados!");
		}
	}
	
	private Tooltip createToolTip(String text) {
		Tooltip tooltip = new Tooltip(text);
		return tooltip;
	}
	
	private Label createLabel(String text) {
		Label label = new Label(text);
		return label;
	}
	
	public void debugMode(boolean debug) {
		if (debug) {
			final String cssDefault = "-fx-border-color: blue;\n"
	                + "-fx-border-insets: 5;\n"
	                + "-fx-border-width: 3;\n"
	                + "-fx-border-style: dashed;\n";
			
			rootPanel.setStyle(cssDefault);
		}
	}
	
	public Pane getPanel() {
		return rootPanel;
	}
	
}
