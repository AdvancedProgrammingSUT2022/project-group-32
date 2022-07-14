import View.GameView;
import View.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GameView.class.getClassLoader().getResource("images/Terrains/hell.png"));
        System.out.println("images/Terrains/hell.png");
        Menu.main(args);
    }
}
