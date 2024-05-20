package seng201.team48.models;

import java.util.ArrayList;
import java.util.List;

public class Bowl {
    private final Integer capacity;
    private Integer currentIndex = 0;
    private Boolean fullBowl = Boolean.FALSE;
    private List<Tower> filled;
    public Bowl(Integer newCapacity, List<Tower> newFilled){
        this.capacity = newCapacity;
        this.filled = newFilled;
        fullBowl = false;
    }
    public void addToBowl(Tower content){
        filled.set(currentIndex, content);
        if((currentIndex + 1) != capacity) {
            currentIndex += 1;
        } else{
            setFullBowl(Boolean.TRUE);
            currentIndex = 0;
        }
    }
    public void setFullBowl(Boolean trueOrFalse) {
        fullBowl = trueOrFalse;
    }
    public Boolean getFullBowl(){return fullBowl;}
    public List<Tower> getFilled(){
        return filled;
    }
    public Integer getCapacity(){
        return capacity;
    }
}
