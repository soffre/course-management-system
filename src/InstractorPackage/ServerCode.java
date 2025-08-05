package InstractorPackage;
        import AdminPackage.instructorPojo;
        import AdminPackage.studentPojo;
        import CoursePackage.coursePojo;
        import DriverPackage.*;
        import MainPackage.EmailSender;
        import MainPackage.UniqueRandomNumberGenerator;

        import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import java.rmi.RemoteException;
        import java.rmi.server.UnicastRemoteObject;
        import java.sql.ResultSet;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Vector;

public class ServerCode extends UnicastRemoteObject implements Interface {

    private ArrayList<String> recipientList = new ArrayList<>();
    String cname ="";
public ServerCode()throws RemoteException{}
@Override
public javax.swing.table.DefaultTableModel populateInstructorByQualification(String qulif){
    GlobalVariable gv = new GlobalVariable();
    DefaultTableModel model =null;
    String coulmns[] = {"ID", "First Name", "Last Name", "DOB", "Gender", "Address", "Email", "Nationality", "Qualification"};
    try{
        model = new DefaultTableModel();
        model.setColumnIdentifiers(coulmns);
        gv.conn = gv.c1.connect();
        String sql = "Select * from instructor where qualification = ? ";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1,qulif);
        ResultSet rs = gv.Stm.executeQuery();
        while (rs.next()){
            model.addRow(new Object[]{rs.getInt("instID"),rs.getString("First_name"),rs.getString("Last_name")
                    ,rs.getString("dob"),rs.getString("gender"),rs.getString("address"),rs.getString("email"),
                    rs.getString("nationality"),rs.getString("qualification")});
        }

    }catch (Exception e){
        JOptionPane.showMessageDialog(null,e);
    }finally {
        try {
            gv.Stm.close();
            gv.conn.close();
        }catch (Exception e){}
    }
    return model;
}
@Override
public javax.swing.table.DefaultTableModel populateStudentByDepartement(String dept, String batch){
    DefaultTableModel model = null;
    GlobalVariable gv = new GlobalVariable();
    try {
        String columns[] = {"ID", "First Name", "Last Name", "DOB", "Gender", "Address", "Email", "Nationality","Semester"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        gv.conn = gv.c1.connect();
        String sql = "Select * from student where stu_dept =? and stu_batch =?";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1,dept);
        gv.Stm.setString(2,batch);
        ResultSet rs = gv.Stm.executeQuery();
        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("studID"),
                    rs.getString("First_name"),
                    rs.getString("Last_name"),
                    rs.getString("dob"),
                    rs.getString("gender"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getString("nationality"),
                    rs.getString("stu_semes")
            });
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }finally {
        try {
            gv.Stm.close();
            gv.conn.close();
        }catch (Exception e){}
    }
    return model;
}
@Override
public String deleteStudent(int id) {
    GlobalVariable gv = new GlobalVariable();
    String message = null;
    try {
        String deletesql = "DELETE FROM student WHERE studID = ?";
        String deletesql2 = "DELETE FROM mainifo WHERE userID = ? AND role = ?";
        gv.conn = gv.c1.connect();
        gv.Stm2 = gv.conn.prepareStatement(deletesql);
        gv.Stm3 = gv.conn.prepareStatement(deletesql2);
        gv.Stm2.setInt(1, id);
        int r1 = gv.Stm2.executeUpdate();
        gv.Stm3.setInt(1, id);
        gv.Stm3.setString(2, "student");
        int r2 = gv.Stm3.executeUpdate();
        if (r1 > 0 && r2 > 0) {
            message = "Student Successfully Deleted!";
        } else {
            message = "Deletion failed. No rows affected.";
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "Error occurred during deletion.";
    } finally {
        try {
            if (gv.Stm2 != null) {
                gv.Stm2.close();
            }
            if (gv.Stm3 != null) {
                gv.Stm3.close();
            }
            if (gv.conn != null) {
                gv.conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return message;
}
@Override
public String deleteInstructor(int id) {
    GlobalVariable gv = new GlobalVariable();
    String message = null;
    try {
        String deletesql = "DELETE FROM instructor WHERE instID = ?";
        String deletesql2 = "DELETE FROM mainifo WHERE userID = ? AND role = ?";
        gv.conn = gv.c1.connect();
        gv.Stm2 = gv.conn.prepareStatement(deletesql);
        gv.Stm3 = gv.conn.prepareStatement(deletesql2);
        gv.Stm2.setInt(1, id);
        int r1 = gv.Stm2.executeUpdate();
        gv.Stm3.setInt(1, id);
        gv.Stm3.setString(2, "instructor");
        int r2 = gv.Stm3.executeUpdate();
        if (r1 > 0 && r2 > 0) {
            message = "Instructor Successfully Deleted!";
        } else {
            message = "Deletion failed. No rows affected.";
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "Error occurred during deletion.";
    } finally {
        try {
            if (gv.Stm2 != null) {
                gv.Stm2.close();
            }
            if (gv.Stm3 != null) {
                gv.Stm3.close();
            }
            if (gv.conn != null) {
                gv.conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return message;
}
@Override
public coursePojo fetchCourseList(int id){
    GlobalVariable gv = new GlobalVariable();
    coursePojo cp = new coursePojo();
    try {
        gv.conn = gv.c1.connect();
        String sql = "Select * from course where CID = ?";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setInt(1, id);
        ResultSet rs = gv.Stm.executeQuery();
        if (rs.next()) {
         cp.setName(rs.getString("course_name"));
            cp.setName(rs.getString("course_name"));
            cp.setcHR(rs.getInt("creditHR"));
            cp.setcStatus(rs.getString("course_status"));
            cp.setcDept(rs.getString("course_dept"));
            cp.setcPre(rs.getInt("pre_req_CID"));
            cp.setcStart(rs.getDate("start_schedule"));
            cp.setcEnd(rs.getDate("end_schedule"));
            cp.setcDesc(rs.getString("course_desc"));
            cp.setcCap(rs.getInt("course_capacity"));
            cp.setId(id);
        }
    }catch (Exception e){e.printStackTrace();}finally {
        try {

        }catch (Exception e){}
    }
    return cp;
}

@Override
public String updateCourse(int id, String name, int hr, String status, String dept, int pre, String start, String end,
                           String desc, int cap){

    GlobalVariable gv = new GlobalVariable();
    String message = "Error";
    try {
        gv.conn = gv.c1.connect();
        String sql = "update course set course_name = ?,creditHR =?,course_status=?,course_dept=?,pre_req_CID=?," +
                "start_schedule =?,end_schedule =?,course_desc =?,course_capacity =? " +
                "where CID = ?";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1, name);
        gv.Stm.setInt(2, hr);
        gv.Stm.setString(3, status);
        gv.Stm.setString(4, dept);
        gv.Stm.setInt(5, pre);
        gv.Stm.setString(6, start);
        gv.Stm.setString(7, end);
        gv.Stm.setString(8, desc);
        gv.Stm.setInt(9, cap);
        gv.Stm.setInt(10, id);
        int rowaff = gv.Stm.executeUpdate();
        if (rowaff > 0) {
           message =  "Course Updated successfully";
        } else {
          message =  "Course Updated failed";
        }

    }catch (Exception e){e.printStackTrace();}finally {
        try {
            gv.Stm.close();
            gv.conn.close();
        }catch (Exception e){}
    }
    return message;
}

@Override
public String deleteCourse(int id){
    GlobalVariable gv = new GlobalVariable();
    String message = "error";
    try {
        gv.conn = gv.c1.connect();
        String deletesql = "Delete from course where CID = ?";
        gv.Stm2 = gv.conn.prepareStatement(deletesql);
        gv.Stm2.setInt(1, id);
        int r1 = gv.Stm2.executeUpdate();
        if (r1 > 0) {
          message = "Course Successfully Deleted!";
        } else {
          message = "Deletion failed. No rows affected.";
        }
    }catch (Exception e){e.printStackTrace();}finally {
        try {
            gv.Stm2.close();
            gv.conn.close();
        }catch (Exception e){}
    }
    return message;
}
@Override
public instructorPojo fetchInstructorList(int id){
    GlobalVariable gv = new GlobalVariable();
    instructorPojo ob = new instructorPojo();
    try {
        gv.conn = gv.c1.connect();
        String sql = "Select * from instructor where instID = ?";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setInt(1, id);
        ResultSet rs = gv.Stm.executeQuery();
        if (rs.next()){
            ob.setfName(rs.getString("First_name"));
            ob.setlName(rs.getString("Last_name"));
            ob.setDob(rs.getDate("dob"));
            ob.setGender(rs.getString("gender"));
            ob.setAddress(rs.getString("address"));
            ob.setEmail(rs.getString("email"));
            ob.setNation(rs.getString("nationality"));
            ob.setQualif(rs.getString("qualification"));
            ob.setId(id);
        }
        rs.close();
        gv.Stm.close();
        gv.conn.close();
        return ob;
    }catch (Exception e){e.printStackTrace();}

    return null;
}
@Override
public studentPojo fetchStudentList(int id){
    GlobalVariable gv = new GlobalVariable();
    studentPojo obj = new studentPojo();
    try {
        gv.conn = gv.c1.connect();
        String sql = "Select * from student where studID = ?";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setInt(1, id);
         ResultSet rs = gv.Stm.executeQuery();
         if (rs.next()){
             obj.setFname(rs.getString("First_name"));
             obj.setLname(rs.getString("Last_name"));
             obj.setDob(rs.getDate("dob"));
             obj.setGender(rs.getString("gender"));
             obj.setAddress(rs.getString("address"));
             obj.setEmail(rs.getString("email"));
             obj.setNation(rs.getString("nationality"));
             obj.setDept(rs.getString("stu_dept"));
             obj.setBatch(rs.getString("stu_batch"));
             obj.setSemester(rs.getString("stu_semes"));
             obj.setYear(rs.getInt("year"));
             obj.setId(id);
         }
        rs.close();
        gv.Stm.close();
        gv.conn.close();
         return obj;
    }catch (Exception e){e.printStackTrace();}

return null;
}
@Override
public String updateStudent(int id, String fn, String ln, String dob, String gender, String address, String email,
                            String nation, String dept, String batch, String semes, int year){
    GlobalVariable gv = new GlobalVariable();

    String message = null;
    try {

        gv.conn = gv.c1.connect();
        String sql = "UPDATE student SET First_name = ?, Last_name = ?, dob = ?, gender = ?," +
                " address = ?, email = ?, nationality = ?, stu_dept =?, stu_batch =?, stu_semes = ?, year = ? WHERE studID = ?";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1, fn);
        gv.Stm.setString(2, ln);
        gv.Stm.setString(3, dob);
        gv.Stm.setString(4, gender);
        gv.Stm.setString(5, address);
        gv.Stm.setString(6, email);
        gv.Stm.setString(7, nation);
        gv.Stm.setString(8, dept);
        gv.Stm.setString(9, batch);
        gv.Stm.setString(10, semes);
        gv.Stm.setInt(11, year);
        gv.Stm.setInt(12, id);
        int rowaff = gv.Stm.executeUpdate();
        if (rowaff > 0) {
            message = "Successfully Updated";
        } else {
          message= "Updated operation field";
        }
    }catch (Exception e){e.printStackTrace();}
    finally {
        try {
            gv.Stm.close();
            gv.conn.close();
        }catch (Exception e){}
    }

    return message;
}
@Override
public String updateInstructor(int id, String fn, String ln, String dob, String gender, String address, String email,
                               String nation, String qulif){
    GlobalVariable gv = new GlobalVariable();
    String message ="Error occured";
    try {
        gv.conn = gv.c1.connect();
        String sql = "UPDATE instructor SET First_name = ?, Last_name = ?, dob = ?, gender = ?, address = ?, email = ?, nationality = ?, qualification = ? WHERE instID = ?";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1, fn);
        gv.Stm.setString(2, ln);
        gv.Stm.setString(3, dob);
        gv.Stm.setString(4, gender);
        gv.Stm.setString(5, address);
        gv.Stm.setString(6, email);
        gv.Stm.setString(7, nation);
        gv.Stm.setString(8, qulif);
        gv.Stm.setInt(9, id);
        int rowaff = gv.Stm.executeUpdate();
        if (rowaff > 0) {
          message = "Successfully Updated";
        } else {
            message =  "Updated operation field";
        }
    }catch (Exception e){e.printStackTrace();}finally {
        try {
            gv.Stm.close();
            gv.conn.close();
        }catch (Exception e){}
    }
    return message;
}
    @Override
    public String assignCourses(int cid, int instid){
    String message = "error";
        GlobalVariable gv = new GlobalVariable();
        try {
            gv.conn = gv.c1.connect();
            String sql = "Insert into course_instructor(course_id, instructor_id) values(?,?)";
            String sql2 = "Select instructor_id from course_instructor where course_id =? and instructor_id = ?";
            String sql3 = "Select instID from instructor where instID = ?";
            gv.Stm3 = gv.conn.prepareStatement(sql3);
            gv.Stm3.setInt(1,instid);

            ResultSet instCheck = gv.Stm3.executeQuery();
            if (instCheck.next()) {
                gv.Stm2 = gv.conn.prepareStatement(sql2);
                gv.Stm2.setInt(1, cid);
                gv.Stm2.setInt(2, instid);

                ResultSet rscheck = gv.Stm2.executeQuery();
                if (rscheck.next()) {
                    message = "This Course is already Assign to this instructor!";
                } else {
                    gv.Stm = gv.conn.prepareStatement(sql);
                    gv.Stm.setInt(1, cid);
                    gv.Stm.setInt(2, instid);
                    int rowaff = gv.Stm.executeUpdate();
                    if (rowaff > 0) {
                        message = "Successfully assigned!";
                    } else {
                        message = "Assign failed!";
                    }
                }
            }else {
                message = "Instructor not Found";
            }
        }catch (Exception e){e.printStackTrace();}finally {
            try {
                gv.Stm.close();
                gv.Stm2.close();
                gv.conn.close();
            }catch (Exception e){}
        }
    return message;
    }
    @Override
    public javax.swing.table.DefaultTableModel populateCourse(){
    DefaultTableModel model =null;
        GlobalVariable gv = new GlobalVariable();
        try {
            String columns[] = {"CID", "Course Name", "Credit HR", "CStatus", "CDepartement", "PRCID", "Cstart", "Cend","CCapacity","CDescription"};
            model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);
            gv.conn = gv.c1.connect();
            String sql = "Select * from course";
            gv.Stm = gv.conn.prepareStatement(sql);
            ResultSet rs = gv.Stm.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("CID"),
                        rs.getString("course_name"),
                        rs.getString("creditHR"),
                        rs.getString("course_status"),
                        rs.getString("course_dept"),
                        rs.getString("pre_req_CID"),
                        rs.getString("start_schedule"),
                        rs.getString("end_schedule"),
                        rs.getString("course_capacity"),
                        rs.getString("course_desc")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                gv.Stm.close();
                gv.conn.close();
            }catch (Exception e){}
        }

        return model;
    }
@Override
public String createCourse(int id, String name,int hr, String status, String dept, int pre, String start,
                           String end, String desc , int capc){
    GlobalVariable gv = new GlobalVariable();
    String message = "Error";
    try {
        gv.conn = gv.c1.connect();
        String sql = "insert into course(CID,course_name,creditHR,course_status,course_dept,pre_req_CID," +
                "start_schedule,end_schedule,course_desc,course_capacity) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setInt(1, id);
        gv.Stm.setString(2, name);
        gv.Stm.setInt(3, hr);
        gv.Stm.setString(4, status);
        gv.Stm.setString(5, dept);
        gv.Stm.setInt(6, pre);
        gv.Stm.setString(7, start);
        gv.Stm.setString(8, end);
        gv.Stm.setString(9, desc);
        gv.Stm.setInt(10, capc);

        int rowaff = gv.Stm.executeUpdate();
        if (rowaff > 0) {
           message =  "Course Created successfully";
        } else {
            message =  "Course Creation failed";
        }
    }catch (Exception e){e.printStackTrace();}finally {
        try {
            gv.Stm.close();
            gv.conn.close();
        }catch (Exception e){}
    }

    return message;
}
    @Override
    public String registerInstructor(int id, String fname, String lname, String dob, String gender, String address, String email,
                                     String nation, String qulif ,String uname,String pass){
        GlobalVariable gv = new GlobalVariable();
        String message = "Error";
        try{
            gv.conn = gv.c1.connect();
            String sql1 = "Insert into instructor(instID,First_name,Last_name,dob,gender,address,email,nationality,qualification) values(?,?,?,?,?,?,?,?,?)";
            String sql2 = "Insert into mainifo(userID ,username,pass_word,role) values(?,?,?,?)";
            gv.Stm = gv.conn.prepareStatement(sql1);
            gv.Stm2 = gv.conn.prepareStatement(sql2);

            gv.Stm.setInt(1,id);
            gv.Stm.setString(2,fname);
            gv.Stm.setString(3,lname);
            gv.Stm.setString(4,dob);
            gv.Stm.setString(5,gender);
            gv.Stm.setString(6,address);
            gv.Stm.setString(7,email);
            gv.Stm.setString(8,nation);
            gv.Stm.setString(9,qulif);
            gv.Stm.addBatch();
            gv.Stm.executeBatch();

            gv.Stm2.setInt(1, id);
            gv.Stm2.setString(2, uname);
            gv.Stm2.setString(3, pass);
            gv.Stm2.setString(4, "instructor");
            gv.Stm2.addBatch();
            gv.Stm2.executeBatch();
            message = "Instructor Register Successfully";
        }catch (Exception e){
          e.printStackTrace();
        }finally {
            try {
                gv.Stm.close();
                gv.Stm2.close();
                gv.conn.close();
            }catch (Exception e){}
        }
        return message;
    }
 @Override
 public String registerStudent(int studid, String stufname, String stulname, String formattedDate,
                               String stugender, String stuemail, String stuaddress, String stunatio,String stuuname, String stupass,
                               String studept, String stubatch, String stusemes, int year ){
     GlobalVariable gv = new GlobalVariable();
     try {
         gv.conn = gv.c1.connect();
         String sql1 = "Insert into student(studID,First_name,Last_name,dob,gender,address,email,nationality,stu_dept,stu_batch,stu_semes, year) values(?,?,?,?,?,?,?,?,?,?,?,?)";
         String sql2 = "Insert into mainifo(userID ,username,pass_word,role) values(?,?,?,?)";
         gv.Stm = gv.conn.prepareStatement(sql1);
         gv.Stm2 = gv.conn.prepareStatement(sql2);

         gv.Stm.setInt(1, studid);
         gv.Stm.setString(2, stufname);
         gv.Stm.setString(3, stulname);
         gv.Stm.setString(4, formattedDate);
         gv.Stm.setString(5, stugender);
         gv.Stm.setString(6, stuaddress);
         gv.Stm.setString(7, stuemail);
         gv.Stm.setString(8, stunatio);
         gv.Stm.setString(9, studept);
         gv.Stm.setString(10, stubatch);
         gv.Stm.setString(11, stusemes);
         gv.Stm.setInt(12, year);
         gv.Stm.addBatch();
         gv.Stm.executeBatch();

         gv.Stm2.setInt(1, studid);
         gv.Stm2.setString(2, stuuname);
         gv.Stm2.setString(3, stupass);
         gv.Stm2.setString(4, "student");
         gv.Stm2.addBatch();
         gv.Stm2.executeBatch();

         return "Register successfully";
     }catch (Exception e){
         e.printStackTrace();
     }finally {
         try {
             gv.Stm.close();
             gv.Stm2.close();
             gv.conn.close();
         }catch (Exception e){}
     }
     return "Error";
 }
    @Override
    public Boolean isUserNameExist(String username){
        GlobalVariable gv = new GlobalVariable();
        try {
            gv.conn = gv.c1.connect();
            String sql = "Select username from mainifo where username = ?";
            gv.Stm = gv.conn.prepareStatement(sql);
            gv.Stm.setString(1, username);
            ResultSet rs = gv.Stm.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                gv.Stm.close();
                gv.conn.close();
            }catch (Exception e){}
        }
        return false;
    }
@Override
public String enrollStudentToCourse(int id, String sDeprt, String sBatch, String sSemes, int year) {
    GlobalVariable gv = new GlobalVariable();
    String message = "error";
    try {
        gv.conn = gv.c1.connect();
        String coursql = "SELECT course_capacity, pre_req_CID, course_status, course_name FROM course WHERE CID = ?";
        gv.Stm3 = gv.conn.prepareStatement(coursql);
        gv.Stm3.setInt(1, id);
        ResultSet courseRS = gv.Stm3.executeQuery();
        int courseCapacity = 0;
        int pre_req_cid = 0;
        String cstat = "";
        String cname = "";
        if (courseRS.next()) {
            courseCapacity = courseRS.getInt("course_capacity");
            pre_req_cid = courseRS.getInt("pre_req_CID");
            cstat = courseRS.getString("course_status");
            cname = courseRS.getString("course_name");

            if (cstat.equals("Active")) {
                // Check if the course has reached its capacity
                gv.Stm6 = gv.conn.prepareStatement("SELECT COUNT(*) AS enroll_count FROM studentenroll WHERE CID = ? AND enroll_status = 'Enrolled'");
                gv.Stm6.setInt(1, id);
                ResultSet rs33 = gv.Stm6.executeQuery();
                if (rs33.next()) {
                    int count = rs33.getInt("enroll_count");
                    if (count < courseCapacity) {
                        String insertSQL = "INSERT INTO studentenroll (SID, CID, enroll_status) VALUES (?, ?, ?)";
                        String studentID = "SELECT studID FROM student WHERE stu_dept = ? AND stu_batch = ? AND stu_semes = ? AND year = ?";
                        gv.Stm5 = gv.conn.prepareStatement(studentID);
                        gv.Stm5.setString(1, sDeprt);
                        gv.Stm5.setString(2, sBatch);
                        gv.Stm5.setString(3, sSemes);
                        gv.Stm5.setInt(4, year);
                        ResultSet stID = gv.Stm5.executeQuery();
                        List<Integer> idList = new ArrayList<>();
                        while (stID.next()) {
                            int selectedID = stID.getInt("studID");
                            idList.add(selectedID);
                        }
                        if (!idList.isEmpty()) {
                            for (int selectedValue : idList) {
                                gv.Stm4 = gv.conn.prepareStatement("SELECT SID FROM studentenroll WHERE SID = ? AND CID = ?");
                                gv.Stm4.setInt(1, selectedValue);
                                gv.Stm4.setInt(2, id);
                                ResultSet enrollrs = gv.Stm4.executeQuery();
                                if (!enrollrs.next()) {
                                    gv.Stm = gv.conn.prepareStatement(insertSQL);
                                    if (pre_req_cid > 0) {
                                        gv.Stm2 = gv.conn.prepareStatement("SELECT SID FROM studentenroll WHERE SID = ? AND CID = ?");
                                        gv.Stm2.setInt(1, selectedValue);
                                        gv.Stm2.setInt(2, pre_req_cid);
                                        ResultSet rs = gv.Stm2.executeQuery();
                                        if (rs.next()) {
                                            gv.Stm.setString(3, "Enrolled");
                                        } else {
                                            gv.Stm.setString(3, "Dropped");
                                        }
                                        gv.Stm2.close();
                                    } else {
                                        gv.Stm.setString(3, "Enrolled");
                                    }
                                    gv.Stm.setInt(1, selectedValue);
                                    gv.Stm.setInt(2, id);
                                    gv.Stm.executeUpdate();
                                    gv.Stm.close();
                                    gv.Stm4.close();
                                } else {
                                    message = "Already enrolled!";
                                    continue;
                                }
                            }
                            gv.Stm5.close();
                            message = "Enroll Successfully";
                        } else {
                            message = "Student not found with this specification";
                        }
                    } else {
                        message = count + " Number of students enrolled. It has reached the course capacity limit.";
                    }
                }
            } else {
                message = "This Course('" + cname + "') is '" + cstat + "' right now and is not active!";
            }
        } else {
            message = "Course not found! Course ID is incorrect! Try again.";
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            gv.Stm3.close();
            gv.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return message;
}

    @Override
public javax.swing.JPanel showPanel(){
    studentEnrollement se = new studentEnrollement();
    return se;
}
    @Override
    public String getRoleOfUser(String username, String password){
        GlobalVariable gv = new GlobalVariable();
       try {
            gv.conn = gv.c1.connect();
            String query = "SELECT role FROM mainifo WHERE username = ? AND pass_word = ?";
            gv.Stm = gv.conn.prepareStatement(query);
            gv.Stm.setString(1, username);
            gv.Stm.setString(2, password);
            ResultSet rs = gv.Stm.executeQuery();
            String role = "";
               if (rs.next()) {
                    role = rs.getString("role");
                   return role;
                }

      }catch (Exception e){
          e.printStackTrace();
      }finally {
           try {
               gv.Stm.close();
               gv.conn.close();
           } catch (Exception ex) {
               ex.printStackTrace();
           }
       }
    return "";
}

@Override
public  javax.swing.table.DefaultTableModel populateEnrolledStudent(){
    DefaultTableModel model = null;
    try {
        String columns[] = {"Student ID", "First Name", "Last Name","pre_req_CID","Course ID", "Course name","Enroll status"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        GlobalVariable gv = new GlobalVariable();
        gv.conn = gv.c1.connect();
        String sql = "SELECT s.studID, s.First_name, s.Last_name, se.enroll_status,c.CID, c.course_name,c.pre_req_CID " +
                "FROM student s INNER JOIN studentenroll se ON s.studID = se.SID LEFT JOIN course c " +
                "ON se.CID = c.CID WHERE se.enroll_status = ?;";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1,"Enrolled");
        ResultSet rs = gv.Stm.executeQuery();
        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("s.studID"),
                    rs.getString("s.First_name"),
                    rs.getString("s.Last_name"),
                    rs.getString("c.pre_req_CID"),
                    rs.getString("c.CID"),
                    rs.getString("c.course_name"),
                    rs.getString("se.enroll_status")
            });
        }
        gv.Stm.close();
        gv.conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    return model;
}


@Override
public int generateID(int digit){
    int a = UniqueRandomNumberGenerator.generateUniqueRandomNumber(digit);
    return a;
}
@Override
public String DroppedStudent(int cid, int sid){
    GlobalVariable gv = new GlobalVariable();
    String message = "error";
    try {
        String insertSQL = "Update studentenroll set enroll_status =? where CID = ? and SID = ?";
        gv.Stm = gv.conn.prepareStatement(insertSQL);
        gv.Stm.setString(1,"Dropped");
        gv.Stm.setInt(2,cid);
        gv.Stm.setInt(3,sid);
        int row = gv.Stm.executeUpdate();
        message = "Successfully Dropp";
        gv.conn.close();
        gv.Stm.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return message;
}
@Override
public String addDroppedStudent(int cid, int sid) {
    GlobalVariable gv = new GlobalVariable();
    String message = "error";
    try {
        String insertSQL = "Update studentenroll set enroll_status =? where CID = ? and SID = ?";
        gv.Stm = gv.conn.prepareStatement(insertSQL);
        gv.Stm.setString(1,"Enrolled");
        gv.Stm.setInt(2,cid);
        gv.Stm.setInt(3,sid);
        int row = gv.Stm.executeUpdate();
            message = "Successfully add";
        gv.conn.close();
        gv.Stm.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return message;
}
@Override
public  javax.swing.table.DefaultTableModel populateEnrolledStudentByCID(int id){
    DefaultTableModel model = null;
    try {
        String columns[] = {"Student ID", "First Name", "Last Name","pre_req_CID","Course ID", "Course name","Enroll status"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        GlobalVariable gv = new GlobalVariable();
        gv.conn = gv.c1.connect();
        String sql = "SELECT s.studID, s.First_name, s.Last_name, se.enroll_status,c.CID, c.course_name,c.pre_req_CID " +
                "FROM student s INNER JOIN studentenroll se ON s.studID = se.SID LEFT JOIN course c " +
                "ON se.CID = c.CID WHERE se.enroll_status = ? and se.CID =?;";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1,"Enrolled");
        gv.Stm.setInt(2,id);
        ResultSet rs = gv.Stm.executeQuery();
        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("s.studID"),
                    rs.getString("s.First_name"),
                    rs.getString("s.Last_name"),
                    rs.getString("c.pre_req_CID"),
                    rs.getString("c.CID"),
                    rs.getString("c.course_name"),
                    rs.getString("se.enroll_status")
            });
        }
        gv.Stm.close();
        gv.conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    return model;
}
@Override
public  javax.swing.table.DefaultTableModel populateDroppedStudentByCID(int id){
    DefaultTableModel model  = null;
    try {
        String columns[] = {"Student ID", "First Name", "Last Name","pre_req_CID","Course ID", "Course name","Enroll status"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        GlobalVariable gv = new GlobalVariable();
        gv.conn = gv.c1.connect();
        String sql = "SELECT s.studID, s.First_name, s.Last_name, se.enroll_status,c.CID, c.course_name,c.pre_req_CID " +
                "FROM student s INNER JOIN studentenroll se ON s.studID = se.SID LEFT JOIN course c " +
                "ON se.CID = c.CID WHERE se.enroll_status = ? and se.CID =?;";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1,"Dropped");
        gv.Stm.setInt(2,id);
        ResultSet rs = gv.Stm.executeQuery();
        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("s.studID"),
                    rs.getString("s.First_name"),
                    rs.getString("s.Last_name"),
                    rs.getString("c.pre_req_CID"),
                    rs.getString("c.CID"),
                    rs.getString("c.course_name"),
                    rs.getString("se.enroll_status")
            });
        }
        gv.Stm.close();
        gv.conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }

    return model;
}
@Override
public  javax.swing.table.DefaultTableModel populateDroppedStudent(){
    DefaultTableModel model = null;
    try {
        String columns[] = {"Student ID", "First Name", "Last Name","pre_req_CID","Course ID", "Course name","Enroll status"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        GlobalVariable gv = new GlobalVariable();
        gv.conn = gv.c1.connect();
        String sql = "SELECT s.studID, s.First_name, s.Last_name, se.enroll_status,c.CID, c.course_name,c.pre_req_CID " +
                "FROM student s INNER JOIN studentenroll se ON s.studID = se.SID LEFT JOIN course c " +
                "ON se.CID = c.CID WHERE se.enroll_status = ?;";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setString(1,"Dropped");
        ResultSet rs = gv.Stm.executeQuery();
        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("s.studID"),
                    rs.getString("s.First_name"),
                    rs.getString("s.Last_name"),
                    rs.getString("c.pre_req_CID"),
                    rs.getString("c.CID"),
                    rs.getString("c.course_name"),
                    rs.getString("se.enroll_status")
            });
        }
        gv.Stm.close();
        gv.conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }

    return model;
}
    @Override
