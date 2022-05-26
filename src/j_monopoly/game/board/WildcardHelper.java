package j_monopoly.game.board;

import j_monopoly.assets.Resources;
import j_monopoly.enums.CardType;
import j_monopoly.models.cards.ActionCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class WildcardHelper {
    public static LinkedList<ActionCard> chanceCards = new LinkedList<>();
    public static LinkedList<ActionCard> communityCards = new LinkedList<>();

    /**
     * Populates the spaces list with data from the default
     * spaces file.
     */
    public static void populateList() {
        try (InputStream chanceStrm = Resources.getResourceAsStream("Chance.txt")) {
            BufferedReader chanceReader = new BufferedReader(new InputStreamReader(chanceStrm));
            chanceReader.lines().forEach(WildcardHelper::addChanceCardToList);
        } catch (IOException ignored) {
        }

        try (InputStream communityStrm = Resources.getResourceAsStream("CChest.txt")) {
            BufferedReader communityReader = new BufferedReader(new InputStreamReader(communityStrm));
            communityReader.lines().forEach(WildcardHelper::addCommunityCardToList);
        } catch (IOException ignored) {
        }
    }

    /**
     * Gets a wildcard from an underscore ("_") separated string.
     */
    public static ActionCard getWildCardFromString(String str, String name) {
        String[] split = str.split("_");
        return new ActionCard(CardType.WILD, name, split[1], Integer.getInteger(split[0]));
    }

    private static void addChanceCardToList(String str) {
        if (str.isEmpty() || str.startsWith("#")) return;
        chanceCards.add(getWildCardFromString(str, "Chance"));
    }

    private static void addCommunityCardToList(String str) {
        if (str.isEmpty() || str.startsWith("#")) return;
        communityCards.add(getWildCardFromString(str, "Community Chest"));
    }
}
