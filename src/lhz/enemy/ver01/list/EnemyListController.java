package lhz.enemy.ver01.list;

import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lhz.enemy.EtvUtil;
import lhz.enemy.ver01.Controls;

public class EnemyListController {

	Stage primaryStage;

	private ObservableList<JsonEnemy> enemyList;

	private TableView<JsonEnemy> table;
	private TableColumn<JsonEnemy, Integer> rankCol;
	private TableColumn<JsonEnemy, String> nameCol;
	private TableColumn<JsonEnemy, String> tagCol;

	public EnemyListController() {

		List<Map<String, Object>> mapList = EtvUtil.getEnemyList();
		enemyList = FXCollections.observableArrayList();
        for (Map<String, Object> map : mapList) {
        	enemyList.add(new JsonEnemy(map));
		}
        SortedList<JsonEnemy> sortedList = new SortedList<JsonEnemy>(enemyList);
		table = new TableView<>(sortedList);
		sortedList.comparatorProperty().bind(table.comparatorProperty());

        AnchorPane.setTopAnchor(table, 10d);
        AnchorPane.setBottomAnchor(table, 10d);
        AnchorPane.setLeftAnchor(table, 10d);
        AnchorPane.setRightAnchor(table, 10d);

		rankCol = new TableColumn<>("ランク");
		rankCol.setMinWidth(30);
		rankCol.setStyle("-fx-alignment: center;");
		rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		table.getColumns().add(rankCol);

		nameCol = new TableColumn<>("名称");
		nameCol.setSortable(false);
		nameCol.setMinWidth(400);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		table.getColumns().add(nameCol);

		tagCol = new TableColumn<>("タグ");
		tagCol.setSortable(false);
		tagCol.setMinWidth(300);
		tagCol.setCellValueFactory(new PropertyValueFactory<>("tags"));
		table.getColumns().add(tagCol);

		table.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(event.getEventType().getName().equals("MOUSE_CLICKED")) {
					MouseEvent me = (MouseEvent) event;
					EventTarget target = me.getTarget();
					String targetStr = target.getClass().toString();
					if(targetStr.endsWith("LabeledText") || targetStr.contains("control.TableColumn")) {
						if(me.getButton().equals(MouseButton.PRIMARY) && me.getClickCount() == 2) {
							JsonEnemy record = table.getFocusModel().getFocusedItem();

							Stage newStage = new Stage();
							newStage.initStyle(StageStyle.UTILITY);
				            newStage.initModality(Modality.NONE);
				            newStage.initOwner(primaryStage);
				            newStage.setMaxWidth(815);
				            newStage.setMinWidth(815);
				            newStage.setTitle(record.getName());

				            GridPane root = new GridPane();
				            root.setAlignment(Pos.TOP_LEFT);
				            root.add(createBasic(record), 0, 1);
				            root.add(createSkills(record), 0, 2);
				            root.add(createItems(record), 0, 3);

				            Button button = new Button("クリップボードにコピー");
				            button.setFocusTraversable(false);
				    		button.setOnAction(new EventHandler<ActionEvent>() {
				    		    @Override
				    		    public void handle(ActionEvent e) {
				    		    	ClipboardContent content = new ClipboardContent();
				    		    	content.putString(record.createClipboardData());
				    		    	Clipboard.getSystemClipboard().setContent(content);
				    		    }
				        	});

				    		GridPane grid = new GridPane();
				    		grid.setAlignment(Pos.TOP_CENTER);
				            grid.setPadding(new Insets(10, 0, 10, 0));
				            grid.add(button, 0, 0);
				            root.add(grid, 0, 4);

				            ScrollPane scroll = new ScrollPane();
				            scroll.setContent(root);

				            newStage.setScene(new Scene(scroll, 800, 600));
				            newStage.show();
						}
					}
				}
			}
		});
	}

	public TableView<JsonEnemy> getTableView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		return table;
	}

	private GridPane createBasic(JsonEnemy r) {
        GridPane basic = new GridPane();
        basic.setAlignment(Pos.TOP_LEFT);
        basic.setHgap(1);
        basic.setVgap(1);
        basic.setPadding(new Insets(10, 0, 10, 10));

        int cellHeight = 24;
        int row = 0;
        basic.add(Controls.createTitleLabel(r.getName()), 0, row, 6, 1);
        row++;
        basic.add(Controls.createMiddleLabel(String.format("ランク：%d    タグ：%s   識別難易度：%s", r.getRank(), r.getTags(), r.getNotoriety())), 0, row, 6, 1);
        row++;
        basic.add(Controls.createHead("STR", 80, cellHeight), 0, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("strength")), 105, cellHeight), 1, row);
        basic.add(Controls.createHead("DEX", 80, cellHeight), 2, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("dexterity")), 105, cellHeight), 3, row);
        basic.add(Controls.createHead("POW", 80, cellHeight), 4, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("power")), 105, cellHeight), 5, row);
        basic.add(Controls.createHead("INT", 85, cellHeight), 6, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("intelligence")), 108, cellHeight), 7, row);
        row++;
        basic.add(Controls.createHead("回避", 80, cellHeight), 0, row);
        basic.add(Controls.createCell(Controls.createLabel(r.getAvoid()), 105, cellHeight), 1, row);
        basic.add(Controls.createHead("抵抗", 80, cellHeight), 2, row);
        basic.add(Controls.createCell(Controls.createLabel(r.getResist()), 105, cellHeight), 3, row);
        basic.add(Controls.createHead("物理防御力", 80, cellHeight), 4, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("physical_defense")), 105, cellHeight), 5, row);
        basic.add(Controls.createHead("魔法防御力", 80, cellHeight), 6, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("magic_defense")), 108, cellHeight), 7, row);
        row++;
        basic.add(Controls.createHead("最大HP", 80, cellHeight), 0, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("hit_point")), 105, cellHeight), 1, row);
        basic.add(Controls.createHead("ヘイト倍率", 80, cellHeight), 2, row);
        basic.add(Controls.createCell(Controls.createLabel(r.getHate()), 105, cellHeight), 3, row);
        basic.add(Controls.createHead("行動力", 80, cellHeight), 4, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("action")), 105, cellHeight), 5, row);
        basic.add(Controls.createHead("移動力", 80, cellHeight), 6, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("move")), 108, cellHeight), 7, row);
        row++;
        basic.add(Controls.createHead("因果力", 80, cellHeight), 0, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("fate")), 105, cellHeight), 1, row);
        basic.add(Controls.createHead("", 80, cellHeight), 2, row);
        basic.add(Controls.createCell(Controls.createLabel(""), 105, cellHeight), 3, row);
        basic.add(Controls.createHead("", 80, cellHeight), 4, row);
        basic.add(Controls.createCell(Controls.createLabel(""), 105, cellHeight), 5, row);
        basic.add(Controls.createHead("", 80, cellHeight), 6, row);
        basic.add(Controls.createCell(Controls.createLabel(""), 108, cellHeight), 7, row);
        row++;
        basic.add(Controls.createHead("解説", 80, cellHeight), 0, row);
        basic.add(Controls.createCell(Controls.createLabel(r.map("contents")), 674, cellHeight), 1 ,row, 7, 1);

		return basic;
	}

	private GridPane createSkills(JsonEnemy r) {
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10, 0, 10, 10));
        grid.add(Controls.createSubTitleLabel("▼特技"), 0, 0);
        int row = 1;
        for (Map<String, Object> skill : r.getSkills()) {
        	grid.add(createSkill(new JsonSkill(skill)), 0, row);
        	row++;
		}
        return grid;
	}

	private GridPane createSkill(JsonSkill s) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(1);
		pane.setVgap(1);
		pane.setPadding(new Insets(0, 0, 7, 0));
        int cellHeight = 24;

		pane.add(Controls.createHead("名称", 60, cellHeight), 0, 0);
		pane.add(Controls.createCell(Controls.createLabel(s.map("name")), 287, cellHeight), 1, 0, 3, 1);
		pane.add(Controls.createHead("タグ", 40, cellHeight), 4, 0);
		pane.add(Controls.createCell(Controls.createLabel(s.getTags()), 364, cellHeight), 5 ,0, 5, 1);
		pane.add(Controls.createHead("タイミング", 60, cellHeight), 0, 1);
		pane.add(Controls.createCell(Controls.createLabel(s.map("timing")), 110, cellHeight), 1 ,1);
		pane.add(Controls.createHead("判定", 40, cellHeight), 2, 1);
		pane.add(Controls.createCell(Controls.createLabel(s.map("role")), 145, cellHeight), 3 ,1);
		pane.add(Controls.createHead("対象", 40, cellHeight), 4, 1);
		pane.add(Controls.createCell(Controls.createLabel(s.map("target")), 120, cellHeight), 5 ,1);
		pane.add(Controls.createHead("射程", 40, cellHeight), 6, 1);
		pane.add(Controls.createCell(Controls.createLabel(s.map("range")), 50, cellHeight), 7 ,1);
		pane.add(Controls.createHead("制限", 40, cellHeight), 8, 1);
		pane.add(Controls.createCell(Controls.createLabel(s.map("limit")), 100, cellHeight), 9 ,1);
		pane.add(Controls.createHead("効果", 60, cellHeight), 0, 2);
		pane.add(Controls.createCell(Controls.createLabel(s.map("function")), 694, cellHeight), 1 ,2, 9, 1);

		return pane;
	}

	private GridPane createItems(JsonEnemy r) {
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10, 0, 10, 10));
        grid.add(Controls.createSubTitleLabel("▼ドロップ品"), 0, 0);

		GridPane pane = new GridPane();
		pane.setHgap(1);
		pane.setVgap(1);
        int row = 0;
        int cellHeight = 24;
        for (Map<String, Object> item : r.getItems()) {
        	pane.add(Controls.createCell(Controls.createLabel(item.get("dice").toString()), 80, cellHeight), 0, row);
        	pane.add(Controls.createCell(Controls.createLabel(item.get("item").toString()), 674, cellHeight), 1, row);
        	row++;
		}
    	grid.add(pane, 0, 1);
       return grid;
	}
}
