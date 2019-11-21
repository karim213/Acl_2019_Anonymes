package model.objects;

import model.Hero;
import model.Position;

public class Teleporter extends AbstractObject {
    public Position posTo;

    public Teleporter(Position position,Position posTo) {
        super(position);
        this.posTo = posTo;
    }

    @Override
    public void action(Hero hero) {
        hero.teleport(posTo);
    }
}
