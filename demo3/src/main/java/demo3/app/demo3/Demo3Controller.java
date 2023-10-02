package demo3.app.demo3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Connects actionable Gui events to functions running in the background
 * @Author David Oti-George
 * @Version 1.0
 */
public class Demo3Controller {
    private final ArrayList<Player> pastplayers = new ArrayList<>(); //A Player Arraylist with all the players of the league added into it
    private final ArrayList<Team> League = new ArrayList<>(); // A Team ArrayList with all the Teams of the league added into it
    private final ArrayList<String> teamNames = new ArrayList<>();
    private final ArrayList<String> playerNames = new ArrayList<>();
    @FXML
    private TextField saveTextField;

    @FXML
    private Label rightStatus;


    @FXML
    private Label leftStatus;

    @FXML
    private TextArea playerStats;

    @FXML
    private TextArea leagueInfo;

    @FXML
    private TextField playerAPG;

    @FXML
    private TextField playerMPG;

    @FXML
    private TextField playerName;

    @FXML
    private TextField playerPPG;

    @FXML
    private TextField playerRPG;

    @FXML
    private TextField playerTeam;

    @FXML
    private TextArea teamInfo;

    @FXML
    private TextField teamName;

    @FXML
    private TextField teamWins;

    @FXML
    private TextField viewPlayerLabel;

    @FXML
    private TextField viewTeamName;

    /**
     * Takes in a team name from the associated text field and displays the info about the team
     * @param event that viewDetails in clicked by the user
     */
    @FXML
    void ViewTeamDetailsClicked(MouseEvent event) {
        try{
            String rosterTeam = viewTeamName.getText();

            if(!teamNames.contains(rosterTeam)){
                throw new Exception();
            }

            for(Team team:League){
                if(team.getTeamName().equals(rosterTeam)){
                    teamInfo.setText(team.toString());
                }
            }
        }catch (Exception e){
            rightStatus.setText("Error Getting Team Details, check yor inputs!");
        }
    }

