import org.junit.jupiter.api.*;
import com.genspark.Main;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;

public class TestInputs
{
	
	Main main;
	@BeforeEach
	public void setUp()
	{
		//noinspection MoveFieldAssignmentToInitializer,InstantiationOfUtilityClass
		main = new Main();
	}
	
	@AfterEach
	public void cleanUp() { }
	
	@Test
	@DisplayName("Get Input from InputStream works.")
	public void TestGetInputWorks()
	{
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("5".getBytes());
		PrintStream printStream = new PrintStream(System.out);
		Assertions.assertEquals(false, Main.guessInput(5, "Error should not occur", byteArrayInputStream, printStream ));
		byteArrayInputStream = new ByteArrayInputStream("10".getBytes());
		Assertions.assertEquals(false, Main.guessInput(10, "Error should not occur", byteArrayInputStream, printStream ));
		
	}
}
