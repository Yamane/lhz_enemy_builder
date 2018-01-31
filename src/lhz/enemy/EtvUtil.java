package lhz.enemy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class EtvUtil {

	public static void loadNarrowRaceClasses(String packageName) {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Set<ClassInfo> classes = ClassPath.from(loader).getTopLevelClasses(packageName);
			for (ClassInfo info : classes) {
				Class.forName(info.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws Exception {
		String script = readText("/enemy_list.json");
		Map<String, Object> map = parseJson(script);
		System.out.println(map);
		System.out.println(System.getProperty("user.dir"));
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getEnemyList() {
		try {
			String script = readText("/enemy_list.json");
			return (List<Map<String, Object>>) parseJson(script).get("list");
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}


	public static Map<String, Object> parseJson(String script) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        Object value = engine.eval(script);
        return jsonToMap(value);
	}

    @SuppressWarnings("unchecked")
	private static Map<String, Object> jsonToMap(Object obj) throws Exception {
        Set<String> keys = (Set<String>) keySetMethod.invoke(obj);
        Map<String, Object> map = new HashMap<String, Object>();
        for (String key : keys) {
        	Object value = getMethod.invoke(obj, key);
        	if(scriptClass.isInstance(value)) {
        		if((boolean) isArrayMethod.invoke(value)) {
                	map.put(key, jsonToList(value));
        		} else {
                	map.put(key, jsonToMap(value));
        		}
        	} else {
            	map.put(key, value);
        	}
		}
        return map;
    }

    private static List<Object> jsonToList(Object obj) throws Exception {
    	List<Object> list = new ArrayList<>();
    	Integer index = 0;
    	while((boolean) containsKeyMethod.invoke(obj, index.toString())) {
        	Object value = getMethod.invoke(obj, index.toString());
        	if(scriptClass.isInstance(value)) {
        		if((boolean) isArrayMethod.invoke(value)) {
        			list.add(jsonToList(value));
        		} else {
        			list.add(jsonToMap(value));
        		}
        	} else {
    			list.add(value);
        	}

    		index++;
    	}
    	return list;
    }

    public static String readText(String filePath){
		List<String> textList = new ArrayList<String>();
		StringBuilder b = new StringBuilder();
		InputStream is = EtvUtil.class.getResourceAsStream(filePath);
		Reader r = null;
		BufferedReader br = null;
		try {
			r = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader(r);
			for (;;) {
				String text = br.readLine();
				if (text == null) {
					break;
				}
				textList.add(text);
				b.append(text + "\n");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
			if (r != null)
				try {
					r.close();
				} catch (IOException e) {
				}
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
				}
		}
		return b.toString();
	}

	private static Class<?> scriptClass;
	private static Method getMethod;
	private static Method keySetMethod;
	private static Method isArrayMethod;
	private static Method containsKeyMethod;

	static {
		try {
			scriptClass = Class.forName("jdk.nashorn.api.scripting.ScriptObjectMirror");
			getMethod = scriptClass.getMethod("get", Object.class);
			keySetMethod = scriptClass.getMethod("keySet");
			isArrayMethod = scriptClass.getMethod("isArray");
			containsKeyMethod = scriptClass.getMethod("containsKey", Object.class);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
