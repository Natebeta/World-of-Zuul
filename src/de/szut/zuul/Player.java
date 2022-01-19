package de.szut.zuul;

import java.util.LinkedList;

public class Player {

    Room currentRoom;
    double loadCapacity = 10;
    LinkedList<Item> inventory = new LinkedList();

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void goTo(Room newRoom) {
        currentRoom = newRoom;
    }

    public boolean takeItem(Item item) {
        if (isTakePossible(item)) {
            inventory.add(item);
            return true;
        } else {
            return false;
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

    public Item dropItem(String name) {
        Item dropItem = null;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                dropItem = inventory.get(i);
                inventory.remove(i);
            } else {
                dropItem = null;
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
                + "absorbed weight: " + calculateWeight() + "\n";
    }

}
