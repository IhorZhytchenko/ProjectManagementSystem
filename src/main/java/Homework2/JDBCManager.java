package Homework2;

import Homework2.entity.Customer;
import Homework2.entity.Developer;
import Homework2.entity.Project;

import java.sql.*;

public class JDBCManager {
    private static final String URL ="jdbc:mysql://localhost:3306/test1?user=root&password=220889";
    private Connection conn ;
    private Statement statement;
    private PreparedStatement createDevStm;
    private PreparedStatement updateDevSalStm;
    private PreparedStatement createCusStm;
    private PreparedStatement createProjStm;


    public JDBCManager(){
        try {
            conn = DriverManager.getConnection(URL);
            statement =conn.createStatement();
            initPreparedStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initPreparedStatement() throws SQLException {
      createDevStm = conn.prepareStatement("insert into developers (first_name,last_name,age,sex,id_company,salary)"
                                             +"values (?,?,?,?,?,?)");
      updateDevSalStm=conn.prepareStatement("update developers set salary =? where id=?");
      createCusStm=conn.prepareStatement("insert into customers (cutomer_name,country)"
                                              +"values (?,?)");
      createProjStm = conn.prepareStatement("insert into projects(project_name,description,id_company,id_customer)"
                                               +"values (?,?,?,?)");
    }

    public void addDeveloper(Developer developer){
        try {
            createDevStm.setString(1,developer.getFirstName());
            createDevStm.setString(2,developer.getLastName());
            createDevStm.setInt(3,developer.getAge());
            createDevStm.setString(4,developer.getSex());
            createDevStm.setInt(5,developer.getIdCompany());
            createDevStm.setInt(6,developer.getSalary());
            createDevStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Developer getDeveloperById(long id){
        Developer result = new Developer();
        try {
            ResultSet rs = statement.executeQuery("select * from developers where id="+id);
            while (rs.next()){
                result.setId(rs.getInt("id"));
                result.setFirstName(rs.getString("first_name"));
                result.setLastName(rs.getString("last_name"));
                result.setSex(rs.getString("sex"));
                result.setAge(rs.getInt("age"));
                result.setIdCompany(rs.getInt("id_company"));
                result.setSalary(rs.getInt("salary"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void deleteDeveloper(long id){
        try {
            conn.setAutoCommit(false);
            statement.executeUpdate("delete from developers_skills where id_developer ="+id);
            statement.executeUpdate("delete from developers_projects where id_developer ="+id);
            statement.executeUpdate("delete from developers where id ="+id);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void updateSalaryById(long id, int salary){
        try {
            updateDevSalStm.setInt(1,salary);
            updateDevSalStm.setLong(2,id);
            updateDevSalStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProject(Project project){
        try {
            createProjStm.setString(1,project.getName());
            createProjStm.setString(2,project.getDescription());
            createProjStm.setInt(3,project.getIdCompany());
            createProjStm.setInt(4,project.getIdCustomer());
            createProjStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addCustomer(Customer customer){
        try {
            createCusStm.setString(1,customer.getName());
            createCusStm.setString(2,customer.getCountry());
            createCusStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
