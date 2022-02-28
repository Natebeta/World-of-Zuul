package de.szut.zuul.model.status;

import de.szut.zuul.model.Player;

public class Gesund implements Zustand {

    private static Gesund instance;
    private Player player;

    private Gesund() {
    }

    public Gesund(Player player){
        this.player = player;
    }

    public static Gesund getInstance(Player player) {
        if (instance == null)
            instance = new Gesund(player);
        return instance;
    }

    public void heilen() {
        player.setAktuellerZustand(Gesund.getInstance(player));
    }

    public void leichtVerletzen() {
        player.setAktuellerZustand(Verwundet.getInstance(player));
    }

    public void schwerVerletzen() {
        player.setAktuellerZustand(Bewegungsunfähig.getInstance(player));
    }

    @Override
    public String toString() {
        return "Gesund";
    }
}