    /**
    * Displays a message with Author information and Gui Info
     * @param event is the event of pressing anything on the About Demo3 menu option
     */
    @FXML
    void about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Message");
        alert.setContentText("""
                Authors: David Oti-George
                Email: otigeorgedavid@gmail.com\s
                Version: v1.0
                This is a NBA Fantasy Team Tracker and Editor.
                Errors will be displayed on the bottom right.
                Successful actions will be displayed on the bottom left.""");
        alert.show();
    }

    /**
     *
     * @param League an arraylist containing all the teams added/created by user
     * @return the purpose of this function is to return a toString with the team name and the number of wins a team has.
     */
    public static String LeagueToString(ArrayList<Team> League){
        StringBuilder ToString = new StringBuilder();
        int TeamNum = 1;
        for(Team team : League){
            ToString.append(TeamNum + ". " + team.getTeamName() + " Wins: " + team.getTeamWins() + "\n");
            TeamNum+=1;
        }
        return ToString.toString();
    }

    /**
     * Allows user to add a team to their league
     * @param event the action of clicking on the add team button with your mouse
     */
    @FXML
    void addTeam(MouseEvent event) {
        try {
            String myTeam = teamName.getText();
//
            if (teamNames.contains(myTeam)) {
                for (Team team : League) {
                    if (team.getTeamName().equals(myTeam)) {
                        team.setTeamWins(Integer.parseInt(teamWins.getText()));
                    }
                }
            } else {
                Team team = new Team(myTeam);
                team.setTeamWins(Integer.parseInt(teamWins.getText()));
                League.add(team);
                teamNames.add(team.getTeamName());
                leftStatus.setText("Team " + myTeam + " has been added into your league");
            }
            leagueInfo.setText(LeagueToString(League));
        }catch (Exception e){
            rightStatus.setText("Something went wrong with adding your team check your inputs");
        }
    }

    /**
     * Allows user to add a player or update an already existing players stats
     * @param event is the action of clicking the add/update player button with your mouse
     */
    @FXML
    void addupdatePlayer(MouseEvent event) {
        try{
            String playername = playerName.getText();
            String playerteam = playerTeam.getText();

            //throw exception if team does not exist
            if(!teamNames.contains(playerteam)){
                throw new Exception();
            }

            if(playerNames.contains(playername)){
                for(Player player: pastplayers){
                    if (player.getPlayerName().equals(playername)) {
                        player.setAPG(Integer.parseInt(playerAPG.getText()));
                        player.setRPG(Integer.parseInt(playerRPG.getText()));
                        player.setMPG(Integer.parseInt(playerMPG.getText()));
                        player.setPPG(Integer.parseInt(playerPPG.getText()));
                    }
                }
                leftStatus.setText("Player " + playername  + "'s stats have been updated in team " + playerteam);
            }
            else {
                Player player = new Player(playername, playerteam);
                player.setAPG(Integer.parseInt(playerAPG.getText()));
                player.setRPG(Integer.parseInt(playerRPG.getText()));
                player.setMPG(Integer.parseInt(playerMPG.getText()));
                player.setPPG(Integer.parseInt(playerPPG.getText()));
                pastplayers.add(player);
                playerNames.add(playername);
                for (Team team : League) {
                    if (team.getTeamName().equals(playerteam)) {
                        team.addPlayer(player);
                    }
                }
                leftStatus.setText("Player " + playername  + " has been added to team " + playerteam);
            }
        }
        catch (Exception e){
            rightStatus.setText("An error has occurred while adding your player");
        }
    }

    private static final int playername = 0;
    private static final int PPG = 1;
    private static final int APG = 2;
    private static final int RPG = 3;
    private static final int MPG = 4;

    /**
     * Reads a file and take out the specified data and adds it into the league and players category
     * @param file the file chosen by the user
     */
    private void teamFileReader1(File file){
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String teamName = br.readLine();
            String teamWins = br.readLine();
            Team team = new Team(teamName);
            team.setTeamWins(Integer.parseInt(teamWins));
            String line = br.readLine();
            while (line!=null) {
                String[] arrinfo = line.split(",");
                Player player = new Player(arrinfo[playername], teamName);
                player.setPPG(Integer.parseInt(arrinfo[PPG]));
                player.setAPG(Integer.parseInt(arrinfo[APG]));
                player.setRPG(Integer.parseInt(arrinfo[RPG]));
                player.setMPG(Integer.parseInt(arrinfo[MPG]));
                team.addPlayer(player);
                pastplayers.add(player);
                line = br.readLine();
            }
            fr.close();
            League.add(team);
            leftStatus.setText("Team " + teamName + " added to your League!");
        } catch(IOException e){
            rightStatus.setText("Something went wrong with loading your team file check your inputs!");
        }
    }

    /**
     * opens file directory to allow user to save the file that they need to save and then reads the file and add a team
     * @param event event that a "load team" is clicked to load a team
     */
    @FXML
    void loadTeam(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("."));
        try {
            File file = fc.showOpenDialog(new Stage());
            if(file != null) {
                teamFileReader1(file);
                leftStatus.setText("File Successfully loaded!");
                leagueInfo.setText(LeagueToString(League));
            }
        }catch(Exception e){
            rightStatus.setText("Something went wrong with loading your file.");
        }

    }

    /**
     *Allows you to quit out of the application
     *@param event is the event of pressing anything on the quit menu option
     */
    @FXML
    void quit(ActionEvent event) {
     System.exit(1);
    }

    /**
     * Prints out a player to string for the specified player chosen by user to a text box
     * @param event the action of clicking the view stats button with your mouse
     */

    @FXML
    void viewStatsClicked(MouseEvent event) {
        try {
            String playerToShow = viewPlayerLabel.getText();
            //if player doesn't exist throw new exception
            if(!playerNames.contains(playerToShow)){
                throw new Exception();
            }

            for (Player player : pastplayers) {
                if (player.getPlayerName().equals(playerToShow)) {
                    playerStats.setText(player.toString());
                    leftStatus.setText("Player details shown successfully!");
                }
                leftStatus.setText("Player details shown successfully!");
            }
        }catch (Exception e){
            rightStatus.setText("Error getting Player details, check yor inputs!");
        }

    }

    /**
     * Allows you to write information of a team and its players to a file
     * @param file the file being written to
     * @param team the team that is saved
     * @param teamWins the number of wins the team which you are saving has
     */
    public  void teamFileSaver1(File file, Team team, int teamWins) {
        try{
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    rightStatus.setText("Error saving file, try Again!");
                }
            }
            if (file.exists() && file.isFile() && file.canWrite()) {
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter br = new BufferedWriter(fileWriter);
                    br.write(team.getTeamName() + "\n");
                    br.write(teamWins + "\n");
                    ArrayList<Player> roster = team.getRoster();
                    for (Player player : roster) {
                        String playerName = player.getPlayerName();
                        int playerPPG = player.getPPG();
                        int playerAPG = player.getAPG();
                        int playerRPG = player.getRPG();
                        int playerMPG = player.getMPG();
                        br.write(playerName + "," + playerPPG + "," + playerAPG + "," + playerRPG + "," + playerMPG + "\n");
                    }
                    br.flush();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                leftStatus.setText("Team Saved!");
            } else {
                rightStatus.setText("File is not valid");
            }
        }catch (Exception e){
            rightStatus.setText("Something went wrong with saving your file!");
        }
    }

    /**
     * **
     *Allows user to save file to their computer (save as)
     *@param event is the action of clicking save on the menu
     *
     * */
    @FXML
    void saveButtonCLicked(MouseEvent event) {
        try {
            FileChooser fc = new FileChooser();
            DirectoryChooser dc = new DirectoryChooser();
            dc.setInitialDirectory(new File("."));
            fc.setInitialFileName("");
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".txt", "*.txt"));//only .txt file can be accessed
            File file = fc.showSaveDialog(new Stage());
            String teamName = saveTextField.getText();
            for (Team team : League) {
                if (team.getTeamName().equals(teamName)) {
                    teamFileSaver1(file, team, team.getTeamWins());
                }
            }
        }catch (Exception e){
            rightStatus.setText("Error Saving your file, check that team exists!");
        }
    }

    /**
     * Creates a message displaying the mvp of a league
     * @param event the action of clicking the show mvp button with your mouse
     */
    @FXML
    void showMVP(MouseEvent event) {
        try {
//              Collections.sort(pastplayers); //Uses collections.sort to sort the Player arraylist pastplayers with the player at the front of the list having highest EPG while player in last spot has lowest EPG
            double  bestEpg = 0.0;
            Player best = null;
            for (Player player: pastplayers){
                if(player.calcEPG() > bestEpg){
                    best = player;
                    bestEpg = player.calcEPG();
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("League MVP!");
            alert.setHeaderText("Most Valuable Player in your league is");
            alert.setContentText(best.getPlayerName() + "!");
            alert.show();
        }catch (Exception e){
            rightStatus.setText("Error Showing MVP, ensure that you have players in your League!");
        }
    }

    /**
     * Creates a message displaying the best team in your league.
     * @param event the action of pressing anything over the show best team
     */

    @FXML
    void showBestTeam(MouseEvent event) {
        try {
            Collections.sort(League); //Uses collections.sort to sort the Player arraylist pastplayers with the player at the front of the list having highest EPG while player in last spot has lowest EPG
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("League Top Team!!");
            alert.setHeaderText("Best Team in your league is");
            alert.setContentText(League.get(0).getTeamName() + " with " + League.get(0).getTeamWins() + " Wins!");
            alert.show();
        }catch (Exception e){
            rightStatus.setText("Error Showing Best Team< ensure that you have Teams in your League");
        }
    }

    /**
     * The purpose of this function is to be able to read a file as a parameter and extract the needed information from it
     * @param file is the file that will be read
     * @param League is an Arraylist with Team inside of it (includes all properties of the team object)
     * @return returns the Team arraylist league
     */
    public static ArrayList[] teamFileReader(File file, ArrayList<Team> League, ArrayList<Player> pastplayer){
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String teamName = br.readLine();
            String teamWins = br.readLine();
            Team team = new Team(teamName);
            team.setTeamWins(Integer.parseInt(teamWins));
            String line = br.readLine();
            while (line!=null) {
                String[] arrinfo = line.split(",");
                Player player = new Player(arrinfo[playername], teamName);
                player.setPPG(Integer.parseInt(arrinfo[PPG]));
                player.setAPG(Integer.parseInt(arrinfo[APG]));
                player.setRPG(Integer.parseInt(arrinfo[RPG]));
                player.setMPG(Integer.parseInt(arrinfo[MPG]));
                team.addPlayer(player);
                pastplayer.add(player);
                line = br.readLine();
            }
            fr.close();
            League.add(team);
            System.out.println("Team " + teamName + " added to your League!");
        } catch(IOException e){
            e.printStackTrace();
        }
        return new ArrayList[] {pastplayer, League};
    }


    /**
     * The purpose of this function is to be able to write your data into a file
     * @param teamName the name of your team (this will be used as the name of the file)
     * @param team an object that represents different aspects about a team (team wins,teamname etc)
     * @param teamWins the amount of wins a team has
     */
    public static void teamFileSaver(String teamName,Team team, int teamWins){
        File file = new File(teamName + ".txt");
        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        if(file.exists() && file.isFile() && file.canWrite()){
            try{
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                fileWriter.write(teamName + "\n");
                fileWriter.write(teamWins + "\n");
                ArrayList<Player> roster = team.getRoster();
                for (Player player:roster){
                    String playerName = player.getPlayerName();
                    int playerPPG = player.getPPG();
                    int playerAPG = player.getAPG();
                    int playerRPG = player.getRPG();
                    int playerMPG = player.getMPG();
                    fileWriter.write(playerName + "," + playerPPG + "," + playerAPG + "," + playerRPG + "," + playerMPG);
                    fileWriter.flush();
                    fileWriter.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Team Saved!");
        }
        else {
            System.out.println("File is not valid");
        }
    }
}
