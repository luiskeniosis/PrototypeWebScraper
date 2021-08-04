package lucien.Webscrape;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SearchEbay {
    public static void startSearch(String URL) {
	System.out.println("Starting WebScrape on eBay listings.");
	WebScrape.filterListings(buildDatabase(getPages(URL)));
	System.out.println("eBay listings filtered and sorted!");
	WebScrape.postSearchReport();
    }

    //Gathers the Jsoup document of all pages of eBay results given an initial URL
    private static ArrayList<Document> getPages(String baseURL) {
	ArrayList<Document> listingPages = new ArrayList<Document>();
	int numListings = 0;
	int maxPages = 0;
	try {
	    Document page1 = Jsoup.connect(baseURL).userAgent("Mozilla/5.0").timeout(30000).get();
	    listingPages.add(page1);
	    numListings = Integer.parseInt(page1.select("h1.srp-controls__count-heading").select("span.BOLD").first().text());
	    maxPages = (int)Math.ceil((double)numListings/50);
	    for(int i = 2; i <= maxPages; i++) {
		String newURL = baseURL.substring(0, baseURL.length()-1) + i;
		listingPages.add(Jsoup.connect(newURL).userAgent("Mozilla/5.0").timeout(30000).get());
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Gathered " + maxPages + " page(s) containing " + numListings + " listing(s).");
	return listingPages;
    }

    //Looks through all eBay listings, building a database of items
    private static ArrayList<ItemListing> buildDatabase(ArrayList<Document> listingPages) {
	ArrayList<ItemListing> rawListings = new ArrayList<ItemListing>();
	for(Document page : listingPages) {
	    try {
		for (Element listing : page.select("div.s-item__wrapper.clearfix")) {
		    if(listing.select("h3.s-item__title").text().equals(""))
			continue;
		    else {
			String itemCost = listing.select("span.s-item__price").text();
			String shippingCost = listing.select("span.s-item__shipping.s-item__logisticsCost").text();
			String totalCost = parsePriceData(itemCost, shippingCost);
			if(totalCost.equals(""))
			    continue;
			String listingName = listing.select("h3.s-item__title").text();
			String listingURL = listing.select("a.s-item__link").attr("href");
			rawListings.add(new ItemListing(listingName, Double.parseDouble(totalCost), "eBay", listingURL));
		    }
		}
	    }
	    catch (Exception e){
		e.printStackTrace();
	    }
	}
	return rawListings;
    }

    //Formats price data for non customizable items.
    //Plan: Add items with a price range to a special static list to go through later
    private static String parsePriceData(String itemCost, String shippingCost) {
	double numShippingCost = 0;
	String totalCost = "";
	if(!shippingCost.contains("Free") && !shippingCost.isEmpty())
	    numShippingCost = Double.parseDouble(shippingCost.replaceAll("[^.&&[^0-9]]", ""));
	if(itemCost.contains("to")) {
	    //int midIndex = itemCost.indexOf(" to ");
	    //Double lowerPrice = Double.parseDouble(itemCost.substring(0, midIndex).replaceAll("[^.&&[^0-9]]", "")) + numShippingCost;
	    //Double higherPrice = Double.parseDouble(itemCost.substring(midIndex).replaceAll("[^.&&[^0-9]]", "")) + numShippingCost;
	    //totalCost = "From " + lowerPrice + " to " + higherPrice;
	}
	else {
	    double numItemCost = Double.parseDouble(itemCost.replaceAll("[^.&&[^0-9]]", ""));
	    totalCost = Double.toString(numItemCost + numShippingCost);
	}
	return totalCost;
    }
}
