package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Oak2 extends NarrowRace {

	public static final NarrowRace instance = new Oak2();

	private Oak2() {
		super(Race.DEMI_HUMAN, "醜豚鬼 (オーク)");
		tags = Arrays.asList("オーク");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.SHOOTER, EnemyType.BOMMER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("オークコンセントレイト", "セットアップ");
		skill.setFunction("同じシーンに［オーク］タグを持つエネミーが自分以外に2体以上いる場合に使用可能。このエネミーは【因果力】1を得る。この【因果力】は特技のコストにしか使用できず、ラウンド終了時に失われる。");
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		if(enemy.getType().equals(EnemyType.SHOOTER)) {
			EnemySkill skill1 = createMajorMagicalSkill(enemy, "汚い悪臭ガス", "2体", "至近");
			skill1.addTag("邪毒");
			Integer arg1 = getDamageFix(enemy);
			skill1.createFunction("対象に［%d+2D］の魔法ダメージを与える。〔因果力1〕対象は目がしみてすべての［命中判定］に-1する。この効果はBSとして扱い、シーン終了時まで持続する。", arg1);

			EnemySkill skill2 = createMajorMagicalSkill(enemy, "炎の渦巻く杖", "2体", "至近");
			skill2.addTag("火炎");
			Integer arg2 = enemy.getParameter(3, 2, 5);
			skill2.createFunction("対象に［%d+2D］の魔法ダメージを与える。〔マイナー＆対象:ヘイトトップ〕対象を1Sqまで［即時移動 (強制)］してよい。このとき対象はこのエネミーに近づくように動かすこと。〔因果力1〕対象に［追撃:%d］を与える。", arg1, arg2);

			return Arrays.asList(skill1, skill2);
		}
		if(enemy.getType().equals(EnemyType.BOMMER)) {
			EnemySkill skill1 = createMajorMagicalSkill(enemy, "火炎放射", "直線2 (選択)", "至近");
			skill1.addTag("火炎");
			Integer arg1 = getDamageFix(enemy);
			skill1.createFunction("対象に［%d+2D］の魔法ダメージを与える。〔因果力1〕対象を「直線4 (選択)」に変更する。", arg1);

			EnemySkill skill2 = createMajorMagicalSkill(enemy, "電光の投網", "広範囲1 (選択)", "2Sq");
			skill2.addTag("電撃");
			Integer arg2 = new Float(arg1 * 0.9).intValue();
			skill2.createFunction("対象に［%d+2D］の魔法ダメージを与える。〔因果力1〕対象に［硬直］を与える。", arg2);


			return Arrays.asList(skill1, skill2);

		}
		return Collections.emptyList();
	}

	@Override
	public Integer getPhysicalDefense(Enemy enemy) {
		return super.getPhysicalDefense(enemy, 1.2);
	}

	@Override
	public Integer getMagicDefense(Enemy enemy) {
		return super.getMagicDefense(enemy, 0.8);
	}

	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		Integer cr = enemy.getCharacterRank();
		if(cr < 10) {
			return defaultValue + enemy.getStubborn(cr / 10);
		} else {
			return defaultValue + enemy.getStubborn();
		}
	}
}
