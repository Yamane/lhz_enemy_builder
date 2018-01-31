package lhz.enemy.ver01.template;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.ver01.Controls;

public class ArrangeSkillControler {

	private GridPane grid;

	private Enemy enemy;

	public ArrangeSkillControler(Enemy enemy) {
		this.enemy = enemy;
		createLayout();
	}

	public Pane getPane() {
		return grid;
	}

	private void createLayout() {
        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10, 0, 10, 10));
        setOverview();
	}

	private Pane createSkillPane(EnemySkill skill) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(1);
		pane.setVgap(1);
		pane.setPadding(new Insets(0, 0, 7, 0));
        int cellHeight = 24;
        StringBuilder tagLabel = new StringBuilder();
        for (String tag : skill.getTags()) {
        	tagLabel.append("［").append(tag).append("］");
		}

		pane.add(Controls.createHead("名称", 60, cellHeight), 0, 0);
		pane.add(Controls.createCell(Controls.createLabel(skill.getName()), 287, cellHeight), 1, 0, 3, 1);
		pane.add(Controls.createHead("タグ", 40, cellHeight), 4, 0);
		pane.add(Controls.createCell(Controls.createLabel(tagLabel.toString()), 364, cellHeight), 5 ,0, 5, 1);
		pane.add(Controls.createHead("タイミング", 60, cellHeight), 0, 1);
		pane.add(Controls.createCell(Controls.createLabel(skill.getTiming()), 110, cellHeight), 1 ,1);
		pane.add(Controls.createHead("判定", 40, cellHeight), 2, 1);
		pane.add(Controls.createCell(Controls.createLabel(skill.getRole()), 145, cellHeight), 3 ,1);
		pane.add(Controls.createHead("対象", 40, cellHeight), 4, 1);
		pane.add(Controls.createCell(Controls.createLabel(skill.getTarget()), 120, cellHeight), 5 ,1);
		pane.add(Controls.createHead("射程", 40, cellHeight), 6, 1);
		pane.add(Controls.createCell(Controls.createLabel(skill.getRange()), 50, cellHeight), 7 ,1);
		pane.add(Controls.createHead("制限", 40, cellHeight), 8, 1);
		pane.add(Controls.createCell(Controls.createLabel(skill.getLimit()), 100, cellHeight), 9 ,1);
		pane.add(Controls.createHead("効果", 60, cellHeight), 0, 2);
		pane.add(Controls.createCell(Controls.createLabel(skill.getFunction()), 694, cellHeight), 1 ,2, 9, 1);

		return pane;
	}

	public void setOverview() {
		grid.getChildren().removeAll(grid.getChildren());
        grid.add(Controls.createSubTitleLabel("▼特技実装例"), 0, 0);
        int row = 1;
        for (EnemySkill skill : enemy.getArrangeSkills()) {
        	grid.add(createSkillPane(skill), 0, row);
        	row++;
		}
	}
}
