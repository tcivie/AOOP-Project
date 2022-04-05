package diet;

/**
 * @author Gleb Tcivie
 * @Date 5/4/22
 */
public abstract class Diet {

    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }
}
