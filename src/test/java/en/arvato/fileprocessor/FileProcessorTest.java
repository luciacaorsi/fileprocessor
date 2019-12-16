package en.arvato.fileprocessor;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import en.arvato.fileprocessor.io.FileReader;
import en.arvato.fileprocessor.json.JsonParser;
import en.arvato.fileprocessor.serrvice.ListComparer;

@RunWith(MockitoJUnitRunner.class)
public class FileProcessorTest {

	@Mock
	private FileReader fileReader;
	@Mock
	private ListComparer listComparer;
	@Mock
	private JsonParser jsonParser;

	private File file1;
	private File file2;

	private String expectedJson;

	@Before
	public void setup() throws IOException {
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		file1 = new File(classloader.getResource("File1.txt").getFile());
		file2 = new File(classloader.getResource("File2.txt").getFile());

		List<String> a = Arrays.asList("word1", "word2", "word5", "word6");
		List<String> b = Arrays.asList("word5", "word6", "word3", "word4");

		Mockito.when(fileReader.getNamesFromFile(file1)).thenReturn(a);
		Mockito.when(fileReader.getNamesFromFile(file2)).thenReturn(b);

		List<String> differenceAB = Arrays.asList("word1", "word2");
		List<String> differenceBA = Arrays.asList("word3", "word4");
		List<String> intersection = Arrays.asList("word5", "word6");

		Mockito.when(listComparer.getDifference(a, b)).thenReturn(differenceAB);
		Mockito.when(listComparer.getDifference(b, a)).thenReturn(differenceBA);
		Mockito.when(listComparer.getIntersection(a, b)).thenReturn(intersection);

		expectedJson = "{\r\n" +
				"\"onlyInList1\": [\r\n" +
				"\"word1\",\r\n" +
				"\"word2\"\r\n" +
				"],\r\n" +
				"\"onlyInList2\": [\r\n" +
				"\"word3\",\r\n" +
				"\"word4\"\r\n" +
				"],\r\n" +
				"\"inBothLists\": [\r\n" +
				"\"word5\",\r\n" +
				"\"word6\"\r\n" +
				"]\r\n" +
				"}";

		Mockito.when(jsonParser.getJsonResponse(Mockito.any())).thenReturn(expectedJson);
	}

	@Test
	public void testCompareFiles() throws IOException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		FileProcessor p = new FileProcessor(fileReader, listComparer, jsonParser);
		p.compareFiles(file1, file2);

		ObjectMapper mapper = new ObjectMapper();
		assertEquals(mapper.readTree(expectedJson), mapper.readTree(outContent.toString()));
	}
}