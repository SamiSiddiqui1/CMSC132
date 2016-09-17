package processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Map;
import java.util.TreeMap;

/** NOTE: the name of this class is different from the other one! That other one has a "s"!
 * iLikeTurtles
 * 
 * @author BigButtBandit */
public class OrderProcessor {

    private String baseName;
    /* Order detail of all clients, including summary */
    private StringBuffer[] finalMessage;
    /* <item name, Data<quantity, cost per item>> */
    private TreeMap<String, Data> itemsMap = new TreeMap<String, Data>();	// Synchronization
                                                                         	// lock

    /** Standard Constructor
     * 
     * @param baseName
     * @param itemName
     * @param numOrders */
    public OrderProcessor(String baseName, String itemName, int numOrders) {
	this.baseName = baseName;
	/* +1 for 1 indexing, +1 for total summary. */
	this.finalMessage = new StringBuffer[numOrders + 2];
	/* Initate itemsMap with complete items list, 0 for quantity, and corresponding price. */
	try {
	    BufferedReader itemFile = new BufferedReader(new FileReader(itemName));
	    String line;
	    while ((line = itemFile.readLine()) != null) {
		this.itemsMap.put(line.replaceAll("\\s+\\d+.*", ""),
		                  new Data(0,
		                           Double.parseDouble((line.replaceAll("[^\\d.]", "")))));
	    }
	    itemFile.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /** Note: called by main method from OrdersProcessor.
     * 
     * @return finaMessage fully appended */
    public StringBuffer[] getMessage() {

	return this.finalMessage;
    }

    /** Return a BufferReader that is ready to be read. Note: not closed.
     * 
     * @param fullFileName
     * @return BufferedReader */
    private BufferedReader getFile(String fullFileName) {

	try {
	    return new BufferedReader(new FileReader(fullFileName));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return null;
    }

    /** Process the order associated with the order number given. This method creates individual
     * message tailored for this particular client, and adds the message to finalMessage array.
     * Synchronized: appends itemsMap. NOTE: should be called before processSummary.
     * 
     * @param orderNum */
    public void processorOrder(int orderNum) {

	/* Get ID of client. */
	BufferedReader file = getFile(baseName + orderNum + ".txt");
	String ID = "";
	try {
	    ID = file.readLine().replaceAll("[^\\d.]", "");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Reading order for client with id: " + ID);
	/* Read and insert this client's order information into local TreeMap (clientMap) AND insert
	 * the client's information into global TreeMap (itemsMap). */
	TreeMap<String, Data> clientMap = new TreeMap<String, Data>();
	String line;
	try {
	    while ((line = file.readLine()) != null) {			// while still readable
		String itemName = line.replaceAll("\\s+\\d+.*", "");
		/* Insert into local TreeMap (clientMap). */
		Data count = clientMap.get(itemName);
		if (count != null) {				// increment the quantity if the
		                    				// item already exist in the tree
		    count.increment();
		} else {					// insert new one otherwise. NOTE:
		        					// slow algorithm!
		    clientMap.put(itemName, new Data(1, itemsMap.get(itemName).getSingleCost()));
		}
		/* Insert into global TreeMap (itemsMap). NOTE: accessed by multiple thread */
		synchronized (itemsMap) {
		    itemsMap.get(itemName).increment();
		}
	    }
	    file.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	/* Append this client's order information into a StringBuffer. */
	StringBuffer clientOrder = new StringBuffer();
	clientOrder.append("----- Order details for client with Id: " + ID + " -----\n");
	Double total = 0.0;
	while (!clientMap.isEmpty()) {
	    Map.Entry<String, Data> entry = clientMap.pollFirstEntry();
	    int quantity = entry.getValue().getQuantity();
	    String item = entry.getKey();
	    Double perCost = entry.getValue().getSingleCost();
	    total += quantity * perCost;
	    clientOrder.append("Item's name: " + item + ", Cost per item: "
	                       + NumberFormat.getCurrencyInstance().format(perCost) + ", Quantity: "
	                       + quantity + ", Cost: "
	                       + NumberFormat.getCurrencyInstance().format(quantity * perCost)
	                       + "\n");
	}
	clientOrder.append("Order Total: " + NumberFormat.getCurrencyInstance().format(total)
	                   + "\n");
	/* add the string buffer into global StringBuffer array (finalMessage). */
	this.finalMessage[orderNum] = clientOrder;
    }

    /** This method creates a StringBuffer appended with summary of all orders, it then inserts this
     * StringBuffer into global StringBuffer array (finalMessage). NOTE: must be called after
     * processorOrder, or itemsQuantity will be empty */
    public void processSummary() {

	Double total = 0.0;
	StringBuffer message = new StringBuffer();
	message.append("***** Summary of all orders *****\n");
	while (!itemsMap.isEmpty()) {
	    Map.Entry<String, Data> entry = itemsMap.pollFirstEntry();
	    int quantity = entry.getValue().getQuantity();
	    if (quantity != 0) {	// only append if necessary
		String item = entry.getKey();
		Double perCost = entry.getValue().getSingleCost();
		total += quantity * perCost;
		message.append("Summary - Item's name: " + item + ", Cost per item: "
		               + NumberFormat.getCurrencyInstance().format(perCost)
		               + ", Number sold: " + quantity + ", Item's Total: "
		               + NumberFormat.getCurrencyInstance().format(quantity * perCost)
		               + "\n");
	    }
	}
	message.append("Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(total)
	               + "\n");
	finalMessage[finalMessage.length - 1] = message;
    }
    
}

/** This class is created for faster incrementation of values in TreeMap AND better store of price
 * per item */
class Data {

    private int quantity;
    private Double singleCost;

    public Data(int quantity, Double singleCost) {
	this.quantity = quantity;
	this.singleCost = singleCost;
    }

    public int getQuantity() {

	return this.quantity;
    }

    public Double getSingleCost() {

	return this.singleCost;
    }

    public void increment() {

	this.quantity++;
    }
    /*If you're reading this it's too late.*/
}
