package enums.Responses;

public class InGameResponses {

    public enum Building {
        ALREADY_EXISTS("\"$\" already exists in this city"),
        IN_PROGRESS_BUILDING_CHANGED("In progress building changed to \"$\""),
        CITY_NOT_SELECTED("No city is selected"),
        NO_BUILDING_IN_PROGRESS("There are no in progress buildings"),
        BUILDING_PAUSED("Building progress paused");

        private final String message;

        Building(String message) {
            this.message = message;
        }

        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }

    public enum Unit {
        //GENERAL(FOR ALL)
        NO_UNIT_SELECTED("no unit is selected"),
        UNIT_NOT_AVAILABLE("selected unit is not available"),
        UNIT_NOT_IN_POSSESS("selected unit is not in possess"),
        UNIT_IS_TIRED("selected unit is out of moves"),
        //MOVE TO
        TILE_NOT_REACHABLE("movement to this position is not possible because its not reachable"),
        TILE_IS_FILLED("movement to this position is not possible because there is a troop on it"),
        //ALERT , FORTIFY , GARRISON , ATTACK
        UNIT_NOT_MILITARY("selected unit is non-military"),
        UNIT_CANT_FORTIFY("selected unit can't fortify"),
        //GARRISON
        UNIT_NOT_PRESENT_IN_CITY("unit is not present in the city"),
        //SETUP
        UNIT_NOT_SIEGE("selected unit is not a siege unit"),
        //ATTACK
        ATTACK_NOT_POSSIBLE("attack to this position is not possible"),
        //FOUND
        UNIT_NOT_A_SETTLER("selected unit is not a settler"),
        CITY_FOUNDATION_NOT_POSSIBLE("city foundation in the current position is not possible"),
        //PILLAGE
        NO_IMPROVEMENT("there is no improvement on the current tile"),
        OWN_IMPROVEMENT("this improvement belongs to you, you can't pillage it"),
        //BUILD , REMOVE , REPAIR
        UNIT_NOT_A_WORKER("selected unit is not a worker"),
        TILE_NOT_FOREST("selected unit is not on a forest tile"),
        //BUILD
        ROAD_ALREADY_EXISTS("road already exists in the current position"),
        RAILROAD_ALREADY_EXISTS("railroad already exists in the current position"),
        IMPROVEMENT_ALREADY_EXISTS("this improvement already exists in the current position"),
        BUILDING_NOT_POSSIBLE("building not possible in the current position"),
        CONTINUING_BUILDING("worker is continuing the building"),
        MINE_ALREADY_EXISTS("mine already exists in the current position"),
        TRADING_POST_ALREADY_EXISTS("trading post already exists in the current position"),
        LUMBER_MILL_ALREADY_EXISTS("lumber-mill already exists in the current position"),
        JUNGLE_NOT_AVAILABLE("jungle is not available in the current position"),
        PASTURE_ALREADY_EXISTS("pasture already exists in the current position"),
        CAMP_ALREADY_EXISTS("camp already exists in the current position"),
        PLANTATION_ALREADY_EXISTS("plantation already exists in the current position"),
        QUARRY_ALREADY_EXISTS("quarry already exists in the current position"),
        //REMOVE
        ROUTE_NOT_AVAILABLE("selected unit is not on a tile with a route"),
        //REPAIR
        RUIN_NOT_AVAILABLE("ruin is not available in the current position"),
        //BUILDING THE UNIT
        UNIT_BUILDING_PAUSED("unit building successfully paused"),
        UNIT_BUILDING_SUCCESSFUL("unit is being built successfully"),
        UNIT_ALREADY_IN_MAKING("a unit of this type is already being built"),

        //SUCCESS
        MOVETO_SUCCESSFUL("unit successfully moved"),
        SLEEP_SUCCESSFUL("unit successfully slept"),
        ALERT_SUCCESSFUL("unit successfully alerted"),
        FORTIFY_SUCCESSFUL("unit successfully fortified"),
        HEAL_SUCCESSFUL("unit is successfully healing"),
        GARRISON__SUCCESSFUL("unit successfully garrisoned"),
        SETUP_SUCCESSFUL("unit successfully setup"),
        ATTACK_SUCCESSFUL("unit successfully attacked"),
        FOUND_SUCCESSFUL("city successfully founded"),
        CANCEL_SUCCESSFUL("mission successfully canceled"),
        WAKE_SUCCESSFUL("unit successfully woken"),
        DELETE_SUCCESSFUL("unit successfully deleted"),
        BUILD_SUCCESSFUL("successfully built"),
        REMOVE_SUCCESSFUL("successfully removed"),
        PILLAGE_SUCCESSFUL("pillage successful"),
        REPAIR_SUCCESSFUL("successfully repaired");

        private final String message;

        Unit(String message) {
            this.message = message;
        }

        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }
    public enum City{
        // general
        NO_CITY_SELECTED("you haven't selected any city yet"),
        LOCATION_NOT_VALID("the coordinates are not valid"),

        // purchase units
        CAPITAL_IS_FULL("capital tile is currently fulled"),
        NOT_ENOUGH_GOLD("not enough gold to buy the unit"),
        UNIT_BUY_SUCCESSFUL("unit purchased successfully"),
        // purchase tile
        TILE_ALREADY_BOUGHT("tile is already bought"),
        CANT_BUY_TILE("this tile is not available to buy"),
        TILE_TOO_FAR("this tile is not near the selected city"),
        TILE_BUY_SUCCESSFUL("tile purchased successfully");


        private final String message;

        City(String message) {
            this.message = message;
        }
        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }
    public enum Map{
        //SHOW
        INVALID_POSITION("invalid position"),
        INVALID_CITY_NAME("invalid city name"),

        //SUCCESS
        SHOW_SUCCESSFUL("successfully shown map"),
        MOVE_SUCCESSFUL("successfully moved map");

        private final String message;

        Map(String message) {
            this.message = message;
        }

        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }
    public enum Select{
        //UNIT
        INVALID_POSITION("invalid position"),
        MILITARY_UNIT_ALREADY_PRESENT("military unit is already present in the position"),
        NON_MILITARY_UNIT_ALREADY_PRESENT("non-military unit is already present in the position"),
        //CITY
        INVALID_CITY_NAME("invalid city name"),
        CITY_ALREADY_EXISTS("city already exists in the position"),

        //SUCCESS
        UNIT_SELECTION_SUCCESSFUL("unit successfully selected"),
        CITY_SELECTION_SUCCESSFUL("city successfully selected");

        private final String message;

        Select(String message) {
            this.message = message;
        }

        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }
    public enum Technology{
        TECH_ALREADY_DONE("you have already researched this technology"),
        TECH_NOT_YET_READY("you don't have the prerequisites for this technology"),
        TECH_RESEARCHED("technology is being researched");

        private final String message;

        Technology(String message) {
            this.message = message;
        }

        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }
    public enum Info {
        //GENERAL(FOR ALL)  needed??
        ACCEPT("accept");

        private final String message;

        Info(String message) {
            this.message = message;
        }

        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }
}

// TODO: 4/21/2022 dynamic?
