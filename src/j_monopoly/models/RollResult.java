package j_monopoly.models;

import j_monopoly.enums.SpaceType;

/**
 * Represents the information regarding the result of a die roll
 * and landing on a space.
 */
public final class RollResult {
    /**
     * The type of space the player landed on.
     */
    public final SpaceType space;

    /**
     * Result of the first die.
     */
    public final int firstDie;

    /**
     * Result of the second die.
     */
    public final int secondDie;

    /**
     * Full result of the roll.
     */
    public final int result;

    /**
     * Whether the player passed Go.
     */
    public final boolean passedGo;

    public RollResult(SpaceType space,
                      int firstDie,
                      int secondDie,
                      int result,
                      boolean passedGo) {
        this.space = space;
        this.firstDie = firstDie;
        this.secondDie = secondDie;
        this.result = result;
        this.passedGo = passedGo;
    }
}
