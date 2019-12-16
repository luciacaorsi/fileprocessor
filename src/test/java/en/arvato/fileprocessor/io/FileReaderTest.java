package en.arvato.fileprocessor.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FileReaderTest {
	
	private FileReader fileReader;
	
	@Before
	public void setUp() {
		fileReader = FileReader.getInstance();
	}
	
	@Test
	public void testReadFiles() throws IOException {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File file1 = new File(classloader.getResource("File1.txt").getFile());
		File file2 = new File(classloader.getResource("File2.txt").getFile());

		List<String> namesFromFile1 = fileReader.getNamesFromFile(file1);
		assertEquals(4, namesFromFile1.size());
		assertTrue(namesFromFile1.contains("word1"));
		assertTrue(namesFromFile1.contains("word2"));
		assertTrue(namesFromFile1.contains("word5"));
		assertTrue(namesFromFile1.contains("word6"));

		List<String> namesFromFile2 = fileReader.getNamesFromFile(file2);
		assertEquals(4, namesFromFile2.size());
		assertTrue(namesFromFile2.contains("word5"));
		assertTrue(namesFromFile2.contains("word6"));
		assertTrue(namesFromFile2.contains("word3"));
		assertTrue(namesFromFile2.contains("word4"));
	}
	

	@Test(expected = FileNotFoundException.class)
	public void testReadNullFile() throws IOException {
		fileReader.getNamesFromFile(null);
	}
}