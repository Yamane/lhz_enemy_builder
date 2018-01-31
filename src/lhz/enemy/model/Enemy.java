package lhz.enemy.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Enemy {

	private TemplateData template;
	private Integer cr;
	private Race race;
	private NarrowRace narrowRace;
	private Notoriety notoriety;
	private EnemyRank rank;

	public Enemy() {
		this.template = TemplateData.get(EnemyType.ARMOROR);
		this.cr = 1;
		this.race = Race.DEMI_HUMAN;
		this.notoriety = Notoriety.R3;
		this.rank = EnemyRank.NORMAL;
	}

	public void setTemplate(TemplateData template) {
		this.template = template;
	}

	public void setCharacterRank(Integer cr) {
		this.cr = cr;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public void setNarrowRace(NarrowRace narrowRace) {
		this.narrowRace = narrowRace;
	}

	public void setNotoriety(Notoriety notoriety) {
		this.notoriety = notoriety;
	}

	public void setRank(EnemyRank rank) {
		this.rank = rank;
	}

	/**
	 * Eテンプレート
	 * @return
	 */
	public TemplateData getTemplate() {
		return this.template;
	}

	/**
	 * Eタイプ
	 * @return
	 */
	public EnemyType getType() {
		return template.getType();
	}

	/**
	 * タグ
	 * @return
	 */
	public List<String> getTags() {
		List<String> tags = new ArrayList<>();
		tags.addAll(rank.getTags());
		tags.addAll(race.getTags());
		if(narrowRace != null) {
			tags.addAll(narrowRace.getTags(cr));
		}
		return tags;
	}

	/**
	 * CR
	 * @return
	 */
	public Integer getCharacterRank() {
		return cr;
	}

	/**
	 * Eランク
	 * @return
	 */
	public EnemyRank getRank() {
		return this.rank;
	}

	/**
	 * 大種族
	 * @return
	 */
	public Race getRace() {
		return this.race;
	}

	/**
	 * 識別難易度（表示用）
	 * @return
	 */
	public String getNotorietyLabel() {
		if(notoriety.equals(Notoriety.R1)) {
			return "自動";
		} else {
			return new Integer(notoriety.value() + (cr - 1) / 3 + 1).toString();
		}
	}

	/**
	 * STR値
	 * @return
	 */
	public Integer getStr() {
		if(race.equals(Race.GIMIC)) {
			return 0;
		}
		return template.getStr(cr);
	}

	/**
	 * DEX値
	 * @return
	 */
	public Integer getDex() {
		if(race.equals(Race.GIMIC)) {
			return 0;
		}
		return template.getDex(cr);
	}

	/**
	 * POW値
	 * @return
	 */
	public Integer getPow() {
		return template.getPow(cr);
	}

	/**
	 * INT値
	 * @return
	 */
	public Integer getInt() {
		return template.getInt(cr);
	}

	/**
	 * 回避（固定値）
	 * @return
	 */
	public Integer getAvoid() {
		if(narrowRace != null) {
			return narrowRace.getAvoid(this);
		} else {
			return template.getAvoid(cr);
		}
	}

	/**
	 * 回避（ダイス）
	 * @return
	 */
	public Integer getAvoidDice() {
		return template.getAvoidDice();
	}

	/**
	 * 回避（表示用）
	 * @return
	 */
	public String getAvoidLabel() {
		Integer avoid = getAvoid();
		Integer avoidDice = getAvoidDice();
		if(rank.equals(EnemyRank.MOB) || race.equals(Race.GIMIC)) {
			return String.format("%d［固定］", avoid + avoidDice * 3);
		} else {
			return String.format("%d+%dD", avoid, avoidDice);
		}
	}

	/**
	 * 抵抗（固定値）
	 * @return
	 */
	public Integer getResist() {
		if(narrowRace != null) {
			return narrowRace.getResist(this);
		} else {
			return template.getResist(cr);
		}
	}

	/**
	 * 抵抗（ダイス）
	 * @return
	 */
	public Integer getResistDice() {
		return template.getResistDice();
	}

	/**
	 * 抵抗（表示用）
	 * @return
	 */
	public String getResistLabel() {
		Integer resist = getResist();
		Integer resistDice =getResistDice();
		if(rank.equals(EnemyRank.MOB) || race.equals(Race.GIMIC)) {
			return String.format("%d［固定］", resist + resistDice * 3);
		} else {
			return String.format("%d+%dD", resist, resistDice);
		}
	}

	/**
	 * 物理防御力
	 * @return
	 */
	public Integer getPhysicalDefense() {
		if(narrowRace != null) {
			return narrowRace.getPhysicalDefense(this);
		} else {
			return template.getPhysicalDefense(cr);
		}
	}

	/**
	 * 魔法防御力
	 * @return
	 */
	public Integer getMagicDefense() {
		if(narrowRace != null) {
			return narrowRace.getMagicDefense(this);
		} else {
			return template.getMagicDefense(cr);
		}
	}

	/**
	 * 最大HP
	 * @return
	 */
	public Integer getHitPoint() {
		Integer hitPoint = template.getHitPoint(cr);
		if(rank.equals(EnemyRank.MOB) || race.equals(Race.GIMIC)) {
			hitPoint =  hitPoint / 2;
		}
		if(rank.equals(EnemyRank.BOSS1)) {
			hitPoint =  hitPoint * 4;
		}
		if(rank.equals(EnemyRank.BOSS2)) {
			hitPoint =  hitPoint * 2;
		}
		if(narrowRace != null) {
			hitPoint = narrowRace.getHitPoint(this, hitPoint);
		}
		return hitPoint;
	}

	/**
	 * ヘイト倍率
	 * @return
	 */
	public Integer getHate() {
		if(race.equals(Race.GIMIC)) {
			return 0;
		}
		if(rank.contains(EnemyRank.BOSS1, EnemyRank.BOSS2)) {
			return template.getBossHate(cr);
		}
		return template.getHate(cr);
	}

	/**
	 * ヘイト倍率（表示用）
	 * @return
	 */
	public String getHateLabel() {
		Integer hate = getHate();
		if(hate == 0) {
			return "なし";
		} else {
			return "×" + hate.toString();
		}
	}

	/**
	 * 行動力
	 * @return
	 */
	public Integer getAction() {
		if(race.equals(Race.GIMIC)) {
			return 0;
		}
		if(narrowRace != null) {
			return narrowRace.getAction(this);
		} else {
			return template.getAction(cr);
		}
	}

	/**
	 * 移動力
	 * @return
	 */
	public Integer getMove() {
		if(race.equals(Race.GIMIC)) {
			return 0;
		}
		if(narrowRace != null) {
			return narrowRace.getMove(this);
		} else {
			return template.getMove(cr);
		}
	}

	/**
	 * 因果力
	 * @return
	 */
	public Integer getFate() {
		if(rank.contains(EnemyRank.BOSS1, EnemyRank.BOSS2, EnemyRank.RAID)) {
			return 4;
		}
		return 0;
	}

	/**
	 * ドロップ期待値
	 * @return
	 */
	public Integer getGold() {
		int gold = template.getGold(cr);
		if(rank.contains(EnemyRank.BOSS1, EnemyRank.BOSS2)) {
			return gold * 4;
		}
		if(rank.equals(EnemyRank.MOB) || race.equals(Race.GIMIC)) {
			return gold / 2;
		}
		if(narrowRace != null) {
			gold = narrowRace.getGold(this, gold);
		}
		return gold;
	}

	/**
	 * しぶとさ
	 * @return
	 */
	public Integer getStubborn() {
		return template.getStubborn(rank, cr);
	}

	/**
	 * しぶとさ(比率計算)
	 * @return
	 */
	public Integer getStubborn(double d) {
		return new BigDecimal(template.getStubborn(rank, cr) * d).intValue();
	}

	/**
	 * 攻撃性
	 * @return
	 */
	public Integer getAggression() {
		return template.getAggression(cr);
	}

	/**
	 * 攻撃性(比率計算)
	 * @return
	 */
	public Integer getAggression(double d) {
		return new BigDecimal(template.getAggression(cr) * d).intValue();
	}

	/**
	 * 変数汎用（CR / a + b）* c
	 * @return
	 */
	public Integer getParameter(Integer a, Integer b, Integer c) {
		return (cr / a + b) * c;
	}

	public Integer getA1() {
		return template.getA1(cr);
	}

	public Integer getA2() {
		return template.getA2(cr);
	}

	public Integer getA3() {
		return template.getA3(cr);
	}

	public Integer getB1() {
		return template.getB1(cr);
	}

	public Integer getB2() {
		return template.getB2(cr);
	}

	public Integer getB3() {
		return template.getB3(cr);
	}

	public Integer getC1() {
		return template.getC1(cr);
	}

	public Integer getC2() {
		return template.getC2(cr);
	}

	public Integer getC3() {
		return template.getC3(cr);
	}

	public Integer getD1() {
		return template.getD1(cr);
	}

	public Integer getD2() {
		return template.getD2(cr);
	}

	public Integer getD3() {
		return new BigDecimal((cr + 3) / 2f).intValue();
	}

	public Integer getAttackRole() {
		if(narrowRace != null) {
			return narrowRace.getAttackRole(this);
		} else {
			return template.getRole(cr);
		}
	}

	public List<String> getBasicAttackTags() {
		List<String> tags = new ArrayList<>();
		tags.add(template.getBasicAttackType().label());
		if(narrowRace != null) {
			narrowRace.addBasicAttackTag(tags);
		}
		return tags;
	}

	public Target getBasicTarget() {
		AttackType type = template.getBasicAttackType();
		Target target = template.getBasicTarget();
		if(narrowRace != null) {
			return narrowRace.getBasicTarget(type, target, getStubborn());
		} else {
			return target;
		}
	}

	public List<EnemySkill> getDefaultSkills() {
		List<EnemySkill> skills = new ArrayList<>();
		skills.addAll(race.getSkills(this));
		if(narrowRace != null) {
			skills.addAll(narrowRace.getDefaultSkills(this));
			narrowRace.enlargeSkills(this, skills);
		}
		return skills;
	}

	public List<EnemySkill> getArrangeSkills() {
		List<EnemySkill> skills = new ArrayList<>();
		skills.add(createBasicSkill());
		skills.addAll(rank.getSkills(this));
		if(narrowRace != null) {
			skills.addAll(narrowRace.getArrangeSkills(this));
			narrowRace.enlargeSkills(this, skills);
		}
		return skills;
	}

	/**
	 * コピー用の文字列を生成
	 * @return
	 */
	public String createClipboardData() {
    	StringBuilder b = new StringBuilder();
    	b.append(getType().label());
    	if(narrowRace != null) {
    		b.append("［").append(narrowRace.getName()).append("］");

    	}
		b.append("\n");
		for (String tag : getTags()) {
	    	b.append("［").append(tag).append("］");
		}
		b.append("\n");
    	b.append("【ランク】CR" + getCharacterRank()).append("\n");
    	b.append("【識別難易度】" + getNotorietyLabel()).append("\n");
    	b.append("【STR】" + getStr()).append("\n");
    	b.append("【DEX】" + getDex()).append("\n");
    	b.append("【POW】" + getPow()).append("\n");
    	b.append("【INT】" + getInt()).append("\n");
    	b.append("【回避】" + getAvoidLabel()).append("\n");
    	b.append("【抵抗】" + getResistLabel()).append("\n");
    	b.append("【物理防御力】" + getPhysicalDefense()).append("\n");
    	b.append("【魔法防御力】" + getMagicDefense()).append("\n");
    	b.append("【最大HP】" + getHitPoint()).append("\n");
    	b.append("【ヘイト倍率】" + getHateLabel()).append("\n");
    	b.append("【行動力】" + getAction()).append("\n");
    	b.append("【移動力】" + getMove()).append("\n");
    	b.append("【因果力】" + getFate()).append("\n");
    	b.append("ドロップ期待値：").append(getGold()).append("G").append("\n");
    	if(!getDefaultSkills().isEmpty()) {
    		b.append("\n").append("▼自動取得特技").append("\n");
    		for (EnemySkill skill : getDefaultSkills()) {
    			b.append(skill.createClipboardData()).append("\n\n");
			}
    	}
		b.append("▼特技実装例").append("\n");
		for (EnemySkill skill : getArrangeSkills()) {
			b.append(skill.createClipboardData()).append("\n\n");
		}
    	return b.toString();
	}

	public String createBasicAttackRoleLabel(AttackType attackType) {
		Integer role = getAttackRole();
		Integer roleDice = template.getRoleDice();
		StringBuilder b = new StringBuilder();
		b.append("対決 (");
		if(rank.equals(EnemyRank.MOB)) {
			b.append(role + roleDice * 3);
			b.append(" [固定] / ");
		} else {
			b.append(role);
			b.append("+");
			b.append(roleDice);
			b.append("D / ");
		}
		b.append(attackType.role() + ")");
		return b.toString();
	}

	private EnemySkill createBasicSkill() {
		AttackType type = template.getBasicAttackType();
		Target target = getBasicTarget();
		Integer range = template.getBasicRange();
		Integer damage = template.getDamageFix(cr);
		Integer damageDice = template.getDamageDice();

		EnemySkill skill = new EnemySkill("基本攻撃手段", "メジャー");
		skill.setTags(getBasicAttackTags());
		skill.setRole(createBasicAttackRoleLabel(type));
		skill.setTarget(target.label());
		skill.setRange(range == 0 ? "至近" : range + "Sq");

		StringBuilder b = new StringBuilder();
		b.append("対象に［").append(damage).append("+").append(damageDice).append("D］");
		if(type.contains(AttackType.MELEE, AttackType.SHOOTING)) {
			b.append("の物理ダメージを与える。");
		} else {
			b.append("の魔法ダメージを与える。");
		}
		skill.setFunction(b.toString());
		return skill;
	}
/*
	public String getBasicSkillDatas() {
		AttackType type = template.getBasicAttackType();
		Integer role = template.getRole(cr);
		Integer roleDice = template.getRoleDice();

		Target target = template.getBasicTarget();
		Integer range = template.getBasicRange();
		StringBuilder b = new StringBuilder();
		b.append("［").append(type.label()).append("］＿");
		b.append("メジャー＿対決（");
		if(rank.equals(EnemyRank.MOB)) {
			Integer fixValue = role + roleDice * 3;
			b.append(fixValue).append("［固定］");
		} else {
			b.append(role).append("+").append(roleDice).append("D / ").append(type.role());
		}
		b.append("）＿");
		b.append(target.label()).append("＿");
		b.append(range == 0 ? "至近" : range + "Sq");
		return b.toString();
	}

	public String getBasicSkillRemarks() {
		AttackType type = template.getBasicAttackType();
		Integer damage = template.getDamageFix(cr);
		Integer damageDice = template.getDamageDice();
		StringBuilder b = new StringBuilder();
		b.append("［").append(damage).append("+").append(damageDice).append("D］");
		if(type.contains(AttackType.MELEE, AttackType.SHOOTING)) {
			b.append("の物理ダメージを与える。");
		} else {
			b.append("の魔法ダメージを与える。");
		}

		return b.toString();
	}
*/
}
