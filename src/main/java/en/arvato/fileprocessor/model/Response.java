package en.arvato.fileprocessor.model;

import java.util.List;

/**
 * This class represents the output of the application.
 * 
 * @author Lucía Caorsi
 */
public class Response {

	private List<String> onlyInList1;
	private List<String> onlyInList2;
	private List<String> inBothLists;

	public Response() {
	}

	public List<String> getOnlyInList1() {
		return onlyInList1;
	}

	public void setOnlyInList1(List<String> onlyInList1) {
		this.onlyInList1 = onlyInList1;
	}

	public List<String> getOnlyInList2() {
		return onlyInList2;
	}

	public void setOnlyInList2(List<String> onlyInList2) {
		this.onlyInList2 = onlyInList2;
	}

	public List<String> getInBothLists() {
		return inBothLists;
	}

	public void setInBothLists(List<String> inBothLists) {
		this.inBothLists = inBothLists;
	}
}