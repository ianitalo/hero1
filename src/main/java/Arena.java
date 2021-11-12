import com.googlecode.lanterna.TextCharacter;
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
    public void draw(Screen screen)
    {
        screen.setCharacter(hero.getX(), hero.getY(), TextCharacter.fromCharacter('X')[0]);
    }
    private void moveHero(Position position)
    {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    private boolean canHeroMove(Position position)
    {
        return true; // preciso fazer isso ainda
    }
}
