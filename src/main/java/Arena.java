import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Arena
{
    private int width,height;
    private Hero hero;
    public Arena(int wid,int hei)
    {
        width = wid;
        height = hei;
        hero = new Hero(10,10);
    }

    public void processKey(KeyStroke key)
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
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getX(), hero.getY()), "X");
    }
    private void moveHero(Position position)
    {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    private boolean canHeroMove(Position position)
    {
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
