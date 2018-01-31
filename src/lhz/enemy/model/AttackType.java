package lhz.enemy.model;

import javafx.util.StringConverter;
import lhz.DataTypeEnum;

public enum AttackType implements DataTypeEnum {

	MELEE("白兵攻撃", 1, "回避"),
	SHOOTING("射撃攻撃", 2, "回避"),
	MAGICAL("魔法攻撃", 3, "抵抗");

	private String label;
	private Integer value;
	private String role;

	private AttackType(String label, Integer value, String role) {
		this.label = label;
		this.value = value;
		this.role = role;
	}

	@Override
	public String label() {
		return label;
	}

	@Override
	public Integer value() {
		return value;
	}

	public String role() {
		return role;
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

	public static AttackType get(Integer value) {
		for (AttackType t : AttackType.values()) {
			if (t.value().equals(value)) {
				return t;
			}
		}
		return null;
	}

	public static AttackType get(String label) {
		for (AttackType t : AttackType.values()) {
			if (t.label().equals(label)) {
				return t;
			}
		}
		return null;
	}

	public static StringConverter<AttackType> converter = new StringConverter<AttackType>() {
		@Override
		public String toString(AttackType t) {
			return t.label();
		}
		@Override
		public AttackType fromString(String label) {
			return AttackType.get(label);
		}
	};
}
