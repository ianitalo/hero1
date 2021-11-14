import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Element
{
    protected Position position;
    public Element(int x, int y)
    {
        position = new Position(x,y);
    }
    abstract void draw(TextGraphics graphics);
    public Position getPosition()
    {
        return position;
    }
    public void setPosition(Position pos)
    {
        position.setX(pos.getX());
        position.setY(pos.getY());
    }
}
