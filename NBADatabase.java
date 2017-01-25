package NBADATABASe;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Pablo on 1/23/2017.
 */
public class NBADatabase {
    private static String absolutePath = System.getProperty("user.dir");

    public static void print_path() {
        System.out.println(absolutePath);


    }

    public static class Queries {
        String key;
        String value;

        public Queries(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Coaches {
        private String first_name;
        private String last_name;
        private int season_win;
        private int season_loss;
        private int payoff_loss;
        private String capital_letters;
        String fileName = "coaches_season.txt";
        private URL path = NBADatabase.class.getResource(fileName);
        private String finalPath = absolutePath + "/" + fileName;

        public Coaches() {

        }


        public void addCoaches(String... parameters) {
            add(finalPath, parameters, true);

        }

        public void printCoaches() {
            //// TODO: 1/24/2017 open coaches.txt and print information to console
            try {
                print(this.finalPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        public void searchCoaches(String[] parameter) throws ArrayIndexOutOfBoundsException {
            ArrayList<Queries> queries = new ArrayList<>();
            //// TODO: 1/24/2017 print by parameter passed
            for (String s : parameter) {
                String[] t = s.split("=");
                queries.add(new Queries(t[0], t[1]));


            }

            File file = new File(this.finalPath);
            try {

                for (Queries q : queries) {

                    //search through the file by each query. O(N)^2
                    //does print duplicate values
                    BufferedReader buffered = new BufferedReader(new FileReader(file));
                    String line = buffered.readLine();
                    while (line != null) {
                        if (line.contains(q.getValue())) {
                            System.out.println(line);
                        }
                        line = buffered.readLine();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        public void loadCoaches(String s) throws ArrayIndexOutOfBoundsException {
            this.fileName = s;

            try {
                load(s);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {

            }
        }

        public void bestCoach(String s) {
            // TODO: 1/24/2017 best coach printed
            highest(finalPath, s);
        }

        public void coachName(String s) {
            ArrayList<String> val = query(s, finalPath);
            for (String values :
                    val) {
                System.out.println(values);
            }

        }


    }

    public static class Teams {
        private String team_ID;
        private String location;
        private String name;
        private String league;
        String fileName = "teams.txt";
        //        private URL path = NBADatabase.class.getResource(fileName);
//        private String finalPath = path.getPath();
        private String finalPath = System.getProperty("user.dir");

        public void addTeam(String[] parameters) {
            //// TODO: 1/24/2017 add team members and do regex checks
            add(finalPath, parameters, false);

        }

        public void printTeams() {
            //// TODO: 1/24/2017 print teams from database
            try {
                print(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void teamsByCity(String s) {
            //// TODO: 1/24/2017 print teams by city
            query(finalPath, s);
        }

        public void loadTeams(String fileName) {
            // TODO: 1/24/2017 load all teams


            this.fileName = fileName;
            try {
                load(fileName);
            } catch (FileNotFoundException e) {

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private static void add(String s, String[] parameters, boolean whichFile) {

        if (whichFile == true) {
            if ((parameters.length != 9)) {
                System.out.println(parameters.length);
                System.out.println("Please enter parameters in the form of ID SEASON FIRST_NAME LAST_NAME SEASON_WIN \n" +
                        "          SEASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM ");
                return;
            }

        } else {
            if (parameters.length != 5) {
                System.out.println("Please enter parameters in the form of ID LOCATION NAME LEAGUE");
                return;
            }
        }
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        File file = new File(s);
        try {


            StringBuilder builder = new StringBuilder();
            if (file.createNewFile()) {
                fileWriter = new FileWriter(file);
                bufferedWriter = new BufferedWriter(fileWriter);
                for (int i = 0; i < parameters.length; i++) {
                    String k = parameters[i];
                    builder.append(k);
                    if (i != parameters.length - 1) {
                        System.out.println("here");
                        builder.append(",");

                    }

                }
                builder.append('\n');
                bufferedWriter.write(builder.toString());
            } else {
                fileWriter = new FileWriter(file, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                for (int i = 0; i < parameters.length; i++) {
                    String k = parameters[i];
                    switch (i) {
                        case 0:
                            if (!k.matches("[A-Z]{7}[0-9]{2}")) {
                                System.out.println
                                        ("Please enter an ID in the form of AAAAA11");
                                return;
                            }
                            break;
                        case 1:
                            if (!k.matches("[0-9]{4}")) {
                                System.out.println("Please enter a four digit year");
                                return;

                            }
                            break;

                        case 3:
                            if (!k.matches("[A-Za-z0-9]")) {
                                System.out.println("Name entries should not have numbers");
                                return;
                            }
                            break;

                        case 4:
                            if (!k.matches("[A-Za-z0-9]")) {
                                System.out.println("Name entries should not have numbers");
                                return;
                            }
                            break;
                        default:
                            break;

                        case 8:
                            if(!k.matches("[A-Z]{3}")){
                                System.out.println("Team entries must be three capital letters");
                            }

                    }

                    if (i >= 4 && i <= 7) {
                        if (!k.matches("[0-9]")) {
                            System.out.println("Please enter a valid number");
                            return;
                        }
                    }




                    builder.append(k);
                    if (i != parameters.length - 1) {
                        builder.append(",");

                    }

                }
                builder.append('\n');
                bufferedWriter.append(builder.toString());


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null && bufferedWriter != null)
                try {
                    bufferedWriter.close();
                    fileWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    private static void load(String s) throws IOException {

        if (!s.contains(".txt")) {
            System.out.println("Please add a .txt to the end of your file");
            return;
        }
        String p = absolutePath + "/" + s;

        BufferedReader reader;
        File file = new File(s);
        FileReader fR = null;
        if (file.exists()) {
            fR = new FileReader(file);
            reader = new BufferedReader(fR);
            try {
                String line = reader.readLine();
                while (line != null) {
                    line.replace(",", " ");
                    System.out.println(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fR != null) {
                    fR.close();
                }
                if (reader != null)
                    reader.close();
            }


        } else {
            file.createNewFile();
        }


    }

    private static void print(String a) throws IOException {
        File file = new File(a);
        BufferedReader bufferedReader = null;
        FileReader fR = null;
        if (file.exists()) {

            try {
                fR = new FileReader(file);
                bufferedReader = new BufferedReader(fR);
                String line = bufferedReader.readLine();
                while (line != null) {
                    System.out.println(line.replace(",", " "));
                    line = bufferedReader.readLine();
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null)
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (fR != null) {
                    fR.close();
                }
            }


        } else {
            System.out.println("File does not exist");

        }


    }


    private static ArrayList<String> query(String query, String fileName) {
        File file = new File(fileName);
        ArrayList<String> list = new ArrayList<>();
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line != null) {
                    if (line.contains(query)) {
                        list.add(line);
                    }

                    line = bufferedReader.readLine();
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");

        }

        return list;
    }

    public static int highest(String f, String year) {
        File file = new File(f);
        int h = 0;
        ArrayList<String> values = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();

            while (line != null) {
                if (line.contains(year)) {
                    values.add(line);
                    line = bufferedReader.readLine();
                } else {
                    line = bufferedReader.readLine();
                }

            }

            for (String s : values) {
                String[] data = s.split(",");
                int y = Integer.parseInt(data[4]);
                if (y > h) {
                    h = y;
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return h;
    }

    public static void main(String[] args) {
        P1 p1 = new P1();
        p1.run();


    }
}
