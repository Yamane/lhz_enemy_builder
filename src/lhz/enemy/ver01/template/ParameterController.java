package lhz.enemy.ver01.template;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemyRank;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Notoriety;
import lhz.enemy.model.Race;
import lhz.enemy.model.TemplateData;
import lhz.enemy.ver01.Controls;

public class ParameterController {

	private BasicDataController basic;
	private DefaultSkillControler defaultSkills;
	private ArrangeSkillControler arrangeSkills;

	private GridPane grid;

    private ChoiceBox<Integer> characterRankChoice;
    private ChoiceBox<EnemyRank> rankChoice;
    private ChoiceBox<Notoriety> notorietyChoice;
    private ChoiceBox<EnemyType> typeChoice;
    private ChoiceBox<Race> raceChoice;
    private ChoiceBox<NarrowRace> narrowRaceChoice;

    private Enemy enemy;

	public ParameterController(Enemy enemy) {
		this.enemy = enemy;

		initChoices();
		createLayout();
	}

	public void setBasicController(BasicDataController basic) {
		this.basic = basic;
		setOverview(null);
	}

	public void setSkillController(DefaultSkillControler defaultSkills) {
		this.defaultSkills = defaultSkills;
		setOverview(null);
	}

	public void setSkillController(ArrangeSkillControler arrangeSkills) {
		this.arrangeSkills = arrangeSkills;
		setOverview(null);
	}

	public Pane getPane() {
		return grid;
	}

	private void initChoices() {
		characterRankChoice = Controls.createChoiceBox(Integer.class, 160);
		for (int rank = 1; rank <= 30; rank++) {
			characterRankChoice.getItems().add(rank);
    	}
		characterRankChoice.setValue(1);
		characterRankChoice.setOnAction((e) -> setOverview(e));

		rankChoice = Controls.createChoiceBox(EnemyRank.class, 160);
    	rankChoice.setConverter(EnemyRank.converter);
		rankChoice.getItems().addAll(EnemyRank.test);
		rankChoice.setValue(EnemyRank.NORMAL);
		rankChoice.setOnAction((e) -> setOverview(e));

		notorietyChoice = Controls.createChoiceBox(Notoriety.class, 160);
    	notorietyChoice.setConverter(Notoriety.converter);
    	notorietyChoice.getItems().addAll(Notoriety.values());
    	notorietyChoice.setValue(Notoriety.R3);
    	notorietyChoice.setOnAction((e) -> setOverview(e));

    	typeChoice = Controls.createChoiceBox(EnemyType.class, 160);
    	typeChoice.setConverter(EnemyType.converter);
    	typeChoice.getItems().addAll(EnemyType.values());
    	typeChoice.setValue(EnemyType.ARMOROR);
    	typeChoice.setOnAction((e) -> setOverviewType(e));

    	raceChoice = Controls.createChoiceBox(Race.class, 160);
    	raceChoice.setConverter(Race.converter);
    	raceChoice.getItems().addAll(Race.values());
    	raceChoice.setValue(Race.DEMI_HUMAN);
    	raceChoice.setOnAction((e) -> setOverviewRace(e));

    	narrowRaceChoice =  Controls.createChoiceBox(NarrowRace.class, 160);
    	narrowRaceChoice.getItems().addAll(NarrowRace.getList(enemy));
    	narrowRaceChoice.setConverter(NarrowRace.converter);
    	narrowRaceChoice.setOnAction((e) -> setOverview(e));
	}

	private void createLayout() {
        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(0, 0, 10, 10));

        grid.add(Controls.createHead("CR", 80, 30), 0, 0);
        grid.add(Controls.createCell(characterRankChoice, 170, 30), 1, 0);
        grid.add(Controls.createHead("Eランク", 80, 30), 2, 0);
        grid.add(Controls.createCell(rankChoice, 170, 30), 3, 0);
        grid.add(Controls.createHead("知名度", 80, 30), 4, 0);
        grid.add(Controls.createCell(notorietyChoice, 170, 30), 5, 0);

        grid.add(Controls.createHead("Eタイプ", 80, 30), 0, 1);
        grid.add(Controls.createCell(typeChoice, 170, 30), 1, 1);
        grid.add(Controls.createHead("大種族", 80, 30), 2, 1);
        grid.add(Controls.createCell(raceChoice, 170, 30), 3, 1);
        grid.add(Controls.createHead("小種族", 80, 30), 4, 1);
        grid.add(Controls.createCell(narrowRaceChoice, 170, 30), 5, 1);
	}

    private void setOverviewType(ActionEvent e) {
		enemy.setTemplate(TemplateData.get(typeChoice.getValue()));
		narrowRaceChoice.getItems().removeAll(narrowRaceChoice.getItems());
		narrowRaceChoice.getItems().addAll(NarrowRace.getList(enemy));
		processOverview();
    }

    private void setOverviewRace(ActionEvent e) {
    	if(raceChoice.getValue().equals(Race.GIMIC)) {
    		rankChoice.setValue(EnemyRank.NORMAL);
    		enemy.setRank(rankChoice.getValue());
    	}
		enemy.setRace(raceChoice.getValue());
		narrowRaceChoice.getItems().removeAll(narrowRaceChoice.getItems());
		narrowRaceChoice.getItems().addAll(NarrowRace.getList(enemy));
		processOverview();
    }


    private void setOverview(ActionEvent e) {
		enemy.setCharacterRank(characterRankChoice.getValue());
		enemy.setRank(rankChoice.getValue());
		enemy.setNotoriety(notorietyChoice.getValue());
		enemy.setNarrowRace(narrowRaceChoice.getValue());
		processOverview();
    }

    private void processOverview() {
		if(basic != null) {
			basic.setOverview();
		}
		if(defaultSkills != null) {
			defaultSkills.setOverview();
		}
		if(arrangeSkills != null) {
			arrangeSkills.setOverview();
		}
    }

}
