package edu.up.math.simplexsolver.ui;

import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class SimplexTypeChooserBar extends HBox {

	private ToggleGroup group;
	private RadioButton min;
	private RadioButton max;
	
	public SimplexTypeChooserBar() {
		configure();
		addComponentsToPanel();
	}
	
	private void configure() {
		super.setWidth(800);
		super.setAlignment(Pos.CENTER);
		
		group = new ToggleGroup();
		min = new RadioButton("Mínimo");
		max = new RadioButton("Máximo");
		max.setSelected(true);
		
		min.setToggleGroup(group);
		max.setToggleGroup(group);
	}
	
	private void addComponentsToPanel() {
		getChildren().add(min);
		getChildren().add(max);
	}
	
	public GoalType getType() {
		if (min.isSelected()) {
			return GoalType.MINIMIZE;
		}
		
		return GoalType.MAXIMIZE;
	}
}
