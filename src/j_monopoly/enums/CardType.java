package j_monopoly.enums;

/**
 * Defines all the available card types.
 */
public enum CardType {
    /**
     * A property card. As soon as you land on a space with it, you get the
     * option to purchase the property if not taken. If taken, you have to
     * pay rent to the property's owner.
     */
    PROPERTY,

    /**
     * A wild card. If you land on a space with it, you take a wild card, at
     * which point you must perform the action specified in the card.
     */
    WILD
}
