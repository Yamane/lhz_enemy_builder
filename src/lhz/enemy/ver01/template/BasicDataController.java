package lhz.enemy.ver01.template;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lhz.enemy.model.Enemy;
import lhz.enemy.ver01.Controls;

public class BasicDataController {

	private GridPane grid;

    private Enemy enemy;

	private Label titleLabel;
	private Label tagLabel;

	private Label strLabel;
	private Label dexLabel;
	private Label powLabel;
	private Label intLabel;
	private Label avoidLabel;
	private Label resistLabel;
	private Label physicalDefenseLabel;
	private Label magicDefenseLabel;
	private Label hitPointLabel;
	private Label hateLabel;
	private Label actionLabel;
	private Label moveLabel;
	private Label fateLabel;
	private Label goldLabel;


	public BasicDataController(Enemy enemy) {
		this.enemy = enemy;
		createLayout();
	}

	public Pane getPane() {
		return grid;
	}

	public void setOverview() {
		titleLabel.setText(enemy.getType().label());
		tagLabel.setText(getTagData());
    	strLabel.setText(enemy.getStr().toString());
    	dexLabel.setText(enemy.getDex().toString());
    	powLabel.setText(enemy.getPow().toString());
    	intLabel.setText(enemy.getInt().toString());
    	avoidLabel.setText(enemy.getAvoidLabel());
    	resistLabel.setText(enemy.getResistLabel());
    	physicalDefenseLabel.setText(enemy.getPhysicalDefense().toString());
    	magicDefenseLabel.setText(enemy.getMagicDefense().toString());
    	hitPointLabel.setText(enemy.getHitPoint().toString());
    	hateLabel.setText(enemy.getHateLabel());
    	actionLabel.setText(enemy.getAction().toString());
    	moveLabel.setText(enemy.getMove().toString());
    	fateLabel.setText(enemy.getFate().toString());
    	goldLabel.setText(enemy.getGold().toString() + "G");
	}

	private void createLayout() {
        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(10, 0, 10, 10));

        int cellHeight = 24;
        int row = 0;
        titleLabel = Controls.createTitleLabel("エネミータイトル");
        grid.add(titleLabel, 0, row, 6, 1);
        row++;
        tagLabel = Controls.createMiddleLabel("ランク：30    タグ：［ボス］［自然］［冷気］");
        grid.add(tagLabel, 0, row, 6, 1);
        row++;
        grid.add(Controls.createHead("STR", 80, cellHeight), 0, row);
        grid.add(Controls.createCell(strLabel = Controls.createLabel("999"), 105, cellHeight), 1, row);
        grid.add(Controls.createHead("DEX", 80, cellHeight), 2, row);
        grid.add(Controls.createCell(dexLabel = Controls.createLabel("999"), 105, cellHeight), 3, row);
        grid.add(Controls.createHead("POW", 80, cellHeight), 4, row);
        grid.add(Controls.createCell(powLabel = Controls.createLabel("999"), 105, cellHeight), 5, row);
        grid.add(Controls.createHead("INT", 85, cellHeight), 6, row);
        grid.add(Controls.createCell(intLabel = Controls.createLabel("999"), 108, cellHeight), 7, row);
        row++;
        grid.add(Controls.createHead("回避", 80, cellHeight), 0, row);
        grid.add(Controls.createCell(avoidLabel = Controls.createLabel("999"), 105, cellHeight), 1, row);
        grid.add(Controls.createHead("抵抗", 80, cellHeight), 2, row);
        grid.add(Controls.createCell(resistLabel = Controls.createLabel("999"), 105, cellHeight), 3, row);
        grid.add(Controls.createHead("物理防御力", 80, cellHeight), 4, row);
        grid.add(Controls.createCell(physicalDefenseLabel = Controls.createLabel("999"), 105, cellHeight), 5, row);
        grid.add(Controls.createHead("魔法防御力", 80, cellHeight), 6, row);
        grid.add(Controls.createCell(magicDefenseLabel = Controls.createLabel("999"), 108, cellHeight), 7, row);
        row++;
        grid.add(Controls.createHead("最大HP", 80, cellHeight), 0, row);
        grid.add(Controls.createCell(hitPointLabel = Controls.createLabel("999"), 105, cellHeight), 1, row);
        grid.add(Controls.createHead("ヘイト倍率", 80, cellHeight), 2, row);
        grid.add(Controls.createCell(hateLabel = Controls.createLabel("999"), 105, cellHeight), 3, row);
        grid.add(Controls.createHead("行動力", 80, cellHeight), 4, row);
        grid.add(Controls.createCell(actionLabel = Controls.createLabel("999"), 105, cellHeight), 5, row);
        grid.add(Controls.createHead("移動力", 80, cellHeight), 6, row);
        grid.add(Controls.createCell(moveLabel = Controls.createLabel("999"), 108, cellHeight), 7, row);
        row++;
        grid.add(Controls.createHead("因果力", 80, cellHeight), 0, row);
        grid.add(Controls.createCell(fateLabel = Controls.createLabel("999"), 105, cellHeight), 1, row);
        grid.add(Controls.createHead("ドロップ期待値", 80, cellHeight), 2, row);
        grid.add(Controls.createCell(goldLabel = Controls.createLabel("999"), 105, cellHeight), 3, row);
        grid.add(Controls.createHead("", 80, cellHeight), 4, row);
        grid.add(Controls.createCell(Controls.createLabel(""), 105, cellHeight), 5, row);
        grid.add(Controls.createHead("", 80, cellHeight), 6, row);
        grid.add(Controls.createCell(Controls.createLabel(""), 108, cellHeight), 7, row);
        row++;
	}

	private String getTagData() {
		StringBuilder b = new StringBuilder();
		b.append("ランク：").append(enemy.getCharacterRank());
		b.append("    タグ：");
		for (String tag : enemy.getTags()) {
			b.append("［").append(tag).append("］");

		}
		b.append("   識別難易度：").append(enemy.getNotorietyLabel());
		return b.toString();
	}
}
