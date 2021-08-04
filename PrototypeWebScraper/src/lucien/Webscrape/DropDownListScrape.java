package lucien.Webscrape;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DropDownListScrape {

    public static void main (String[] args) {
	String baseURL = "https://www.ebay.com/itm/124814037284?hash=item1d0f7f1124%3Ag%3AnUIAAOSw%7EUhg8e4-&LH_BIN=1";
	try {
	    Document page1 = Jsoup.connect(baseURL).userAgent("Mozilla/5.0").timeout(30000).get();
	    for (Element listing : page1.select("div.u-flL.sh-col")) {
		System.out.println(listing.select("option").text());
	    }
	}catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
