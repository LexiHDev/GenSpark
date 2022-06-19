import com.google.common.base.Throwables;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main
{
	
	public static void setWords(String word, HashMap<Character, Boolean> solved)
	{
		for (Character chara : word.toCharArray())
		{
			solved.put(chara, false);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		boolean running = true;
		AtomicBoolean operating = new AtomicBoolean(false);
		AtomicInteger errors = new AtomicInteger();
		Words words = new Words();
		Terminal terminal;
		WindowBasedTextGUI gui = null;
		final String[] curWord = new String[1];
		var ref = new Object()
		{
			Screen screen = null;
			HashMap<Character, Boolean> solved = new HashMap<>();
		};
		
		try
		{
			terminal = new DefaultTerminalFactory(System.out, System.in, StandardCharsets.UTF_8).setTerminalEmulatorTitle("Hangman!").createTerminal();
			
			ref.screen = new TerminalScreen(terminal);
			gui = new MultiWindowTextGUI(ref.screen);
			ref.screen.startScreen();
			
			Window window = new BasicWindow("Hangman!");
			Panel errPanel = new Panel(new GridLayout(2));
			
			
			Window endWindow = new BasicWindow("Game over!");
			Label errorLabel = new Label("Try again?");
			errPanel.addComponent(errorLabel);
			endWindow.setComponent(errPanel);
			errPanel.addComponent(new EmptySpace());
			
			Button closeButton = new Button("Close.", () -> {
				try
				{
//					running.set(false);
					ref.screen.stopScreen();
					System.out.println("Close functionality here");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			});
			
			
			Panel mainPanel = new Panel(new LinearLayout(Direction.VERTICAL));
			Label hangmanUI = new Label(new HangmanASCII().main().get(0));
			
			curWord[0] = words.getRandomWord();
			Label textLabel = new Label(curWord[0].replaceAll("[a-zA-Z]*", "_"));
			
			
			mainPanel.addComponent(hangmanUI);
			mainPanel.addComponent(textLabel);
			window.setComponent(mainPanel);
			setWords(curWord[0], ref.solved);
			
			gui.addWindow(window);
			
			StringBuilder guessedLabel = new StringBuilder();
			for (Character chr : curWord[0].toCharArray())
			{
				guessedLabel.append(ref.solved.get(chr) ? chr : "_");
			}
			textLabel.setText(guessedLabel.toString());
			gui.getGUIThread().processEventsAndUpdate();
			KeyStroke keyStroke = terminal.readInput();
			
			WindowBasedTextGUI finalGui = gui;
			Button okButton = new Button("OK!", () -> {
				ref.solved = new HashMap<>();
				curWord[0] = words.getRandomWord();
				
				setWords(curWord[0], ref.solved);
				for (Character chr : curWord[0].toCharArray())
				{
					guessedLabel.append(ref.solved.get(chr) ? chr : "_");
				}
				textLabel.setText(guessedLabel.toString());
				try
				{
					
					finalGui.removeWindow(endWindow);
					operating.set(false);
					finalGui.getGUIThread().processEventsAndUpdate();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				errors.set(0);
			});
			errPanel.addComponent(okButton);
			errPanel.addComponent(closeButton);
			
			
			while (true)
			{
				System.out.printf("%s, %s", ref.solved, errors.get());
				if (errors.get() >= 6 && !operating.get())
				{
					operating.set(true);
					gui.addWindow(endWindow);
					gui.setActiveWindow(endWindow);
				}
				else if (errors.get() >= 0 || errors.get() < 6)
				{
					hangmanUI.setText(new HangmanASCII().main().get(errors.get()));
				}
				if (keyStroke.getKeyType() == KeyType.Character)
				{
					keyStroke = terminal.readInput();
					Character kChar = keyStroke.getCharacter();
					if (ref.solved.containsKey(kChar))
					{
						ref.solved.put(kChar, Boolean.TRUE);
						
					}
					else
					{
						errors.getAndIncrement();
					}
				}
				guessedLabel.setLength(0);
				for (Character chr : curWord[0].toCharArray())
				{
					guessedLabel.append(ref.solved.get(chr) ? chr : "_");
				}
				textLabel.setText(guessedLabel.toString());
				gui.getGUIThread().processEventsAndUpdate();
			}
		}
		
		catch (Exception e)
		{
//				assert screen != null;
//				screen.stopScreen();
			assert gui != null;
			Window errWin = new BasicWindow("Error!");
			gui.addWindow(errWin);
			errWin.setComponent(new Label(e.getClass().getName() + "\n" + Throwables.getStackTraceAsString(e)));
			try { // add extra handling as if there is an EOF exception it will be ignored since this is in the catch block.
				gui.getGUIThread().processEventsAndUpdate();
			} catch (Exception exc) {
				ref.screen.stopScreen();
			}
		}
	}
	
}
