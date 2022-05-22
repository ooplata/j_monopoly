package j_monopoly.models;

import j_monopoly.models.cards.PurchasableActionCard;

import java.util.LinkedList;
import java.util.Objects;

public class Player {
    private final LinkedList<Property> properties = new LinkedList<>();
    private final LinkedList<String> groups = new LinkedList<>();
    private final LinkedList<PurchasableActionCard> actionCards = new LinkedList<>();

    /**
     * Amount of money the player has.
     */
    public int money = 0;

    /**
     * Amount of get out of jail free cards the player has.
     */
    public int outOfJailCards = 0;

    /**
     * Gets a specific property by its title.
     * @return The property with the specified title. A null return value
     * indicates the property is not owned.
     */
    public Property getPropertyByTitle(String title) {
        for (Property prop : properties) {
            if (Objects.equals(prop.card.title, title)) {
                return prop;
            }
        }
        return null;
    }

    /**
     * Gets the number of properties the player owns that belong to the
     * specified group.
     */
    public int ownedPropertiesInGroup(String group) {
        int i = 0;
        for (Property prop : properties) {
            if (Objects.equals(prop.card.group, group)) {
                i++;
            }
        }
        return i;
    }

    /**
     * Returns whether the specified group is owned.
     */
    public boolean isGroupOwned(String group) {
        return groups.contains(group);
    }
}
