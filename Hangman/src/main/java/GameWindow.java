import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GameWindow extends BasicWindow
{
	Screen gameScreen;
	Terminal gameTerminal;
	WindowBasedTextGUI gameGUI;
	int errors = 0;
	Words gameWords;
	Label wordLabel;
	String curWord;
	HashMap<Character, Boolean> solved = new HashMap<>();
	List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	HashSet<Character> possibleChars = new HashSet<>(chars);
	
	
	public GameWindow(Screen screen, Terminal terminal, WindowBasedTextGUI gui, Words word) throws IOException, InterruptedException
	{
		super("Hangman!");
		gameWords = word;
		gameScreen = screen;
		gameTerminal = terminal;
		gameGUI = gui;
		Panel mainPanel = new Panel(new GridLayout(3));
		mainPanel.setPreferredSize(new TerminalSize(20, 10));
		Label hangmanBox = new Label("" +
			"           \n" +
			"    +---+  \n" +
			"    |   |  \n" +
			"        |  \n" +
			"        |  \n" +
			"        |  \n" +
			"        |  \n" +
			"  =======  \n" +
			"           ");
		
		hangmanBox.setBackgroundColor(ANSI.BLACK); // Set colors
		hangmanBox.setForegroundColor(ANSI.WHITE); // for hangman box
		hangmanBox
			.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER))
			.setLabelWidth(20);
		
		wordLabel = new Label("")// Create word Label
			.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT)
			.setBackgroundColor(TextColor.ANSI.BLACK)
			.setLabelWidth(null)
			.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, true, true, 3, 3));
		
		curWord = gameWords.getRandomWord();
		updateShown();
		for (char ch : curWord.toCharArray())
		{
			solved.put(ch, Boolean.FALSE);
		}
		
		Panel rPanel = new Panel(new GridLayout(1));
		mainPanel.addComponent(new EmptySpace());
		rPanel.addComponent(new Label("Guess the word!").addStyle(SGR.BOLD).setForegroundColor(TextColor.ANSI.RED)
			.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, true, true, 3, 2)));
		rPanel.addComponent(hangmanBox);
		rPanel.addComponent(new EmptySpace());
		rPanel.addComponent(new EmptySpace());
		rPanel.addComponent(new EmptySpace());
		rPanel.addComponent(new EmptySpace());
		rPanel.addComponent(wordLabel);
		
		mainPanel.addComponent(rPanel);
		
		
		setComponent(mainPanel);
		
		updateShown();
		run();
	}
	public void updateShown()
	{
		for (char ch : curWord.toCharArray())
		{
			wordLabel.setText(wordLabel.getText() + (solved.containsKey(ch) ? ch : '_'));
			try
			{
				gameScreen.refresh();
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void run() throws IOException, InterruptedException
	{
		while(!gameGUI.getWindows().isEmpty()) {
			gameGUI.processInput();
			if(gameGUI.isPendingUpdate()) {
				gameGUI.updateScreen();
			}
			else {
				Thread.sleep(1);
			}
		}
		System.out.println("Running!");
		while (solved.containsValue(false))
		{
			try
			{
				gameScreen.refresh();
				gameGUI.updateScreen();
				KeyStroke keyStroke = gameTerminal.readInput();
				if (keyStroke.getKeyType() == KeyType.Character)
				{
					Character kChar = keyStroke.getCharacter();
					if (solved.containsKey(kChar))
					{
						solved.put(kChar, Boolean.TRUE);
						updateShown();
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			gameTerminal.exitPrivateMode();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
//
	}
}
