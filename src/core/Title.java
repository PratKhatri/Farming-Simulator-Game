package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Title extends BasicGameState {

    private Image titleImage;
    private StateBasedGame sbg;


    public Title(int state, StateBasedGame sbg) {
        super();
        this.sbg = sbg;
    }

    @Override
    public int getID() {
        return Main.TITLE_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        titleImage = new Image("res/title.png");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        titleImage.draw(0, 0, gc.getWidth(), gc.getHeight());
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

    @Override
    public void keyPressed(int key, char c) {
        if (Character.toUpperCase(c) == 'P') {
            sbg.enterState(Main.GAME_ID);
        }
    }

}

