package NBADATABASe;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Pablo on 1/23/2017.
 */
public class NBADatabase {
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
        String fileName = "coaches";

        public Coaches() {

        }


        public void addCoaches(String... parameters) {
            add(fileName, parameters);

        }

        public void printCoaches() {
            //// TODO: 1/24/2017 open coaches.txt and print information to console

        }


        public void searchCoaches(String[] parameter) throws ArrayIndexOutOfBoundsException {
            ArrayList<Queries> queries = new ArrayList<>();
            //// TODO: 1/24/2017 print by parameter passed
            for (String s : parameter) {
                String[] t = s.split("=");
                queries.add(new Queries(t[0], t[1]));


            }

            File file = new File(fileName);
            try {

                for(Queries q: queries) {

                    //search through the file by each query. O(N)^2
                    //does print duplicate values
                    BufferedReader buffered = new BufferedReader(new FileReader(file));
                    String line = buffered.readLine();
                    while (line != null) {
                        if (line.contains(q.getValue())){
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
            }
        }

        public void bestCoach(String s) {
            // TODO: 1/24/2017 best coach printed
            highest(fileName, s);
        }

        public void coachName(String s){
            ArrayList<String> val = query(s, fileName);
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
        private String fileName = "teams";


        public void addTeam(String[] parameters) {
            //// TODO: 1/24/2017 add team members and do regex checks
            add(fileName, parameters);

        }

        public void printTeams() {
            //// TODO: 1/24/2017 print teams from database
            print(this.fileName);

        }

        public void teamsByCity(String s) {
            //// TODO: 1/24/2017 print teams by city
            query(fileName, s);
        }

        public void loadTeams(String fileName) {
            // TODO: 1/24/2017 load all teams
            this.fileName = fileName;
            try {
                load(fileName);
            } catch (FileNotFoundException e) {

            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }


    private static void add(String s, String[] parameters) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(s.concat(".txt"));
             fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder builder = new StringBuilder();
            if (file.createNewFile()) {


                for (int i = 0; i < parameters.length; i++) {
                    String k = parameters[i];
                    builder.append(k);
                    builder.append(",");


                }
                builder.append('\n');
                fileWriter.write(builder.toString());
                fileWriter.close();
            } else {
                 fileWriter = new FileWriter(file, true);
                for (int i = 0; i < parameters.length; i++) {
                    if (i == 0) {
                        // TODO: 1/24/2017 check the id to see if it matches
                    }
                    String k = parameters[i];
                    builder.append(k);
                    builder.append(",");


                }
                builder.append('\n');
                bufferedWriter.write(builder.toString());


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter!=null && bufferedWriter!=null)
                try {
                    fileWriter.close();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    private static void load(String s) throws IOException {
        File file = new File(s.concat("txt"));
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            try {
                String line = reader.readLine();
                while (line != null) {
                    line.replace(",", " ");
                    System.out.println(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            file.createNewFile();
        }


    }

    private static void print(String a) {

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
