package lhz.enemy.ver01.list;

import java.util.List;
import java.util.Map;

public class JsonSkill {

	Map<String, Object> data;
	String tags;

	public JsonSkill(Map<String, Object> data) {
		this.data = data;

		StringBuilder b = new StringBuilder();
		List<?> tagList = (List<?>) data.get("tags");
		for (Object tag : tagList) {
			b.append("［").append(tag).append("］");
		}
		this.tags = b.toString();
	}

	public String getTags() {
		return tags;
	}

	public String map(String key) {
		Object obj = data.get(key);
		if(obj != null) {
			return obj.toString();
		} else {
			return "";
		}
	}

	public String createClipboardData() {
    	StringBuilder b = new StringBuilder();
    	b.append(map("name")).append("＿");
    	if(tags.length() != 0) {
    		b.append(tags);
    		b.append("＿");
    	}
    	if(data.get("timing") != null) {
    		b.append(map("timing")).append("＿");
    	}
    	if(data.get("role") != null) {
    		b.append(map("role")).append("＿");
    	}
    	if(data.get("target") != null) {
    		b.append(map("target")).append("＿");
    	}
    	if(data.get("range") != null) {
    		b.append(map("range")).append("＿");
    	}
    	if(data.get("limit") != null) {
    		b.append(map("limit")).append("＿");
    	}
		b.append(map("function"));
    	return b.toString();
	}
}
