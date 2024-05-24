package seng201.team48.services;

import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.models.Bowl;
import seng201.team48.models.Tower;

import java.util.ArrayList;
import java.util.List;

/**Creates bowl objects based on the round difficulty and number of bowls already sent
 * Can create large or small bowls, small bowls are sent first.
 * @author Hannah Grace, Elise Newman
 */
public class BowlService {
    Integer numSmallBowlsSelected = 0;
    Integer numLargeBowlsSelected = 0;
    Integer numSmallBowlsSent = 0;
    Integer numLargeBowlsSent = 0;
    TowerManager towerManager = new TowerManager();

    /**Sets the bowls based on difficulty at the start of a round
     * @param small is the number of small bowls to be sent
     * @param large is the number of large bowls to be sent
     */
    public void setNumBowlsSelected(Integer small, Integer large){
        numSmallBowlsSelected = small;
        numLargeBowlsSelected = large;
    }

    /**Checks if all the bowls selected have been sent
     * @return boolean where true means all have been sent
     */
    public Boolean getBowlsUsed(){
        if (numLargeBowlsSelected.equals(numLargeBowlsSent)){
            if (numSmallBowlsSelected.equals(numSmallBowlsSent)){
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } else{
            return Boolean.FALSE;
        }
    }

    /**Creates a list of null tower objects based on the size of the bowl required, small or large
     * The list is used to create a Bowl object
     * @return new Bowl object
     */
    public Bowl getNewBowl(){
        // bowl is called for if not all have been used
        if (numSmallBowlsSelected.equals(numSmallBowlsSent)){
            if(numLargeBowlsSelected.equals(numLargeBowlsSent)){
                numSmallBowlsSent = 0;
                numLargeBowlsSent = 0;
                return getNewBowl();
            } else {
                //send a large bowl
                List<Tower> newFilled = new ArrayList<Tower>(5);
                newFilled.add(null);
                newFilled.add(null);
                newFilled.add(null);
                newFilled.add(null);
                newFilled.add(null);
                numLargeBowlsSent += 1;
                return new Bowl(5, "Large", newFilled);
            }
        } else{
            //send a small bowl
            List<Tower> newFilled = new ArrayList<Tower> (3);
            newFilled.add(null);
            newFilled.add(null);
            newFilled.add(null);
            numSmallBowlsSent += 1;
            return new Bowl(3, "Small", newFilled);
        }
    }

    /**Gets the number of bowls sent
     * number of bowls sent is made of small bowls sent + large bowls sent
     * @return numBowlsSent Integer
     */
    public Integer getNumBowlsSent(){
        return numSmallBowlsSent + numLargeBowlsSent;
    }

    /**Gets the number of bowls selected
     * number of bowls selected is made of small bowls selected + large bowls selected
     * @return numBowlsSelected Integer
     */
    public Integer getNumBowlsSelected(){
        return numSmallBowlsSelected + numLargeBowlsSelected;
    }

}
