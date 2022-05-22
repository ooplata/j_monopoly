package j_monopoly.enums;

public enum PropertyPurchaseResult {
    /**
     * The property was not purchased, likely due to lack of money.
     */
    NOT_PURCHASED,

    /**
     * The property was purchased.
     */
    PURCHASED,

    /**
     * The property was purchased, and the player now owns the full group.
     */
    GROUP_PURCHASED,
}
