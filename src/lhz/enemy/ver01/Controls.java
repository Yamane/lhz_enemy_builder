package lhz.enemy.ver01;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Controls {

	public static Font baseFont = Font.font("Meiryo UI", 12);
	public static Font middleFont = Font.font("Meiryo UI", 14);
	public static Font titleFont = Font.font("Meiryo UI", FontWeight.BOLD, 18);
	public static Font subTitleFont = Font.font("Meiryo UI", FontWeight.BOLD, 14);

	public static Paint BLACK = Paint.valueOf("#000");
	public static Paint WHITE = Paint.valueOf("#fff");

	public static Label createTitleLabel(String text) {
		Label label = new Label(text);
		label.setFont(titleFont);
		label.setTextFill(BLACK);
		return label;
	}

	public static Label createSubTitleLabel(String text) {
		Label label = new Label(text);
		label.setFont(subTitleFont);
		label.setStyle("-fx-padding: 0 0 2 0;");
		label.setTextFill(BLACK);
		return label;
	}

	public static Label createMiddleLabel(String text) {
		Label label = new Label(text);
		label.setFont(middleFont);
		label.setTextFill(BLACK);
		label.setStyle("-fx-padding: 0 0 5 0;");
		return label;
	}

	public static Label createLabel(String text) {
		return createLabel(text, BLACK);
	}

	public static Label createLabel(String text, Paint color) {
		Label label = new Label(text);
		label.setFont(baseFont);
		label.setTextFill(color);
		return label;
	}

	public static <T> ChoiceBox<T> createChoiceBox(Class<T> targetClass, int width) {
		ChoiceBox<T> choice = new ChoiceBox<T>();
		choice.setMaxSize(width, 24);
		choice.setPrefSize(width, 24);
		choice.setMinSize(width, 24);
		choice.setStyle("-fx-font: 12px \"Meiryo UI\";");
		return choice;
	}

	public static TilePane createHead(String text, int width, int height) {
		TilePane head = new TilePane(createLabel(text, WHITE));
		head.setPrefSize(width, height);
		head.setAlignment(Pos.CENTER);
		head.setStyle("-fx-background-color: #666;");
		return head;
	}

	public static TilePane createCell(Node node, int width, int height) {
		TilePane cell = new TilePane(node);
		if(node instanceof Label) {
			((Label) node).setMaxWidth(width - 14);
			((Label) node).setWrapText(true);
		}
		cell.setPrefSize(width, height);
		cell.setAlignment(Pos.CENTER_LEFT);
		cell.setStyle("-fx-background-color: #ccc;-fx-padding: 5;");
		return cell;
	}

	public static TilePane createBlank() {
		TilePane blank = new TilePane();
		blank.setMaxSize(0, 0);
		return blank;
	}

	public static TilePane createCell(String text, int width, int height) {
		return createCell(createLabel(text, BLACK), width, height);
	}
}
