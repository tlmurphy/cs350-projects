/*----------------|
|   CS350         |
|   Project #2    |
|   Trevor Murphy |
|-----------------*/

/**
 * Holds the info for survey samples.
 */
public class CSample {

    private static int counter = 0;
    private int id = 000000;
    private String zipCode;
    private String carrier;
    private char rating;
    private String services = "---";
    private char[] charArray = services.toCharArray();

    public CSample() {
        id = ++counter;
    }

    // Getters and Setters

    public String getId() {
        return String.format("%06d", id);
    }

    public void setId(String id) { this.id = Integer.parseInt(id); }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public char getRating() {
        return rating;
    }

    public void setRating(char rating) {
        this.rating = rating;
    }

    public String getServices() {
        return services;
    }

    public void setVoice(boolean selected) {
        if (selected) charArray[0] = 'V';
        else charArray[0] = '-';
        services = String.valueOf(charArray);
    }

    public void setTextMessaging(boolean selected) {
        if (selected) charArray[1] = 'T';
        else charArray[1] = '-';
        services = String.valueOf(charArray);
    }

    public void setDataPlan(boolean selected) {
        if (selected) charArray[2] = 'D';
        else charArray[2] = '-';
        services = String.valueOf(charArray);
    }

    // If an entry is canceled decrement count.
    public void canceledEntry() {
        --counter;
    }

    // Format string to output to Jlist.
    public String returnedSample() {
        return String.format("%-24s %-23s %-26s %-24s %-20s", getId(), zipCode, carrier, rating, services);
    }
}
