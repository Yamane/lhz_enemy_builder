package lhz.enemy.model.narrow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lhz.enemy.model.Enemy;
import lhz.enemy.model.EnemySkill;
import lhz.enemy.model.EnemyType;
import lhz.enemy.model.NarrowRace;
import lhz.enemy.model.Race;

public class Giant2 extends NarrowRace {

	public static final NarrowRace instance = new Giant2();

	private Giant2() {
		super(Race.DEMI_HUMAN, "氷巨人 (アイスジャイアント)");
	}

	@Override
	public List<String> getTags(Integer cr) {
		if(cr < 14) {
			return Arrays.asList("巨人", "冷気");
		} else {
			return Arrays.asList("巨人", "冷気", "高位保護");
		}
	}

	@Override
	public List<EnemyType> getApplyTypes() {
		return Arrays.asList(EnemyType.ARMOROR, EnemyType.ARCHER, EnemyType.HEALER);
	}

	@Override
	public List<EnemySkill> getDefaultSkills(Enemy enemy) {
		EnemySkill skill = new EnemySkill("炎熱の巨人", "常時");
		skill.setFunction("このエネミーは［冷気］タグを持つプロップの影響の全体もしくは一部 (GMが選択) を受けなくても良い。また、［即時移動］するときは［高さ］タグの影響を受けず、《ダッシュ》を行なうことができない。");
		return Arrays.asList(skill);
	}

	@Override
	public List<EnemySkill> getArrangeSkills(Enemy enemy) {
		if(enemy.getType().equals(EnemyType.ARMOROR)) {
			EnemySkill skill1 = createMajorMeleeSkill(enemy, "フロストアックス");
			skill1.addTag("冷気");
			Integer arg1 = getDamageFix(enemy);
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔マイナー〕この行動は「対象：2体」になる。", arg1);

			EnemySkill skill2 = new EnemySkill("凍てつく氷壁", "ムーブ", null, "広範囲1(選択)", "至近");
			Integer arg2 = enemy.getParameter(8, 1, 10);
			skill2.createFunction("対象は［軽減(［火炎］以外):%d］を得る。この効果はCSとして扱い、ラウンド終了時まで持続する。", arg2);

			EnemySkill skill3 = new EnemySkill("凍てつく防御", "ダメージ適用直前", null, "単体", "2Sq");
			skill3.createFunction("この行動は［火炎］ダメージに対して使用できない。対象が受ける予定のHPダメージから-%dする。1回の攻撃に対して1回まで有効。", arg2);

			return Arrays.asList(skill1, skill2, skill3);
		}
		if(enemy.getType().equals(EnemyType.ARCHER)) {
			EnemySkill skill1 = createMajorShotSkill(enemy, "スロウトマホーク", "3Sq");
			skill1.addTag("冷気");
			Integer arg1 = getDamageFix(enemy);
			Integer arg2 = enemy.getAggression(0.5);
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔マイナー〕ダメージロールに+%dする。", arg1, arg2);

			EnemySkill skill2 = new EnemySkill("きらめく吐息", "セットアップ", null, "直線3 (選択)", "至近");
			Integer arg3 = enemy.getParameter(8, 1, 10);
			skill2.createFunction("対象が［軽減 (冷気)］を持っていない場合［弱点 (冷気):%d］を与える。この効果はOSとして扱い、ラウンド終了時まで持続する。", arg3);

			EnemySkill skill3 = new EnemySkill("大遠投", "ムーブ");
			skill3.createFunction("コストとして直後のマイナーを失う。直後に行う《★》を「射程:5Sq」にしてよい。\n★:このエネミーの持つ「タイミング:メジャー / 対象:単体」の［射撃攻撃］の特技名称");

			return Arrays.asList(skill1, skill2, skill3);
		}
		if(enemy.getType().equals(EnemyType.HEALER)) {
			EnemySkill skill1 = createMajorMeleeSkill(enemy, "アイシクルメイス");
			skill1.addTag("冷気");
			Integer arg1 = getDamageFix(enemy);
			Integer arg2 = enemy.getParameter(9, 1, 10);
			skill1.createFunction("対象に［%d+2D］の物理ダメージを与える。〔マイナー〕このエネミーの【HP】を%d点回復しBS1つを解除する。", arg1, arg2);

			EnemySkill skill2 = new EnemySkill("凍てつく障壁", "セットアップ", null, "広範囲2 (選択)", "至近");
			Integer arg3 = enemy.getStubborn(0.3);
			skill2.createFunction("対象に［障壁:%d］を与える。〔因果力1〕［障壁］の強度は+%dされる。", arg3, arg2);

			EnemySkill skill3 = new EnemySkill("氷の大楯", "セットアップ", null, "単体", "2Sq");
			Integer arg4 = enemy.getParameter(7, 1, 10);
			skill3.createFunction("対象とこのエネミーは［軽減 (［火炎］以外):%d］を得る。", arg4);

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
	public Integer getMagicDefense(Enemy enemy) {
		Integer def = super.getMagicDefense(enemy);
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
