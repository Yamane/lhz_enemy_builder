package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Giant1 extends NarrowRace {

	public static final NarrowRace instance = new Giant1();

	private Giant1() {
		super(Race.DEMI_HUMAN, "火炎巨人 (ファイアジャイアント)");
	}

	@Override
	public List<String> getTags(Integer cr) {
		if(cr < 14) {
			return Arrays.asList("巨人", "火炎");
		} else {
			return Arrays.asList("巨人", "火炎", "高位保護");
		}
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.FENCER, EnemyType.SPEAR, EnemyType.SHOOTER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("炎熱の巨人", "常時");
		skill.setFunction("このエネミーは［火炎］タグを持つプロップの影響の全体もしくは一部 (GMが選択) を受けなくても良い。また、［即時移動］するときは［高さ］タグの影響を受けず、《ダッシュ》を行なうことができない。");
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		if(enemy.getType().equals(EnemyType.FENCER)) {
			EnemySkill skill1 = createMajorMeleeSkill(enemy, "燃えさかる大斧");
			skill1.addTag("火炎");
			Integer arg1 = getDamageFix(enemy);
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔対象：衰弱〕対象に直接ダメージを与える。その点数は対象の持つ［衰弱］の強度と等しい。", arg1);

			EnemySkill skill2 = new EnemySkill("大股歩き", "ムーブ");
			skill2.setFunction("コストとして直後のマイナーを失う。このエネミーは3Sqまで［通常移動］して良い。〔因果力1〕移動は［即時移動］となり、「移動時に1回でも同じSqにいたキャラクター(選択)」を二次対象として［放心］を与える。");

			Integer arg2 = enemy.getStr() * 2 + 5;
			EnemySkill skill3 = new EnemySkill("復讐の反撃", "ダメージ適用直後");
			skill3.createFunction("あなたから見て2Sq以内にいる味方がダメージを受けた時使用する。ダメージを与えた対象に直接ダメージ%d点を与える。", arg2);

			return Arrays.asList(skill1, skill2, skill3);
		}
		if(enemy.getType().equals(EnemyType.SPEAR)) {
			EnemySkill skill1 = createMajorMeleeSkill(enemy, "串刺しの槍");
			skill1.addTag("火炎");
			Integer arg1 = getDamageFix(enemy);
			Integer arg2 = enemy.getParameter(6, 1, 5);
			skill1.createFunction("対象に［%d+2D］の物理ダメージと［追撃:%d］を与える。この［追撃］は対象が現在のSqを離れないかぎり解除出来ない。", arg1, arg2);

			EnemySkill skill2 = new EnemySkill("武器の振り回し", "ムーブ");
			skill2.setFunction("コストとして直後のマイナーを失う。直後に行う《★》を「対象：3体」にしてよい。\n★:このエネミーの持つ「タイミング:メジャー / 対象:単体」の［白兵攻撃］の特技名称");

			EnemySkill skill3 = new EnemySkill("巨人の突撃", "ムーブ");
			skill3.setLimit("シーン1回");
			skill3.createFunction("コストとして直後のマイナーを失う。このエネミーは4Sqまで現在位置から直線上のSqに［通常移動］を行う。「移動時に1回でも同じSqにいたキャラクター(無差別・このエネミーをのぞく)」を二次対象として1Sqまでの［即時移動（強制）］を行わなければならない。この時二次対象の移動先として、このエネミーの移動経路に含まれるSqは選択できない。");

			return Arrays.asList(skill1, skill2, skill3);
		}
		if(enemy.getType().equals(EnemyType.SHOOTER)) {
			EnemySkill skill1 = createMajorShotSkill(enemy, "溶岩投げ", "3Sq");
			skill1.addTag("火炎");
			Integer arg1 = getDamageFix(enemy);
			Integer arg2 = enemy.getParameter(6, 1, 5);
			skill1.createFunction("対象に［%d+2D］の魔法ダメージと［衰弱:%d］を与える。", arg1, arg2);

			EnemySkill skill2 = new EnemySkill("爆炎の火柱", "セットアップ");
			skill2.setLimit("シーン1回");
			Integer arg3 = enemy.getParameter(7, 1, 5);
			skill2.createFunction("このエネミーより4Sq以内のSq1つを指定する。このラウンド終了時、そのSqにいる対象(無差別)に%d点の直接ダメージを与える。", arg3);

			EnemySkill skill3 = new EnemySkill("大遠投", "ムーブ");
			skill3.createFunction("コストとして直後のマイナーを失う。直後に行う《★》を「射程：5Sq」にしてよい。\n★:このエネミーの持つ「タイミング:メジャー / 対象:単体」の［射撃攻撃］の特技名称");

			return Arrays.asList(skill1, skill2, skill3);
		}

		return Collections.emptyList();
	}

	@Override
	public void enlargeSkills(Enemy enemy, List<EnemySkill> skills) {
		for (EnemySkill skill : skills) {
			if(skill.getTags().contains("白兵攻撃") && "至近".equals(skill.getRange())) {
				skill.setRange("1Sq");
			}
		}
	}

	@Override
	public Integer getAvoid(Enemy enemy) {
		Integer def = super.getAvoid(enemy);
		return def > 2 ?  def - 2 : 0;
	}

	@Override
	public Integer getResist(Enemy enemy) {
		Integer def = super.getResist(enemy);
		return def > 2 ?  def - 2 : 0;
	}

	@Override
	public Integer getPhysicalDefense(Enemy enemy) {
		Integer def = super.getPhysicalDefense(enemy);
		Integer add = enemy.getStubborn(0.25);
		if(def / 2 < add) {
			add = def / 2;
		}
		return def + add;
	}

	@Override
	public Integer getHitPoint(Enemy enemy, Integer defaultValue) {
		return defaultValue + enemy.getStubborn();
	}
}
