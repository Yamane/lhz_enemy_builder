package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Kobold3 extends NarrowRace {

	public static final NarrowRace instance = new Kobold3();

	private Kobold3() {
		super(Race.DEMI_HUMAN, "小牙竜鬼 (コボルド)");
		tags = Arrays.asList("コボルド");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.SUPPORTER, EnemyType.SHOOTER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("コボルドの連携", "常時");
		skill.setFunction("このエネミーは［コボルド］タグを持つ他のキャラクターと同じSqにいる時、［防御判定］に+1、［解除判定］に+1Dを得る。また［機械］プロップの影響の全体もしくは一部(GMが選択)を受けなくてもよい。");
		return Arrays.asList(skill);
	}

	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		Integer cr = enemy.getCharacterRank();
		if(cr <= 15) {
			return defaultValue + enemy.getStubborn(0.25);
		} else {
			return defaultValue + enemy.getStubborn(0.8);
		}
	}
}
