package fr.egloo.wikommune.services;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class JSoupExampleTest {

	@Test
	public void testGetDocumentElements() {
		Document doc;
		try {
			doc = Jsoup.connect("http://en.wikipedia.org/").get();
			Elements newsHeadlines = doc.select("#mp-itn b a");
			assertNotNull(newsHeadlines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBestOf() {
		assertNotNull(Boolean.TRUE);
	}
}