public boolean addResourseToDB(int c_id, byte[] fileBytes, String fileType, String fileTitle, String fileDescription , String link){
    GlobalVariable gv = new GlobalVariable();
    try{
        gv.conn = gv.c1.connect();
        String sql = "insert into courseresource(CID,file,file_title,file_desc,reference,fileType) values(?,?,?,?,?,?)";
        gv.Stm = gv.conn.prepareStatement(sql);
        gv.Stm.setInt(1, c_id);
        gv.Stm.setBytes(2, fileBytes);
        gv.Stm.setString(3, fileTitle);
        gv.Stm.setString(4, fileDescription);
        gv.Stm.setString(5, link);
        gv.Stm.setString(6, fileType);
        int rowaff = gv.Stm.executeUpdate();
        if (rowaff > 0) {
            return true;
        }

    }catch (Exception e){
        e.printStackTrace();
    }
    finally {
        try{
            gv.Stm.close();
            gv.conn.close();
        }catch (Exception e){e.printStackTrace();}
    }
    return false;
}
@Override
public void sendEmailNotification(int cid , String emailAddress, String password){
    fetchEmail(cid);
    String text = "A new resource for "+cname+" is added! you can download it from the website page!";
    EmailSender.sendEmail(recipientList,emailAddress,password,text);
}

