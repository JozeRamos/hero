import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.TerminalScreen;

public class Arena {
    private final Hero hero;
    private int width;
    private int height;
    private Position position;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveRight(){
        return new Position(position.getX() + 1, position.getY());
    }

    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }

    public void draw(TerminalScreen screen) {
        screen.setCharacter(position.getX(), position.getY(),
                TextCharacter.fromCharacter('X')[0]);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private class Hero {
        private Hero(int x, int y){
            position = new Position(x,y);
        }
    }
}
