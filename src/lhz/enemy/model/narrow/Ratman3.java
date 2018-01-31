package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Ratman3 extends NarrowRace {

	public static final NarrowRace instance = new Ratman3();

	private Ratman3() {
		super(Race.DEMI_HUMAN, "鼠人間 (ラットマン)");
		tags = Arrays.asList("ラットマン", "暗視");
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.HEALER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("鼠の体術", "セットアップ");
		skill.setFunction("このエネミーの行なう［通常移動］は常に［即時移動］になる。この効果はラウンド終了時まで持続する。〔因果力1〕シーンが屋内や洞窟内であれば［飛行］状態となる。この効果はラウンド終了時まで持続する。");
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("ラットマンフィーバー", "クリンナップ", null, "広範囲20(選択)", "至近", "シーン1回");
		skill.createFunction("同じシーンに［ラットマン］タグを持つキャラクターが3体以上いる場合に使用可能。対象の【HP】を%d点回復する。", enemy.getStubborn(0.2));
		return Arrays.asList(skill);
	}

	@Override
	public void enlargeSkills(Enemy enemy, List<EnemySkill> skills) {
		for (EnemySkill skill : skills) {
			if("メジャー".equals(skill.getTiming())) {
				if(skill.getTags().contains("白兵攻撃")) {
					Integer arg = enemy.getParameter(5, 1, 5);
					skill.setFunction(skill.getFunction() + String.format("〔確定効果〕［衰弱:%d］を与える。", arg));
				}
			}
		}
	}

	@Override
	public Integer getPhysicalDefense(Enemy enemy) {
		return super.getPhysicalDefense(enemy, 0.8);
	}

	@Override
	public Integer getMagicDefense(Enemy enemy) {
		return super.getMagicDefense(enemy, 0.8);
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
