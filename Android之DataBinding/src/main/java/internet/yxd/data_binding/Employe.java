package internet.yxd.data_binding;

/**
 * Created by asus on 2017/12/20.
 */

public class Employe {

    private boolean isFired;
    private String firstName;
    private String lastName;

    public Employe() {
    }

    public Employe(boolean isFired, String firstName, String lastName) {
        this.isFired = isFired;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isFired() {
        return isFired;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }

    @Override
    public String toString() {
        return "Employe{" + "isFired=" + isFired + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
