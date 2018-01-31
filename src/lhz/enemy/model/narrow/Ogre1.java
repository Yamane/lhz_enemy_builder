package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Ogre1 extends NarrowRace {

	public static final NarrowRace instance = new Ogre1();

	private Ogre1() {
		super(Race.DEMI_HUMAN, "人食い鬼 (オーガ)");
		tags = Arrays.asList("巨人", "オーガ");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.ARMOROR, EnemyType.SPEAR, EnemyType.ARCHER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("野蛮な魂", "常時");
		Integer arg = enemy.getParameter(8, 1, 10);
		skill.createFunction("このエネミーが［追撃］［衰弱］を受ける時、その強度は-%dされる。強度が0以下になった場合は［追撃］［衰弱］を受けない。また［放心］［萎縮］を受ける時、それらの代わりに［惑乱］を受ける。", arg);
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		if(enemy.getType().equals(EnemyType.ARMOROR)) {
			EnemySkill skill1 = createMajorMeleeSkill(enemy, "打ち倒しての踏みにじり");
			Integer arg1 = getDamageFix(enemy);
			Integer arg2 = getAttackRole(enemy) + 14;
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔マイナー〕この特技の［攻撃判定］に+1D。〔達成値:%d〕このエネミーは対象を踏みつける。対象は［移動］タグを持つ行動と［武器攻撃］を実行できなくなり［阻止能力］を失う。この効果は対象がこのエネミーと異なるSqに離れるか、このエネミーが何らかのダメージを受けるまで持続する。", arg1, arg2);

			Integer arg3 = enemy.getStr() * 5;
			EnemySkill skill2 = new EnemySkill("怒り出す", "ダメージ適用直後", null, "単体", "4Sq", "シーン1回");
			skill2.createFunction("このエネミー以外のエネミーのみ対象にできる。対象がダメージを受けた時に使用する。このエネミーが次に行なうダメージロールに+%d (この効果はCSとして扱い、効果が一度発揮されると解除される)。〔因果力1〕再利用可能。", arg3);

			return Arrays.asList(skill1, skill2);
		}
		if(enemy.getType().equals(EnemyType.SPEAR)) {
			EnemySkill skill1 = createMajorMeleeSkill(enemy, "棍棒を振り回す");
			Integer arg1 = getDamageFix(enemy);
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔マイナー〕対象に［放心］を与える。〔自身：惑乱〕この特技は「対象:範囲(選択)」になる。", arg1);
			return Arrays.asList(skill1);
		}
		if(enemy.getType().equals(EnemyType.ARCHER)) {
			EnemySkill skill1 = createMajorShotSkill(enemy, "岩を投げる", "3Sq");
			Integer arg1 = getDamageFix(enemy);
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔マイナー〕対象に［鬼の標的］タグがある場合、(そのタグの数ｘ10)をダメージに追加する。", arg1);

			EnemySkill skill2 = new EnemySkill("はやし立て", "ムーブ", "自動成功", "単体", "6Sq", "シーン1回");
			skill2.setFunction("対象に［鬼の標的］タグ1つを与える。〔対象：ヘイトアンダー〕対象の【ヘイト】+1。〔因果力1〕再使用可能。");

			return Arrays.asList(skill1, skill2);

		}
		return Collections.emptyList();
	}

	@Override
	public Integer getAttackRole(Enemy enemy) {
		return super.getAttackRole(enemy) + 1;
	}

	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		int cr = enemy.getCharacterRank();
		if(cr <= 15) {
			return defaultValue + enemy.getStubborn(cr * 0.08);
		} else {
			return defaultValue + enemy.getStubborn(1.25);
		}
	}
}
