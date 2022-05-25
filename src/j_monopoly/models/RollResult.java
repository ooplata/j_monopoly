package j_monopoly.models;

import j_monopoly.enums.SpaceType;

/**
 * Represents the information regarding the result of a die roll
 * and landing on a space.
 */
public final class RollResult<T> {
    /**
     * The space the player landed on.
     */
    public final Space<T> space;

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

    public RollResult(Space<T> space,
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
