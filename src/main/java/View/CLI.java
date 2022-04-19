package View;

import org.apache.commons.cli.*;

import java.util.ArrayList;

public class CLI {
    private final static Options options = new Options();

    {
        // LOGIN, MAIN, PROFILE MENU PARAMETERS
        options.addOption("u", "username", true, "");
        options.addOption("p", "password", true, "");
        options.addOption("n", "nickname", true, "");
        options.addOption("m", "menu", true, "");

        // GAME MENU PARAMETERS
        Option option = new Option("l", "location", true, ""); // [x] [y]
        option.setArgs(2);
        options.addOption(option);
        options.addOption("n", "name", true, "City name"); // in show, select city
        options.addOption("c", "count", true, "how much to move in a direction");
        options.addOption("r", "right", false, ""); // camera movement direction
        // All parameters must be added here
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
                values.add(cmd.getOptionValue(parameterKey));
            }
            return values;
        } catch (ParseException e) {
            System.err.println("error occurred while parsing");
            e.printStackTrace();
            return null;
        }
    }
}
