package graphics;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 18/04/2022
 */
public interface IAnimalBehavior {
    String getAnimalName();
    int getSize();
    void eatInc();
    int getEatCount();
    void setSuspended();
    void setResumed();
}
