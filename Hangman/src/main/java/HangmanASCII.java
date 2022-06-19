import java.util.HashMap;

public class HangmanASCII
{
	public HashMap<Integer, String> main()
	{
		HashMap<Integer, String> hangman = new HashMap<>();
		hangman.put(0, "  +---+\n" + "  |   |\n" + "      |\n" + "      |\n" + "      |\n" + "      |\n" + "========="); // ASCII Formatted Hangman.
		hangman.put(1, "  +---+\n" + "  |   |\n" + "  O   |\n" + "      |\n" + "      |\n" + "      |\n" + "=========");
		hangman.put(2, "  +---+\n" + "  |   |\n" + "  O   |\n" + "  |   |\n" + "      |\n" + "      |\n" + "=========");
		hangman.put(3, "  +---+\n" + "  |   |\n" + "  O   |\n" + " /|   |\n" + "      |\n" + "      |\n" + "=========");
		hangman.put(4, "  +---+\n" + "  |   |\n" + "  O   |\n" + " /|\\  |\n" + "      |\n" + "      |\n" + "=========");
		hangman.put(5, "  +---+\n" + "  |   |\n" + "  O   |\n" + " /|\\  |\n" + " /    |\n" + "      |\n" + "=========");
		hangman.put(6, "  +---+\n" + "  |   |\n" + "  O   |\n" + " /|\\  |\n" + " / \\  |\n" + "      |\n" + "=========");
		return hangman;
	}
}
