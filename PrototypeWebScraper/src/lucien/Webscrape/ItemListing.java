package lucien.Webscrape;

public class ItemListing {
    private String type;
    private String fullName;
    private Double price;
    private String originWebsite;
    private String URL;
    private String acceptsOffer;
    
    public ItemListing(String fullName, Double price, String originWebsite, String URL) {
	this.fullName = fullName;
	this.price = price;
	this.originWebsite = originWebsite;
	this.URL = URL;
    }
    
    public void print() {
	System.out.printf( "%-80s | %-4.2f | %1s | %5s | %n%-200s %n%n", fullName, price, type, originWebsite, URL);
    }
    
    public boolean isSame(ItemListing item) {
	if(this.getFullName().equals(item.getFullName())) return true;
	return false;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOriginWebsite() {
        return originWebsite;
    }

    public void setOriginWebsite(String originWebsite) {
        this.originWebsite = originWebsite;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public String getAcceptsOffer() {
	return acceptsOffer;
    }

    public void setAcceptsOffer(String acceptsOffer) {
	this.acceptsOffer = acceptsOffer;
    }
}
