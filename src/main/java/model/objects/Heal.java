package model.objects;

import model.Hero;
import model.Position;

public class Heal extends  AbstractObject{

    public Heal(Position position) {
        super(position);
    }

    @Override
    public void action(Hero hero) {
        hero.receiveHeal();
    }

}
