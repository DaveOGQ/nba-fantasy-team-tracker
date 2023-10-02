package demo3.app.demo3;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Team class, each object is with its own wins, roster comprised of players, and comparable by the number of wins
 * @Author David Oti-George, Amir Abassi
 * @Version 1.0
 */
public class Team implements Comparable<Team> {
    private final String TeamName;
    private int TeamWins = 0;
    private final ArrayList<Player> roster = new ArrayList<>(); //A Player arraylist which has the players of the league in it

    /**
     * Class called Team which will allow user to create Team objects. A team consists of only a team name
     * @param TeamName
     */
    public Team(String TeamName){
        this.TeamName = TeamName;
    }

    /**
     * Adds a player to the Player ArrayList roster
     * @param player an object Player which represents the player created by user
     */
    public void addPlayer(Player player){
        roster.add(player);
    }

    /**
     * Gets the object Player
     * @param playerName A string value of a players name
     * @return returns the player user inputted
     */
    public Player getPlayer(String playerName){
        Player player1 = new Player("Random Player Name", "Random Team Name");
        for(Player player : roster){
            if(player.getPlayerName().equals(playerName)){
                player1 = player;
            }
        }
        return player1;
    }

    /**
     * Sets a teams wins
     * @param teamWins an integer value representing the amount of wins a team has
     */
    public void setTeamWins(int teamWins) {
        TeamWins = teamWins;
    }

    /**
     * Gets a teams wins
     * @return returns an integer value representing the amount of wins a team has
     */
    public int getTeamWins(){
        return TeamWins;
    }

    /**
     * Gets a teams name
     * @return returns a string value representing a teams name
     */
    public String getTeamName() {
        return TeamName;
    }

    /**
     * Gets the MVP of a team
     * @return returns the MVP on a team based on the player with the highest EPG. If roster size is 0 functions returns null
     */
    public Player getTeamMVP(){
        if(roster.size() != 0) {
            Collections.sort(roster);
            return roster.get(0);
        }
        return null;
        //loop through array list for best epg calculation
    }

    /**
     * Gets the roster (players) of a team
     * @return returns the player array list roster
     */
    public ArrayList<Player> getRoster(){
        return roster;
    }

    /**
     * Creates a string table with a teams name and the teams roster underneath
     * @return returns a string table with team name and the teams roster
     */
    @Override
    public String toString() {
        StringBuilder builtRoster = new StringBuilder();
        for(Player player: roster){
            builtRoster.append("\n");
            builtRoster.append(player.getPlayerName());
        }
        return "TeamName: " + TeamName + "\nWins: " + TeamWins +
                "\nRoster:" + builtRoster;
    }

    /**
     * Compares two teams based on the player with the higher efficiency rating
     * @param o other team being compared to this team
     * @return the integer difference of the two teams wins
     */
    @Override
    public int compareTo(Team o) {
        return o.getTeamWins() - this.getTeamWins();
    }
}




