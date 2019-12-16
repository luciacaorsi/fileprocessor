package en.arvato.fileprocessor.serrvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class performs comparison operations between lists.
 * 
 * @author Lucía Caorsi
 */
public class ListComparer {

    private static ListComparer INSTANCE;
    
    private ListComparer() {        
    }
     
    public static ListComparer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ListComparer();
        }
        return INSTANCE;
    }
    
	/**
	 * Gets the difference between two given lists of Strings. Note that the order
	 * is important in this operation. E.g.: the difference between ["a", "b", "c"]
	 * and ["b", "c", "d"] would be ["a"]. the difference between ["b", "c", "d"]
	 * and ["a", "b", "c"] would be ["d"].
	 * 
	 * @param a
	 *            the first list to compare
	 * @param b
	 *            the second list to compare
	 * @return A new {@link List} representing the difference between the two lists
	 */
	public List<String> getDifference(List<String> a, List<String> b) {
		return a.stream()
				.filter(name -> !b.contains(name))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the intersection between two given lists of Strings, i.e. the items that
	 * appear on both lists. The order is not important in this operation.
	 * 
	 * @param a
	 *            the first list to compare
	 * @param b
	 *            the second list to compare
	 * @return A new {@link List} representing the intersection between the two
	 *         lists
	 */
	public List<String> getIntersection(List<String> a, List<String> b) {
		return a.stream()
				.filter(b::contains)
				.collect(Collectors.toList());
	}
}