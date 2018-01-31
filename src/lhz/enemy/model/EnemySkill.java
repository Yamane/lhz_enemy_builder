package lhz.enemy.model;

import java.util.ArrayList;
import java.util.List;

public class EnemySkill {

	private String name;
	private String timing;
	private String role;
	private String target;
	private String range;
	private String limit;
	private String function;
	private List<String> tags;

	public EnemySkill(String name) {
		this(name, null, null, null, null, null);
	}

	public EnemySkill(String name, String timing) {
		this(name, timing, null, null, null, null);
	}

	public EnemySkill(String name, String timing, String role, String target, String range) {
		this(name, timing, role, target, range, null);
	}

	public EnemySkill(String name, String timing, String role, String target, String range, String limit) {
		this.name = name;
		this.timing = timing;
		this.role = role;
		this.target = target;
		this.range = range;
		this.limit = limit;
		this.tags = new ArrayList<String>();
	}

	public void createFunction(String format, Object...args) {
		setFunction(String.format(format, args));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public EnemySkill addTag(String... tags) {
		for (String tag : tags) {
			getTags().add(tag);
		}
		return this;
	}

	public String createClipboardData() {
    	StringBuilder b = new StringBuilder();
    	b.append(name).append("＿");
    	if(tags != null && !tags.isEmpty()) {
    		for (String tag : tags) {
    	    	b.append("［").append(tag).append("］");
			}
    		b.append("＿");
    	}
    	if(timing != null) {
    		b.append(timing).append("＿");
    	}
    	if(role != null) {
    		b.append(role).append("＿");
    	}
    	if(target != null) {
    		b.append(target).append("＿");
    	}
    	if(range != null) {
    		b.append(range).append("＿");
    	}
    	if(limit != null) {
    		b.append(limit).append("＿");
    	}
		b.append(function);
    	return b.toString();
	}
}
