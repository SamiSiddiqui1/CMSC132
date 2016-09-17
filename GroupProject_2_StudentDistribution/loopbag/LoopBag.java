
package loopbag;
import java.util.Iterator;
public interface LoopBag<E> extends Iterable<E> {
	
    /**
     * Adds the given item to bad LoopBag. 
     * @param item the item to add
     */
    void insert(E item);
    
    /**
     * Returns the number of items in this LoopBag.
     * @return the number of items in this LoopBag
     */
    int size();

    /**
     * Returns true if this LoopBag is empty and false otherwise.
     * @return true if this LoopBag is empty; false otherwise.
     **/
    boolean isEmpty();
    
    /**
    *   if the bag contains a given item?
    * @return true if bag contains the item. false otherwise
    */
    boolean contains(E item);
    
    /**
    * creates the union with the given LoopBag
    */
    void union(LoopBag<E> lb);
                
    /**
     * Returns an iterator for this LoopBag.
     * @return an iterator for this LoopBag
     */
    Iterator<E> iterator();
}
