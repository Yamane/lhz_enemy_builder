package lhz.enemy.model;

import javafx.util.StringConverter;
import lhz.DataTypeEnum;

public enum Notoriety implements DataTypeEnum {

	R1("超有名", 0),
	R2("有名", 2),
	R3("一般的", 4),
	R4("普通", 6),
	R5("珍しい", 7),
	R6("無名", 9),
	R7("秘密", 12);

	private String label;
	private Integer value;


	private Notoriety(String label, Integer value) {
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

	public static Notoriety get(Integer value) {
		for (Notoriety t : Notoriety.values()) {
			if (t.value().equals(value)) {
				return t;
			}
		}
		return null;
	}

	public static Notoriety get(String label) {
		for (Notoriety t : Notoriety.values()) {
			if (t.label().equals(label)) {
				return t;
			}
		}
		return null;
	}

	public static StringConverter<Notoriety> converter = new StringConverter<Notoriety>() {
		@Override
		public String toString(Notoriety t) {
			return t.label();
		}
		@Override
		public Notoriety fromString(String label) {
			return Notoriety.get(label);
		}
	};
}
