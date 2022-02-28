package de.szut.zuul.model.status;

import de.szut.zuul.model.Player;

public class Bewegungsunfähig implements Zustand{

    private static Bewegungsunfähig instance;
    private Player player;

    private Bewegungsunfähig() {
    }

    public Bewegungsunfähig(Player player) {
        this.player = player;
    }

    public static Bewegungsunfähig getInstance(Player player) {
        if (instance == null)
            instance = new Bewegungsunfähig(player);
        return instance;
    }

    public void heilen() {
        player.setAktuellerZustand(Verwundet.getInstance(player));
    }

    public void leichtVerletzen() {
        player.setAktuellerZustand(Bewegungsunfähig.getInstance(player));
    }

    public void schwerVerletzen() {
        player.setAktuellerZustand(Bewegungsunfähig.getInstance(player));
    }

    @Override
    public String toString() {
        return "Bewegungsunfähig";
    }
}
