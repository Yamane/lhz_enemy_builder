package lhz.enemy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.util.StringConverter;
import lhz.DataTypeEnum;

public enum Race implements DataTypeEnum {

	DEMI_HUMAN("人型", 1),
	NATURE("自然", 2),
	SPILIT("精霊", 3),
	CRYPTID("幻獣", 4),
	UNDEAD("不死", 5),
	ARTIFICIAL("人造", 6),
	HUMAN("人間", 7),
	GIMIC("ギミック", 99) {
		@Override
		public List<EnemySkill> getSkills(Enemy enemy) {
			List<EnemySkill> result = new ArrayList<>();
			EnemySkill skill = new EnemySkill("意志なき機構", "常時");
			int intValue = new Float(enemy.getCharacterRank() * 0.4f + 11).intValue() ;
			skill.createFunction("このエネミーの攻撃ではヘイトダメージが発生せず［ヘイトアンダー］の防御ボーナスも得られない。また、このエネミーを対象として「解除難易度：%d」の《プロップ解除》に成功すると、このエネミーは［戦闘不能］となる。さらにこのエネミーはムーブアクションを持たない。", intValue);
			result.add(skill);
			return result;
		}
	};

	private String label;
	private Integer value;
	private List<String> tags;


	private Race(String label, Integer value) {
		this.label = label;
		this.value = value;
		this.tags = new ArrayList<>();
		tags.add(this.label);
		if(this.value.equals(99)) {
			tags.add("物品");
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

	public static Race get(Integer value) {
		for (Race t : Race.values()) {
			if (t.value().equals(value)) {
				return t;
			}
		}
		return null;
	}

	public static Race get(String label) {
		for (Race t : Race.values()) {
			if (t.label().equals(label)) {
				return t;
			}
		}
		return null;
	}

	public static StringConverter<Race> converter = new StringConverter<Race>() {
		@Override
		public String toString(Race t) {
			return t.label();
		}
		@Override
		public Race fromString(String label) {
			return Race.get(label);
		}
	};
}
