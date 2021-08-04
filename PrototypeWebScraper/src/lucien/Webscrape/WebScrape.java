package lucien.Webscrape;

import java.util.ArrayList;

public class WebScrape {

    public static String[] blackList = new String[] {
	    "napalm",
	    "suprimo",
	    "star",
	    "pipe",
	    "beef",
	    "stix",
	    "bcaa",
    };

    public static String[] rwboomKeywords = new String[] {
	    "red",
	    "white",
	    "boom",
	    "pre",
	    "workout",
	    "preworkout",
    };

    public static String[] patriotWheyKeywords = new String[] {
	    "patriot",
	    "whey",
	    "protein",
	    "powder"
    };
    
    public static ArrayList<ItemListing> preworkoutList = new ArrayList<ItemListing>();
    public static ArrayList<ItemListing> proteinList = new ArrayList<ItemListing>();
    
    private static int lastPreworkoutListSize = 0;
    private static int lastProteinListSize = 0;
    
    public static void main (String[] args) {
	SearchEbay.startSearch("https://www.ebay.com/sch/i.html?_from=R40&_nkw=merica+labz&_sacat=0&_sop=15&rt=nc&LH_BIN=1&_pgn=1");
	SearchGNC.startSearch("https://www.gnc.com/merica-energy/?start=0&sz=64");
	printFullReport();
    }
    
    public static void filterListings(ArrayList<ItemListing> rawListings) {
	for(ItemListing item : rawListings) {
	    String itemName = item.getFullName().toLowerCase();
	    if(isBlacklisted(itemName)) continue;
	    if(isDuplicate(item)) continue;
	    if(isRWBoom(itemName)) {
		item.setType("Preworkout");
		WebScrape.preworkoutList.add(item);
	    }
	    else if(isPatriotWhey(itemName)) {
		item.setType("Protein");
		WebScrape.proteinList.add(item);
	    }
	}
    }

    private static boolean isBlacklisted(String itemName) {
	for(String word : blackList) {
	    if(itemName.contains(word)) return true;
	}
	return false;
    }

    private static boolean isRWBoom(String itemName) {
	for(String word : rwboomKeywords) {
	    if(itemName.contains(word)) return true;
	}
	return false;
    }

    private static boolean isPatriotWhey(String itemName) {
	for(String word : patriotWheyKeywords) {
	    if(itemName.contains(word)) return true;
	}
	return false;
    }
    
    private static boolean isDuplicate(ItemListing itemToCheck) {
	for(ItemListing item : preworkoutList) {
	    if(item.isSame(itemToCheck)) return true;
	}
	
	for(ItemListing item : proteinList) {
	    if(item.isSame(itemToCheck)) return true;
	}
	return false;
    }
    
    public static void postSearchReport() {
	System.out.println("Found " + (preworkoutList.size() - lastPreworkoutListSize) + " new listings for Preworkout.");
	System.out.println("Found " + (proteinList.size() - lastProteinListSize) + " new listings for Protein.");
	lastPreworkoutListSize = preworkoutList.size();
	lastProteinListSize = proteinList.size();
	System.out.println("===============================================");
    }
    
    private static void printFullReport() {
	for(ItemListing item : preworkoutList)
	    item.print();
	for(ItemListing item : proteinList)
	    item.print();
	System.out.println("===============================================");
    }
}
