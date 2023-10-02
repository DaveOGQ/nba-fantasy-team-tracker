package demo3.app.demo3;

/**
 * Player class, each object is with its own set of stats, and comparable by overall efficiency
 * @Author David Oti-George
 * @Version 1.0
 */
public class Player implements Comparable<Player>{
    private final String playerName;
    private  String TeamName;
    private static int ID = 1;
    private int playerID;
    private int PPG = 0;
    private int APG = 0;
    private int RPG = 0;
    private int MPG = 0;

    /**
     * Class called Player which will allow us to create player objects. A player has a name, teamname and an ID.
     * @param playerName A string value of a players name
     * @param TeamName A string value of the team the player is on
     */
    public Player(String playerName, String TeamName ){
        this.playerName = playerName;
        this.TeamName = TeamName;
        this.playerID = ID;
        ID++;
    }

    /**
     * Sets the APG of a player
     * @param APG  an integer value that represents the assists per game a player has
     */
    public void setAPG(int APG) {
        this.APG = APG;
    }

    /**
     * Gets the APG of a player
     * @return returns the APG of a player
     */
    public int getAPG() {
        return APG;
    }

    /**
     * Sets the RPG of a player
     * @param RPG an integer value that represents the rebounds per game a player has
     */
    public void setRPG(int RPG) {
        this.RPG = RPG;
    }

    /**
     * Gets the RPG of a player
     * @return returns the RPG of a player
     */
    public int getRPG() {
        return RPG;
    }

    /**
     * Sets the MPG of a player
     * @param MPG an integer value that represents the minutes per game a player has
     */
    public void setMPG(int MPG) {
        this.MPG = MPG;
    }

    /**
     * Gets the MPG of a player
     * @return returns the MPG of a player
     */
    public int getMPG() {
        return MPG;
    }

    /**
     * Sets the PPG of a player
     * @param PPG an integer value that represents the points per game a player has
     */
    public void setPPG(int PPG) {
        this.PPG = PPG;
    }

    /**
     * Gets the PPG of a player
     * @return returns the PPG of a player
     */
    public int getPPG() {
        return PPG;
    }

    /**
     * Calculates a players efficiency per game by adding their PPG,APG,RPG and then dividing by the MPG. EPG is used to distinguish which player will be the MVP
     * @return if the MPG is greater than 0 the EPG of the player will be returned otherwise zero will be returned
     */
    public double calcEPG(){
        if(MPG > 0){
            return 1.0 * (PPG + RPG + APG) / MPG;
        }
        else{
            return 0.0;
        }
    }

    /**
     * Gets the name of a player
     * @return A string value of a players name
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Creating a string table which represents the properties of a player in your league
     * @return a table with a players name,id,PPG,APG,RPG,MPG and efficiency (EPG)
     */
    @Override
    public String toString() {
        return "Player Name:" + playerName + '\n' +
                "ID:" + playerID + '\n' +
                "PPG:" + PPG + '\n' +
                "APG:" + APG + '\n' +
                "RPG:" + RPG + '\n' +
                "MPG:" + MPG + '\n' +
                "Efficiency:" + this.calcEPG()
                ;
    }

    /**
     * Compares two players based on the player with the higher efficiency rating
     * @param o other player being compared to this player
     * @return the integer difference of the two players efficency rating
     */
    @Override
    public int compareTo(Player o) {
        return (int) (o.calcEPG() - (int) this.calcEPG());
    }
}
