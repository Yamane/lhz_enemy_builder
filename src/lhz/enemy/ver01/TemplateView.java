package lhz.enemy.ver01;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lhz.enemy.EtvUtil;
import lhz.enemy.model.Enemy;
import lhz.enemy.ver01.list.EnemyListController;
import lhz.enemy.ver01.template.ArrangeSkillControler;
import lhz.enemy.ver01.template.BasicDataController;
import lhz.enemy.ver01.template.DefaultSkillControler;
import lhz.enemy.ver01.template.ParameterController;

public class TemplateView extends Application {

	private Stage primaryStage;

	private TabPane tabs;

	private GridPane grid;
	private AnchorPane anchor;
	private ScrollPane scroll;

	private ParameterController parameters;
	private BasicDataController basic;
	private DefaultSkillControler defaultSkills;
	private ArrangeSkillControler arrangeSkills;

	private EnemyListController enemyList;

	private Button button;

	private Enemy enemy;

	@Override
	public void start(Stage stage) {

		EtvUtil.loadNarrowRaceClasses("lhz.enemy.model.narrow");

		this.enemy = new Enemy();

		this.primaryStage = stage;
        this.primaryStage.setTitle("LHZ エネミー図鑑 ver_0.2");
        this.primaryStage.setMaxWidth(810);
        this.primaryStage.setMinWidth(810);

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);

		// パラメータ設定のやつ
        parameters = new ParameterController(enemy);
        // 能力値表示のやつ
        basic = new BasicDataController(enemy);
        parameters.setBasicController(basic);
        // 特技(自動取得)
        defaultSkills = new DefaultSkillControler(enemy);
        parameters.setSkillController(defaultSkills);
        // 特技(アレンジ)
        arrangeSkills = new ArrangeSkillControler(enemy);
        parameters.setSkillController(arrangeSkills);

        grid.add(parameters.getPane(), 0, 0);
        grid.add(basic.getPane(), 0, 1);
        grid.add(defaultSkills.getPane(), 0, 2);
        grid.add(arrangeSkills.getPane(), 0, 3);

        grid.add(createButtonBlock(), 0, 4);

        scroll = new ScrollPane();
        scroll.setPadding(new Insets(20, 0, 0, 0));
        scroll.setContent(grid);

        tabs = new TabPane();
        tabs.setTabMinWidth(140);

        Tab tab1 = new Tab("テンプレート");
        tab1.setClosable(false);
        tab1.setContent(scroll);
        tabs.getTabs().add(tab1);

        anchor = new AnchorPane();
        AnchorPane.setTopAnchor(anchor, 0d);
        AnchorPane.setBottomAnchor(anchor, 0d);
        AnchorPane.setLeftAnchor(anchor, 0d);
        AnchorPane.setRightAnchor(anchor, 0d);

        enemyList = new EnemyListController();
        anchor.getChildren().add(enemyList.getTableView(stage));

        Tab tab2 = new Tab("エネミーデータ");
        tab2.setClosable(false);
        tab2.setContent(anchor);
        tabs.getTabs().add(tab2);

        Scene scene = new Scene(tabs, 800, 600);
        primaryStage.setScene(scene);

		primaryStage.show();
	}

	private GridPane createButtonBlock() {
		button = new Button("クリップボードにコピー");
		button.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	ClipboardContent content = new ClipboardContent();
		    	content.putString(enemy.createClipboardData());
		    	Clipboard.getSystemClipboard().setContent(content);
		    }
    	});

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(0, 10, 10, 10));
        grid.add(button, 0, 0);
		return grid;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
