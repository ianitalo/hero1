import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game
{
    private Screen screen;
    Hero hero = new Hero(10,10);
    public Game() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
    }
    private void draw() throws IOException
    {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    public void run() throws IOException
    {
        while(true)
        {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            {
                screen.close();
            }
            if (key.getKeyType() == KeyType.EOF)
            {
                break;
            }
            processKey(key);
        }
    }
    private void processKey(KeyStroke key)
    {
        String a = key.getKeyType().toString();
            switch(a)
            {
                case "ArrowUp": moveHero(hero.moveUp()); break;
                case "ArrowLeft": moveHero(hero.moveLeft()); break;
                case "ArrowDown": moveHero(hero.moveDown()); break;
                case "ArrowRight": moveHero(hero.moveRight()); break;
            }
    }
    private void moveHero(Position position)
    {
        hero.setPosition(position);
    }


}
