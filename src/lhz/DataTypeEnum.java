package lhz;

public interface DataTypeEnum {
	public Integer value();
	public String label();
	public Boolean contains(DataTypeEnum...checks);
}
