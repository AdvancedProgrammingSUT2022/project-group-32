package View;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

public class CLI {
    private static final Options options = new Options();

    static {
        // LOGIN, MAIN, PROFILE MENU PARAMETERS
        options.addOption("u", "username", true, "");
        options.addOption("p", "password", true, "");
        options.addOption("n", "nickname", true, "");
        options.addOption("m", "menu", true, "");
        Option option1 = new Option("P", "player", true, "players for new game");
        option1.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(option1);
        // GAME MENU PARAMETERS
        Option option = new Option("l", "location", true, ""); // [x] [y]
        option.setArgs(2);
        options.addOption(option);
        options.addOption("n", "name", true, "City name"); // in show, select city
        options.addOption("c", "count", true, "how much to move in a direction");
        options.addOption("r", "right", false, ""); // camera movement direction
        // TODO: 4/21/2022   All parameters must be added here
    }

    /**
     * @param command       input command from user
     * @param parameterKeys variable number of parameter keys
     * @return arrayList of parameters' values, returns null with error or invalid command
     */
    public static ArrayList<String> getParameters(String command, String... parameterKeys) {
        ArrayList<String> values = new ArrayList<>();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, command.split(" "));
            for (String parameterKey : parameterKeys) {
                if (!cmd.hasOption(parameterKey)) return null;
                if (parameterKey.equals("P") || parameterKey.equals("player")) {
                    String[] rawValues = cmd.getOptionValues("P");
                    List<String> playersUsernames = java.util.Arrays.stream(rawValues, 1, rawValues.length).toList();
                    values.addAll(playersUsernames);
                } else values.add(cmd.getOptionValue(parameterKey));
            }
            return values;
        } catch (ParseException e) {
            System.err.println("error occurred while parsing");
            e.printStackTrace();
            return null;
        }
    }
}
