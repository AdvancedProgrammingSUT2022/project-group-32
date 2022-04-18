package Controller;

import Model.Player;
import Model.Trade;
import enums.Responses.Response;

public class PlayerController {

    public static Response.GameMenu moveCamera(char direction, int value){
        // gets current player from game
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }
    // overloading
    public static Response.GameMenu moveCamera(char direction){
        return moveCamera(direction, 0);
    }


    public static Response.GameMenu setCameraByXY(int x, int y){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu setCameraByCityName(String cityName){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    // TODO: 4/17/2022 War declaration needs confirmation
    public static Response.GameMenu startWarWith(Player player){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }
    
    public static Response.GameMenu endWarWith(Player player){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Response.GameMenu offerTrade(Player player, Trade trade){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }
    
    public static String citiesPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String dealsPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String demographicsPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String diplomaticPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String diplomacyPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String economyPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String militaryPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String notificationsPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String researchPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String unitsPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String victoryPanelInfo(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static void updateFieldOfView() {
        // TODO: 4/16/2022
    }

    public static void updateResources(){
        // TODO: 4/17/2022
    }

    public static boolean checkIfLost(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static boolean checkIfWon(){
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }
}
