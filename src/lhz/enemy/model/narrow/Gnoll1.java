package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Gnoll1 extends NarrowRace {

	public static final NarrowRace instance = new Gnoll1();

	private Gnoll1() {
		super(Race.DEMI_HUMAN, "灰斑犬鬼 (ノール)");
		tags = Arrays.asList("ノール", "暗視");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.SPEAR, EnemyType.ARCHER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("屍肉あさりの犬鬼", "常時");
		skill.createFunction("このエネミーは常に［弱点 (ヘイトトップからの攻撃):%d］を持つ。", enemy.getParameter(5, 1, 5));
		return Arrays.asList(skill);
	}

	@Override
	public void enlargeSkills(Enemy enemy, List<EnemySkill> skills) {
		for (EnemySkill skill : skills) {
			if("メジャー".equals(skill.getTiming())) {
				if(skill.getTags().contains("白兵攻撃") || skill.getTags().contains("射撃攻撃") || skill.getTags().contains("魔法攻撃")) {
					Integer arg = enemy.getParameter(4, 1, 5);
					skill.setFunction(skill.getFunction() + String.format("〔対象:放心、硬直、萎縮、惑乱〕ダメージに+%dする。", arg));
				}
			}
		}
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
