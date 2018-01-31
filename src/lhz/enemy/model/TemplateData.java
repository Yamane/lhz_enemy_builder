package lhz.enemy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class TemplateData implements Serializable {

	private static Map<EnemyType, TemplateData> instances;

	static {
		instances = new HashMap<EnemyType, TemplateData>();
		for (EnemyType type : EnemyType.values()) {
			instances.put(type, new TemplateData(type));
		}
	}

	public static TemplateData get(EnemyType type) {
		return instances.get(type);
	}

	/** エネミータイプ */
	private EnemyType type;

	/** STR */
	private Integer strength;

	/** DEX */
	private Integer dexterity;

	/** POW */
	private Integer power;

	/** INT */
	private Integer intelligence;

	/** 回避係数 */
	private Float avoidCoef;

	/** 回避固定値 */
	private Integer avoidFix;

	/** 抵抗係数 */
	private Float resistCoef;

	/** 抵抗固定値 */
	private Integer resistFix;

	/** 物理防御係数 */
	private Float pdCoef;

	/** 物理防御固定値 */
	private Integer pdFix;

	/** 魔法防御係数 */
	private Float mdCoef;

	/** 魔法防御固定値 */
	private Integer mdFix;

	/** HP係数 */
	private Float hpCoef;

	/** HP固定値 */
	private Integer hpFix;

	/** 行動力固定値 */
	private Integer actionFix;

	/** ヘイトCR修正 */
	private Integer hateCr;

	/** ヘイト固定値 */
	private Integer hateFix;

	/** 巡航ダイス全体係数 */
	private Float damageAllCoef;

	/** 基本攻撃手段 */
	private AttackType basicAttackType;

	/** 基本攻撃手段(対象) */
	private Target basicTarget;

	/** 基本攻撃手段(射程) */
	private Integer basicRange;

	private TemplateData(EnemyType type) {
		this.type = type;
		switch (type) {
		case ARMOROR:
			this.strength = 7;
			this.dexterity = 3;
			this.power = 4;
			this.intelligence = 2;
			this.avoidCoef = 1.2f;
			this.avoidFix = 4;
			this.resistCoef = 1.1f;
			this.resistFix = 2;
			this.pdCoef = 2.2f;
			this.pdFix = 8;
			this.mdCoef = 1.7f;
			this.mdFix = 2;
			this.hpCoef = 8.5f;
			this.hpFix = 48;
			this.actionFix = -2;
			this.hateCr = 0;
			this.hateFix = 1;
			this.damageAllCoef = 1f;
			this.basicAttackType = AttackType.MELEE;
			this.basicTarget = Target.SINGLE;
			this.basicRange = 0;
			break;
		case FENCER:
			this.strength = 7;
			this.dexterity = 4;
			this.power = 2;
			this.intelligence = 3;
			this.avoidCoef = 1.1f;
			this.avoidFix = 4;
			this.resistCoef = 1.1f;
			this.resistFix = 2;
			this.pdCoef = 1.7f;
			this.pdFix = 5;
			this.mdCoef = 1.7f;
			this.mdFix = 1;
			this.hpCoef = 8.4f;
			this.hpFix = 45;
			this.actionFix = -2;
			this.hateCr = 2;
			this.hateFix = 1;
			this.damageAllCoef = 1f;
			this.basicAttackType = AttackType.MELEE;
			this.basicTarget = Target.SINGLE;
			this.basicRange = 0;
			break;
		case GRAPPLER:
			this.strength = 7;
			this.dexterity = 4;
			this.power = 2;
			this.intelligence = 3;
			this.avoidCoef = 1.1f;
			this.avoidFix = 2;
			this.resistCoef = 1.1f;
			this.resistFix = 4;
			this.pdCoef = 0.9f;
			this.pdFix = 2;
			this.mdCoef = 1.3f;
			this.mdFix = 3;
			this.hpCoef = 7.5f;
			this.hpFix = 45;
			this.actionFix = 0;
			this.hateCr = 0;
			this.hateFix = 1;
			this.damageAllCoef = 1f;
			this.basicAttackType = AttackType.MELEE;
			this.basicTarget = Target.SINGLE;
			this.basicRange = 0;
			break;
		case SUPPORTER:
			this.strength = 4;
			this.dexterity = 2;
			this.power = 7;
			this.intelligence = 3;
			this.avoidCoef = 1.2f;
			this.avoidFix = 2;
			this.resistCoef = 1.1f;
			this.resistFix = 7;
			this.pdCoef = 1.5f;
			this.pdFix = 3;
			this.mdCoef = 1.8f;
			this.mdFix = 5;
			this.hpCoef = 5.0f;
			this.hpFix = 35;
			this.actionFix = 2;
			this.hateCr = 0;
			this.hateFix = 1;
			this.damageAllCoef = 1f;
			this.basicAttackType = AttackType.MAGICAL;
			this.basicTarget = Target.SINGLE;
			this.basicRange = 4;
			break;
		case HEALER:
			this.strength = 3;
			this.dexterity = 2;
			this.power = 7;
			this.intelligence = 4;
			this.avoidCoef = 1.2f;
			this.avoidFix = 2;
			this.resistCoef = 1.1f;
			this.resistFix = 7;
			this.pdCoef = 1.8f;
			this.pdFix = 8;
			this.mdCoef = 1.7f;
			this.mdFix = 1;
			this.hpCoef = 6.0f;
			this.hpFix = 30;
			this.actionFix = -2;
			this.hateCr = 0;
			this.hateFix = 1;
			this.damageAllCoef = 1f;
			this.basicAttackType = AttackType.MELEE;
			this.basicTarget = Target.SINGLE;
			this.basicRange = 2;
			break;
		case SPEAR:
			this.strength = 4;
			this.dexterity = 7;
			this.power = 2;
			this.intelligence = 3;
			this.avoidCoef = 1.2f;
			this.avoidFix = 7;
			this.resistCoef = 1.1f;
			this.resistFix = 2;
			this.pdCoef = 1.7f;
			this.pdFix = 5;
			this.mdCoef = 1.5f;
			this.mdFix = 3;
			this.hpCoef = 6.0f;
			this.hpFix = 30;
			this.actionFix = 0;
			this.hateCr = 0;
			this.hateFix = 2;
			this.damageAllCoef = 1f;
			this.basicAttackType = AttackType.MELEE;
			this.basicTarget = Target.SINGLE;
			this.basicRange = 0;
			break;
		case ARCHER:
			this.strength = 3;
			this.dexterity = 4;
			this.power = 2;
			this.intelligence = 7;
			this.avoidCoef = 1.1f;
			this.avoidFix = 4;
			this.resistCoef = 1.1f;
			this.resistFix = 2;
			this.pdCoef = 1.6f;
			this.pdFix = 6;
			this.mdCoef = 1.9f;
			this.mdFix = 5;
			this.hpCoef = 5.0f;
			this.hpFix = 26;
			this.actionFix = 0;
			this.hateCr = 2;
			this.hateFix = 2;
			this.damageAllCoef = 0.9f;
			this.basicAttackType = AttackType.SHOOTING;
			this.basicTarget = Target.SINGLE;
			this.basicRange = 3;
			break;
		case SHOOTER:
			this.strength = 3;
			this.dexterity = 2;
			this.power = 5;
			this.intelligence = 7;
			this.avoidCoef = 1.2f;
			this.avoidFix = 2;
			this.resistCoef = 1.1f;
			this.resistFix = 5;
			this.pdCoef = 1.3f;
			this.pdFix = 3;
			this.mdCoef = 1.9f;
			this.mdFix = 5;
			this.hpCoef = 4.0f;
			this.hpFix = 26;
			this.actionFix = 1;
			this.hateCr = 2;
			this.hateFix = 2;
			this.damageAllCoef = 1f;
			this.basicAttackType = AttackType.MAGICAL;
			this.basicTarget = Target.SINGLE;
			this.basicRange = 4;
			break;
		case BOMMER:
			this.strength = 3;
			this.dexterity = 2;
			this.power = 5;
			this.intelligence = 7;
			this.avoidCoef = 1.2f;
			this.avoidFix = 2;
			this.resistCoef = 1.1f;
			this.resistFix = 5;
			this.pdCoef = 1.3f;
			this.pdFix = 3;
			this.mdCoef = 1.9f;
			this.mdFix = 5;
			this.hpCoef = 4.0f;
			this.hpFix = 26;
			this.actionFix = -2;
			this.hateCr = 2;
			this.hateFix = 2;
			this.damageAllCoef = 0.85f;
			this.basicAttackType = AttackType.MAGICAL;
			this.basicTarget = Target.MULTI;
			this.basicRange = 4;
			break;
		default:
			break;
		}
	}

	public EnemyType getType() {
		return this.type;
	}

	/**
	 * STR
	 * ( STR * 1.1 + CR ) / 3
	 * @param cr
	 * @return
	 */
	public Integer getStr(int cr) {
		return new BigDecimal((cr * 1.1 + strength) / 3).intValue();
	}

	/**
	 * DEX
	 * ( DEX * 1.1 + CR ) / 3
	 * @param cr
	 * @return
	 */
	public Integer getDex(int cr) {
		return new BigDecimal((cr * 1.1 + dexterity) / 3).intValue();
	}

	/**
	 * POW
	 * ( POW * 1.1 + CR ) / 3
	 * @param cr
	 * @return
	 */
	public Integer getPow(int cr) {
		return new BigDecimal((cr * 1.1 + power) / 3).intValue();
	}

	/**
	 * INT
	 * ( INT * 1.1 + CR ) / 3
	 * @param cr
	 * @return
	 */
	public Integer getInt(int cr) {
		return new BigDecimal((cr * 1.1 + intelligence) / 3).intValue();
	}

	/**
	 * 回避固定値
	 * ( CR * 回避係数 + 回避固定値 ) / 3
	 * @param cr
	 * @return
	 */
	public Integer getAvoid(int cr) {
		return new BigDecimal((cr * avoidCoef + avoidFix) / 3).intValue();
	}

	/**
	 * 回避ダイス数
	 * グラップラーのみ3 他は2
	 * @return
	 */
	public Integer getAvoidDice() {
		return type.equals(EnemyType.GRAPPLER) ? 3 : 2;
	}

	/**
	 * 抵抗固定値
	 * ( CR * 抵抗係数 + 抵抗固定値 ) / 3
	 * @param cr
	 * @return
	 */
	public Integer getResist(int cr) {
		return new BigDecimal((cr * resistCoef + resistFix) / 3).intValue();
	}

	/**
	 * 抵抗ダイス数
	 * グラップラーのみ3 他は2
	 * @return
	 */
	public Integer getResistDice() {
		return type.equals(EnemyType.GRAPPLER) ? 3 : 2;
	}

	/**
	 * 物理防御
	 * CR * 物防係数 + 物防固定値
	 * @param cr
	 * @return
	 */
	public Integer getPhysicalDefense(int cr) {
		return new BigDecimal(cr * pdCoef + pdFix).intValue();
	}

	/**
	 * 魔法防御
	 * CR * 魔防係数 + 魔防固定値
	 * @param cr
	 * @return
	 */
	public Integer getMagicDefense(int cr) {
		return new BigDecimal(cr * mdCoef + mdFix).intValue();
	}

	/**
	 * ヒットポイント
	 * CR * HP係数 + HP固定値
	 * @param cr
	 * @return
	 */
	public Integer getHitPoint(int cr) {
		return new BigDecimal(cr * hpCoef + hpFix).intValue();
	}

	/**
	 * ヘイト倍率
	 * ( CR + ヘイトCR修正) / 6 + ヘイト固定値
	 * @param cr
	 * @return
	 */
	public Integer getHate(int cr) {
		return  new BigDecimal((cr + hateCr) / 6 + hateFix).intValue();
	}

	/**
	 * ヘイト倍率強化
	 * @param cr
	 * @return
	 */
	public Integer getForceHate(int cr) {
		return new BigDecimal(cr / 5f).intValue() + 1;
	}

	/**
	 * ボスのヘイト倍率
	 * @param cr
	 * @return
	 */
	public Integer getBossHate(int cr) {
		return new BigDecimal(cr / 2.4f + 4).intValue();
	}

	/**
	 * 行動力
	 * ( CR * 1.1 + 7) / 3 + ( CR * 1.1 + 3) / 3 + 行動力固定値
	 * @param cr
	 * @return
	 */
	public Integer getAction(int cr) {
		int value1 = new BigDecimal((cr * 1.1f + 7) / 3).intValue();
		int value2 = new BigDecimal((cr * 1.1f + 3) / 3).intValue();
		return value1 + value2 + actionFix;
	}

	/**
	 * 移動力
	 * 一律2
	 * @param cr
	 * @return
	 */
	public Integer getMove(int cr) {
		return  2;
	}

	/**
	 * 基本攻撃手段の種別
	 * @return
	 */
	public AttackType getBasicAttackType() {
		return basicAttackType;
	}

	/**
	 * 基本攻撃手段の範囲
	 * @return
	 */
	public Target getBasicTarget() {
		return basicTarget;
	}

	/**
	 * 基本攻撃手段の射程
	 * @return
	 */
	public Integer getBasicRange() {
		return basicRange;
	}

	/**
	 * 判定固定値
	 * ( CR * 1.1 + 7) / 3 + タイプによる補正
	 * @param cr
	 * @return
	 */
	public Integer getRole(int cr) {
		int value = new BigDecimal((cr * 1.1 + 7) / 3).intValue();
		switch (type) {
		case ARMOROR:
		case FENCER:
		case GRAPPLER:
		case SUPPORTER:
		case HEALER:
			value += 2;
			break;
		case SPEAR:
			value += 1;
			break;
		default:
			break;
		}
		return value;
	}

	/**
	 * 判定ダイス数
	 * タイプによる補正
	 * @return
	 */
	public Integer getRoleDice() {
		switch (type) {
		case SPEAR:
		case ARCHER:
		case SHOOTER:
		case BOMMER:
			return 3;
		default:
			return 2;
		}
	}

	/**
	 * ダメージ固定値
	 * @param cr
	 * @return
	 */
	public Integer getDamageFix(int cr) {
		return getDamage(cr) - 7;
	}

	/**
	 * ダメージダイス数
	 * 一律2
	 * @param cr
	 * @return
	 */
	public Integer getDamageDice() {
		return 2;
	}

	/**
	 * タイプ別巡航ダメージ
	 * @param cr
	 * @return
	 */
	public Integer getDamage(int cr) {
		switch (type) {
		case ARMOROR:
		case FENCER:
		case GRAPPLER:
		case HEALER:
			return getPhysicalDamage1(cr);
		case SUPPORTER:
			return getMagicDamage1(cr);
		case SPEAR:
		case ARCHER:
			return getPhysicalDamage2(cr);
		case SHOOTER:
		case BOMMER:
			return getMagicDamage2(cr);
		default:
			return 0;
		}
	}

	/**
	 * 巡航ダメージ(物理・小)
	 * @param cr
	 * @return
	 */
	public Integer getPhysicalDamage1(int cr) {
		return getMagicDamage1(cr) + 8;
	}

	/**
	 * 巡航ダメージ(物理・大)
	 * @param cr
	 * @return
	 */
	public Integer getPhysicalDamage2(int cr) {
		return getMagicDamage2(cr) + 8;
	}

	/**
	 * 巡航ダメージ(魔法・小)
	 * @param cr
	 * @return
	 */
	public Integer getMagicDamage1(int cr) {
		return new BigDecimal(cr * 3.5).intValue() + 8;
	}

	/**
	 * 巡航ダメージ(魔法・大)
	 * @param cr
	 * @return
	 */
	public Integer getMagicDamage2(int cr) {
		return cr * 6 + 18;
	}

	/**
	 * ドロップ期待値
	 * @param cr
	 * @return
	 */
	public Integer getGold(int cr) {
		return new BigDecimal((cr + 2) * (cr + 2) * 0.72 + 17).intValue();
	}

	/**
	 * しぶとさ
	 * @param cr
	 * @return
	 */
	public Integer getStubborn(EnemyRank rank, int cr) {
		switch (rank) {
		case MOB:
			return cr * 4;
		case NORMAL:
			return cr * 8;
		case BOSS1:
			return cr * 40;
		case BOSS2:
			return cr * 20;
		case RAID:
			return cr * 80;
		default:
			return 0;
		}
	}

	/**
	 * 攻撃性
	 * @param cr
	 * @return
	 */
	public Integer getAggression(int cr) {
		switch (type) {
		case ARMOROR:
		case FENCER:
		case GRAPPLER:
		case HEALER:
		case SUPPORTER:
			return new Float(getDamage(cr) * 0.55f).intValue();
		case SPEAR:
		case ARCHER:
		case SHOOTER:
		case BOMMER:
			return new Float(getDamage(cr) * 0.85f).intValue();
		default:
			return 0;
		}
	}

	/* エネミー諸数値  */

	public Integer getA1(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 0.3 * 0.75 / 5f).intValue() * 5;
	}

	public Integer getA2(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 0.7 * 0.75 / 5f).intValue() * 5;
	}

	public Integer getA3(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 1.1 * 0.75 / 5f).intValue() * 5;
	}

	public Integer getB1(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 0.3 / 5f).intValue() * 5;
	}

	public Integer getB2(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 0.7 / 5f).intValue() * 5;
	}

	public Integer getB3(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 1.1 / 5f).intValue() * 5;
	}

	public Integer getC1(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 0.3).intValue();
	}

	public Integer getC2(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 0.6).intValue();
	}

	public Integer getC3(int cr) {
		return new BigDecimal(cr * 2.2 + 10).intValue();
	}

	public Integer getD1(int cr) {
		return cr + 3;
	}

	public Integer getD2(int cr) {
		return new BigDecimal((cr * 2.2 + 10) * 0.12).intValue() + 2;
	}

	public Integer getD3(int cr) {
		return new BigDecimal((cr + 3) / 2f).intValue();
	}

	/**
	 * ギミック解除値
	 * ガゼット28の記述に準拠
	 * @param cr
	 * @return
	 */
	public Integer getE(int cr) {
		return new BigDecimal(cr * 0.4f).intValue() + 11;
	}

	public Float getDummy() {
		return damageAllCoef;
	}

//	public static void main(String[] args) {
//		TemplateData temp = new TemplateData(EnemyType.ARMOROR);
//		for (int i = 1; i <= 30; i++) {
//			System.out.print("CR" + i + ":");
//			System.out.print(temp.getAvoid(i));
//			System.out.println();
//		}
//	}
}
