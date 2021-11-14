import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element
{
    public Monster(int x, int y)
    {
        super(x,y);
    }
    public void draw(TextGraphics graphics)
    {
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
    public void move()
    {
        Random random = new Random();
        int x = random.nextInt(4);
        switch(x)
        {
            case 0: position.setX(position.getX()+1);break;
            case 1: position.setX(position.getX()-1);break;
            case 2: position.setY(position.getY()+1);break;
            case 3: position.setY(position.getY()-1);break;
        }
    }
}
