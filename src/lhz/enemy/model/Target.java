package lhz.enemy.model;

import javafx.util.StringConverter;
import lhz.DataTypeEnum;

public enum Target implements DataTypeEnum {

	SINGLE("単体", 1),
	SQ1("1Sq", 11),
	MULTI("範囲（選択）", 20),
	MULTI1("広範囲1（選択）", 21);

	private String label;
	private Integer value;


	private Target(String label, Integer value) {
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

	public static Target get(Integer value) {
		for (Target t : Target.values()) {
			if (t.value().equals(value)) {
				return t;
			}
		}
		return null;
	}

	public static Target get(String label) {
		for (Target t : Target.values()) {
			if (t.label().equals(label)) {
				return t;
			}
		}
		return null;
	}

	public static StringConverter<Target> converter = new StringConverter<Target>() {
		@Override
		public String toString(Target t) {
			return t.label();
		}
		@Override
		public Target fromString(String label) {
			return Target.get(label);
		}
	};
}
