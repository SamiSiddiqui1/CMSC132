package mediaRentalManager;

/** Media object representing album types. Extends Media.
 * 
 * @author BigButtBandit */
class Album extends Media {

    private String artist;
    private String songs;

    /* Default constructor. */
    public Album() {
	super();
	this.artist = null;
	this.songs = null;
    }

    /* Standard constructor. */
    public Album(String title, int numOfCopy, String artist, String song) {
	super(title, numOfCopy);
	this.artist = artist;
	this.songs = song;
    }

    public String getArtist() {

	return this.artist;
    }

    public String getSongs() {

	return this.songs;
    }

    @Override
    public String toString() {

	return super.toString() + ", Artist: " + this.artist + ", Songs: " + this.songs;
    }
}
