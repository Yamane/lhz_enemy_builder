package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Sahagin2 extends NarrowRace {

	public static final NarrowRace instance = new Sahagin2();

	private Sahagin2() {
		super(Race.DEMI_HUMAN, "水棲緑鬼 (サファギン)");
		tags = Arrays.asList("サファギン", "水棲", "暗視");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.HEALER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("魚人の宿命", "常時");
		skill.createFunction("このエネミーは常に［弱点 (電撃):%d］を持つ。", enemy.getParameter(5, 1, 5));
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		Integer cr = enemy.getCharacterRank();
		Integer arg1 = 0;
		if(cr <= 10) {
			arg1 = enemy.getStubborn(0.2);
		} else if(cr <= 10) {
			arg1 = enemy.getStubborn(0.25);
		} else {
			arg1 = enemy.getStubborn(0.35);
		}
		Integer arg2 = cr <= 10 ? 1 : 2;

		EnemySkill skill = new EnemySkill("サファヒール", "クリンナップ", null, "広範囲1(選択)", "至近");
		skill.createFunction("対象の【HP】を%d点回復する。〔因果力1〕対象はBS%dつを解除してもよい。", arg1, arg2);
		return Arrays.asList(skill);
	}

	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		int cr = enemy.getCharacterRank();
		if(cr <= 5) {
			return defaultValue + enemy.getStubborn(cr / 10d);
		} else {
			return defaultValue + enemy.getStubborn(0.6);
		}
	}
}
