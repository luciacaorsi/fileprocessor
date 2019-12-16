package en.arvato.fileprocessor.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import en.arvato.fileprocessor.serrvice.ListComparer;

public class ListComparerTest {
	
	private  ListComparer listComparer;
	
	@Before
	public void setUp() {
		listComparer =  ListComparer.getInstance();
	}
	
	@Test
	public void testGetDifference() {

		List<String> a = Arrays.asList("word1", "word2", "word5", "word6");
		List<String> b = Arrays.asList("word5", "word6", "word3", "word4");

		List<String> differenceAB = Arrays.asList("word1", "word2");
		List<String> differenceBA = Arrays.asList("word3", "word4");

		assertEquals(differenceAB, listComparer.getDifference(a, b));
		assertEquals(differenceBA, listComparer.getDifference(b, a));
	}

	@Test
	public void testGetIntersection() {

		List<String> a = Arrays.asList("word1", "word2", "word5", "word6");
		List<String> b = Arrays.asList("word5", "word6", "word3", "word4");
		
		List<String> intersection = Arrays.asList("word5", "word6");
		
		assertEquals(intersection, listComparer.getIntersection(a, b));
	}
}