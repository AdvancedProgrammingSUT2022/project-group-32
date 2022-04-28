package enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public enum TechnologyType {
    AGRICULTURE("Agriculture", 20, new ArrayList<>(List.of())),
    ANIMAL_HUSBANDRY("Animal Husbandry", 35, new ArrayList<>(List.of(TechnologyType.AGRICULTURE))),
    ARCHERY("Archery", 35, new ArrayList<>(List.of(TechnologyType.AGRICULTURE))),
    MINING("Mining", 35, new ArrayList<>(List.of(TechnologyType.AGRICULTURE))),
    BRONZE_WORKING("Bronze Working", 55, new ArrayList<>(List.of(TechnologyType.MINING))),
    POTTERY("Pottery", 35, new ArrayList<>(List.of(TechnologyType.AGRICULTURE))),
    CALENDAR("Calendar", 70, new ArrayList<>(List.of(TechnologyType.POTTERY))),
    MASONRY("Masonry", 55, new ArrayList<>(List.of(TechnologyType.MINING))),
    THE_WHEEL("The Wheel", 55, new ArrayList<>(List.of(TechnologyType.ANIMAL_HUSBANDRY))),
    TRAPPING("Trapping", 55, new ArrayList<>(List.of(TechnologyType.ANIMAL_HUSBANDRY))),
    WRITING("Writing", 55, new ArrayList<>(List.of(TechnologyType.POTTERY))),
    CONSTRUCTION("Construction", 100, new ArrayList<>(List.of(TechnologyType.MASONRY))),
    HORSEBACK_RIDING("Horseback Riding", 100, new ArrayList<>(List.of(TechnologyType.THE_WHEEL))),
    IRON_WORKING("Iron Working", 150, new ArrayList<>(List.of(TechnologyType.BRONZE_WORKING))),
    MATHEMATICS("Mathematics", 100, new ArrayList<>(Arrays.asList(TechnologyType.THE_WHEEL, TechnologyType.ARCHERY))),
    PHILOSOPHY("Philosophy", 100, new ArrayList<>(List.of(TechnologyType.WRITING))),
    CIVIL_SERVICE("Civil Service", 400, new ArrayList<>(Arrays.asList(TechnologyType.PHILOSOPHY, TechnologyType.TRAPPING))),
    CURRENCY("Currency", 250, new ArrayList<>(List.of(TechnologyType.MATHEMATICS))),
    CHIVALRY("Chivalry", 440, new ArrayList<>(Arrays.asList(TechnologyType.CIVIL_SERVICE, TechnologyType.HORSEBACK_RIDING, TechnologyType.CURRENCY))),
    THEOLOGY("Theology", 250, new ArrayList<>(Arrays.asList(TechnologyType.CALENDAR, TechnologyType.PHILOSOPHY))),
    EDUCATION("Education", 440, new ArrayList<>(List.of(TechnologyType.THEOLOGY))),
    ENGINEERING("Engineering", 250, new ArrayList<>(Arrays.asList(TechnologyType.MATHEMATICS, TechnologyType.CONSTRUCTION))),
    MACHINERY("Machinery", 440, new ArrayList<>(List.of(TechnologyType.ENGINEERING))),
    METAL_CASTING("Metal Casting", 240, new ArrayList<>(List.of(TechnologyType.IRON_WORKING))),
    PHYSICS("Physics", 440, new ArrayList<>(Arrays.asList(TechnologyType.ENGINEERING, TechnologyType.METAL_CASTING))),
    STEEL("Steel", 440, new ArrayList<>(List.of(TechnologyType.METAL_CASTING))),
    ACOUSTICS("Acoustics", 650, new ArrayList<>(List.of(TechnologyType.EDUCATION))),
    ARCHAEOLOGY("Archaeology", 1300, new ArrayList<>(List.of(TechnologyType.ACOUSTICS))),
    BANKING("Banking", 650, new ArrayList<>(Arrays.asList(TechnologyType.EDUCATION, TechnologyType.CHIVALRY))),
    GUNPOWDER("Gunpowder", 680, new ArrayList<>(Arrays.asList(TechnologyType.PHYSICS, TechnologyType.STEEL))),
    CHEMISTRY("Chemistry", 900, new ArrayList<>(List.of(TechnologyType.GUNPOWDER))),
    PRINTING_PRESS("Printing Press", 650, new ArrayList<>(Arrays.asList(TechnologyType.MACHINERY, TechnologyType.PHYSICS))),
    ECONOMICS("Economics", 900, new ArrayList<>(Arrays.asList(TechnologyType.BANKING, TechnologyType.PRINTING_PRESS))),
    FERTILIZER("Fertilizer", 1300, new ArrayList<>(List.of(TechnologyType.CHEMISTRY))),
    METALLURGY("Metallurgy", 900, new ArrayList<>(List.of(TechnologyType.GUNPOWDER))),
    MILITARY_SCIENCE("Military Science", 1300, new ArrayList<>(Arrays.asList(TechnologyType.ECONOMICS, TechnologyType.CHEMISTRY))),
    RIFLING("Rifling", 1425, new ArrayList<>(List.of(TechnologyType.METALLURGY))),
    SCIENTIFIC_THEORY("Scientific Theory", 1300, new ArrayList<>(List.of(TechnologyType.ACOUSTICS))),
    BIOLOGY("Biology", 1680, new ArrayList<>(Arrays.asList(TechnologyType.ARCHAEOLOGY, TechnologyType.SCIENTIFIC_THEORY))),
    DYNAMITE("Dynamite", 1900, new ArrayList<>(Arrays.asList(TechnologyType.FERTILIZER, TechnologyType.RIFLING))),
    STEAM_POWER("Steam Power", 1680, new ArrayList<>(Arrays.asList(TechnologyType.SCIENTIFIC_THEORY, TechnologyType.MILITARY_SCIENCE))),
    ELECTRICITY("Electricity", 1900, new ArrayList<>(Arrays.asList(TechnologyType.BIOLOGY, TechnologyType.STEAM_POWER))),
    RADIO("Radio", 2200, new ArrayList<>(List.of(TechnologyType.ELECTRICITY))),
    RAILROAD("Railroad", 1900, new ArrayList<>(List.of(TechnologyType.STEAM_POWER))),
    REPLACEABLE_PARTS("Replaceable Parts", 1900, new ArrayList<>(List.of(TechnologyType.STEAM_POWER))),
    COMBUSTION("Combustion", 2200, new ArrayList<>(Arrays.asList(TechnologyType.REPLACEABLE_PARTS, TechnologyType.RAILROAD, TechnologyType.DYNAMITE))),
    TELEGRAPH("Telegraph", 2200, new ArrayList<>(List.of(TechnologyType.ELECTRICITY)));


    public final String name;
    public final int cost;
    public final ArrayList<TechnologyType> neededTechs;

    TechnologyType(String name, int cost, ArrayList<TechnologyType> neededTechs) {
        this.name = name;
        this.cost = cost;
        this.neededTechs = neededTechs;
    }

    /**
     * gets a techName and returns corresponding enum
     * @return TechnologyType or null
     */
    public static TechnologyType getEnumByName(String name) {
        for (TechnologyType technologyType : TechnologyType.values()) {
            if (technologyType.name.toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                return technologyType;
        }
        return null;
    }

    public ArrayList<TechnologyType> getUnlockingTechs() {
        return Arrays.stream(TechnologyType.values()).filter(t -> t.neededTechs.contains(this)).collect(Collectors.toCollection(ArrayList::new));
    }
}
