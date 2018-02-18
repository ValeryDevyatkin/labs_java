package mypack;
import java.util.HashMap;
import java.util.Map;

public class Map_Class {
	Map<String, String> abc = new HashMap<String, String>() {
		{
			put("��������", "potato");
			put("������", "apple");
			put("����", "bread");
			put("������", "sun");
			put("�����", "boot");
			put("���", "cat");
			put("�����", "wall");
			put("�����", "book");
			put("�����", "wall");
			put("���", "juice");
		}
	};
	
	Map<String, String> cba;

	public Map_Class() {
		cba = new HashMap<String, String>();
		Object keys[] = abc.keySet().toArray();
		Object values[] = abc.values().toArray();
		for (int i = 0; i<keys.length; i++) {
			cba.put(values[i].toString(), keys[i].toString());
		}
	}
	
	public String translate(String word) {
		String translated_word = abc.get(word);
		if (translated_word != null)
			return translated_word;
		else {
			translated_word = cba.get(word);
			if (translated_word != null)
				return translated_word;
			else
				return "NO MATCH";
		}
	}
}
