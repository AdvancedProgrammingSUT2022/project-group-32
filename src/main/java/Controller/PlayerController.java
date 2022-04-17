package Controller;

import enums.Responses.Response;

public class PlayerController {

    public static Response.GameMenu moveCamera(char direction, int value){
        // gets current player from game
    }
    // overloading
    public static Response.GameMenu moveCamera(char direction){
        return moveCamera(direction, 0);
    }


    public static Response.GameMenu setCameraByXY(int x, int y){

    }

    public static Response.GameMenu setCameraByCityName(String cityName){

    }

    public static void updateFieldOfView() {
        // TODO: 4/16/2022
    }

    public static void updateResources(){
        // TODO: 4/17/2022
    }


}
