package in.gogol.scrap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import in.gogol.exception.ApplicationException;

public class HtmlReaderImpl_WebDriver implements HtmlReader{

	@Override
	public InputStream readHtml(String url) throws ApplicationException{
		WebDriver driver = new ChromeDriver();
		ByteArrayInputStream bais = null;
		driver.get(url);
		try {
			bais = new ByteArrayInputStream(driver.getPageSource().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			new ApplicationException(e.getMessage());
		}
		
		return bais;
	}

}
