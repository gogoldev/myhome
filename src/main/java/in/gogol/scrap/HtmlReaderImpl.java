package in.gogol.scrap;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import in.gogol.exception.ApplicationException;

public class HtmlReaderImpl implements HtmlReader {
	
	private static final String URL="http://m.livescore.com/";
	private static final String MALFORMED_URL="The url to scrap is malformed";
	private static final String UNREADABLE_STREAM="The url can not be read";
	
	private ApplicationException appExcp = null;

	@Override
	public InputStream readHtml(String url) {
		InputStream is = null;
		String _url =url;
		if(_url !=null && !_url.isEmpty()){
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
			} catch (MalformedURLException e) {
				_url=null;
				appExcp=new ApplicationException(MALFORMED_URL);
				appExcp.printStackTrace();
			}
		}
		
		return _url;
	}
	
	private InputStream readUrlToStream(URL url){
		InputStream is = null;
		
		try {
			is=url.openConnection().getInputStream();
		} catch (IOException e) {
			appExcp=new ApplicationException(UNREADABLE_STREAM);
			appExcp.printStackTrace();
		}
		
		return is;
	}

}
