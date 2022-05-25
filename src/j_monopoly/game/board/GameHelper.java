package j_monopoly.game.board;

import j_monopoly.enums.SpaceType;
import j_monopoly.models.Player;
import j_monopoly.models.RollResult;

public final class GameHelper {
    private static int currentPlayerIndex;

    /**
     * 0 based index of the current player.
     */
    public static int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

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

        Spaces.spaces.clear();
        Spaces.populateList();
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

    /**
     * Rolls two dice, advances the current player, and
     * returns the result.
     */
    public static RollResult rollTwoDice() {
        Player curr = getCurrentPlayer();

        int first = curr.rollSingleDie();
        int second = curr.rollSingleDie();
        int full = first + second;

        boolean passed = curr.moveForward(full);
        SpaceType landedOn = Spaces.spaces.get(curr.space).type;

        return new RollResult(landedOn, first, second, full, passed);
    }

    /**
     * Makes the current player go bankrupt.
     *
     * @return The amount of money the player's goods were worth.
     */
    public static int bankrupt() {
        Player curr = getCurrentPlayer();
        Players.players.removeFirstOccurrence(curr);
        currentPlayerIndex -= 1;
        return curr.goBankrupt();
    }
}
