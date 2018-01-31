package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Oak1 extends NarrowRace {

	public static final NarrowRace instance = new Oak1();

	private Oak1() {
		super(Race.DEMI_HUMAN, "醜豚鬼 (オーク)");
		tags = Arrays.asList("オーク");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.FENCER, EnemyType.SPEAR);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("オークアーミー", "セットアップ");
		Integer arg1 = enemy.getParameter(8, 1, 10);
		skill.createFunction("同じシーンに［オーク］タグを持つエネミーが自分以外に2体以上いる場合に使用可能。このエネミーは［軽減:%d］を得る。", arg1);
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		if(enemy.getType().equals(EnemyType.FENCER)) {
			EnemySkill skill1 = createMajorMeleeSkill(enemy, "鉄の突撃剣");
			Integer arg1 = getDamageFix(enemy);
			Integer arg2 = enemy.getParameter(4, 1, 10);
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔達成値14〕剣は相手に突き刺さる。対象に［衰弱:%d］を与える。このエネミーは次のセットアッププロセスで［未行動］状態にならない。", arg1, arg2);

			EnemySkill skill2 = new EnemySkill("みなぎる義憤", "クリンナップ");
			skill2.createFunction("このエネミーがこのラウンドにHPダメージを1点も受けていない場合、次のラウンド終了時までこのエネミーのヘイト倍率を「×%d」として扱う。", enemy.getHate() + 2);

			return Arrays.asList(skill1, skill2);
		}
		if(enemy.getType().equals(EnemyType.SPEAR)) {
			EnemySkill skill1 = createMajorMeleeSkill(enemy, "大突撃");
			Integer arg1 = getDamageFix(enemy);
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔マイナー〕このエネミーとおなじSqにいる［オーク］タグを持つエネミー1体を1Sqまで［即時移動］してもよい。", arg1);

			EnemySkill skill2 = new EnemySkill("体当たりによる撹乱", "本文", "自動成功", "単体", "至近", "シーン1回");
			skill2.addTag("移動");
			skill2.setFunction("このエネミーが［阻止能力］の対象となった時に使用できる。対象は即座に［放心］を受ける。");

			EnemySkill skill3 = createMajorMeleeSkill(enemy, "両手斧の一撃");
			Integer arg2 = enemy.getParameter(3, 2, 5);
			skill3.createFunction("対象に［%d+2D］の物理ダメージを与える。対象の【HP】が【最大HP】と等しい場合、ダメージは+%dされる。", arg1, arg2);

			EnemySkill skill4 = createMajorMeleeSkill(enemy, "突撃槍による攻撃");
			Integer arg3 = enemy.getAttackRole() + 14;
			skill4.createFunction("対象に［%d+2D］の物理ダメージを与える。〔達成値%d〕即座に《弾丸のような突進》の使用回数を回復する。", arg1, arg3);

			EnemySkill skill5 = new EnemySkill("弾丸のような突進", "ムーブ", "自動成功", "単体", "至近", "シーン1回");
			skill5.addTag("移動");
			skill5.setFunction("効果発揮前にこのエネミーは4Sqまで［即時移動］をしてもよい。対象が次に行なう［回避判定］を-1Dする。この効果はBSとして扱う。");

			return Arrays.asList(skill1, skill2, skill3, skill4, skill5);

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
