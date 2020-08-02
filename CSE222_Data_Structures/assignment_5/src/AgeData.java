/**
 * AgeData class to handle both age and number of people at that age values.
 * I should keep instances of AgeData in my tree
 * It implements DataInterface because i designed more object-oriented way to
 * store all data and use it generic way
 * @author Türker Tercan
 */
public class AgeData implements DataInterface<AgeData> {
    /** Hold the age of people */
    public int age;
    /** How many people are there in that age */
    public int people;

    /**
     * Basic constructor
     * @param age given age
     */
    public AgeData(int age) {
        this.age = age;
        people = 1;
    }

    /**
     * Checks if the element has 1 people
     * @return true if people is equal to 1; otherwise, false
     */
    @Override
    public boolean isAlone() {
        return people == 1;
    }

    /**
     * Increase people by one
     */
    @Override
    public void increase() {
        people++;
    }

    /**
     * Decrease people by one
     */
    @Override
    public void decrease() {
        people--;
    }

    /**
     * Compares two object
     * @param o object that will compared
     * @return extraction of two elements
     */
    @Override
    public int compareTo(AgeData o) {
        return age - o.age;
    }

    /**
     * Returns people
     * @return people
     */
    @Override
    public int getCount() {
        return people;
    }

    /**
     * Returns age
     * @return age
     */
    @Override
    public int getValue() {
        return age;
    }

    /**
     * To convert the data fields to String
     * @return String contains two data fields
     */
    public String toString() {
        return age + " - " + people;
    }
}
