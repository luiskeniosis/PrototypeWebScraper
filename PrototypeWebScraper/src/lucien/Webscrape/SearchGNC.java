package lucien.Webscrape;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//Shipping Info:
//$9.99 Standard Shipping
//Free Shipping on Orders $49+
public class SearchGNC {
    public static void startSearch(String URL) {
	System.out.println("Starting WebScrape on GNC listings.");
	WebScrape.filterListings(buildDatabase(getPages(URL)));
	System.out.println("GNC listings filtered and sorted!");
	WebScrape.postSearchReport();
    }

    private static ArrayList<Document> getPages(String baseURL) {
	ArrayList<Document> listingPages = new ArrayList<Document>();
	int numListings = 0;
	int maxPages = 0;
	try {
	    Document page1 = Jsoup.connect(baseURL)
	            .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
	            .header("cookie", "incap_ses_436_255598=zI1vN7X6+BY84PhGvPsMBjKChVcAAAAAVhJ+1//uCecPhV2QjUMw6w==")
	            .timeout(0)
	            .get();
	    listingPages.add(page1);
	    numListings = Integer.parseInt(page1.select("span.total-count").first().text());
	    maxPages = (int)Math.ceil((double)numListings/64);
	    for(int i = 2; i <= maxPages; i++) {
		String newURL = baseURL.substring(0, 41) + (64 * (i-1)) + baseURL.substring(42);
		System.out.println(newURL);
		listingPages.add(Jsoup.connect(newURL).userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
		         .timeout(5000).get());
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	catch(NullPointerException e) {
	    
	    System.out.println("Failed to bypass GNC's bot detection. Try again later.");
	}
	System.out.println("Gathered " + maxPages + " page(s) containing " + numListings + " listing(s).");
	return listingPages;
    }

    private static ArrayList<ItemListing> buildDatabase(ArrayList<Document> listingPages) {
	ArrayList<ItemListing> rawListings = new ArrayList<ItemListing>();
	for(Document page : listingPages) {
	    try {
		for (Element listing : page.select("div.product-tile")) {
		    String listingName = listing.select("span.tile-product-name").text();
		    Double itemCost = Double.parseDouble(listing.select("span.product-standard-price").text().replaceAll("[^.&&[^0-9]]", ""));
		    Double totalCost;
		    if(itemCost > 49)
			totalCost = itemCost;
		    else
			totalCost = itemCost + 9.99;
		    String listingURL = listing.select("a.name-link").attr("href");
		    rawListings.add(new ItemListing(listingName, totalCost, "GNC", listingURL));
		}
	    }
	    catch (Exception e){
		e.printStackTrace();
	    }
	}
	return rawListings;
    }
}
