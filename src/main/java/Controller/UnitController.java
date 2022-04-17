package Controller;

import Model.Tile;
import Model.Units.Unit;
import enums.ImprovementType;
import enums.Responses.Response;
import enums.RoadType;

public class UnitController {
    public static Response.GameMenu moveTo(int x, int y){
        // TODO: 4/17/2022 handle vision at the end
        // TODO: Handle MP
    }

    // these functions should affect isDone
    public static Response.GameMenu sleep(){
        Unit unit = GameController.getSelectedUnit();
        if(unit == null){

        }
    }

    public static Response.GameMenu alert(){

    }

    public static Response.GameMenu fortify(){

    }

    public static Response.GameMenu heal(){

    }

    public static Response.GameMenu garrison(){

    }

    public static Response.GameMenu setup(){

    }

    public static Response.GameMenu attack(int x, int y){

    }

    public static Response.GameMenu foundCity(){
        // only for settlers
    }

    public static Response.GameMenu cancelOrder(){

    }

    public static Response.GameMenu wake(){

    }

    public static Response.GameMenu delete(){

    }

    public static Response.GameMenu buildImprovement(ImprovementType improvement){

    }

    public static Response.GameMenu buildRoad(RoadType roadType){

    }

    public static Response.GameMenu removeForest(){

    }

    public static Response.GameMenu removeRoute(){

    }

    public static Response.GameMenu repair(){

    }
}
