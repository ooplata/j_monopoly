package j_monopoly.models;

import j_monopoly.enums.PropertyPurchaseResult;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Player {
    private boolean bankrupt;
    private boolean inJail;
    private int outOfJailCards = 0;

    /**
     * Every property the player owns.
     */
    public final LinkedList<Property> properties = new LinkedList<>();

    /**
     * Every group the player owns.
     */
    public final LinkedList<String> groups = new LinkedList<>();

    /**
     * The player's name, usually "Player n".
     */
    public final String name;

    /**
     * Amount of money the player has.
     */
    public int money = 1500;

    /**
     * 0 based index of the space the player's in.
     */
    public int space = 0;

    /**
     * Whether the player is bankrupt.
     */
    public boolean isBankrupt() {
        return bankrupt;
    }

    /**
     * Whether the player is in jail.
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Amount of get out of jail free cards the player has.
     */
    public int getOutOfJailCards() {
        return outOfJailCards;
    }

    /**
     * Moves forward the specified number of spaces.
     *
     * @return Whether the player passed go.
     */
    public boolean moveForward(int spaces) {
        space += spaces;

        // If we're past the last space, loop back
        if (space >= 32) {
            space -= 32;
            return true;
        }
        return false;
    }

    /**
     * Moves back the specified number of spaces.
     *
     * @return Whether the player passed go.
     */
    public boolean moveBack(int spaces) {
        space -= spaces;

        // If we're before the initial space, loop back
        if (space < 0) {
            space += 32;
            return true;
        }
        return false;
    }

    /**
     * Rolls a single 1 to 6 die.
     *
     * @return The number that was rolled.
     */
    public int rollSingleDie() {
        Random rand = new Random();
        return rand.nextInt(1, 7);
    }

    /**
     * Gets a specific property by its title.
     *
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
     *
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
     *
     * @return true if the player can pay rent, false otherwise.
     */
    public boolean tryPayRent(Property property) {
        int rent = property.getRent();
        if (rent > money) return false;

        money -= rent;
        return true;
    }

    /**
     * Gives the player the specified amount of out of jail cards.
     */
    public void addOutOfJailCards(int amount) {
        outOfJailCards += amount;
    }

    /**
     * Makes the player go to jail, getting them to space 8
     * without advancing Go.
     */
    public void goToJail() {
        inJail = true;
        space = 8;
    }

    /**
     * Gets the player out of jail. This only updates the result of
     * isInJail(), it doesn't move the player.
     */
    public void exitJail() {
        inJail = false;
    }

    /**
     * Gets the player out of jail with a get out of jail card, only
     * if any are available. This only updates the result of isInJail(),
     * it doesn't move the player.
     */
    public boolean tryExitJailWithCard() {
        boolean canExit = outOfJailCards > 0;
        if (canExit) {
            inJail = false;
            outOfJailCards--;
        }

        return canExit;
    }

    /**
     * Makes the player go bankrupt, revoking ownership of all properties,
     * goods, money, and get out of jail cards.
     *
     * @return The amount of money the player's goods were worth.
     */
    public int goBankrupt() {
        bankrupt = true;
        outOfJailCards = 0;
        inJail = false;

        int worth = 0;
        worth += money;
        money = 0;

        for (Property property : properties) {
            worth += property.revokeOwnership();
            worth += property.info.cost;
        }

        properties.clear();
        groups.clear();

        return worth;
    }

    public Player(String name) {
        this.name = name;
    }
}
