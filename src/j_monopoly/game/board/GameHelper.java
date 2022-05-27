package j_monopoly.game.board;

import j_monopoly.models.Player;
import j_monopoly.models.Property;
import j_monopoly.models.RollResult;
import j_monopoly.models.Space;

public final class GameHelper {
    private static int currentPlayerIndex;
    private static boolean canStartTurn = true;
    private static boolean finished = false;

    /**
     * Whether the game should end.
     */
    public static boolean isGameFinished() {
        return finished;
    }

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

        canStartTurn = true;
        finished = false;

        Players.players.clear();
        currentPlayerIndex = -1;
        for (int i = 0; i < playerCount; i++) {
            Players.players.add(new Player("Player " + (i + 1)));
        }

        Spaces.spaces.clear();
        Spaces.populateList();

        WildcardHelper.communityCards.clear();
        WildcardHelper.chanceCards.clear();

        WildcardHelper.populateLists();
    }

    /**
     * Starts the next turn if possible, updates the current player info.
     */
    public static void startNewTurn() {
        if (!canStartTurn) return;
        canStartTurn = false;

        currentPlayerIndex += 1;
        if (currentPlayerIndex >= Players.playerCount()) {
            currentPlayerIndex = 0;
        }
    }

    /**
     * Rolls two dice, advances the current player, and
     * returns the result.
     */
    public static RollResult<Object> rollTwoDice() {
        Player curr = getCurrentPlayer();

        int first = curr.rollSingleDie();
        int second = curr.rollSingleDie();
        int full = first + second;

        boolean passed = curr.moveForward(full);
        Space<Object> landedOn = Spaces.spaces.get(curr.space);

        return new RollResult<>(landedOn, first, second, full, passed);
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

    /**
     * Finishes the current turn, allows a new one to be started.
     *
     * @return Whether there's players besides the current one left.
     */
    public static boolean finishTurn() {
        finished = Players.playerCount() < 2;
        canStartTurn = !finished;
        return finished;
    }

    /**
     * Performs the action associated with the specified
     * chance card action ID.
     *
     * @return Whether the player went bankrupt after performing
     * the action.
     */
    public static boolean useChanceCard(int actionId) {
        Player curr = getCurrentPlayer();
        switch (actionId) {
            case 0 -> goToSpace(31);
            case 1 -> advanceToGo();
            case 2 -> {
                if (goToSpace(20)) curr.money += 200;
            }
            case 3 -> {
                if (goToSpace(9)) curr.money += 200;
            }
            case 4 -> curr.money += 50;
            case 5 -> curr.addOutOfJailCards(1);
            case 6 -> curr.moveBack(3);
            case 7 -> curr.goToJail();
            case 8 -> curr.money -= getRepairPrices(25, 100);
            case 9 -> curr.money -= 15;
            case 10 -> giveToPlayers(50);
            case 11 -> curr.money += 150;
        }

        if (curr.money < 0) bankrupt();
        return curr.money < 0;
    }

    /**
     * Performs the action associated with the specified
     * community chest card action ID.
     *
     * @return Whether the player went bankrupt after performing
     * the action.
     */
    public static boolean useCommunityCard(int actionId) {
        Player curr = getCurrentPlayer();
        switch (actionId) {
            case 0 -> advanceToGo();
            case 1 -> curr.money += 200;
            case 2, 11 -> curr.money -= 50;
            case 3 -> curr.money += 50;
            case 4 -> curr.addOutOfJailCards(1);
            case 5 -> curr.goToJail();
            case 6, 9, 15 -> curr.money += 100;
            case 7 -> curr.money += 20;
            case 8 -> curr.money += collectFromPlayers(10);
            case 10 -> curr.money -= 100;
            case 12 -> curr.money += 25;
            case 13 -> curr.money -= getRepairPrices(40, 115);
            case 14 -> curr.money += 10;
        }

        if (curr.money < 0) bankrupt();
        return curr.money < 0;
    }

    private static void advanceToGo() {
        Player player = getCurrentPlayer();
        player.space = 0;
        player.money += 200;
    }

    private static boolean goToSpace(int index) {
        Player player = getCurrentPlayer();
        int toMove = index - player.space;
        if (toMove < 0) toMove += 31;

        return player.moveForward(toMove);
    }

    private static int collectFromPlayers(int amount) {
        Player curr = getCurrentPlayer();
        int earnings = 0;

        for (Player player : Players.players) {
            if (player != curr) {
                if (player.money - amount >= 0) {
                    player.money -= amount;
                    earnings += amount;
                } else {
                    Players.players.removeFirstOccurrence(curr);
                    currentPlayerIndex -= 1;

                    earnings += curr.money;
                    curr.goBankrupt();
                }
            }
        }
        return earnings;
    }

    private static void giveToPlayers(int amount) {
        Player curr = getCurrentPlayer();

        for (Player player : Players.players) {
            if (player != curr) {
                player.money += amount;
                curr.money -= amount;
            }
        }
    }

    private static int getRepairPrices(int perHouse, int perHotel) {
        int amount = 0;
        for (Property property : getCurrentPlayer().properties) {
            int houses = property.getHouses();
            if (houses > 0) {
                if (houses == 5) amount += perHotel;
                else amount += (perHouse * houses);
            }
        }

        return amount;
    }
}
