import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.HashMap;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		Words words = new Words();
		Terminal terminal;
		Screen screen = null;
		int errors = 0;
		HashMap<Character, Boolean> solved = new HashMap<>();
		
		try
		{
			Label hangmanBox = new Label("");
			
			
			
			
			hangmanBox.setBackgroundColor(ANSI.BLACK); // Set colors
			hangmanBox.setForegroundColor(ANSI.WHITE); // for hangman box
			hangmanBox
				.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER))
				.setLabelWidth(20);
			
			Label wordLabel = new Label("")// Create word Label
				.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT)
				.setBackgroundColor(TextColor.ANSI.BLACK)
				.setLabelWidth(null)
				.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, true, true, 3, 3));
			
			String curWord = words.getRandomWord();
			for (char ch : curWord.toCharArray())
			{
				solved.put(ch, Boolean.FALSE);
			}
			
			Panel rPanel = new Panel(new GridLayout(1));
			rPanel.addComponent(new Label("Guess the word!").addStyle(SGR.BOLD).setForegroundColor(TextColor.ANSI.RED)
				.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, true, true, 3, 2)));
			rPanel.addComponent(hangmanBox);
			rPanel.addComponent(new EmptySpace());
			rPanel.addComponent(new EmptySpace());
			rPanel.addComponent(new EmptySpace());
			rPanel.addComponent(new EmptySpace());
			rPanel.addComponent(wordLabel);
			
			
			
			
			
			terminal = new DefaultTerminalFactory().createTerminal();
			screen = new TerminalScreen(terminal);
			terminal.enterPrivateMode();
			terminal.setForegroundColor(TextColor.ANSI.RED);
			terminal.setBackgroundColor(TextColor.ANSI.BLUE);
			
			WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
			final BasicWindow window = new BasicWindow("Hangman!");
			window.setComponent(rPanel);
			
			gui.addWindowAndWait(window) ;
			gui.updateScreen();
			while(!gui.getWindows().isEmpty()){
				gui.processInput();
				if(gui.isPendingUpdate()){
					gui.updateScreen();
				}
				else{
					Thread.sleep(30);
				}
			}
			
			screen.startScreen(); // Initialize Screen.
			
			screen.clear();
			screen.refresh(); // Cleanup Terminal.
			
		}
		catch (IOException | InterruptedException e)
		{
			e.printStackTrace();
		} finally
		{
			assert screen != null;
			screen.stopScreen();
		}
		
	}
}
