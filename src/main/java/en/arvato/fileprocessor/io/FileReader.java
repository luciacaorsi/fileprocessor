package en.arvato.fileprocessor.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Helper class for obtaining the lists of names from the text files.
 * 
 * @author Lucía Caorsi
 */
public class FileReader {
    private static FileReader INSTANCE;
     
    private FileReader() {        
    }
     
    public static FileReader getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FileReader();
        }
        return INSTANCE;
    }
	
	/**
	 * Obtains a list of the names contained in a text file, assuming there is one
	 * name per line.
	 * 
	 * @param file
	 *            the file to be read
	 * @return a {@link List} of the names contained in the file
	 * @throws IOException
	 *             if the file cannot be read
	 */
	public List<String> getNamesFromFile(File file) throws IOException {
		if (file == null) {
			throw new FileNotFoundException("A file must be provided.");
		}
		try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
			return stream.collect(Collectors.toList());
		} catch (IOException e) {
			throw e;
		}
	}
}