package lhz.enemy.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.util.StringConverter;

public abstract class NarrowRace {

	private static List<NarrowRace> narrowRaces = new ArrayList<>();

	protected Race parent;
	protected String name;
	protected List<String> tags;

	public NarrowRace(Race parent, String name) {
		this.parent = parent;
		this.name = name;

		this.tags = new ArrayList<>();
		if(!narrowRaces.contains(this)) {
			narrowRaces.add(this);
		}
	}

	public String getName() {
		return name;
	}

	public List<String> getTags(Integer cr) {
		return tags;
	}

	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		return Collections.emptyList();
	}

	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		return Collections.emptyList();
	}

	public void enlargeSkills(Enemy enemy, List<EnemySkill> skills) {

	}

	public Race getParent() {
		return parent;
	}

	public Integer getAvoid(Enemy enemy) {
		return enemy.getTemplate().getAvoid(enemy.getCharacterRank());
	}

	public Integer getResist(Enemy enemy) {
		return enemy.getTemplate().getResist(enemy.getCharacterRank());
	}

	public Integer getPhysicalDefense(Enemy enemy) {
		return enemy.getTemplate().getPhysicalDefense(enemy.getCharacterRank());
	}

	public Integer getPhysicalDefense(Enemy enemy, double d) {
		int def = enemy.getTemplate().getPhysicalDefense(enemy.getCharacterRank());
		return new BigDecimal(def * d).intValue();
	}

	public Integer getMagicDefense(Enemy enemy) {
		return enemy.getTemplate().getMagicDefense(enemy.getCharacterRank());
	}

	protected Integer getMagicDefense(Enemy enemy, double d) {
		int def = enemy.getTemplate().getMagicDefense(enemy.getCharacterRank());
		return new BigDecimal(def * d).intValue();
	}

	public Integer getAction(Enemy enemy) {
		return enemy.getTemplate().getAction(enemy.getCharacterRank());
	}

	public Integer getMove(Enemy enemy) {
		return enemy.getTemplate().getMove(enemy.getCharacterRank());
	}


	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		return defaultValue;
	}

	public Integer getGold(Enemy enemy, Integer defaultValue) {
		return defaultValue;
	}

	public void addBasicAttackTag(List<String> tags) {

	}

	public Integer getAttackRole(Enemy enemy) {
		return enemy.getTemplate().getRole(enemy.getCharacterRank());
	}

	public Integer getDamageFix(Enemy enemy) {
		return enemy.getTemplate().getDamageFix(enemy.getCharacterRank());
	}

	public Target getBasicTarget(AttackType type, Target target, Integer stubborn) {
		return target;
	}

	private static List<EnemyType> applyList = Arrays.asList(EnemyType.values());

	public List<EnemyType> getApplyTypes() {
		return applyList;
	}

	protected EnemySkill createMajorMeleeSkill(Enemy enemy, String name) {
		EnemySkill skill = new EnemySkill(name, "メジャー");
		skill.addTag("白兵攻撃");
		skill.setRole(enemy.createBasicAttackRoleLabel(AttackType.MELEE));
		skill.setTarget("単体");
		skill.setRange("至近");
		return skill;
	}

	protected EnemySkill createMajorShotSkill(Enemy enemy, String name, String range) {
		EnemySkill skill = new EnemySkill(name, "メジャー");
		skill.addTag("射撃攻撃");
		skill.setRole(enemy.createBasicAttackRoleLabel(AttackType.SHOOTING));
		skill.setTarget("単体");
		skill.setRange(range);
		return skill;
	}

	protected EnemySkill createMajorMagicalSkill(Enemy enemy, String name, String target, String range) {
		EnemySkill skill = new EnemySkill(name, "メジャー");
		skill.addTag("魔法攻撃");
		skill.setRole(enemy.createBasicAttackRoleLabel(AttackType.MAGICAL));
		skill.setTarget(target);
		skill.setRange(range);
		return skill;
	}

	public static StringConverter<NarrowRace> converter = new StringConverter<NarrowRace>() {
		@Override
		public String toString(NarrowRace r) {
			return r != null ? r.getName() : "";
		}
		@Override
		public NarrowRace fromString(String label) {
			if(label != null) {
				for (NarrowRace r : NarrowRace.narrowRaces) {
					if(r.getName().equals(label)) {
						return r;
					}
				}
			}
			return null;
		}
	};

	public static List<NarrowRace> getList(Enemy enemy) {
		List<NarrowRace> result = new ArrayList<>();
		for (NarrowRace r : NarrowRace.narrowRaces) {
			if(r.getParent().equals(enemy.getRace()) && r.getApplyTypes().contains(enemy.getType())) {
				result.add(r);
			}
		}
		return result;
	}
}
