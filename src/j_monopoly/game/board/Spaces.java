package j_monopoly.game.board;

import j_monopoly.assets.Resources;
import j_monopoly.enums.SpaceType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class Spaces {
    public static LinkedList<SpaceType> spaces = new LinkedList<>();

    /**
     * Populates the spaces list with data from the default
     * spaces file.
     */
    public static void populateList() {
        try (InputStream spaceStrm = Resources.class.getResourceAsStream("Properties.txt")) {
            assert spaceStrm != null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(spaceStrm));
            reader.lines().forEach(Spaces::addSpaceToList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets a space type from a string.
     */
    public static SpaceType getSpaceTypeFromString(String str) {
        return switch (str) {
            case "Go" -> SpaceType.GO;
            case "Chance" -> SpaceType.CHANCE;
            case "CChest" -> SpaceType.COMMUNITY_CHEST;
            case "Free" -> SpaceType.FREE_PASS;
            case "Jail" -> SpaceType.JAIL;
            case "GoToJail" -> SpaceType.GO_TO_JAIL;
            default -> SpaceType.PROPERTY;
        };
    }

    private static void addSpaceToList(String str) {
        if (str.startsWith("#") || str.isEmpty()) return;

        SpaceType space = getSpaceTypeFromString(str);
        spaces.add(space);
    }
}
