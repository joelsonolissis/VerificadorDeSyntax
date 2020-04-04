import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Luis Antunes
 */
public class TestVerificador {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@org.junit.Test
	public void test1() throws IOException {

		String esperado = "[ 5 * ( 20 + 30 ) – 4 ] ==> ok" + "\n" + "{ 101 , 202 , ( 3 * 100 ) + 3 } ==> ok" + "\n"
				+ "[ 5 * ( 20 + 30 ) – 4 ] > { 101 , 202 , ( 3 * 100 } + 3 ) ==> esperava ) encontrei }" + "\n"
				+ "begin ( um dois três ) de oliveira end [ quatro ] ==> ok" + "\n"
				+ "begin ( um dois três ) de oliveira end [ quatro } ==> esperava ] encontrei }" + "\n"
				+ "begin ( um dois três ) de oliveira end ] quatro ) ==> encontrei ] extemporâneo" + "\n";

		String[] args = { "simbolos.txt", "expressoes.txt", "final.txt" };
		Verificador.main(args);

		String obtida = new String(Files.readAllBytes(Paths.get("final.txt")), StandardCharsets.UTF_8);

		assertEquals(esperado, obtida); // outContent.toString());
	}
}
