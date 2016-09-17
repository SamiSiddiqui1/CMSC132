package sixdegrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Experiment {

    public static void main(String[] args) {
//	String s = "test;asdasdasdad";
//	System.out.println(Arrays.toString(s.split(";")));
//	
//	ArrayList<String> test = new ArrayList<String>();
//	test.add("test1");
//	test.add("test2");
//	System.out.println(test.size());
//	System.out.println(test.get(test.size() - 1));
	
	TreeMap<String, Integer> st = new TreeMap<String, Integer>();
	st.put("test0", 0);
	st.put("test1", 1);
	st.put("test2", 2);
	st.put("test3", 3);
	for(String s : st.navigableKeySet())
	    System.out.println(s);
    }
}
