package model.objects;

import model.Hero;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class Objects {

    private List<AbstractObject> objectList;
    private Chest chest;

    public Objects() {
        this.objectList = new ArrayList<>();
    }

    public void addObject(AbstractObject object) {
        if (!(object instanceof Chest)) {
            this.objectList.add(object);
        }
    }

    public Chest getChest() {
        return chest;
    }

    public List<AbstractObject> getObjectList() {
        return objectList;
    }

    public void addChest(Position position) {
        this.chest = new Chest(position);
    }

    public boolean isOnChest(Position position) {
        return chest.isOn(position);
    }

    public void performObjectActions(Hero hero) {
        for (AbstractObject object : objectList) {
            if (object.isOn(new Position(hero.getX(),hero.getY()))) {
                object.action(hero);
            }
        }
    }


    public void addTrap(Position position) {
        objectList.add(new Trap(position));
    }

    public boolean isOnHeal(Position position) {
        return false;
    }

    public Position getPosChest() {
        return chest.getPosition();
    }

    public List<Position> getPosTraps() {
        List<Position> res = new ArrayList<>();
        for (AbstractObject object:objectList) {
            if(object instanceof Trap){
                res.add(object.getPosition());
            }
        }
        return res;
    }
}
