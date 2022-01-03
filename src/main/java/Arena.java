import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private List<Wall> walls;
    private final Hero hero;
    private int width;
    private int height;
    private Position position;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
        this.walls = createWalls();
    }

    private List<Wall> createWalls() {
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

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        hero.draw(screen);
        for (Wall wall : walls)
            wall.draw(screen);
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

    public boolean canHeroMove(Position pos){
        return (pos.getX() >= 0 && pos.getX() < width) &&
                (pos.getY() >= 0 && pos.getY() < height) &&
                !walls.contains(new Wall(pos.getX(), pos.getY()));
    }


    private class Hero {
        private Hero(int x, int y){
            position = new Position(x,y);
        }
        public void draw(TextGraphics screen){
            screen.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
            screen.enableModifiers(SGR.BOLD);
            screen.putString(new TerminalPosition(position.getX(), position.getY()), "X");
        }
    }
}
