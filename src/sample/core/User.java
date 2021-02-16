package sample.core;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    /**
     *
     */
    private Date dateOfCreation;
    /**
     *
     */
    private String firstName;
    /**
     *
     */
    private String lastName;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private Date dateOfBirth;
    /**
     *
     */
    private String phoneNumber;
    /**
     *
     */
    private String ssn;
    /**
     *
     */
    private String hashedPass;




    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param dateOfBirth
     * @param phoneNumber
     * @param ssn
     * @param hashedPass
     */
    public User(String firstName, String lastName, String email, Date dateOfBirth, String phoneNumber, String ssn, String hashedPass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.ssn = ssn;
        this.hashedPass = hashedPass;
        dateOfCreation = new Date();
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getssn() {
        return ssn;
    }

    public void setSsN(String ssN) {
        this.ssn = ssN;
    }

    public String gethashedPass() {
        return hashedPass;
    }

    public void sethashedPass(String hashedPass) {
        this.hashedPass = hashedPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(dateOfCreation, user.dateOfCreation) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(ssn, user.ssn) &&
                Objects.equals(hashedPass, user.hashedPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfCreation, firstName, lastName, email, dateOfBirth, phoneNumber, ssn, hashedPass);

    }
}
