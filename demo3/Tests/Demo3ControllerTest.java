import demo3.app.demo3.Demo3Controller;
import demo3.app.demo3.Player;
import demo3.app.demo3.Team;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functions within the controller to ensure that they function correctly
 * @Authors David-Oti-George,Maad Abassi
 * @Version
 */
class Demo3ControllerTest {
    @Test
    public void teamFileReader() {
        ArrayList<Team> League = new ArrayList<Team>();
        ArrayList<Player> pastplayer = new ArrayList<>();
        File file = new File("Lakers.txt");
        ArrayList[] readList = Demo3Controller.teamFileReader(file,League,pastplayer);
        pastplayer = readList[0];
        League = readList[1];
        Team team1 = League.get(0);
        assertEquals(team1.getTeamWins(), 13);
        Player player = team1.getPlayer("David");
        assertEquals(player.getPPG(), 24);
        assertEquals(player.getAPG(), 7);
        assertEquals(player.getRPG(), 8);
        assertEquals(player.getMPG(), 23);
    }

    @Test
    /**
     * The purpose of this test is to see which team is the best team based on number of wins. Test should pass if best team is team with most wins
     */
    void BestTeam() {
        ArrayList<Team> League = new ArrayList<Team>();
        Team team1 = new Team("Lakers");
        Team team2 = new Team("Nuggets");
        Team team3 = new Team("Suns");
        League.add(team1);
        League.add(team2);
        League.add(team3);
        team1.setTeamWins(11);
        team2.setTeamWins(24);
        team3.setTeamWins(8);
        Collections.sort(League);
        assertEquals(League.get(0).getTeamName(), "Nuggets");
    }


    @Test
    /**
     * The purpose of this test is to view the mvp on a team. The test should return pass if the player with the higher EPG is returned
     */
    void viewTeamMVP() {
        ArrayList<Team> League = new ArrayList<Team>();
        Team team1 = new Team("Lakers");
        Team team2 = new Team("Nuggets");
        Team team3 = new Team("Suns");
        League.add(team1);
        League.add(team2);
        League.add(team3);
        Player player1 = new Player("David", "Lakers");
        player1.setMPG(15);
        player1.setPPG(35);
        Player player2 = new Player("Maad", "Lakers");
        player2.setMPG(15);
        player2.setPPG(20);
        team1.addPlayer(player1);
        team1.addPlayer(player2);
        assertEquals(team1.getTeamMVP().getPlayerName(), "David");
    }


    @Test
    /**
     *  The purpose of this test is to view the mvp on a team when no players exist. Test should pass if null is returned
     */
    void viewTeamMVP2() {
        ArrayList<Team> League = new ArrayList<Team>();
        Team team1 = new Team("Lakers");
        Team team2 = new Team("Nuggets");
        Team team3 = new Team("Suns");
        League.add(team1);
        League.add(team2);
        League.add(team3);

        assertNull(team1.getTeamMVP());
    }

    @Test
    /**
     * The purpose of this test is to view the mvp on a team. The test should return pass if the player with the higher EPG is returned
     */
    void MVPProjection() {
        ArrayList<Player> Players = new ArrayList<>();
        Player player1 = new Player("David", "Lakers");
        player1.setMPG(15);
        player1.setPPG(35);
        Player player2 = new Player("Maad", "Lakers");
        player2.setMPG(15);
        player2.setPPG(20);
        Players.add(player1);
        Players.add(player2);
        Collections.sort(Players);
        assertEquals(Players.get(0).getPlayerName(), "David");
    }

    /**
     * The purpose of this test is to check if the correct PPG is returned for a player. The test should pass if the correct PPG is returned
     */
    @Test
    void getPPG(){
        Player player1 = new Player("David", "Lakers");
        player1.setMPG(15);
        player1.setPPG(35);
        assertEquals(player1.getPPG(), 35);
    }

    @Test
    /**
     * The purpose of this test is to check if the correct APG is returned for a player. The test should pass if the correct APG is returned
     */
    void getAPG(){
        Player player1 = new Player("David", "Lakers");
        player1.setMPG(15);
        player1.setAPG(35);
        assertEquals(player1.getAPG(), 35);
    }

    @Test
    /**
     * The purpose of this is to check if the correct RPG is returned for a player. The test should pass if the correct RPG is returned
     */
    void getRPG(){
        Player player1 = new Player("David", "Lakers");
        player1.setMPG(15);
        player1.setRPG(35);
        assertEquals(player1.getRPG(), 35);
    }

    @Test
    /**
     * The purpose of this test is to check if the correct MPG is returned for a player. The test should pass if the correct MPG is returned
     */
    void getMPG(){
        Player player1 = new Player("David", "Lakers");
        player1.setMPG(15);
        assertEquals(player1.getMPG(), 15);
    }

    @Test
    /**
     * The purpos eof this test is to check if the correct EPG is calculated with the given values. The test should return true if the correct EPG is returned
     */
    void calcEPG(){
        Player player1 = new Player("David", "Lakers");
        player1.setPPG(15);
        player1.setAPG(15);
        player1.setRPG(15);
        player1.setMPG(15);
        assertEquals(player1.calcEPG(), 3);
    }

    @Test
    /**
     * The purpose of this test is to check if the MPG of a player is at 0 then there EPG should then be 0. The test returns true if EPG is 0
     */
    void calcEPGZero(){
        Player player1 = new Player("David", "Lakers");
        player1.setPPG(15);
        player1.setAPG(15);
        player1.setRPG(15);
        player1.setMPG(0);
        assertEquals(player1.calcEPG(), 0);
    }

    @Test
    /**
     * The purpose of this test is to make sure you get the correct name when using getTeamName function. Test should pass if correct team name is given
     */
    void getTeamName(){
        Team team = new Team("Lakers");
        assertEquals(team.getTeamName(), "Lakers");
    }

    @Test
    /**
     * The purpose of this test is to make sure you get the correct amount of team wins when using getTeamWins function. Test should pass if correct number of winns for a team is given
     */
    void getTeamWins(){
        Team team = new Team("Lakers");
        team.setTeamWins(15);
        assertEquals(team.getTeamWins(), 15);
    }

    @Test
    /**
     * The purpose of this test to make sure that the Player.toString() is printing all values correctly. Test should pass if player to string is same as expected stirng
     */
    void playerToString(){
        Player player1 = new Player("David", "Lakers");
        player1.setPPG(15);
        player1.setAPG(15);
        player1.setRPG(15);
        player1.setMPG(15);
        String expectedString = """
                Player Name:David
                ID:1
                PPG:15
                APG:15
                RPG:15
                MPG:15
                Efficiency:3.0""";
        assertEquals(player1.toString(), expectedString);
    }

    @Test
    /**
     * The purpose of this test is to make sure that the Team.toString() is printing all values correctly. Test should pass if Team.toString() is same as expected toString().
     */
    void teamToString(){
        Team team = new Team("Lakers");
        team.setTeamWins(15);
        Player player1 = new Player("David", "Lakers");
        player1.setPPG(15);
        player1.setAPG(15);
        player1.setRPG(15);
        player1.setMPG(15);
        team.addPlayer(player1);
        String expectedString = """
                TeamName:Lakers Wins: 15
                Roster:
                David""";
        assertEquals(team.toString(), expectedString);
    }

    @Test
    void LeagueToString(){
        ArrayList<Team> League = new ArrayList<Team>();
        Team team = new Team("Lakers");
        team.setTeamWins(15);
        League.add(team);
        assertEquals("1. Lakers Wins: 15\n", Demo3Controller.LeagueToString(League));
    }
}