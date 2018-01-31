package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Goblin2 extends NarrowRace {

	public static final NarrowRace instance = new Goblin2();

	private Goblin2() {
		super(Race.DEMI_HUMAN, "緑小鬼 (ゴブリン)");
		tags = Arrays.asList("ゴブリン");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.HEALER, EnemyType.SUPPORTER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("ゴブリンレイジボルト", "ダメージ適用直後");
		skill.createFunction("このエネミーが［精神］ダメージを受けた時に使用する。このエネミーは即座に「対象:広範囲1(選択) / 距離:至近」の対象に%d点の直接ダメージを与える。", enemy.getAggression());
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		Integer arg = enemy.getCharacterRank() <= 15 ? enemy.getStubborn(0.2) : enemy.getStubborn(0.3);
		EnemySkill skill = new EnemySkill("ゴブリンヒール", "セットアップ", null, "単体", "2Sq");
		skill.createFunction("対象の【HP】を%d点回復する。〔因果力1〕対象のBSすべてを解除する。", arg);
		return Arrays.asList(skill);
	}

	@Override
	public Integer getAction(Enemy enemy) {
		return super.getAction(enemy) + 1;
	}

	@Override
	public Integer getMagicDefense(Enemy enemy) {
		return super.getMagicDefense(enemy, 0.8);
	}

	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		int cr = enemy.getCharacterRank();
		if(cr <= 10) {
			return defaultValue + enemy.getStubborn(0.1);
		} else if(cr <= 15) {
			return defaultValue + enemy.getStubborn(0.2);
		} else {
			return defaultValue + enemy.getStubborn(0.4);
		}
	}
}
