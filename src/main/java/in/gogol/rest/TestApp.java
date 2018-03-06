package in.gogol.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import in.gogol.exception.ApplicationException;
import in.gogol.scrap.HtmlReader;
import in.gogol.scrap.HtmlReaderFactory;

@Path("test")
public class TestApp {
	
	@Path("htmlreader")
	public Response testReader(){
		HtmlReader reader = null;
		
		try {
			reader = HtmlReaderFactory.getHtmlReader("basic");
			InputStream is = reader.readHtml(null);
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String content = rd.lines().collect(Collectors.joining());
			
			return Response.ok(content).build();
		} catch (ApplicationException | UnsupportedEncodingException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
