package j_monopoly.game.board;

import j_monopoly.models.Player;

import java.util.LinkedList;

public final class Players {
    public static LinkedList<Player> players = new LinkedList<>();
    public static int playerCount() {
        return players.size();
    }

    public static Player getPlayerAt(int index) {
        return players.get(index);
    }

    /**
     * Gets the owner of a property with the given title.
     * @return The owner if found, null otherwise.
     */
    public static Player getPropertyOwnerByTitle(String title) {
        for (Player player : players)
            if (player.getPropertyByTitle(title) != null)
                return player;
        return null;
    }
}
