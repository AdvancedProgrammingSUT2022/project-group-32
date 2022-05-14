import View.Menu;
import enums.ImprovementType;
import enums.ResourceType;
import enums.TerrainFeature;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ImprovementType.values();
        Scanner scanner = new Scanner(System.in);
        Menu.run(scanner);
    }
}
