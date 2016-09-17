package mediaRentalManager;

/** Media object representing movie types. Extends Media.
 * 
 * @author BigButtBandit */
class Movie extends Media {

    private String rating;

    /* Default constructor. */
    public Movie() {
	super();
	this.rating = null;
    }

    /* Standard constructor. */
    public Movie(String title, int numOfCopy, String rating) {
	super(title, numOfCopy);
	this.rating = rating;
    }

    public String getRating() {

	return this.rating;
    }

    @Override
    public String toString() {

	return super.toString() + ", Rating: " + this.rating;
    }
}
