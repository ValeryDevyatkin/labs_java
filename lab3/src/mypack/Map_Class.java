package mypack;
import java.util.HashMap;
import java.util.Map;

public class Map_Class {
	Map<String, String> abc = new HashMap<String, String>() {
		{
			put("картошка", "potato");
			put("€блоко", "apple");
			put("хлеб", "bread");
			put("солнце", "sun");
			put("сапог", "boot");
			put("кот", "cat");
			put("стена", "wall");
			put("книга", "book");
			put("стена", "wall");
			put("сок", "juice");
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
