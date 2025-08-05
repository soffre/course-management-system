package AdminPackage;

import com.sun.tools.attach.AgentInitializationException;

import java.io.Serializable;
import java.util.Date;

public class instructorPojo implements Serializable {
    String fName,lName,gender,address,email,nation,qualif;
    Date dob;
    int id;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getQualif() {
        return qualif;
    }

    public void setQualif(String qualif) {
        this.qualif = qualif;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public instructorPojo(){

        this.dob = dob;
        this.id = id;
        this.address = address;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.gender = gender;
        this.nation = nation;
        this.qualif = qualif;
    }
}
