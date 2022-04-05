package diet;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public abstract class Diet {

    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }
}
