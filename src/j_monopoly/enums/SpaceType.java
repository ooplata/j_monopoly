package j_monopoly.enums;

/**
 * Types of space the player can land on.
 */
public enum SpaceType {
    /**
     * The Go space. When a player passes it or lands on it, they
     * get 200 bucks.
     */
    GO,

    /**
     * The space contains a property, player can buy the property,
     * or pay rent if necessary.
     */
    PROPERTY,

    /**
     * The space indicates the player must take a chance card and
     * do as it says.
     */
    CHANCE,

    /**
     * The space indicates the player must take a community chest
     * card and do as it says.
     */
    COMMUNITY_CHEST,

    /**
     * The space essentially does nothing, a player that lands on it
     * is not affected in any way.
     */
    FREE_PASS,

    /**
     * This space is where jailed players go. Players that land
     * on it won't be affected.
     */
    JAIL,

    /**
     * The space indicates the player must immediately go to jail.
     */
    GO_TO_JAIL,
}
