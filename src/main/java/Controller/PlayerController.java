package Controller;

import Model.Player;
import View.MiniMenus.VictoryPanel;
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
        
    }
    
    public static Response.GameMenu endWarWith(Player player){
        
    }
    
    public static String citiesPanelInfo(){

    }

    public static String dealsPanelInfo(){

    }

    public static String demographicsPanelInfo(){

    }

    public static String diplomaticPanelInfo(){

    }

    public static String diplomacyPanelInfo(){

    }

    public static String economyPanelInfo(){

    }

    public static String militaryPanelInfo(){

    }

    public static String notificationsPanelInfo(){

    }

    public static String researchPanelInfo(){

    }

    public static String unitsPanelInfo(){

    }

    public static String victoryPanelInfo(){

    }

    public static void updateFieldOfView() {
        // TODO: 4/16/2022
    }

    public static void updateResources(){
        // TODO: 4/17/2022
    }

    public static boolean checkIfLost(){

    }

    public static boolean checkIfWon(){

    }
}
