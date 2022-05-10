package View.Panels;

import View.GameMenu;

public class MilitaryPanel extends GameMenu {
    public static void run(String command) {
        if (command.startsWith("show")) {
            printPanel();
        }
    }

    private static void printPanel() {

    }

    private static void printRow(String s1, String s2, String s3, String s4, String s5, String s6) {
        String format = "|%1$-13s|%2$-8s|%3$-8s|%4$-8s|%5$-8s|%6$-8s|";
        System.out.format(format, s1, s2, s3, s4, s5, s6);
        System.out.println();
    }
}
