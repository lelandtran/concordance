package concordance;

import org.testng.annotations.Test;

public class ConcordanceTest {

	/*
	 * Test cases to cover
	 * 
	 * Single sentence without period
	 * Single sentence with period
	 * Two sentences, with two of the same words, with different capitalization
	 * Three sentences including an "i.e."
	 */
	
//	@Test
	public void motherOfAllTest(){
		String testString = "The beast i.e. quick brown fox jumps over the brown dog.\n"+
							"He didn't have any regard for the dog at all.\n"+
							"Could you imagine that?";
		Concordance.concordance(testString);
	}
}
