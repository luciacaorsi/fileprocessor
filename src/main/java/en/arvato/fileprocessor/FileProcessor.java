package en.arvato.fileprocessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import en.arvato.fileprocessor.io.FileReader;
import en.arvato.fileprocessor.json.JsonParser;
import en.arvato.fileprocessor.model.Response;
import en.arvato.fileprocessor.serrvice.ListComparer;

/**
 * Entry point for the application, this class manages the file comparison and
 * generates the JSON output.
 * 
 * @author Lucía Caorsi
 */
public class FileProcessor {
	
	private FileReader fileReader;
	private ListComparer listComparer;
	private JsonParser jsonParser;
	
	public FileProcessor() {
		fileReader = FileReader.getInstance();
		listComparer = ListComparer.getInstance();
		jsonParser = JsonParser.getInstance();
	}
	
	public FileProcessor(FileReader fileReader, ListComparer listComparer, JsonParser jsonParser) {
		this.fileReader = fileReader;
		this.listComparer = listComparer;
		this.jsonParser = jsonParser;
	}
	
	/**
	 * Compares two files containing lists of names and prints the JSON output
	 * comparing the file contents.
	 * 
	 * @param file1
	 *            the first file to compare
	 * @param file2
	 *            the second file to compare
	 * @throws IOException
	 *             if there is an error reading the files
	 */
	public void compareFiles(File file1, File file2) throws IOException {

		List<String> namesFromFile1 = fileReader.getNamesFromFile(file1);
		List<String> namesFromFile2 = fileReader.getNamesFromFile(file2);

		Response res = new Response();
		res.setOnlyInList1(listComparer.getDifference(namesFromFile1, namesFromFile2));
		res.setOnlyInList2(listComparer.getDifference(namesFromFile2, namesFromFile1));
		res.setInBothLists(listComparer.getIntersection(namesFromFile1, namesFromFile2));

		System.out.println(jsonParser.getJsonResponse(res));
	}
}