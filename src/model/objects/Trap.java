package model.objects;

import model.Hero;
import model.Position;

public class Trap extends AbstractObject{

    public Trap(Position position) {
        super(position);
    }

    @Override
    public void action(Hero hero) {
        hero.setOver(true);
    }
}
