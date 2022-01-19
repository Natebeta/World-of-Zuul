package de.szut.zuul;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room marketsquare, templePyramid, tavern, sacrificialSite, hut, jungle, secretPassage, cave, beach, keller, wizard;
      
        // create the rooms
        marketsquare = new Room("on the market square");
        templePyramid = new Room("in a temple pyramid");
        tavern = new Room("in the tavern at the market square");
        sacrificialSite = new Room("at a sacrificial site");
        hut = new Room("in a hut");
        jungle = new Room("in the jungle");
        secretPassage = new Room("in a secret passage");
        cave = new Room("in a cave");
        beach = new Room("on the beach");
        keller = new Room("in the keller");
        wizard = new Room("in wizard's room");


        // initialise room exits
        marketsquare.setExit("north", tavern);
        marketsquare.setExit("east", templePyramid);
        marketsquare.setExit("west", sacrificialSite);

        templePyramid.setExit("north", hut);
        templePyramid.setExit("west", marketsquare);
        templePyramid.setExit("up", wizard);
        templePyramid.setExit("down", keller);

        wizard.setExit("down", templePyramid);

        keller.setExit("west", secretPassage);
        keller.setExit("up", templePyramid);

        tavern.setExit("east", hut);
        tavern.setExit("south", marketsquare);

        sacrificialSite.setExit("east", marketsquare);
        sacrificialSite.setExit("down", cave);

        hut.setExit("east", jungle);
        hut.setExit("south", templePyramid);
        hut.setExit("west", tavern);

        jungle.setExit("west", hut);

        secretPassage.setExit("east", keller);
        secretPassage.setExit("west", cave);


        cave.setExit("east", secretPassage);
        cave.setExit("sout", beach);
        cave.setExit("west", sacrificialSite);

        beach.setExit("north", cave);

        currentRoom = marketsquare;  // start game on market square
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        printRoomInformation();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the jungle. At once there is a glade. On it there a buildings...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.getExit("north");
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.getExit("east");
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.getExit("south");
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.getExit("west");
        }
        if(direction.equals("up")) {
            nextRoom = currentRoom.getExit("up");
        }
        if(direction.equals("down")) {
            nextRoom = currentRoom.getExit("down");
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printRoomInformation();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printRoomInformation() {
        System.out.println(currentRoom.getLongDescription());
    }

    private void look() {
        System.out.println(currentRoom.getLongDescription());
    }
}
