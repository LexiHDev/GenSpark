import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordsTest
{
	Words words;
	@BeforeEach
	void setUp() {
		words = new Words();
	}
	
	@AfterEach
	void tearDown()
	{
	}
	
	@Test
	@Description("Can getWords, and words is correct length.")
	void getWords()
	{
		assertEquals(999, words.getWords().size()); // Verify there are 999 words available to choose from
	}
	
	@Test
	void getRandomWord()
	{
		assertTrue(words.getWords().contains(words.getRandomWord()));
	}
}