package pe.apiconz.apps.perusmart;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

public class FeedReader {

	private String feedURL;

	public FeedReader(String feedURL) {
		this.feedURL = feedURL;
	}

	public List<Articulo> getItems() throws Exception{

		Log.d(MainActivity.TAG, "Entro a getItems");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		FeedParseHandler handler = new FeedParseHandler();
		saxParser.parse(feedURL, handler);

		Log.d(MainActivity.TAG, " " + handler.getRssItems().size());
		return handler.getRssItems();
	}
	
}
