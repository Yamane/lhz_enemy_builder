package lhz.enemy.ver01.list;

import java.util.List;
import java.util.Map;

public class JsonEnemy {

	Map<String, Object> data;

	Integer rank;
	String name;
	String tags;
	String notoriety;
	String avoid;
	String resist;
	String hate;

	public JsonEnemy(Map<String, Object> data) {
		this.data = data;
		this.rank = (Integer) data.get("character_rank");

		StringBuilder b = new StringBuilder();
		b.append(data.get("name")).append(" 《").append(data.get("ruby")).append("》");
		this.name = b.toString();

		b = new StringBuilder();
		List<?> tagList = (List<?>) data.get("tags");
		for (Object tag : tagList) {
			b.append("［").append(tag).append("］");
		}
		this.tags = b.toString();

		Integer noto = (Integer) data.get("identification");
		if(noto.equals(0)) {
			notoriety = "自動";
		} else {
			notoriety = noto.toString();
		}

		Integer a = (Integer) data.get("avoid");
		Integer ad =  (Integer) data.get("avoid_dice");
		avoid = a.toString() + (ad.equals(0) ? " [固定]" : "+" + ad.toString() + "D");

		Integer r = (Integer) data.get("resist");
		Integer rd =  (Integer) data.get("resist_dice");
		resist = r.toString() + (rd.equals(0) ? " [固定]" : "+" + rd.toString() + "D");

		Integer h = (Integer) data.get("hate");
		if(h.equals(0)) {
			hate = "なし";
		} else {
			hate = "×" + h.toString();
		}
	}

	public Integer getRank() {
		return rank;
	}

	public String getName() {
		return name;
	}

	public String getTags() {
		return tags;
	}

	public String getNotoriety() {
		return notoriety;
	}

	public String getAvoid() {
		return avoid;
	}

	public String getResist() {
		return resist;
	}

	public String getHate() {
		return hate;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSkills() {
		return (List<Map<String, Object>>) data.get("skills");
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getItems() {
		return (List<Map<String, Object>>) data.get("items");
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
    	b.append(getName());
		b.append("\n");
    	b.append(getTags());
		b.append("\n");
    	b.append("【ランク】CR" + getRank()).append("\n");
    	b.append("【識別難易度】" + getNotoriety()).append("\n");
    	b.append("【STR】" + map("strength")).append("\n");
    	b.append("【DEX】" + map("dexterity")).append("\n");
    	b.append("【POW】" + map("power")).append("\n");
    	b.append("【INT】" + map("intelligence")).append("\n");
    	b.append("【回避】" + getAvoid()).append("\n");
    	b.append("【抵抗】" + getResist()).append("\n");
    	b.append("【物理防御力】" + map("physical_defense")).append("\n");
    	b.append("【魔法防御力】" + map("magic_defense")).append("\n");
    	b.append("【最大HP】" + map("hit_point")).append("\n");
    	b.append("【ヘイト倍率】" + getHate()).append("\n");
    	b.append("【行動力】" + map("action")).append("\n");
    	b.append("【移動力】" + map("move")).append("\n");
    	b.append("【因果力】" + map("fate")).append("\n");
		b.append("\n");
		b.append("▼特技").append("\n");
        for (Map<String, Object> skill : getSkills()) {
			b.append(new JsonSkill(skill).createClipboardData()).append("\n\n");
		}
		b.append("\n");
		b.append("▼ドロップ品").append("\n");
        for (Map<String, Object> item : getItems()) {
			b.append(item.get("dice")).append(" : ").append(item.get("item")).append("\n");
		}
		b.append("\n").append("\n");
		b.append("▼解説").append("\n");
		b.append(map("contents")).append("\n");

    	return b.toString();
	}
}
