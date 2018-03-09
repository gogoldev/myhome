package in.gogol.scrap;

import java.io.InputStream;

import in.gogol.exception.ApplicationException;

public interface HtmlReader {

	InputStream readHtml(String url) throws ApplicationException;
}
