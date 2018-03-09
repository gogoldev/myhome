package in.gogol.scrap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import in.gogol.exception.ApplicationException;

public class HtmlReaderImpl_HtmlUnit implements HtmlReader{

	@Override
	public InputStream readHtml(String url) throws ApplicationException {
		WebClient webClient = null;
		webClient = new WebClient();
		System.out.println("Javascript is " + (webClient.getOptions().isJavaScriptEnabled()?"enabled":"disabled"));
		System.out.println("Css is " + (webClient.getOptions().isCssEnabled()?"enabled":"disabled"));
		HtmlPage page = null;
		ByteArrayInputStream bias = null;
		try {
			page = webClient.getPage(url);
			webClient.waitForBackgroundJavaScript(30000);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
			throw new ApplicationException("Source page did not respond OK");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new ApplicationException("Webpage Url does not seem right!!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApplicationException("IO Exception Occured while reading the page");
		}finally{
			webClient.close();
		}
		
		bias = new ByteArrayInputStream(page.asXml().getBytes());
		return bias;
	}

}
