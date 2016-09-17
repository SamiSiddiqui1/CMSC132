package mediaRentalManager;

/** Representing the medias managed by MediaRentalManager. Parent class of Album and Movie.
 * 
 * @author BigButtBandit */
class Media implements Comparable<Media> {

    private String title;
    private int numOfCopy;

    /* Default constructor. */
    public Media() {
	this.title = null;
	this.numOfCopy = 0;
    }

    /* Standard constructor. */
    public Media(String title, int numOfCopy) {
	this.title = title;
	this.numOfCopy = numOfCopy;
    }

    public String getTitle() {

	return this.title;
    }

    public int getNumOfCopy() {

	return this.numOfCopy;
    }

    /* Decrease the number of copies available by 1. Return false if the number of copies is already
     * 0. */
    protected boolean decNumOfCopy() {

	if (this.numOfCopy > 0) {
	    this.numOfCopy--;
	    return true;
	} else {
	    return false;
	}
    }

    /* Increase the number of copies by 1. */
    protected boolean incNumOfCopy() {

	this.numOfCopy++;
	return true;
    }

    /* Refer to pubTestAddingMedia.txt for format. */
    @Override
    public String toString() {

	return "Title: " + this.title + ", Copies Available: " + this.numOfCopy;
    }

    /* Overridden for Collections.sort(), which is determined by calling the compareTo method */
    @Override
    public int compareTo(Media m) {

	int compare = 0;
	if (m.getTitle() != null)
	    compare = this.title.compareTo(m.getTitle());
	return compare;
    }

    @Override
    public boolean equals(Object o) {

	if (o instanceof Media)
	    if (this.title != null && ((Media) o).getTitle() != null)
		return this.getTitle().equals(((Media) o).getTitle());
	return false;
    }
}
