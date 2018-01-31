package lhz.enemy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.util.StringConverter;
import lhz.DataTypeEnum;

public enum EnemyRank implements DataTypeEnum {

	MOB("モブ", 1),
	NORMAL("ノーマル", 2),
	BOSS1("ソロボス", 3) {
		@Override
		public List<EnemySkill> getSkills(Enemy enemy) {

			EnemySkill skill1 = new EnemySkill("再行動Ⅰ", "本文");
			skill1.setLimit("ラウンド1回");;
			skill1.setFunction("このエネミーが［行動済］になった時に使用する。即座に［未行動］となり、その後ラウンド終了時まで【行動力】が0となる。");

			EnemySkill skill2 = new EnemySkill("孤高の一撃", "クリンナップ");
			skill2.setTags(Collections.emptyList());
			skill2.setRole("自動成功");
			skill2.setTarget(Target.MULTI1.label());
			skill2.setRange("至近");
			Integer args = enemy.getTemplate().getA2(enemy.getCharacterRank());
			String format = "%d点の直接ダメージを与える。このエネミーは自身のBSを1つ解除しても良い。\n※ シューター、ボマー以外に適用する場合、威力を上げる、［追撃］の強度を上げる等、火力不足を補うこと。";
			skill2.createFunction(format, args);

			List<EnemySkill> result = new ArrayList<>();
			result.add(skill1);
			result.add(skill2);
			return result;
		}
	},
	BOSS2("群れボス", 4) {
		@Override
		public List<EnemySkill> getSkills(Enemy enemy) {
			EnemySkill skill1 = new EnemySkill("再行動Ⅱ", "本文");
			skill1.setTarget(Target.SINGLE.label());
			skill1.setLimit("ラウンド1回");
			skill1.setFunction("対象が［行動済］になった時に使用する。対象は即座に［未行動］となり、その後ラウンド終了時まで【行動力】が0となる。");

			EnemySkill skill2 = new EnemySkill("近衛兵配置", "常時");
			skill2.setFunction("このエネミーはシーン登場時に、〈通常エネミー名〉2体を任意の位置に配置できる。〈通常エネミー名〉1体は〈モブエネミー名〉2体に置き換えても良い。");

			List<EnemySkill> result = new ArrayList<>();
			result.add(skill1);
			result.add(skill2);
			return result;
		}
	},
	RAID("レイド", 5);

	private String label;
	private Integer value;
	private List<String> tags;


	public static EnemyRank[] test = {
		MOB,
		NORMAL,
		BOSS1,
		BOSS2
	};

	private EnemyRank(String label, Integer value) {
		this.label = label;
		this.value = value;
		this.tags = new ArrayList<>();
		if(value.equals(1)) {
			tags.add("モブ");
		}
		if(value.equals(3) || value.equals(4)) {
			tags.add("ボス");
		}
	}

	@Override
	public String label() {
		return label;
	}

	@Override
	public Integer value() {
		return value;
	}

	public List<String> getTags() {
		return tags;
	}

	public List<EnemySkill> getSkills(Enemy enemy) {
		return Collections.emptyList();
	}

	@Override
	public Boolean contains(DataTypeEnum ...checks) {
		for (DataTypeEnum check : checks) {
			if(check.equals(this)) {
				return true;
			}
		}
		return false;
	}

	public static EnemyRank get(Integer value) {
		for (EnemyRank t : EnemyRank.values()) {
			if (t.value().equals(value)) {
				return t;
			}
		}
		return null;
	}

	public static EnemyRank get(String label) {
		for (EnemyRank t : EnemyRank.values()) {
			if (t.label().equals(label)) {
				return t;
			}
		}
		return null;
	}

	public static StringConverter<EnemyRank> converter = new StringConverter<EnemyRank>() {
		@Override
		public String toString(EnemyRank t) {
			return t.label();
		}
		@Override
		public EnemyRank fromString(String label) {
			return EnemyRank.get(label);
		}
	};
}
