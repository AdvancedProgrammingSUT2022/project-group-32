package enums;

import java.util.Locale;

//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Locale;
//
public enum TechnologyType {
    AGRICULTURE("Agriculture"),
    ANIMAL_HUSBANDRY("Animal Husbandry"),
    ARCHERY("Archery"),
    BRONZE_WORKING("Bronze Working"),
    CALENDAR("Calendar"),
    MASONRY("Masonry"),
    MINING("Mining"),
    POTTERY("Pottery"),
    THE_WHEEL("The Wheel"),
    TRAPPING("Trapping"),
    WRITING("Writing"),
    CONSTRUCTION("Construction"),
    HORSEBACK_RIDING("Horseback Riding"),
    IRON_WORKING("Iron Working"),
    MATHEMATICS("Mathematics"),
    PHILOSOPHY("Philosophy"),
    CHIVALRY("Chivalry"),
    CIVIL_SERVICE("Civil Service"),
    CURRENCY("Currency"),
    EDUCATION("Education"),
    ENGINEERING("Engineering"),
    MACHINERY("Machinery"),
    METAL_CASTING("Metal Casting"),
    PHYSICS("Physics"),
    STEEL("Steel"),
    THEOLOGY("Theology"),
    ACOUSTICS("Acoustics"),
    ARCHAEOLOGY("Archaeology"),
    BANKING("Banking"),
    CHEMISTRY("Chemistry"),
    ECONOMICS("Economics"),
    GUNPOWDER("Gunpowder"),
    METALLURGY("Metallurgy"),
    MILITARY_SCIENCE("Military Science"),
    PRINTING_PRESS("Printing Press"),
    RIFLING("Rifling"),
    SCIENTIFIC_THEORY("Scientific Theory"),
    FERTILIZER("fertilizer"),
    COMBUSTION("Combustion"),
    DYNAMITE("Dynamite"),
    ELECTRICITY("Electricity"),
    RADIO("Radio"),
    RAILROAD("Railroad"),
    REPLACEABLE_PARTS("Replaceable Parts"),
    STEAM_POWER("Steam Power"),
    TELEGRAPH("Telegraph");

    private final String name;

    TechnologyType(String name) {
        this.name = name;
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
