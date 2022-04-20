package enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public enum TechnologyType {
    AGRICULTURE("Agriculture", 20, new ArrayList<>(Arrays.asList()), new ArrayList<>(Arrays.asList(TechnologyType.POTTERY, TechnologyType.ANIMAL_HUSBANDRY, TechnologyType.ARCHERY, TechnologyType.MINING))),
    ANIMAL_HUSBANDRY("Animal Husbandry", 35, new ArrayList<>(Arrays.asList(TechnologyType.AGRICULTURE)), new ArrayList<>(Arrays.asList(TechnologyType.TRAPPING, TechnologyType.THE_WHEEL))),
    ARCHERY("Archery", 35, new ArrayList<>(Arrays.asList(TechnologyType.AGRICULTURE)), new ArrayList<>(Arrays.asList(TechnologyType.MATHEMATICS))),
    BRONZE_WORKING("Bronze Working", 55, new ArrayList<>(Arrays.asList(TechnologyType.MINING)), new ArrayList<>(Arrays.asList(TechnologyType.IRON_WORKING))),
    CALENDAR("Calendar", 70, new ArrayList<>(Arrays.asList(TechnologyType.POTTERY)), new ArrayList<>(Arrays.asList(TechnologyType.THEOLOGY))),
    MASONRY("Masonry", 55, new ArrayList<>(Arrays.asList(TechnologyType.MINING)), new ArrayList<>(Arrays.asList(TechnologyType.CONSTRUCTION))),
    MINING("Mining", 35, new ArrayList<>(Arrays.asList(TechnologyType.AGRICULTURE)), new ArrayList<>(Arrays.asList(TechnologyType.MASONRY, TechnologyType.BRONZE_WORKING))),
    POTTERY("Pottery", 35, new ArrayList<>(Arrays.asList(TechnologyType.AGRICULTURE)), new ArrayList<>(Arrays.asList(TechnologyType.CALENDAR, TechnologyType.WRITING))),
    THE_WHEEL("The Wheel", 55, new ArrayList<>(Arrays.asList(TechnologyType.ANIMAL_HUSBANDRY)), new ArrayList<>(Arrays.asList(TechnologyType.HORSEBACK_RIDING, TechnologyType.MATHEMATICS))),
    TRAPPING("Trapping", 55, new ArrayList<>(Arrays.asList(TechnologyType.ANIMAL_HUSBANDRY)), new ArrayList<>(Arrays.asList(TechnologyType.CIVIL_SERVICE))),
    WRITING("Writing", 55, new ArrayList<>(Arrays.asList(TechnologyType.POTTERY)), new ArrayList<>(Arrays.asList(TechnologyType.PHILOSOPHY))),
    CONSTRUCTION("Construction", 100, new ArrayList<>(Arrays.asList(TechnologyType.MASONRY)), new ArrayList<>(Arrays.asList(TechnologyType.ENGINEERING))),
    HORSEBACK_RIDING("Horseback Riding", 100, new ArrayList<>(Arrays.asList(TechnologyType.THE_WHEEL)), new ArrayList<>(Arrays.asList(TechnologyType.CHIVALRY))),
    IRON_WORKING("Iron Working", 150, new ArrayList<>(Arrays.asList(TechnologyType.BRONZE_WORKING)), new ArrayList<>(Arrays.asList(TechnologyType.METAL_CASTING))),
    MATHEMATICS("Mathematics", 100, new ArrayList<>(Arrays.asList(TechnologyType.THE_WHEEL, TechnologyType.ARCHERY)), new ArrayList<>(Arrays.asList(TechnologyType.CURRENCY, TechnologyType.ENGINEERING))),
    PHILOSOPHY("Philosophy", 100, new ArrayList<>(Arrays.asList(TechnologyType.WRITING)), new ArrayList<>(Arrays.asList(TechnologyType.THEOLOGY, TechnologyType.CIVIL_SERVICE))),
    CHIVALRY("Chivalry", 440, new ArrayList<>(Arrays.asList(TechnologyType.CIVIL_SERVICE, TechnologyType.HORSEBACK_RIDING, TechnologyType.CURRENCY)), new ArrayList<>(Arrays.asList(TechnologyType.BANKING))),
    CIVIL_SERVICE("Civil Service", 400, new ArrayList<>(Arrays.asList(TechnologyType.PHILOSOPHY, TechnologyType.TRAPPING)), new ArrayList<>(Arrays.asList(TechnologyType.CHIVALRY))),
    CURRENCY("Currency", 250, new ArrayList<>(Arrays.asList(TechnologyType.MATHEMATICS)), new ArrayList<>(Arrays.asList(TechnologyType.CHIVALRY))),
    EDUCATION("Education", 440, new ArrayList<>(Arrays.asList(TechnologyType.THEOLOGY)), new ArrayList<>(Arrays.asList(TechnologyType.ACOUSTICS, TechnologyType.BANKING))),
    ENGINEERING("Engineering", 250, new ArrayList<>(Arrays.asList(TechnologyType.MATHEMATICS, TechnologyType.CONSTRUCTION)), new ArrayList<>(Arrays.asList(TechnologyType.MACHINERY, TechnologyType.PHYSICS))),
    MACHINERY("Machinery", 440, new ArrayList<>(Arrays.asList(TechnologyType.ENGINEERING)), new ArrayList<>(Arrays.asList(TechnologyType.PRINTING_PRESS))),
    METAL_CASTING("Metal Casting", 240, new ArrayList<>(Arrays.asList(TechnologyType.IRON_WORKING)), new ArrayList<>(Arrays.asList(TechnologyType.PHYSICS, TechnologyType.STEEL))),
    PHYSICS("Physics", 440, new ArrayList<>(Arrays.asList(TechnologyType.ENGINEERING, TechnologyType.METAL_CASTING)), new ArrayList<>(Arrays.asList(TechnologyType.PRINTING_PRESS, TechnologyType.GUNPOWDER))),
    STEEL("Steel", 440, new ArrayList<>(Arrays.asList(TechnologyType.METAL_CASTING)), new ArrayList<>(Arrays.asList(TechnologyType.GUNPOWDER))),
    THEOLOGY("Theology", 250, new ArrayList<>(Arrays.asList(TechnologyType.CALENDAR, TechnologyType.PHILOSOPHY)), new ArrayList<>(Arrays.asList(TechnologyType.EDUCATION))),
    ACOUSTICS("Acoustics", 650, new ArrayList<>(Arrays.asList(TechnologyType.EDUCATION)), new ArrayList<>(Arrays.asList(TechnologyType.SCIENTIFIC_THEORY))),
    ARCHAEOLOGY("Archaeology", 1300, new ArrayList<>(Arrays.asList(TechnologyType.ACOUSTICS)), new ArrayList<>(Arrays.asList(TechnologyType.BIOLOGY))),
    BANKING("Banking", 650, new ArrayList<>(Arrays.asList(TechnologyType.EDUCATION, TechnologyType.CHIVALRY)), new ArrayList<>(Arrays.asList(TechnologyType.ECONOMICS))),
    CHEMISTRY("Chemistry", 900, new ArrayList<>(Arrays.asList(TechnologyType.GUNPOWDER)), new ArrayList<>(Arrays.asList(TechnologyType.MILITARY_SCIENCE, TechnologyType.FERTILIZE))),
    ECONOMICS("Economics", 900, new ArrayList<>(Arrays.asList(TechnologyType.BANKING, TechnologyType.PRINTING_PRESS)), new ArrayList<>(Arrays.asList(TechnologyType.MILITARY_SCIENCE))),
    FERTILIZER("Fertilizer", 1300, new ArrayList<>(Arrays.asList(TechnologyType.CHEMISTRY)), new ArrayList<>(Arrays.asList(TechnologyType.DYNAMITE))),
    GUNPOWDER("Gunpowder", 680, new ArrayList<>(Arrays.asList(TechnologyType.PHYSICS, TechnologyType.STEEL)), new ArrayList<>(Arrays.asList(TechnologyType.CHEMISTRY, TechnologyType.METALLURGY))),
    METALLURGY("Metallurgy", 900, new ArrayList<>(Arrays.asList(TechnologyType.GUNPOWDER)), new ArrayList<>(Arrays.asList(TechnologyType.RIFLING))),
    MILITARY_SCIENCE("Military Science", 1300, new ArrayList<>(Arrays.asList(TechnologyType.ECONOMICS, TechnologyType.CHEMISTRY)), new ArrayList<>(Arrays.asList(TechnologyType.STEAM_POWER))),
    PRINTING_PRESS("Printing Press", 650, new ArrayList<>(Arrays.asList(TechnologyType.MACHINERY, TechnologyType.PHYSICS)), new ArrayList<>(Arrays.asList(TechnologyType.ECONOMICS))),
    RIFLING("Rifling", 1425, new ArrayList<>(Arrays.asList(TechnologyType.METALLURGY)), new ArrayList<>(Arrays.asList(TechnologyType.DYNAMITE))),
    SCIENTIFIC_THEORY("Scientific Theory", 1300, new ArrayList<>(Arrays.asList(TechnologyType.ACOUSTICS)), new ArrayList<>(Arrays.asList(TechnologyType.BIOLOGY, TechnologyType.STEAM_POWER))),
    BIOLOGY("Biology", 1680, new ArrayList<>(Arrays.asList(TechnologyType.ARCHAEOLOGY, TechnologyType.SCIENTIFIC_THEORY)), new ArrayList<>(Arrays.asList(TechnologyType.ELECTRICITY))),
    COMBUSTION("Combustion", 2200, new ArrayList<>(Arrays.asList(TechnologyType.REPLACEABLE_PARTS, TechnologyType.RAILROAD, TechnologyType.DYNAMITE)), new ArrayList<>(Arrays.asList())),
    DYNAMITE("Dynamite", 1900, new ArrayList<>(Arrays.asList(TechnologyType.FERTILIZER, TechnologyType.RIFLING)), new ArrayList<>(Arrays.asList(TechnologyType.COMBUSTION))),
    ELECTRICITY("Electricity", 1900, new ArrayList<>(Arrays.asList(TechnologyType.BIOLOGY, TechnologyType.STEAM_POWER)), new ArrayList<>(Arrays.asList(TechnologyType.TELEGRAPH, TechnologyType.RADIO))),
    RADIO("Radio", 2200, new ArrayList<>(Arrays.asList(TechnologyType.ELECTRICITY)), new ArrayList<>(Arrays.asList())),
    RAILROAD("Railroad", 1900, new ArrayList<>(Arrays.asList(TechnologyType.STEAM_POWER)), new ArrayList<>(Arrays.asList(TechnologyType.COMBUSTION))),
    REPLACEABLE_PARTS("Replaceable Parts", 1900, new ArrayList<>(Arrays.asList(TechnologyType.STEAM_POWER)), new ArrayList<>(Arrays.asList(TechnologyType.COMBUSTION))),
    STEAM_POWER("Steam Power", 1680, new ArrayList<>(Arrays.asList(TechnologyType.SCIENTIFIC_THEORY, TechnologyType.MILITARY_SCIENCE)), new ArrayList<>(Arrays.asList(TechnologyType.ELECTRICITY, TechnologyType.REPLACEABLE_PARTS, TechnologyType.RAILROAD))),
    TELEGRAPH("Telegraph", 2200, new ArrayList<>(Arrays.asList(TechnologyType.ELECTRICITY)), new ArrayList<>(Arrays.asList()));

    private final String name;
    private final int cost;
    private final ArrayList<TechnologyType> neededTechs;
    private final ArrayList<TechnologyType> unlockingTechs;

    TechnologyType(String name, int cost, ArrayList<TechnologyType> neededTechs, ArrayList<TechnologyType> unlockingTechs) {
        this.name = name;
        this.cost = cost;
        this.neededTechs = neededTechs;
        this.unlockingTechs = unlockingTechs;
    }

    /**
     * gets a techName and returns corresponding enum
     *
     * @param name
     * @return TechnologyType or null
     */
    public static TechnologyType getEnumByName(String name) {
        for (TechnologyType technologyType : TechnologyType.values()) {
            if (technologyType.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                return technologyType;
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}
