package j_monopoly.enums;

/**
 * Types of space the player can land on.
 */
public enum SpaceType {
    /**
     * The space contains a property, player can buy the property,
     * or pay rent if necessary.
     */
    PROPERTY_SPACE,

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
     * The space indicates the player must immediately go to jail.
     */
    GO_TO_JAIL,

    /**
     * The space essentially does nothing, a player that lands on it
     * is not affected in any way.
     */
    PASS,
}
