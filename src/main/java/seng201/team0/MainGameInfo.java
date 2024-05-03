package seng201.team0;

/**
 * Class which stores information about the current game session.
 * @author Hannah Grace, Elise Newman
 */

public class MainGameInfo {
    private Integer difficulty;
    private String name;
    private Integer numRounds;
    private Integer currentRound;
    private Integer remainingRounds;

    public Integer getDifficulty(){
        return difficulty;
    }

    public String getName(){
        return name;
    }

    public Integer getNumRounds(){
        return numRounds;
    }

    public Integer getCurrentRound(){
        return currentRound;
    }

    public void updateRounds(){
        currentRound += 1;
        remainingRounds -= 1;
        //should it do anything if numRounds exceeded?
    }
    public Integer getRemainingRounds(){
        return remainingRounds;
    }


}
