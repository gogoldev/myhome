package in.gogol.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import in.gogol.exception.ApplicationException;
import in.gogol.scrap.HtmlReader;
import in.gogol.scrap.HtmlReaderFactory;

@Path("test")
public class TestApp {
	
	private static final String URL="http://m.livescore.com/";
	
	@Path("htmlreader")
	@GET
	public Response testReader(){
		HtmlReader reader = null;
		
		try {
			reader = HtmlReaderFactory.getHtmlReader("htmlunit");
			InputStream is = reader.readHtml(URL);
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String content = rd.lines().collect(Collectors.joining());
			//String responseContent = content.substring(content.indexOf('Z')+1);
			//BufferedReader rd2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(responseContent.getBytes("UTF-8")))));
			//String content3 = rd2.lines().collect(Collectors.joining());
			return Response.ok(content).build();
		} catch (ApplicationException  e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error").build();
		}
	}
}
