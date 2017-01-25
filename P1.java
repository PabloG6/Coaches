package NBADATABASe;

public class P1 {

    /* Define data structures for holding the data here */
    NBADatabase database;
    NBADatabase.Coaches coaches;
    NBADatabase.Teams teams;

    public P1() {
        /* initialize the data structures */
        coaches = new NBADatabase.Coaches();
        teams = new NBADatabase.Teams();

    }

    public void run() {
        CommandParser parser = new CommandParser();

        System.out.println("The mini-DB of NBA coaches and teams");
        System.out.println("Please enter a command.  Enter 'help' for a list of commands.");
        System.out.println();
        System.out.print("> ");

        Command cmd = null;

        while ((cmd = parser.fetchCommand()) != null) {
            //System.out.println(cmd);

            boolean result = false;
            String[] parameters = cmd.getParameters();
            if (cmd.getCommand().equals("help")) {
                result = doHelp();

		/* You need to implement all the following commands */
            } else if (cmd.getCommand().equals("add_coach")) {

                coaches.addCoaches(parameters);

            } else if (cmd.getCommand().equals("add_team")) {
                teams.addTeam(parameters);


            } else if (cmd.getCommand().equals("print_coaches")) {
                coaches.printCoaches();

            } else if (cmd.getCommand().equals("print_teams")) {
                teams.printTeams();

            } else if (cmd.getCommand().equals("coaches_by_name")) {

                try {
                    coaches.coachName(cmd.getParameters()[0]);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("You must pass the name of the coach to search for");
                }

            } else if (cmd.getCommand().equals("teams_by_city")) {

                try {
                    teams.teamsByCity(cmd.getParameters()[0]);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Please pass one parameter");
                }

            } else if (cmd.getCommand().equals("load_coaches")) {
                try {
                    coaches.loadCoaches(cmd.getParameters()[0]);
                } catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println("Please enter the name of the file you want to load");
                }

            } else if (cmd.getCommand().equals("load_teams")) {
                try {
                    teams.loadTeams(cmd.getParameters()[0]);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("please pass one parameter");
                }


            } else if (cmd.getCommand().equals("best_coach")) {
                try {
                    coaches.bestCoach(cmd.getParameters()[0]);

                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("please pass one parameter");
                }

            } else if (cmd.getCommand().equals("search_coaches")) {
                try {
                    coaches.searchCoaches(cmd.getParameters());

                } catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println("please pass search parameters in the form of key=value");
                }


            } else if (cmd.getCommand().equals("exit")) {
                System.out.println("Leaving the database, goodbye!");
                break;
            } else if (cmd.getCommand().equals("")) {
            } else {
                System.out.println("Invalid Command, try again!");
            }

            if (result) {
                // ...
            }

            System.out.print("> ");
        }
    }

    private boolean doHelp() {
        System.out.println("add_coach ID SEASON FIRST_NAME LAST_NAME SEASON_WIN ");
        System.out.println("          EASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM - add new coach data");
        System.out.println("add_team ID LOCATION NAME LEAGUE - add a new team");
        System.out.println("print_coaches - print a listing of all coaches");
        System.out.println("print_teams - print a listing of all teams");
        System.out.println("coaches_by_name NAME - list info of coaches with the specified name");
        System.out.println("teams_by_city CITY - list the teams in the specified city");
        System.out.println("load_coach FILENAME - bulk load of coach info from a file");
        System.out.println("load_team FILENAME - bulk load of team info from a file");
        System.out.println("best_coach SEASON - print the name of the coach with the most netwins in a specified season");
        System.out.println("search_coaches field=VALUE - print the name of the coach satisfying the specified conditions");
        System.out.println("exit - quit the program");
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new P1().run();
    }


}
