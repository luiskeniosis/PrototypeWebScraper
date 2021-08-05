package lucien.Webscrape;

public class StoreMeta {
	public enum Stores{
		EBAY("https://www.ebay.com/sch/i.html?_from=R40&_nkw=merica+labz&_sacat=0&_sop=15&rt=nc&LH_BIN=1&_pgn=1",
			"div.s-item__wrapper.clearfix",
			50,
			"h1.srp-controls__count-heading",
			"h3.s-item__title",
			"span.s-item__price",
			"span.s-item__shipping.s-item__logisticsCost",
			"a.s-item__link",
			"Nutrition");
		
		String baseSearchURL;
		String htmlListingCount;
		int displayPerPage;
		String htmlListing;
		String htmlTitle;
		String htmlItemPrice;
		String htmlShippingPrice;
		String htmlItemURL;
		String storeType;
		
		Stores(String baseSearchURL, String htmlListingCount, int displayPerPage, String htmlListing, String htmlTitle, String htmlItemPrice, String htmlShippingPrice, String htmlItemURL, String storeType){
			this.baseSearchURL = baseSearchURL;
			this.htmlListingCount = htmlListingCount;
			this.displayPerPage = displayPerPage;
			this.htmlListing = htmlListing;
			this.htmlTitle = htmlTitle;
			this.htmlItemPrice = htmlItemPrice;
			this.htmlShippingPrice = htmlShippingPrice;
			this.htmlItemURL = htmlItemURL;
			this.storeType = storeType;
		}

		public String getBaseSearchURL() {
			return baseSearchURL;
		}

		public void setBaseSearchURL(String baseSearchURL) {
			this.baseSearchURL = baseSearchURL;
		}

		public String getHtmlListingCount() {
			return htmlListingCount;
		}

		public void setHtmlListingCount(String htmlListingCount) {
			this.htmlListingCount = htmlListingCount;
		}

		public int getDisplayPerPage() {
			return displayPerPage;
		}

		public void setDisplayPerPage(int displayPerPage) {
			this.displayPerPage = displayPerPage;
		}

		public String getHtmlListing() {
			return htmlListing;
		}

		public void setHtmlListing(String htmlListing) {
			this.htmlListing = htmlListing;
		}

		public String getHtmlTitle() {
			return htmlTitle;
		}

		public void setHtmlTitle(String htmlTitle) {
			this.htmlTitle = htmlTitle;
		}

		public String getHtmlItemPrice() {
			return htmlItemPrice;
		}

		public void setHtmlItemPrice(String htmlItemPrice) {
			this.htmlItemPrice = htmlItemPrice;
		}

		public String getHtmlShippingPrice() {
			return htmlShippingPrice;
		}

		public void setHtmlShippingPrice(String htmlShippingPrice) {
			this.htmlShippingPrice = htmlShippingPrice;
		}

		public String getHtmlItemURL() {
			return htmlItemURL;
		}

		public void setHtmlItemURL(String htmlItemURL) {
			this.htmlItemURL = htmlItemURL;
		}

		public String getStoreType() {
			return storeType;
		}

		public void setStoreType(String storeType) {
			this.storeType = storeType;
		}
	}
}
