import com.genspark.Main;
import org.junit.jupiter.api.*;


import java.io.ByteArrayInputStream;

public class TestMain
{
	
	Main main;
	@BeforeEach
	public void setUp()
	{
		//noinspection MoveFieldAssignmentToInitializer,InstantiationOfUtilityClass
		main = new Main();
	}
	
	@AfterEach
	public void cleanUp()
	{
	
	}
	
	@Test
	@DisplayName("Get Input from InputStream works.")
	public void TestGetInputWorks()
	{
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("1".getBytes());
		Assertions.assertEquals(1, Main.getInput("int", "Error: You have not provided valid input", byteArrayInputStream));
		byteArrayInputStream = new ByteArrayInputStream("2".getBytes());
		Assertions.assertEquals(2, Main.getInput("int", "Error should not occur", byteArrayInputStream));
		
	}
	
	@Test
	@DisplayName("Handles error if InputStream is not expected data type.")
	public void TestInvalidInputDoesNotWork(){
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("lol".getBytes());
		Assertions.assertEquals(1, Main.getInput("int", "Error handled successfully", byteArrayInputStream));
	}
}
