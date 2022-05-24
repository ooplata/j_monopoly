package j_monopoly.game.board;

import j_monopoly.assets.Resources;
import j_monopoly.enums.SpaceType;
import j_monopoly.models.Property;
import j_monopoly.models.Space;
import j_monopoly.models.cards.PropertyCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class Spaces {
    public static LinkedList<Space<Object>> spaces = new LinkedList<>();

    /**
     * Populates the spaces list with data from the default
     * spaces file.
     */
    public static void populateList() {
        try (InputStream spaceStrm = Resources.getResourceAsStream("Spaces.txt")) {
            try (InputStream propStrm = Resources.getResourceAsStream("Properties.txt")) {
                assert spaceStrm != null;
                assert propStrm != null;

                BufferedReader spaceReader = new BufferedReader(new InputStreamReader(spaceStrm));
                BufferedReader propReader = new BufferedReader(new InputStreamReader(propStrm));

                spaceReader.lines().forEach((s -> {
                    if (s.isEmpty() || s.startsWith("#"))
                        return;

                    SpaceType type = getSpaceTypeFromString(s);
                    switch (type) {
                        case PROPERTY -> {
                            try {
                                String line = "#";
                                while (line.isEmpty() || line.startsWith("#"))
                                    line = propReader.readLine();

                                PropertyCard card = getPropertyCardFromString(line);
                                Property prop = new Property(card);
                                spaces.add(new Space<>(prop, type));
                            } catch (IOException ignored) {
                            }
                        }
                        default -> spaces.add(new Space<>(0, type));
                    }
                }));
            } catch (IOException ignored) {
            }
        } catch (IOException ignored) {
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
            case "Property" -> SpaceType.PROPERTY;
            default -> throw new IllegalArgumentException(str + " is not a valid property type.");
        };
    }

    /**
     * Gets a property card from a comma space (", ") separated string.
     */
    public static PropertyCard getPropertyCardFromString(String str) {
        String[] split = str.split(", ");

        String title = split[0];
        String desc = split[1];
        String group = split[2];

        int inGrp = Integer.parseInt(split[3]);
        int cost = Integer.parseInt(split[4]);
        int rent = Integer.parseInt(split[5]);
        int rentWithGrp = Integer.parseInt(split[6]);
        int house = Integer.parseInt(split[7]);
        int hotel = Integer.parseInt(split[8]);
        int withHouse = Integer.parseInt(split[9]);
        int withTwoHouse = Integer.parseInt(split[10]);
        int withThreeHouse = Integer.parseInt(split[11]);
        int withFourHouse = Integer.parseInt(split[12]);
        int withHotel = Integer.parseInt(split[13]);

        return new PropertyCard(title, desc, group, inGrp, cost, rent, rentWithGrp, house, hotel, withHouse, withTwoHouse, withThreeHouse, withFourHouse, withHotel);
    }
}
