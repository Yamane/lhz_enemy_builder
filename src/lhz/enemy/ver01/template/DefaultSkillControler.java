package lhz.enemy.ver01.template;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.ver01.Controls;

public class DefaultSkillControler {

	private GridPane grid;

	private Enemy enemy;

	public DefaultSkillControler(Enemy enemy) {
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
		pane.add(Controls.createCell(Controls.createLabel(skill.getName()), 180, cellHeight), 1, 0);
		pane.add(Controls.createHead("タイミング", 60, cellHeight), 2, 0);
		pane.add(Controls.createCell(Controls.createLabel(skill.getTiming()), 110, cellHeight), 3 ,0);
		pane.add(Controls.createHead("タグ", 40, cellHeight), 4, 0);
		pane.add(Controls.createCell(Controls.createLabel(tagLabel.toString()), 264, cellHeight), 5 ,0);

		pane.add(Controls.createHead("効果", 60, cellHeight), 0, 1);
		pane.add(Controls.createCell(Controls.createLabel(skill.getFunction()), 694, cellHeight), 1 ,1, 5, 1);

		return pane;
	}

	public void setOverview() {
		grid.getChildren().removeAll(grid.getChildren());
        if(!enemy.getDefaultSkills().isEmpty()) {
            grid.add(Controls.createSubTitleLabel("▼自動取得特技"), 0, 0);
            int row = 1;
            for (EnemySkill skill : enemy.getDefaultSkills()) {
            	grid.add(createSkillPane(skill), 0, row);
            	row++;
    		}
        }

	}
}
