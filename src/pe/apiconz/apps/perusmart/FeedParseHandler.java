package pe.apiconz.apps.perusmart;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class FeedParseHandler extends DefaultHandler {

	private List<Articulo> rssItems;
	private Articulo currentItem;
	private boolean parsingTitle;
	private boolean parsingLink;
	private boolean parsingDate;

	public FeedParseHandler() {
		setRssItems(new ArrayList<Articulo>());
	}

	public List<Articulo> getRssItems() {
		return rssItems;
	}

	public void setRssItems(List<Articulo> rssItems) {
		this.rssItems = rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		Log.d(MainActivity.TAG, "startElement");
		Log.d(MainActivity.TAG, qName);
		
		if ("item".equals(qName)) {
			currentItem = new Articulo();
		} else if ("title".equals(qName)) {
			parsingTitle = true;
		} else if ("link".equals(qName)) {
			parsingLink = true;
		} else if("pubDate".equals(qName)){
			parsingDate = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		Log.d(MainActivity.TAG, "endElement");
		Log.d(MainActivity.TAG, qName);
		if ("item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			parsingTitle = false;
		} else if ("link".equals(qName)) {
			parsingLink = false;
		} else if("pubDate".equals(qName)){
			parsingDate = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingTitle) {
			if (currentItem != null) {
				currentItem.setTitulo(new String(ch, start, length));
			}
		} else if (parsingLink) {
			if (currentItem != null) {
				currentItem.setEnlace(new String(ch, start, length));
				parsingLink = false;
			}
		} else if (parsingDate) {
			if (currentItem != null) {
				currentItem.setFecha(new String(ch, start, length));
				parsingDate = false;
			}
		}
	}
}
