import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game
{
    private Screen screen;
    public void Game()
    {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        try {
            screen = new TerminalScreen(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        screen.setCursorPosition(null); // we don't need a cursor
        try {
            screen.startScreen(); // screens must be started
        } catch (IOException e) {
            e.printStackTrace();
        }
        screen.doResizeIfNecessary(); // resize screen if necessary
    }
    private void draw()
    {
        screen.clear();
        screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')[0]);
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run()
    {
        draw();
    }

}
