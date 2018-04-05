package Homework2;

import Homework2.entity.Customer;
import Homework2.entity.Developer;
import Homework2.entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int getCostProjectById(long id){
        int result =-1;
        try(ResultSet rs =statement.executeQuery("SELECT SUM(salary) cost FROM developers , developers_projects , projects " +
                "WHERE developers.id=id_developer AND projects.id=id_project AND projects.id="+id)) {

            while (rs.next()){
                result =rs.getInt("cost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Developer> getDevelopersByProjectId(long id){
        List<Developer> result = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery("select  id, first_name, last_name, age, sex, id_company, salary from developers, developers_projects " +
                "where developers.id=developers_projects.id_developer and developers_projects.id_project="+id )) {
            while (rs.next()){
                Developer developer =new Developer();
                developer.setId(rs.getInt("id"));
                developer.setFirstName(rs.getString("first_name"));
                developer.setLastName(rs.getString("last_name"));
                developer.setSex(rs.getString("sex"));
                developer.setAge(rs.getInt("age"));
                developer.setIdCompany(rs.getInt("id_company"));
                developer.setSalary(rs.getInt("salary"));
                result.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Developer> getJavaDevelopers(){
        List<Developer> result = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery("select developers.id, first_name, last_name, age, sex, id_company, salary from developers, developers_skills, skills " +
                "where developers.id=developers_skills.id_developer and developers_skills.id_skill=skills.id and skills.language like 'java'" )) {
            while (rs.next()){
                Developer developer =new Developer();
                developer.setId(rs.getInt("id"));
                developer.setFirstName(rs.getString("first_name"));
                developer.setLastName(rs.getString("last_name"));
                developer.setSex(rs.getString("sex"));
                developer.setAge(rs.getInt("age"));
                developer.setIdCompany(rs.getInt("id_company"));
                developer.setSalary(rs.getInt("salary"));
                result.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public List<Developer> getMiddleDevelopers(){
        List<Developer> result = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery("select distinct developers.id, first_name, last_name, age, sex, id_company, salary from developers, developers_skills, skills " +
                "where developers.id=developers_skills.id_developer and developers_skills.id_skill=skills.id and skills.level like 'middle'" )) {
            while (rs.next()){
                Developer developer =new Developer();
                developer.setId(rs.getInt("id"));
                developer.setFirstName(rs.getString("first_name"));
                developer.setLastName(rs.getString("last_name"));
                developer.setSex(rs.getString("sex"));
                developer.setAge(rs.getInt("age"));
                developer.setIdCompany(rs.getInt("id_company"));
                developer.setSalary(rs.getInt("salary"));
                result.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Map<Project,Integer> getProjectWithDevelopersCount(){
        Map<Project,Integer> result = new HashMap<>();
        try (ResultSet rs = statement.executeQuery("select count(developers.id) devCount, projects.project_name, projects.description from developers , projects , developers_projects " +
                "where projects.id =developers_projects.id_project and developers.id=developers_projects.id_developer " +
                "group by projects.project_name" )) {
            while (rs.next()){
                Project project = new Project();
                project.setDescription(rs.getString("description"));
                project.setName(rs.getString("project_name"));
                result.put(project,rs.getInt("devCount"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}

