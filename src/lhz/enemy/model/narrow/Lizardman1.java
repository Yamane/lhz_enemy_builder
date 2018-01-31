package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Lizardman1 extends NarrowRace {

	public static final NarrowRace instance = new Lizardman1();

	private Lizardman1() {
		super(Race.DEMI_HUMAN, "蜥蜴人 (リザードマン)");
		tags = Arrays.asList("リザードマン", "水棲");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.ARCHER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("湿地の蜥蜴人", "常時");
		skill.createFunction("このエネミーは常に［弱点 (冷気):%d］を持つ。", enemy.getParameter(8, 1, 10));
		return Arrays.asList(skill);
	}

	@Override
	public Integer getAttackRole(Enemy enemy) {
		return super.getAttackRole(enemy) + 1;
	}

	@Override
	public Integer getResist(Enemy enemy) {
		return super.getResist(enemy) + 1;
	}


	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		int cr = enemy.getCharacterRank();
		if(cr <= 7) {
			return defaultValue + enemy.getStubborn(cr / 10d);
		} else {
			return defaultValue + enemy.getStubborn(0.8);
		}
	}
}
