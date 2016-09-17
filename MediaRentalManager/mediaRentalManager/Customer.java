package mediaRentalManager;

import java.util.ArrayList;

/** Representing the constituent customers managed by MediaRentalManager. Will process its own
 * interest and rental queues.
 * 
 * @author BigButtBandit */
class Customer implements Comparable<Customer> {

    private String name;
    private String address;
    private String plan;
    /* Two ArrayList of type <Media> to represent the interest and rented queue. */
    protected ArrayList<Media> interest = new ArrayList<Media>();;
    protected ArrayList<Media> rented = new ArrayList<Media>();;

    /* Default constructor. */
    public Customer() {
	this.name = null;
	this.address = null;
	this.plan = null;
    }

    /* Standard constructor. */
    public Customer(String name, String address, String plan) {
	this.name = name;
	this.address = address;
	this.plan = plan;
    }

    /* Return a copy of the interest queue, each element is instead the Media's title. */
    protected ArrayList<String> intTitleList() {

	ArrayList<String> intTitleList = new ArrayList<String>();
	for (Media m : this.interest)
	    intTitleList.add(m.getTitle());
	return intTitleList;
    }

    /* Return a copy of the rented queue, each element is instead the Media's title. */
    protected ArrayList<String> renTitleList() {

	ArrayList<String> renTitleList = new ArrayList<String>();
	for (Media m : this.rented)
	    renTitleList.add(m.getTitle());
	return renTitleList;
    }

    /* Return a concatenated String of the actions performed by processing the interest queue.
     * Remove Media from interest array whenever possible and add it to the rented array, then
     * decrement the number of copies of the Media. */
    protected String processQueue() {

	ArrayList<Media> list = new ArrayList<Media>(this.interest);
	StringBuffer message = new StringBuffer();
	/* For limited plans. */
	if (this.plan.equals("LIMITED")) {
	    for (Media m : list) {
		/* Check: 1) Plan limit. 2) If it's already rented. 3) If a copy is available. */
		if (this.rented.size() < MediaRentalManager.limited && !this.rented.contains(m)
		    && m.getNumOfCopy() > 0) {
		    message.append("Sending " + m.getTitle() + " to " + this.name + "\n");
		    this.rented.add(m);
		    m.decNumOfCopy();
		    this.interest.remove(m);
		}
	    }
	}
	/* For unlimited plans. */
	if (this.plan.equals("UNLIMITED")) {
	    for (Media m : list) {
		/* Check: 1) If it's already rented. 2) If a copy is available. */
		if (!this.rented.contains(m) && m.getNumOfCopy() > 0) {
		    message.append("Sending " + m.getTitle() + " to " + this.name + "\n");
		    this.rented.add(m);
		    m.decNumOfCopy();
		    this.interest.remove(m);
		}
	    }
	}
	return message.toString();
    }

    public String getName() {

	return this.name;
    }

    public String getAddress() {

	return this.address;
    }

    public String getPlan() {

	return this.plan;
    }

    /* Refer to pubTestAddingCustomer.txt for format. */
    @Override
    public String toString() {

	return "Name: " + this.name + ", Address: " + this.address + ", Plan: " + this.plan
	       + "\nRented: " + renTitleList() + "\nQueue: " + intTitleList();
    }

    /* Overridden for Collections.sort(), which is determined by calling the compareTo method */
    @Override
    public int compareTo(Customer c) {

	int compare = 0;
	if (c.getName() != null)
	    compare = this.name.compareTo(c.getName());
	return compare;
    }

    /* Overridden for (object).equals(). */
    @Override
    public boolean equals(Object o) {

	if (o instanceof Customer)
	    if (this.name != null && ((Customer) o).getName() != null)
		return this.getName().equals(((Customer) o).getName());
	return false;
    }
}
