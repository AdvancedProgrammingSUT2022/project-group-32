package enums.Responses;

public class InGameResponses {
    public enum Unit{
        //GENERAL(FOR ALL)
        UNIT_NOT_AVAILABLE("current unit is not available"),
        UNIT_NOT_IN_POSSESS("current unit is not in possess"),
        //MOVE TO
        TILE_NOT_REACHABLE("movement to this position is not possible because its not reachable"),
        TILE_IS_FILLED("movement to this position is not possible because there is a troop on it"),
        //ALERT , FORTIFY , GARRISON , ATTACK
        UNIT_NOT_MILITARY("current unit is non-military"),
        //GARRISON
        UNIT_NOT_PRESENT_IN_CITY("unit is not present in the city"),
        //SETUP
        SURROUND_NOT_POSSIBLE("current unit is not capable of surrounding"),
        //ATTACK
        ATTACK_NOT_POSSIBLE("attack to this position is not possible"),
        //FOUND
        UNIT_NOT_A_SETTLER("current unit is not a settler"),
        CITY_FOUNDATION_NOT_POSSIBLE("city foundation in the current position is not possible"),
        //BUILD , REMOVE , REPAIR
        UNIT_NOT_A_WORKER("current unit is not a worker"),
        //BUILD
        ROAD_ALREADY_EXISTS("road already exists in the current position"),
        RAILROAD_ALREADY_EXISTS("railroad already exists in the current position"),
        FARM_ALREADY_EXISTS("farm already exists in the current position"),
        BUILDING_NOT_POSSIBLE("building not possible in the current position"),
        MINE_ALREADY_EXISTS("mine already exists in the current position"),
        TRADING_POST_ALREADY_EXISTS("trading post already exists in the current position"),
        LUMBER_MILL_ALREADY_EXISTS("lumber-mill already exists in the current position"),
        JUNGLE_NOT_AVAILABLE("jungle is not available in the current position"),
        PASTURE_ALREADY_EXISTS("pasture already exists in the current position"),
        CAMP_ALREADY_EXISTS("camp already exists in the current position"),
        PLANTATION_ALREADY_EXISTS("plantation already exists in the current position"),
        QUARRY_ALREADY_EXISTS("quarry already exists in the current position"),
        //REMOVE
        ROUTE_NOT_AVAILABLE("route is not available in this tile"),
        //REPAIR
        RUIN_NOT_AVAILABLE("ruin is not available in the current position"),

        //SUCCESS
        MOVETO_SUCCESSFUL("unit successfully moved"),
        SLEEP_SUCCESSFUL("unit successfully slept"),
        ALERT_SUCCESSFUL("unit successfully alerted"),
        FORTIFY_SUCCESSFUL("unit successfully fortified"),
        GARRISON__SUCCESSFUL("unit successfully garrisoned"),
        SETUP_SUCCESSFUL("unit successfully setup"),
        ATTACK_SUCCESSFUL("unit successfully attacked"),
        FOUND_SUCCESSFUL("city successfully founded"),
        CANCEL_SUCCESSFUL("mission successfully canceled"),
        WAKE_SUCCESSFUL("unit successfully woken"),
        DELETE_SUCCESSFUL("unit successfully deleted"),
        BUILD_SUCCESSFUL("successfully built"),
        REMOVE_SUCCESSFUL("successfully removed"),
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
