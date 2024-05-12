package seng201.team0;

/**
 * This class stores information about the current game session.
 * It manages holding state, GUI windows, and passing objects into controller classes.
 * @author Hannah Grace, Elise Newman
 */

public class MainGameInfo {
    private Integer gameDifficulty;
    private Integer roundDifficulty;
    private String name;
    private Integer numRounds;
    private Integer currentRound;
    private Integer remainingRounds;

    /**
     * Get game difficulty
     * @return gameDifficulty
     */
    public Integer getGameDifficulty(){
        return gameDifficulty;
    }

    /**
     * Get difficulty of current round
     * @return roundDifficulty
     */
    public Integer getRoundDifficulty() {
        return roundDifficulty;
    }

    /**
     * Get name of player
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Get number of rounds in current game (between 5 and 15)
     * @return numRounds
     */
    public Integer getNumRounds(){
        return numRounds;
    }

    /**
     * Get number of current round
     * @return currentRound
     */
    public Integer getCurrentRound(){
        return currentRound;
    }

    /**
     * Increase current round by 1, and decrease remaining rounds by 1. Called after round completion.
     */
    public void updateRounds(){
        currentRound += 1;
        remainingRounds -= 1;
        //should it do anything if numRounds exceeded?
    }

    /**
     * Get number of remaining rounds in current game.
     * @return remainingRounds
     */
    public Integer getRemainingRounds(){
        return remainingRounds;
    }


}
