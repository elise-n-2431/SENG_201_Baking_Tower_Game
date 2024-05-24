package seng201.team48.models;

import java.util.ArrayList;
import java.util.List;

public class Bowl {
    private Integer capacity = 0;
    private Integer currentIndex = 0;
    private String size;
    private Boolean fullBowl = Boolean.FALSE;
    private List<Tower> filled;

    /**Creates a bowl object
     * @param newCapacity is the number of towers the bowl can hold. Will be 3 or 5
     * @param newSize is the string representation of capacity. "Small" or "Large"
     * @param newFilled is the list of towers in the bowl, starts size = capacity, only contains null
     */
    public Bowl(Integer newCapacity, String newSize, List<Tower> newFilled){
        capacity = newCapacity;
        filled = newFilled;
        fullBowl = false;
        size = newSize;

    }

    /**
     *
     * @param content
     */
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

    public void setEmpty(){
        for(int i = 0; i < capacity; i++){
            filled.set(i, null);
        }
        currentIndex = 0;
        fullBowl = false;
    }
    public Boolean getFullBowl(){
        return fullBowl;
    }
    public List<Tower> getFilled(){
        return filled;
    }
    public Integer getCapacity(){
        return capacity;
    }
    public String getSize(){
        return size;
    }
}