@Override
public javax.swing.table.DefaultTableModel populateAssignCoursesToInstructor(String username){
    DefaultTableModel modelcourse = null;
    GlobalVariable gv = new GlobalVariable();
    try {
        String columns[] = {"Course ID", "Course Name", "Credit HR", "Course Status", "Course Depart", "Start Schedule", "End Schedule", "Pre-request Course ID"};
        modelcourse = new DefaultTableModel();
        modelcourse.setColumnIdentifiers(columns);
        gv.conn = gv.c1.connect();
        String sql1 = "Select userID from mainifo where username = ? ";
        gv.Stm2 = gv.conn.prepareStatement(sql1);
        gv.Stm2.setString(1,username);
        ResultSet rs1 = gv.Stm2.executeQuery();
        int id = 0;
        if (rs1.next()){
            id = rs1.getInt("userID");
        }
        String sql2 = "Select * from course c INNER JOIN course_instructor ci on c.CID = ci.course_id where ci.instructor_id = ?";
        gv.Stm = gv.conn.prepareStatement(sql2);
        gv.Stm.setInt(1,id);
        ResultSet rs2 = gv.Stm.executeQuery();
        while (rs2.next()) {
            modelcourse.addRow(new Object[]{
                    rs2.getInt("CID"),
                    rs2.getString("course_name"),
                    rs2.getString("creditHR"),
                    rs2.getString("course_status"),
                    rs2.getString("course_dept"),
                    rs2.getString("start_schedule"),
                    rs2.getString("end_schedule"),
                    rs2.getString("pre_req_CID")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }  finally {
        try{
            gv.Stm.close();
            gv.Stm2.close();
            gv.conn.close();
        }catch (Exception e){e.printStackTrace();}
    }
    return modelcourse;
}

@Override
    public javax.swing.table.DefaultTableModel populateTable(int courseid) {
        DefaultTableModel modelstudent = null;
    GlobalVariable gv = new GlobalVariable();
        try {
            String columns[] = {"ID", "First Name", "Last Name", "DOB", "Gender", "Address", "Email", "Nationality"};
           modelstudent = new DefaultTableModel();
            modelstudent.setColumnIdentifiers(columns);
            gv.conn = gv.c1.connect();
            String sql = "SELECT s.studID, s.First_name, s.Last_name, s.DOB, s.Gender, s.Address, s.Email, s.Nationality " +
                    "FROM student s INNER JOIN studentenroll se ON s.studID = se.SID  WHERE se.CID = ? and se.enroll_status =? ;";
            gv.Stm = gv.conn.prepareStatement(sql);
            gv.Stm.setInt(1,courseid);
            gv.Stm.setString(2,"Enrolled");
            ResultSet rs = gv.Stm.executeQuery();
            while (rs.next()) {
                modelstudent.addRow(new Object[]{
                        rs.getInt("studID"),
                        rs.getString("First_name"),
                        rs.getString("Last_name"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("nationality")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  finally {
            try{
                gv.Stm.close();
                gv.conn.close();
            }catch (Exception e){e.printStackTrace();}
        }
        return modelstudent;
    }
    @Override
    public Vector getStudentDepartment(){
        String sql = "Select stu_dept from student";
        GlobalVariable gv = new GlobalVariable();
        Vector v = new Vector();
        try{
            gv.conn = gv.c1.connect();
            gv.Stm = gv.conn.prepareStatement(sql);
            ResultSet rs = gv.Stm.executeQuery();
            while (rs.next()){
                String dept = rs.getString("stu_dept");
                if (!v.contains(dept)) {
                    v.add(dept);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                gv.Stm.close();
                gv.conn.close();
            }catch (Exception ex){}
        }
        return v;
    }
    @Override
    public Vector getCourseDepartment(){
    String sql = "Select course_dept from course";
    GlobalVariable gv = new GlobalVariable();
    Vector v = new Vector();
    try{
        gv.conn = gv.c1.connect();
        gv.Stm = gv.conn.prepareStatement(sql);
        ResultSet rs = gv.Stm.executeQuery();
        while (rs.next()){
            String dept = rs.getString("course_dept");
            if (!v.contains(dept)) {
                v.add(dept);
            }
        }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        try {
            gv.Stm.close();
            gv.conn.close();
        }catch (Exception ex){}
    }
    return v;
    }
    @Override
    public javax.swing.table.DefaultTableModel courseByDepartement(String deprt){
        DefaultTableModel coursmodel = null;
        GlobalVariable gv = new GlobalVariable();
        try {
            String columns[] = {"ID", "Course Name", "course status", "creditHR", "start day", "end day", "capacity"};
            coursmodel = new DefaultTableModel();
            coursmodel.setColumnIdentifiers(columns);
            gv.conn = gv.c1.connect();
            String sql = "SELECT * from course where course_dept = ?";
            gv.Stm = gv.conn.prepareStatement(sql);
            gv.Stm.setString(1,deprt);
            ResultSet rs = gv.Stm.executeQuery();
            while (rs.next()) {
                coursmodel.addRow(new Object[]{
                        rs.getInt("CID"),
                        rs.getString("course_name"),
                        rs.getString("course_status"),
                        rs.getString("creditHR"),
                        rs.getString("start_schedule"),
                        rs.getString("end_schedule"),
                        rs.getString("course_capacity"),
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  finally {
            try{
                gv.Stm.close();
                gv.conn.close();
            }catch (Exception e){e.printStackTrace();}
        }
        return coursmodel;
    }
    public void fetchEmail(int cid){
        GlobalVariable gv = new GlobalVariable();
        try {
            gv.conn = gv.c1.connect();
            String sql = "SELECT s.email, c.course_name FROM student s INNER JOIN studentenroll" +
                    " se ON s.studID = se.SID LEFT JOIN course c ON se.CID = c.CID WHERE se.CID = ? " +
                    "AND se.enroll_status = ?;";
            gv.Stm = gv.conn.prepareStatement(sql);
            gv.Stm.setInt(1,cid);
            gv.Stm.setString(2,"Enrolled");
            ResultSet rs = gv.Stm.executeQuery();
            while(rs.next()){
                recipientList.add(rs.getString("s.email"));
                cname = rs.getString("c.course_name");
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try{
                gv.Stm.close();
                gv.conn.close();
            }catch (Exception e){e.printStackTrace();}
        }
    }

}
