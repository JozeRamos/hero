import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private TerminalScreen screen;
    private Arena arena;

    public Game(){
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e){
            e.printStackTrace();
        }
        arena = new Arena(40, 20);
    }
    private void processKey(KeyStroke key) {
        System.out.println(key);
         switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(arena.moveUp());
                break;
            case ArrowDown:
                moveHero(arena.moveDown());
                break;
            case ArrowRight:
                moveHero(arena.moveRight());
                break;
            case ArrowLeft:
                moveHero(arena.moveLeft());
                break;
        }
    }
    private void moveHero(Position position) {
        if (arena.canHeroMove(position))
            arena.setPosition(position);
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                screen.close();
                break;
            }
            if (key.getKeyType() == KeyType.EOF){
                break;
            }
        }

    }
}
