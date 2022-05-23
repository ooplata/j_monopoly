package j_monopoly.models;

import j_monopoly.enums.PropertyPurchaseResult;

import java.util.LinkedList;
import java.util.Objects;

public class Player {
    private final LinkedList<Property> properties = new LinkedList<>();
    private final LinkedList<String> groups = new LinkedList<>();

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
            if (Objects.equals(prop.info.title, title)) {
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
            if (Objects.equals(prop.info.group, group)) {
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

    /**
     * Purchases the specified property if possible. This method automatically
     * manages group ownership when necessary.
     * @param property Property to purchase.
     * @return The result of the purchase.
     */
    public PropertyPurchaseResult purchaseProperty(Property property) {
        if (property.info.cost > money) return PropertyPurchaseResult.NOT_PURCHASED;

        money -= property.info.cost;

        property.isOwned = true;
        properties.add(property);

        // If the full group is now owned, update the other properties
        String group = property.info.group;
        if (ownedPropertiesInGroup(group) == property.info.amountInGroup) {
            groups.add(group);
            for (Property prop : properties) {
                if (Objects.equals(prop.info.group, group)) {
                    prop.isGroupOwned = true;
                }
            }
            return PropertyPurchaseResult.GROUP_PURCHASED;
        }

        return PropertyPurchaseResult.PURCHASED;
    }

    /**
     * Tries to pay rent for the specified property. If the player can
     * pay, automatically subtracts the required amount.
     * @return true if the player can pay rent, false otherwise.
     */
    public boolean tryPayRent(Property property) {
        int rent = property.getRent();
        if (rent > money) return false;

        money -= rent;
        return true;
    }
}
