package j_monopoly.game.board;

import j_monopoly.models.Player;

public final class GameHelper {
    private static int currentPlayerIndex;

    /**
     * Gets the player whose turn is active.
     */
    public static Player getCurrentPlayer() {
        return Players.getPlayerAt(currentPlayerIndex);
    }

    /**
     * Starts a new game with the specified number of players, discarding
     * all saved state.
     */
    public static void startNewGame(int playerCount) {
        if (playerCount < 2 || playerCount > 12)
            throw new IndexOutOfBoundsException("Player count must be between 2 and 12 (inclusive).");

        Players.players.clear();
        currentPlayerIndex = -1;
        for (int i = 0; i < playerCount; i++) {
            Players.players.add(new Player());
        }

        Properties.properties.clear();
        Properties.populateList();
    }

    /**
     * Starts the next turn, updates the current player info.
     */
    public static void startNewTurn() {
        currentPlayerIndex += 1;
        if (currentPlayerIndex >= Players.playerCount()) {
            currentPlayerIndex = 0;
        }
    }
}
