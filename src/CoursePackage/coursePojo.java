package CoursePackage;

import java.io.Serializable;
import java.util.Date;

public class coursePojo implements Serializable {
    int id, cHR,cCap,cPre;
    String name, cStatus, cDept,cDesc;
    Date cStart, cEnd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getcHR() {
        return cHR;
    }

    public void setcHR(int cHR) {
        this.cHR = cHR;
    }

    public int getcCap() {
        return cCap;
    }

    public void setcCap(int cCap) {
        this.cCap = cCap;
    }

    public int getcPre() {
        return cPre;
    }

    public void setcPre(int cPre) {
        this.cPre = cPre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getcDept() {
        return cDept;
    }

    public void setcDept(String cDept) {
        this.cDept = cDept;
    }

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public Date getcStart() {
        return cStart;
    }

    public void setcStart(Date cStart) {
        this.cStart = cStart;
    }

    public Date getcEnd() {
        return cEnd;
    }

    public void setcEnd(Date cEnd) {
        this.cEnd = cEnd;
    }

    public coursePojo(){
    this.id = id;
    this.name = name;
    this.cCap = cCap;
    this.cEnd = cEnd;
    this.cStart = cStart;
    this.cDept = cDept;
    this.cPre = cPre;
    this.cHR = cHR;
    this.cStatus = cStatus;
    this.cDesc = cDesc;
    }
}
