# nba-fantasy-team-tracker
Java Application allowing users to build a fantasy league populated with their own teams and players and review statistics 

How to Run Jar File:
1. Open CMD at the location of the jar File
2. Run the Jar file using (java --module-path "location of the JavaFX library" --add-modules javafx.controls,javafx.fxml -jar Name.jar)
3. This will run the Jar File
---
The Fantasy Team Editor and Tracker is capable of the following:
1. Adding a Team
    1. Enter the name of your Team
    2. Enter the wins of your Team
    3. Click Add Team/Update Team Wins to Create your team
    4. If your Team already exists clicking add team will upadate the number wins for that team
2. Adding a Player to a Team
    1. Enter your player's Name
    2. Enter you player's Team Name, must be an existing team 
    3. Enter the PPG, APG, RPG and MPG for your player
    4. Click Add/Update Player to Add your Player
    5. If your Player already exists the current Stats in the textfields will replace the player's current stats
3. Displaying the details of a Team
    1. Enter the team's name
    2. click view details in order to view the team's name, wins, and roster
4. Displaying the details and stats of a Player]
    1. Enter the player's name 
    2. click view stats to view the stats of that players
5. Displaying all current teams in your League
6. Showing Most Valuable Player in your League
7. Showing the best Team in your League
8. Saving a Team to a csv .txt team file
    1. Enter the name of the team you want to save and save it with whatever name you desire and in which whichever directory
9. Loading a Team from a csv .txt team file
    1. In the file menu click load team and chose the csv .txt team file you want to load
10. About
    1. In the file menu click about to view info about the authors and the gui platform
