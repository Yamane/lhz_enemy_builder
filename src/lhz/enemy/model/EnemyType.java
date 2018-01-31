package lhz.enemy.model;

import javafx.util.StringConverter;
import lhz.DataTypeEnum;

public enum EnemyType implements DataTypeEnum {

	ARMOROR("Type-1 アーマラー", 1),
	FENCER("Type-2 フェンサー", 2),
	GRAPPLER("Type-3 グラップラー", 3),
	SUPPORTER("Type-4 サポーター", 4),
	HEALER("Type-5 ヒーラー", 5),
	SPEAR("Type-6 スピア", 6),
	ARCHER("Type-7 アーチャー", 7),
	SHOOTER("Type-8 シューター", 8),
	BOMMER("Type-9 ボマー", 9);

	private String label;
	private Integer value;


	private EnemyType(String label, Integer value) {
		this.label = label;
		this.value = value;
	}

	@Override
	public String label() {
		return label;
	}

	@Override
	public Integer value() {
		return value;
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

	public static EnemyType get(Integer value) {
		for (EnemyType t : EnemyType.values()) {
			if (t.value().equals(value)) {
				return t;
			}
		}
		return null;
	}

	public static EnemyType get(String label) {
		for (EnemyType t : EnemyType.values()) {
			if (t.label().equals(label)) {
				return t;
			}
		}
		return null;
	}

	public static StringConverter<EnemyType> converter = new StringConverter<EnemyType>() {
		@Override
		public String toString(EnemyType t) {
			return t.label();
		}
		@Override
		public EnemyType fromString(String label) {
			return EnemyType.get(label);
		}
	};
}
