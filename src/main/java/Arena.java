import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena
{
    private int width,height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    public Arena(int wid,int hei)
    {
        width = wid;
        height = hei;
        hero = new Hero(10,10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
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
        retrieveCoins();
        verifyMonsterCollisions();
        moveMonsters();
        verifyMonsterCollisions();
    }
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        hero.draw(graphics);
        for (Coin coin: coins) coin.draw(graphics);
        for (Wall wall : walls) wall.draw(graphics);
        for (Monster monster: monsters) monster.draw(graphics);
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
    private List<Coin> createCoins()
    {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            if(hero.getPosition().equals(new Position (x,y)))
            {
                i--;
                continue;
            }
            for (Coin c: coins)
            {
                if(c.getPosition().equals(new Position(x,y)))
                {
                    i--;
                    continue;
                }
            }
            coins.add(new Coin(x, y));
        }
        return coins;
    }
    private void retrieveCoins()
    {
        for(int i = 0;i<coins.size();i++)
        {
            if (coins.get(i).getPosition().equals(hero.getPosition()))
            {
                coins.remove(i);
                break;
            }
        }
    }
    private List<Monster> createMonsters()
    {
        Random random= new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            if(hero.getPosition().equals(new Position (x,y)))
            {
                i--;
                continue;
            }
            for (Monster m: monsters)
            {
                if(m.getPosition().equals(new Position(x,y)))
                {
                    i--;
                    continue;
                }
            }
            monsters.add(new Monster(x, y));
        }
        return monsters;
    }
    private void moveMonsters()
    {
        for(Monster m: monsters)
        {
            m.move();
        }
    }
    private void verifyMonsterCollisions()
    {
        for(Monster m: monsters)
        {
            if (m.position.equals(hero.position))
            {
                System.out.println("You died!");
                System.exit(0);
            }
        }
    }
}
