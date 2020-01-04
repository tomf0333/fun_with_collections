/**
 * a class for objects that have String values and a member telling if they were deleted or not.
 */
public class SuperString {

    private String value;

    private boolean deleted;

    /**
     * default constructor makes a SuperString which is null bot not deleted.
     */
    public SuperString(){
        this.value = null;
        this.deleted = false;
    }

    /**
     * create a new SuperString with the given value, start off as not deleted obviously.
     *
     * @param value -   the String value of the SuperString.
     */
    public SuperString(String value){
        this.value = value;
        this.deleted = false;
    }

    /**
     * @return  the String Value of the Super String.
     */
    public String getValue(){
        return this.value;
    }

    /**
     * @return  true if this string was deleted and false otherwise.
     */
    public boolean isDeleted(){
        return this.deleted;
    }

    /**
     * makes a SuperString deleted by turning its value to null and marking it as deleted.
     */
    public void setDeleted(){
        this.value = null;
        this.deleted = true;
    }
}
