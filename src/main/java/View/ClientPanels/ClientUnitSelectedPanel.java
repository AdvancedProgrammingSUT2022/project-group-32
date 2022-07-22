package View.ClientPanels;

import Controller.GameController;
import Controller.UnitController;
import Model.Units.Unit;
import View.GameView;
import View.Network;
import View.PastViews.CLI;
import View.PastViews.GameMenu;
import enums.RequestActions;
import enums.Responses.InGameResponses;
import enums.Types.ImprovementType;
import enums.Types.RoadType;

import java.util.ArrayList;

public class ClientUnitSelectedPanel extends GameView {

    // TODO: 7/22/2022 This whole file needs to be duplicated

    public static void run(String command) {
        /*if (command.startsWith("move unit")) moveTo(command);
        else if (command.startsWith("back")) GameMenu.currentPanel = null;
        else if (command.startsWith("build city")) foundCity(command);
        else if (command.startsWith("sleep")) sleep();
        else if (command.startsWith("alert")) alert();
        else if (command.startsWith("fortify")) fortify();
        else if (command.startsWith("heal")) heal();
        else if (command.startsWith("wake")) wake();
        else if (command.startsWith("delete")) delete();
        else if (command.startsWith("build improvement")) buildImprovement(command);
        else if (command.startsWith("build road")) buildRoad(command);
        else if (command.startsWith("remove forest")) removeForest();
        else if (command.startsWith("remove jungle")) removeJungle();
        else if (command.startsWith("remove marsh")) removeMarsh();
        else if (command.startsWith("remove road")) removeRoute();
        else if (command.startsWith("pillage")) pillage();
        else if (command.startsWith("repair")) repair();
        else if (command.startsWith("set up")) setup();
        else if (command.startsWith("garrison")) garrison();
        else if (command.startsWith("attack")) attack(command);
        else if (command.startsWith("show selected unit")) showSelected();*/
    }

    public static String showSelected() {
        Unit unit = selectedUnit;
        StringBuilder resp = new StringBuilder();
        resp.append("location: " + unit.getTile().getRow() + " " + unit.getTile().getColumn() + "\n");
        resp.append("type: " + unit.getUnitType().name + "\n");
        resp.append("owner: " + unit.getOwner().getName() + "\n");
        resp.append("HP: " + unit.getHP() + "\n");
        resp.append("current order status: " + unit.getOrderType().toString());
        return resp.toString();
    }

    public static void moveTo() {
        InGameResponses.Unit response = ((InGameResponses.Unit) Network.getResponseObjOfPanelCommand("move unit -l " + selectedRow + " " + selectedColumn));
        if(response != InGameResponses.Unit.MOVETO_SUCCESSFUL){
            showAlert(invalidAlert, response.getString());
        }
    }

    public static void foundCity() {/*
        ArrayList<String> parameters = CLI.getParameters(command, "cn");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        if (parameters.get(0).length() < 3) {
            System.out.println("City name is too small");
            return;
        }
        System.out.println(UnitController.foundCity(parameters.get(0)).getString());
    */}

    public static void sleep() {
        InGameResponses.Unit response = ((InGameResponses.Unit) Network.getResponseObjOfPanelCommand("sleep"));
        show(stage);
    }

    public static void alert() {
        System.out.println(UnitController.alert().getString());
    }

    public static void fortify() {
        System.out.println(UnitController.fortify().getString());
    }

    public static void heal() {
        System.out.println(UnitController.heal().getString());
    }

    public static void wake() {
        System.out.println(UnitController.wake().getString());
    }

    public static void delete() {
        System.out.println(UnitController.delete().getString());
    }

    public static void buildImprovement() {/*
        ArrayList<String> parameters = CLI.getParameters(command, "t");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        System.out.println(UnitController.buildImprovement(ImprovementType.getTypeByName(parameters.get(0))).getString());
    */}

    public static void buildRoad() {/*
        ArrayList<String> parameters = CLI.getParameters(command, "t");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        System.out.println(UnitController.buildRoad(RoadType.getTypeByName(parameters.get(0))).getString());
    */}

    public static void removeForest() {
        System.out.println(UnitController.removeForest().getString());
    }

    public static void removeJungle() {
        System.out.println(UnitController.removeJungle().getString());
    }

    public static void removeMarsh() {
        System.out.println(UnitController.removeMarsh().getString());
    }

    public static void removeRoute() {
        System.out.println(UnitController.removeRoute().getString());
    }

    public static void pillage() {
        System.out.println(UnitController.pillage().getString());
    }

    public static void repair() {
        System.out.println(UnitController.repair().getString());
    }

    public static void setup() {
        System.out.println(UnitController.setup().getString());
    }

    public static void garrison() {
        System.out.println(UnitController.garrison().getString());
    }

    public static void attack() {/*
        ArrayList<String> parameters = CLI.getParameters(command, "l");
        if (parameters == null) {
            invalidCommand();
            return;
        }
        int row = Integer.parseInt(parameters.get(0)), column = Integer.parseInt(parameters.get(1));
        System.out.println(UnitController.attack(row, column).getString());
    */}

}
