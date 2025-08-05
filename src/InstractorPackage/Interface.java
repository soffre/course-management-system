package InstractorPackage;

import AdminPackage.instructorPojo;
import AdminPackage.studentPojo;
import CoursePackage.coursePojo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.SimpleTimeZone;
import java.util.Vector;

public interface Interface extends Remote {
    public boolean addResourseToDB(int c_id, byte[] fileBytes, String fileType, String fileTitle, String fileDescription , String link) throws RemoteException;
    public void sendEmailNotification(int cid , String emailAddress, String password)throws RemoteException;
    public javax.swing.table.DefaultTableModel populateAssignCoursesToInstructor(String username)throws RemoteException;
    public javax.swing.table.DefaultTableModel populateTable(int courseid)throws RemoteException;
    public javax.swing.table.DefaultTableModel populateInstructorByQualification(String qulif)throws RemoteException;
    public String getRoleOfUser(String username, String password)throws RemoteException;
    public javax.swing.JPanel showPanel() throws RemoteException;
    public Vector getCourseDepartment()throws RemoteException;
    public Vector getStudentDepartment()throws RemoteException;
    public String enrollStudentToCourse(int id, String sDeprt, String sBatch, String sSemes, int year)throws RemoteException;
    public javax.swing.table.DefaultTableModel courseByDepartement(String deprt)throws RemoteException;
    public Boolean isUserNameExist(String username)throws RemoteException;
    public studentPojo fetchStudentList(int id)throws RemoteException;
    public  javax.swing.table.DefaultTableModel populateDroppedStudent()throws RemoteException;
    public  javax.swing.table.DefaultTableModel populateDroppedStudentByCID(int id)throws RemoteException;
    public  javax.swing.table.DefaultTableModel populateEnrolledStudentByCID(int id)throws RemoteException;
    public  javax.swing.table.DefaultTableModel populateEnrolledStudent()throws RemoteException;
    public coursePojo fetchCourseList(int id)throws  RemoteException;
    public String addDroppedStudent(int cid, int sid)throws RemoteException;
    public String DroppedStudent(int cid, int sid)throws RemoteException;
    public int generateID(int digit)throws RemoteException;
    public String deleteStudent(int id)throws RemoteException;
    public String deleteInstructor(int id)throws RemoteException;
    public instructorPojo fetchInstructorList(int id)throws RemoteException;
    public String updateInstructor(int id, String fn, String ln, String dob, String gender, String address, String email,
                                   String nation, String qulif)throws RemoteException;
    public String updateCourse(int id, String name, int hr, String status, String dept, int pre, String start, String end,
                                String desc, int cap)throws RemoteException;
    public String deleteCourse(int id)throws RemoteException;
    public String updateStudent(int id, String fn, String ln, String dob, String gender, String address, String email,
                                String nation, String dept, String batch, String semes, int year)throws RemoteException;
    public String registerStudent(int studid, String stufname, String stulname, String formattedDate,
                                  String stugender, String stuemail, String stuaddress, String stunatio,String stuuname, String stupass,
                                  String studept, String stubatch, String stusemes, int year )throws  RemoteException;
    public String registerInstructor(int id, String fname, String lname, String dob, String gender, String address, String email,
                                     String nation, String qulif ,String uname,String pass)throws RemoteException;
    public String createCourse(int id, String name,int hr, String status, String dept, int pre, String start,
                                  String end, String desc , int capc)throws RemoteException;
    public javax.swing.table.DefaultTableModel populateCourse()throws  RemoteException;
    public String assignCourses(int cid, int instid)throws RemoteException;
    public javax.swing.table.DefaultTableModel populateStudentByDepartement(String dept, String batch)throws RemoteException;


}
