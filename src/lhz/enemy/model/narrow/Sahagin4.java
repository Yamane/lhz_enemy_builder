package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Sahagin4 extends NarrowRace {

	public static final NarrowRace instance = new Sahagin4();

	private Sahagin4() {
		super(Race.DEMI_HUMAN, "水棲緑鬼 (サファギン)");
		tags = Arrays.asList("サファギン", "水棲", "暗視");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.SUPPORTER, EnemyType.SHOOTER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("魚人の宿命", "常時");
		skill.createFunction("このエネミーは常に［弱点 (電撃):%d］を持つ。", enemy.getParameter(5, 1, 5));
		return Arrays.asList(skill);
	}

	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		int cr = enemy.getCharacterRank();
		if(cr <= 10) {
			return defaultValue + enemy.getStubborn(cr / 10d);
		} else {
			return defaultValue + enemy.getStubborn(1.1);
		}
	}
}
