package de.szut.zuul.model.status;

import de.szut.zuul.model.Player;

public class Verwundet implements Zustand{

    private static Verwundet instance;
    private Player player;

    private Verwundet() {
    }

    public Verwundet(Player player) {
        this.player = player;
    }

    public static Verwundet getInstance(Player player) {
        if (instance == null)
            instance = new Verwundet(player);
        return instance;
    }

    public void heilen() {
        player.setAktuellerZustand(Gesund.getInstance(player));
    }

    public void leichtVerletzen() {
        player.setAktuellerZustand(Bewegungsunfähig.getInstance(player));
    }

    public void schwerVerletzen() {
        player.setAktuellerZustand(Bewegungsunfähig.getInstance(player));
    }

    @Override
    public String toString() {
        return "Verwundet";
    }
}
