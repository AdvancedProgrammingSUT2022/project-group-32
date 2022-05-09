package Controller;

import Model.*;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.*;
import enums.Responses.InGameResponses;
import enums.Responses.Response;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class PlayerController {

    public static void initializePlayers(ArrayList<Player> players){
        Game game = GameController.getGame();
        //setting camera to an initial tile
        for (Player player : players) {
            // fixme: initialTile conflict is possible
            int randomRow = ThreadLocalRandom.current().nextInt(0, game.getMap().getWidth());
            int randomColumn = ThreadLocalRandom.current().nextInt(0, game.getMap().getHeight());
            Tile initialTile = game.getMap().getTile(randomRow, randomColumn);
            while(initialTile.getTerrainType() == TerrainType.OCEAN || initialTile.getTerrainType() == TerrainType.MOUNTAIN){
                randomRow = ThreadLocalRandom.current().nextInt(0, game.getMap().getWidth());
                randomColumn = ThreadLocalRandom.current().nextInt(0, game.getMap().getHeight());
                initialTile = game.getMap().getTile(randomRow, randomColumn);
            }
            player.setCamera(initialTile); // setting camera to capital
            Unit unit = new Unit(initialTile, player, UnitType.SETTLER);
            Troop troop = new Troop(initialTile, player, UnitType.WARRIOR);
            initialTile.putUnit(unit);
            initialTile.putUnit(troop);
            player.addUnit(unit); // adding initial units
            player.addUnit(troop);
            player.setMap(new Map(game.getMap())); // deep copying map
        }
        for(Player player : players){
            updateFieldOfView(player);
        }
    }

    // TODO: 4/17/2022 War declaration needs confirmation
    public static Response.GameMenu startWarWith(Player player) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Response.GameMenu endWarWith(Player player) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Response.GameMenu offerTrade(Player player, Trade trade) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String citiesPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String dealsPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String demographicsPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String diplomaticPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String diplomacyPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String economyPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String militaryPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String notificationsPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String researchPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String unitsPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String victoryPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static InGameResponses.Technology researchTech(TechnologyType technologyType){
        Player player = GameController.getCurrentPlayer();
        if(player.getTechnologyByType(technologyType) != null){
            return InGameResponses.Technology.TECH_RESEARCHED;
        }
        for (TechnologyType neededTech : technologyType.neededTechs) {
            if(player.getTechnologyByType(neededTech) == null){
                return InGameResponses.Technology.TECH_NOT_YET_READY; // can be dynamic
            }
        }
        player.addIncompleteTechnology(player.getTechnologyInProgress());
        player.setTechnologyInProgress(null);
        Technology technology = new Technology(technologyType);
        if (player.getIncompleteTechnologyByType(technologyType) != null){
            technology = player.getIncompleteTechnologyByType(technologyType);
            player.removeIncompleteTechnology(technology);
        }
        player.setTechnologyInProgress(technology);
        return InGameResponses.Technology.TECH_RESEARCHED;
    }

    public static void startTurn() {
        // TODO: 4/23/2022 does the necessary stuff at the start of the turn
        // TODO: 4/27/2022 decrease ramaining turns in in progress units, techs, buildings, improvements
        Player player = GameController.getGame().getCurrentPlayer();
        for (Unit unit : player.getUnits()) {
            UnitController.updateUnit(unit);
        }
        for (City city : player.getCities()) {
            CityController.updateCity(city);
        }
        updateFieldOfView();
        updateTechnology();
        updateFood();
        updateScience();
        updateGold(); // gold must be updated after science
        updateHappiness();
    }

    private static void updateHappiness() {
        int happiness = 0;
        happiness -= GameController.getCurrentPlayer().getCities().size() * 3;

        ArrayList<ResourceType> playerResources = getPlayerWorkingResourceTypes(GameController.getCurrentPlayer());
        for (ResourceType luxuryResourceType : ResourceType.getLuxuryResourceTypes()) {
            if (playerResources.contains(luxuryResourceType)) happiness += 4;
        }


        ArrayList<BuildingType> buildingTypes = getPlayerBuildingTypes(GameController.getCurrentPlayer());
        if (buildingTypes.contains(BuildingType.BURIAL_TOMB)) happiness += 2;
        if (buildingTypes.contains(BuildingType.CIRCUS)) happiness += 3; // TODO: 5/9/2022
        if (buildingTypes.contains(BuildingType.COLOSSEUM)) happiness += 4;
        if (buildingTypes.contains(BuildingType.SATRAP_COURT)) happiness += 2;

        happiness -= GameController.getCurrentPlayer().getPopulation() / 10;

        GameController.getCurrentPlayer().setHappiness(happiness);
    }

    private static void updateFood() { // citizen eat, death, settler no production
        Player player = GameController.getCurrentPlayer();
        for (City city : player.getCities()) {
            int foodIncome = 0;
            foodIncome += city.getTilesFoodIncome();
            ArrayList<BuildingType> buildingTypes = city.getBuildings().stream().map(Building::getBuildingType).collect(Collectors.toCollection(ArrayList::new));
            if (buildingTypes.contains(BuildingType.GRANARY)) foodIncome += 2;
            if (buildingTypes.contains(BuildingType.WATER_MILL) && city.hasRiver()) {
                foodIncome += 2;
            }
            int foodConsumption = city.getPopulation() * 2;
            if (foodConsumption > foodIncome) {
                city.setPopulation(foodIncome / 2);
                city.setFoodIncome(0);
                return;
            }
            if (city.getUnitInProgress() != null && city.getUnitInProgress().getUnitType().equals(UnitType.SETTLER)) {
                city.setFoodIncome(0);
                return;
            }

            city.setFoodIncome(foodIncome - foodConsumption);
            city.updateNewCitizenStoredFood();
        }
    }

    public static void endTurn() {
        // TODO: 4/23/2022 does the necessary stuff at the end of the turn
        GameController.setSelectedCity(null);
        GameController.setSelectedUnit(null);
    }

    public static Response.GameMenu nextTurn() {
        endTurn();
        GameController.getGame().nextTurn();
        startTurn();
        return Response.GameMenu.TURN_PASSED;
    }

    // basically makes the visible tiles visited
    private static void clearView(Map map){
        for (int row = 0; row < map.getHeight(); row++) {
            for (int column =  0; column < map.getWidth(); column++){
                Tile tile = map.getTile(row, column);
                if(tile.getFogState() != FogState.UNKNOWN){
                    tile.setTroop(null);
                    tile.setUnit(null);
                    tile.setImprovement(null);
                    tile.setFogState(FogState.VISITED);
                }
                else {
                    tile.setTroop(null);
                    tile.setUnit(null);
                    tile.setImprovement(null);
                    tile.setTerrain(new Terrain(null, null, null));
                    tile.setCity(null);
                    tile.setRoadType(null);
                }
            }
        }
    }

    public static void updateFieldOfView(Player player) {
        Map map = player.getMap();
        Map gameMap = GameController.getMap();

        clearView(map);
        ArrayList<Tile> inSight = new ArrayList<>();
        for (Unit unit : player.getUnits()) {
            inSight.addAll(gameMap.lookAroundInRange(unit.getTile(), unit.getSightRange()));
        }
        for (City city : player.getCities()) {
            for (Tile tile : city.getTerritory()) {
                inSight.addAll(gameMap.lookAroundInRange(tile, city.getSightRange()));
            }
        }
        for (Tile tile : inSight) {
            map.setTile(tile.getRow(), tile.getColumn(), new Tile(tile));
            map.getTile(tile.getRow(), tile.getColumn()).setFogState(FogState.VISIBLE);
        }
    }

    public static void updateFieldOfView() {
        Player player = GameController.getGame().getCurrentPlayer();
        updateFieldOfView(player);
    }

    public static void updateSupplies() {

        // TODO: 4/17/2022
    }


    private static void updateGold() {
        // handles turn based coin changes - NOT HANDLING TRADES, BUYS, ...
        double goldIncome = 0;
        for (City city : GameController.getGame().getCurrentPlayer().getCities()) {
            // todo: the building effects are applied to gross city gold production not net!, MINT EFFECT APPLIED!
            ArrayList<BuildingType> buildingTypes = city.getBuildings().stream().map(Building::getBuildingType).collect(Collectors.toCollection(ArrayList::new));
            double cityGrossGold = city.getTerritory().stream().
                    mapToInt(t -> (t.getGold() > 0 && buildingTypes.contains(BuildingType.MINT)) ? t.getGold() + 3 : t.getGold()).sum();
            if (buildingTypes.contains(BuildingType.MARKET)) cityGrossGold *= 1.25;
            if (buildingTypes.contains(BuildingType.BANK)) cityGrossGold *= 1.25;
            if (buildingTypes.contains(BuildingType.SATRAP_COURT)) cityGrossGold *= 1.5;
            if (buildingTypes.contains(BuildingType.STOCK_EXCHANGE)) cityGrossGold *= 1.33;
            for (Building building : city.getBuildings()) {
                cityGrossGold -= building.getCost();
            }
            goldIncome += cityGrossGold;
        }
        int unitMaintenanceCost = 1;
        goldIncome -= unitMaintenanceCost * GameController.getCurrentPlayer().getUnits().size();
        GameController.getCurrentPlayer().setGoldIncome((int) goldIncome);
        GameController.getCurrentPlayer().updateGoldByIncome();
        // +: terrains, terrain Features, resources, buildings cost , handling route  and unit cost
    }

    private static void updateTechnology() { // TODO: 5/9/2022 THIS IS WRONG!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Player player = GameController.getGame().getCurrentPlayer();
        Technology inProgressTechnology = player.getTechnologyInProgress();
        if (inProgressTechnology != null) {
            inProgressTechnology.setRemainingCost(inProgressTechnology.getRemainingCost() - player.getScienceIncome());
            if (inProgressTechnology.getRequiredCost() <= 0) {
                player.addTechnology(inProgressTechnology);
                player.setTechnologyInProgress(null);
                // TODO: 4/27/2022 tech fininsh popup and Logic, new build required
            }
        }
    }

    private static void updateScience() {
        Player player = GameController.getCurrentPlayer();
        int scienceIncome = 0;
        if (player.getCities().size() > 0) scienceIncome += 3;
        for (City city : player.getCities()) {
            scienceIncome += city.getScienceIncome();
        }
        player.setScienceIncome(scienceIncome);
    }

    public static boolean checkIfLost() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static boolean checkIfWon() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static ArrayList<ResourceType> getPlayerWorkingResourceTypes(Player player) {
        ArrayList<ResourceType> res = new ArrayList<>();
        for (City city : player.getCities()) {
            res.addAll(city.getWorkingResources());
        }
        return res;
    }

    public static ArrayList<BuildingType> getPlayerBuildingTypes(Player player) {
        ArrayList<BuildingType> buildingTypes = new ArrayList<>();
        for (City city : player.getCities()) {
            buildingTypes.addAll(city.getBuildings().stream().map(Building::getBuildingType).toList());
        }
        return buildingTypes;
    }

}
