package in.gogol.scrap;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.HttpHeaders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import in.gogol.exception.ApplicationException;

public class HtmlReaderImpl implements HtmlReader {
	
	private static final String URL="http://m.livescore.com/~~/app1-home/soccer/";
	private static final String MALFORMED_URL="The url to scrap is malformed";
	private static final String UNREADABLE_STREAM="The url can not be read";
	
	private ApplicationException appExcp = null;

	@Override
	public InputStream readHtml(String url) throws ApplicationException{
		InputStream is = null;
		String _url =url;
		if(_url ==null || _url.isEmpty()){
			_url=URL;
		}
		
		URL url2 = getUrl(_url);
		if(appExcp==null){

			is = readUrlToStream(url2);
		}
		return is;
	}
	
	
	private URL getUrl(String url){
		URL _url = null;
		if(url != null && !url.isEmpty()){
			try {
				_url = new URL(url);
				System.out.println("try");
			} catch (MalformedURLException e) {
				_url=null;
				appExcp=new ApplicationException(MALFORMED_URL);
				appExcp.printStackTrace();
				System.out.println("catch");
			}
		}
		return _url;
	}
	
	private InputStream readUrlToStream(URL url){
		InputStream is = null;
		
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
			conn.getHeaderFields().forEach((header,values)->{
				System.out.println(header + " : "+values);
			});
			is=conn.getInputStream();
		} catch (IOException e) {
			appExcp=new ApplicationException(UNREADABLE_STREAM);
			appExcp.printStackTrace();
		}
		
		return is;
	}

}
