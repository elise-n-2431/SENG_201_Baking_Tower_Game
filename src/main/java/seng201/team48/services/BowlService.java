package seng201.team48.services;

import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.models.Bowl;
import seng201.team48.models.Tower;

import java.util.ArrayList;
import java.util.List;

public class BowlService {
    Integer numSmallBowlsSelected = 0;
    Integer numLargeBowlsSelected = 0;
    Integer numSmallBowlsSent = 0;
    Integer numLargeBowlsSent = 0;
    TowerManager towerManager = new TowerManager();


    public void setNumBowlsSelected(Integer small, Integer large){
        numSmallBowlsSelected = small;
        numLargeBowlsSelected = large;
    }
    public Boolean getBowlsUsed(){
        //checks if all the bowls have been sent
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
    public Bowl getNewBowl(){
        // bowl is called for if not all have been used
        if (numSmallBowlsSelected.equals(numSmallBowlsSent)){
            //send a large bowl
            List<Tower> newFilled = new ArrayList<Tower> (5);
            newFilled.add(null);
            newFilled.add(null);
            newFilled.add(null);
            newFilled.add(null);
            newFilled.add(null);
            numLargeBowlsSent += 1;
            return new Bowl(5, "Large", newFilled);
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
    public Integer getNumBowlsSent(){
        return numSmallBowlsSent + numLargeBowlsSent;
    }
    public Integer getNumBowlsSelected(){
        return numSmallBowlsSelected + numLargeBowlsSelected;
    }

}
