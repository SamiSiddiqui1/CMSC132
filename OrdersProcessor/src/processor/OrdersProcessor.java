package processor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** @author BigButtBandit */
public class OrdersProcessor {

    /* For the brave TA souls that are reading this, I applaud you. For when I wrote this, only God
     * and I knew what I was doing, now only God knows. GLHF */
    public static void main(String[] args) {

	/* Print user configuration in console. */
	Scanner input = new Scanner(System.in);
	System.out.print("Enter item's data file name: ");
	String itemName = input.nextLine();
	System.out.print("Enter 'y' for multiple threads, any other character otherwise: ");
	String isMultiThreaded = input.nextLine();
	boolean isInvalid;
	int numOrders = 0;
	do {
	    try {
		System.out.print("Enter number of orders to process: ");
		numOrders = Integer.parseInt(input.nextLine());
		isInvalid = false;
	    } catch (NumberFormatException e) {
		System.out.println("Invalid Input");
		isInvalid = true;
	    }
	} while (isInvalid);
	System.out.print("Enter order's base filename: ");
	String baseName = input.nextLine();
	System.out.print("Enter result's filename: ");
	String resultsName = input.nextLine();
	input.close();
	/* Process order */
	OrderProcessor order = new OrderProcessor(baseName, itemName, numOrders);
	long startTime = System.currentTimeMillis();
	if (isMultiThreaded.equalsIgnoreCase("y")) {
	    /* Using multithread. */
	    ArrayList<Thread> threadList = new ArrayList<Thread>();
	    for (int i = 1; i <= numOrders; i++) {	// start the threads
		Thread t = new Thread(new MyThread(order, i));
		threadList.add(t);
		t.start();
	    }
	    for (Thread i : threadList) {	// join the threads
		try {
		    i.join();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	    order.processSummary();		// add Summary of All Orders
	} else {
	    /* Not using multithread. */
	    for (int i = 1; i <= numOrders; i++) {
		order.processorOrder(i);
	    }
	    order.processSummary();		// add Summary of All Orders
	}
	/* Write into results file. */
	StringBuffer finalMessage = new StringBuffer();
	StringBuffer[] messageList = order.getMessage();
	for (int i = 1; i < messageList.length; i++) {
	    finalMessage.append(messageList[i]);
	}
	try {
	    BufferedWriter resultFile = new BufferedWriter(new FileWriter(resultsName));
	    resultFile.write(finalMessage.toString());
	    resultFile.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	/* Print last bit of information onto console */
	long endTime = System.currentTimeMillis();
	System.out.println("Processing time (msec): " + (endTime - startTime));
	System.out.println("Results can be found in the file: " + resultsName);
    }
}

class MyThread implements Runnable {

    private OrderProcessor order;
    private int orderNum;

    public MyThread(OrderProcessor order, int orderNum) {
	this.order = order;
	this.orderNum = orderNum;
    }

    public void run() {

	order.processorOrder(orderNum);
    }
}
