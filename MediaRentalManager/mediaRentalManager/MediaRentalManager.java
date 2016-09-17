package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt {

    public static int limited = 2;	// LIMITED plan default limit.
    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private ArrayList<Media> mediaList = new ArrayList<Media>();

    /* Return a copy of the customer list, each element is instead the customer's name. */
    private ArrayList<String> customerTitle() {

	ArrayList<String> customerTitle = new ArrayList<String>();
	for (Customer c : this.customerList)
	    customerTitle.add(c.getName());
	return customerTitle;
    }

    /* Return a copy of the customer list, each element is instead the media's title. */
    private ArrayList<String> mediaTitle() {

	ArrayList<String> mediaTitle = new ArrayList<String>();
	for (Media m : this.mediaList)
	    mediaTitle.add(m.getTitle());
	return mediaTitle;
    }

    @Override
    public void addCustomer(String name, String address, String plan) {

	customerList.add(new Customer(name, address, plan));
    }

    @Override
    public void addMovie(String title, int copiesAvailable, String rating) {

	mediaList.add(new Movie(title, copiesAvailable, rating));
    }

    @Override
    public void addAlbum(String title, int copiesAvailable, String artist, String songs) {

	mediaList.add(new Album(title, copiesAvailable, artist, songs));
    }

    @Override
    public void setLimitedPlanLimit(int value) {

	MediaRentalManager.limited = value;
    }

    @Override
    public String getAllCustomersInfo() {

	Collections.sort(this.customerList);
	StringBuffer info = new StringBuffer("***** Customers' Information *****\n");
	for (Customer c : this.customerList)
	    info.append(c.toString() + "\n");
	return info.toString();
    }

    @Override
    public String getAllMediaInfo() {

	Collections.sort(this.mediaList);
	StringBuffer info = new StringBuffer("***** Media Information *****\n");
	for (Media m : this.mediaList)
	    info.append(m.toString() + "\n");
	return info.toString();
    }

    @Override
    public boolean addToQueue(String customerName, String mediaTitle) {

	int indexOfCustomer = this.customerTitle().indexOf(customerName);
	if (indexOfCustomer == -1) // Return false if the customer DNE in database.
	    return false;
	Customer customer = this.customerList.get(indexOfCustomer);
	/* Return false if the customer's interest list already contains the media or if the media
	 * DNE in database. */
	if (customer.intTitleList().contains(mediaTitle) || !this.mediaTitle().contains(mediaTitle))
	    return false;
	else {
	    /* Add the media to the customer's interest queue. */
	    int mediaIndex = mediaTitle().indexOf(mediaTitle);
	    customer.interest.add(this.mediaList.get(mediaIndex));
	    return true;
	}
    }

    @Override
    public boolean removeFromQueue(String customerName, String mediaTitle) {

	int indexOfCustomer = this.customerTitle().indexOf(customerName);
	if (indexOfCustomer == -1) // Return false if the customer DNE.
	    return false;
	Customer customer = this.customerList.get(indexOfCustomer);
	/* Return false if the customer's interest list does not contains the media or if the media
	 * DNE in database. */
	if (!customer.intTitleList().contains(mediaTitle)
	    || !this.mediaTitle().contains(mediaTitle))
	    return false;
	else {
	    /* Remove the media. */
	    int mediaIndex = mediaTitle().indexOf(mediaTitle);
	    customer.interest.remove(this.mediaList.get(mediaIndex));
	    return true;
	}
    }

    @Override
    public String processRequests() {

	Collections.sort(this.customerList);
	StringBuffer message = new StringBuffer();
	/* Process request for each customer and concatenate the message. */
	for (Customer c : this.customerList)
	    message.append(c.processQueue());
	return message.toString();
    }

    @Override
    public boolean returnMedia(String customerName, String mediaTitle) {

	int indexCustomer = this.customerTitle().indexOf(customerName);
	Customer c = this.customerList.get(indexCustomer);
	/* If the customer is currently in possession of the media. */
	if (c.renTitleList().contains(mediaTitle)) {
	    /* Increase the number of copies available for the media. */
	    c.rented.get(c.renTitleList().indexOf(mediaTitle)).incNumOfCopy();
	    /* Remove the media from the customer's rented queue. */
	    c.rented.remove(c.renTitleList().indexOf(mediaTitle));
	    return true;
	}
	return false;
    }

    @Override
    public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {

	ArrayList<String> returnList = mediaTitle();
	ArrayList<ArrayList<String>> totalList = new ArrayList<ArrayList<String>>();
	/* Create four different arrayList for each parameter that is not null. Simultaneously add
	 * the list the totalList. */
	if (title != null) {
	    ArrayList<String> titleList = new ArrayList<String>();
	    for (Media m : this.mediaList)
		if (m.getTitle().equals(title))
		    titleList.add(m.getTitle());
	    totalList.add(titleList);
	}
	if (rating != null) {
	    ArrayList<String> ratingList = new ArrayList<String>();
	    for (Media m : this.mediaList)
		if ((m instanceof Movie) && (((Movie) m).getRating().equals(rating)))
		    ratingList.add(m.getTitle());
	    totalList.add(ratingList);
	}
	if (artist != null) {
	    ArrayList<String> artistList = new ArrayList<String>();
	    for (Media m : this.mediaList)
		if ((m instanceof Album) && ((Album) m).getArtist().equals(artist))
		    artistList.add(m.getTitle());
	    totalList.add(artistList);
	}
	if (songs != null) {
	    ArrayList<String> songsList = new ArrayList<String>();
	    for (Media m : this.mediaList)
		/* Instead of comparing names. Songs are determined by whether if the list of songs
		 * contains the parameter songs' name. */
		if ((m instanceof Album) && ((Album) m).getSongs().contains(songs))
		    songsList.add(m.getTitle());
	    totalList.add(songsList);
	}
	/* Finalize returnList as the intersection of the four different arrayLists. For which every
	 * list that is available. */
	for (ArrayList<String> list : totalList)
	    returnList.retainAll(list);
	/* Sort it in alphabetical order. */
	Collections.sort(returnList);
	return returnList;
    }
}
