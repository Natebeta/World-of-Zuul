package de.szut.zuul;

import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;

    private HashMap<String, Room> roomHashMap;

    /*
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room upExit;
    private Room downExit;
     */

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        roomHashMap = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction direction of exit.
     * @param neighbour room next to current room.
     */
    public void setExit(String direction, Room neighbour) {
        roomHashMap.put(direction, neighbour);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction) {

        return roomHashMap.get(direction);

        /* Braucht man nicht mehr?
        switch (direction) {
            case "north":
                return northExit;
            case "east":
                return eastExit;
            case "south":
                return southExit;
            case "west":
                return westExit;
            case "up":
                return upExit;
            case "down":
                return downExit;
            default:
                return null;
        }
         */
    }

    public String exitsToString() {
        StringBuilder exits = new StringBuilder("");
        if(getExit("north") != null) {
            exits.append("north ");
        }
        if(getExit("east") != null) {
            exits.append("east ");
        }
        if(getExit("south") != null) {
            exits.append("south ");
        }
        if(getExit("west") != null) {
            exits.append("west ");
        }
        if(getExit("up") != null) {
            exits.append("up ");
        }
        if(getExit("down") != null) {
            exits.append("down ");
        }
        return exits.toString();
    }

}
