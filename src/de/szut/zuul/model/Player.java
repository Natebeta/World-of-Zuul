package de.szut.zuul.model;

import de.szut.zuul.exceptions.ItemNotFoundException;
import de.szut.zuul.exceptions.ItemTooHeavyException;
import de.szut.zuul.model.status.Gesund;
import de.szut.zuul.model.status.Verwundet;
import de.szut.zuul.model.status.Zustand;

import java.util.LinkedList;

public class Player {

    public Room currentRoom;
    public double loadCapacity = 10;
    LinkedList<Item> inventory = new LinkedList();
    private Zustand aktuellerZustand;

    public Player() {
        setAktuellerZustand(new Verwundet(this));
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void goTo(Room newRoom) {
        currentRoom = newRoom;
    }

    public void takeItem(Item item) throws ItemTooHeavyException {
        if (isTakePossible(item)) {
            inventory.add(item);
        } else {
            throw new ItemTooHeavyException("Too heavy to take");
        }
    }

    private boolean isTakePossible(Item item) {
        return calculateWeight() + item.getWeight() <= loadCapacity;
    }

    private double calculateWeight() {
        double inventoryWeight = 0;
        if (inventory.size() != 0) {
            for (int i = 0; i <= inventory.size()-1; i++) {
                inventoryWeight += inventory.get(i).getWeight();
            }
        }
        return inventoryWeight;
    }

    public Item dropItem(String name) throws ItemNotFoundException {
        Item dropItem = null;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                dropItem = inventory.get(i);
                inventory.remove(i);
            } else if (dropItem == null){
                throw new ItemNotFoundException("You don't own this item!");
            }
        }

        return dropItem;
    }

    public String showStatus() {
        StringBuilder items = new StringBuilder();
        for (Item item : inventory) {
            items.append(item.getName() + ", " + item.getWeight() + "\n");
        }

        return "> Status of the player \n"
                + "loadCapacity: " + loadCapacity + "\n"
                + "taken items: " + items + "\n"
                + "absorbed weight: " + calculateWeight() + "\n"
                + "Zustand: " + aktuellerZustand + "\n";
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setAktuellerZustand(Zustand aktuellerZustand) {
        this.aktuellerZustand = aktuellerZustand;
    }

    public Zustand getAktuellerZustand() {
        return aktuellerZustand;
    }
}
