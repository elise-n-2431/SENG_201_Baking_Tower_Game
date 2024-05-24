package seng201.team48.models;

import java.util.List;

/**
 * This class is the blueprint for bowl ('cart') objects which must be filled by player during gameplay.
 * @author Hannah Grace
 */
public class Bowl {
    private Integer capacity = 0;
    private Integer currentIndex = 0;
    private String size;
    private Boolean fullBowl = Boolean.FALSE;
    private List<Tower> filled;

    /**Creates a bowl object
     * Bowl is used in MainScreen and created by BowlService
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

    /**Adds tower to bowl
     * @param content is the tower object and everything it contains
     */
    public void addToBowl(Tower content) {
        filled.set(currentIndex, content);
        if ((currentIndex + 1) != capacity) {
            currentIndex += 1;
        } else {
            setFullBowl(Boolean.TRUE);
            currentIndex = 0;
        }
    }

    /**Sets boolean value for fullBowl
     * @param trueOrFalse is the new value, true = full
     */
    public void setFullBowl(Boolean trueOrFalse) {
        fullBowl = trueOrFalse;
    }

    /**Resets the bowl by setting each tower to null
     */
    public void setEmpty(){
        for(int i = 0; i < capacity; i++){
            filled.set(i, null);
        }
        currentIndex = 0;
        fullBowl = false;
    }

    /**Checks value of fullBowl and returns boolean
     * @return fullBowl
     */
    public Boolean getFullBowl(){
        return fullBowl;
    }

    /**Get all items stored in bowl
     * @return filled list
     */
    public List<Tower> getFilled(){
        return filled;
    }

    /**Get the capacity of the bowl
     * @return capacity Integer
     */
    public Integer getCapacity(){
        return capacity;
    }

    /**Get size of bowl, "Small" or "Large"
     * @return size String
     */
    public String getSize(){
        return size;
    }
}
