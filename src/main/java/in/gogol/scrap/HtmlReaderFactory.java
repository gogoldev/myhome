package in.gogol.scrap;

import java.util.HashMap;
import java.util.Map;

import in.gogol.exception.ApplicationException;

public class HtmlReaderFactory {

	private static Map<String, Class<? extends HtmlReader>> CLASS_INVENTORY = new HashMap<String, Class<? extends HtmlReader>>();

	public static HtmlReader getHtmlReader(String type) throws ApplicationException {
		init();
		Class<? extends HtmlReader> clazz = CLASS_INVENTORY.get(type);
		HtmlReader reader = null;

		try {
			reader = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ApplicationException("Error in instantiating HtmlReader");
		}

		return reader;
	}

	private static void init() {
		if (CLASS_INVENTORY.isEmpty()) {
			CLASS_INVENTORY.put("basic", HtmlReaderImpl.class);
		}
	}
}
