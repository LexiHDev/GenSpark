import com.genspark.Main;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;

class MainTest {
	
	Main main;
	@BeforeEach
	void setUp() {
		//noinspection MoveFieldAssignmentToInitializer,InstantiationOfUtilityClass
		main = new Main();
	}
	
	@AfterEach
	void cleanUp() {
	
	}

	@Test
	void getInputWorks()
	{
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("1".getBytes());
		Assertions.assertEquals(1, Main.getInput("int", "Error: You have not provided valid input", byteArrayInputStream));
	}
}
