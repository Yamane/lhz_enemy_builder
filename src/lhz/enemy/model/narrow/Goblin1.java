package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Goblin1 extends NarrowRace {

	public static final NarrowRace instance = new Goblin1();

	private Goblin1() {
		super(Race.DEMI_HUMAN, "緑小鬼 (ゴブリン)");
		tags = Arrays.asList("ゴブリン");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.ARMOROR, EnemyType.GRAPPLER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("ゴブリンレイジ", "ダメージ適用直後");
		skill.createFunction("このエネミーが［精神］ダメージを受けた時に使用する。このエネミーは即座に《★》を使用してもよい。この時、ダメージロールに+%dを得る。\n※ ★:このエネミーの持つメジャーアクションのダメージロールがある特技名称", enemy.getAggression());
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
		if(cr <= 8) {
			return defaultValue + enemy.getStubborn(0.3);
		} else if(cr <= 16) {
			return defaultValue + enemy.getStubborn(0.6);
		} else {
			return defaultValue + enemy.getStubborn();
		}
	}
}
