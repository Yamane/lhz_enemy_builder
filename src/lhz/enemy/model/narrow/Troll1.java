package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Troll1 extends NarrowRace {

	public static final NarrowRace instance = new Troll1();

	private Troll1() {
		super(Race.DEMI_HUMAN, "灰色大鬼 (トロウル)");
		tags = Arrays.asList("巨人", "暗視");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.FENCER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("灰色大鬼の生命力", "クリンナップ");
		skill.createFunction("このエネミーは【HP】を%d点回復する。この特技はエネミーがこのラウンドに［火炎］［光輝］タグを持つダメージを受けていた場合は使用できない。", enemy.getStubborn(0.25));
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		EnemySkill skill1 = createMajorMeleeSkill(enemy, "捕獲する腕");
		Integer arg1 = new Float(getDamageFix(enemy) * 0.9).intValue();
		skill1.createFunction("対象に［%d+2D］の物理ダメージと［硬直］を与える。この［硬直］は通常の方法では解除されず、対象とこのエネミーが別のSqに離れた時のみ解除される。〔対象:硬直〕このエネミーの攻撃に対する対象の【物理防御力】は、対象の【STR】の値に等しいものとして扱う。", arg1);

		EnemySkill skill2 = createMajorMeleeSkill(enemy, "引き裂く豪腕");
		Integer arg2 = getDamageFix(enemy);
		Integer arg3 = enemy.getAttackRole() + 9;
		Integer arg4 = enemy.getParameter(4, 1, 10);
		skill2.createFunction("対象に［%d+2D］の物理ダメージを与える。〔達成値:%d〕対象に［硬直］と［追撃:%d］を与える。このふたつのBSはひとつとして扱い、どちらかを解除すれば両方解除される。〔対象:硬直〕この特技の［攻撃判定］に+2。", arg2, arg3, arg4);

		return Arrays.asList(skill1, skill2);
	}

	@Override
	public Integer getAvoid(Enemy enemy) {
		Integer def = super.getAvoid(enemy);
		return def > 2 ?  def - 2 : 0;
	}

	@Override
	public Integer getResist(Enemy enemy) {
		Integer def = super.getResist(enemy);
		return def > 2 ?  def - 2 : 0;
	}

	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		Integer cr = enemy.getCharacterRank();
		Float param = cr > 4 ? 0.6f : cr * 20 / 100f;
		return defaultValue + new Float(defaultValue * param).intValue();
	}
}
