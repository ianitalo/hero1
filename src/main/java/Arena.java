import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;

public class Arena
{
    private int width,height;
    private Hero hero;
    private List<Wall> walls;
    public Arena(int wid,int hei)
    {
        width = wid;
        height = hei;
        hero = new Hero(10,10);
        this.walls = createWalls();
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
        graphics.putString(new TerminalPosition(hero.getX(), hero.getY()), "X"); //this is the hero.draw() method dk if I should put there
        for (Wall wall : walls) wall.draw(graphics);
    }
    private void moveHero(Position position)
    {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    private boolean canHeroMove(Position position)
    {
        if (position.getX() <= 0 || position.getX() >= width -1 || position.getY() <= 0 || position.getY() >= height -1)
        {
            return false;
        }
        for (Wall wall : walls)
        {
            if (wall.getPosition().equals(position))
            {
                return false;
            }
        }
        return true;
    }
    private List<Wall> createWalls()
    {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
}
