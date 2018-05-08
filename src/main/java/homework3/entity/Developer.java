package homework3.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "sex")
    private String sex;
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "id_company")
    private Company company;
    @Column(name = "salary")
    private int salary;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "developers_skills",
            joinColumns = @JoinColumn(name = "id_developer"),
            inverseJoinColumns = @JoinColumn(name = "id_skill"))
    private List<Skill> skills;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "developers_projects",
            joinColumns = @JoinColumn(name = "id_developer"),
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    private List<Project> projects;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + this.id +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", age=" + this.age +
                ", sex='" + this.sex + '\'' +
                ", company=" + this.company.getName() +
                ", salary=" + this.salary +
                ", skills=" + this.getSkillsName() +
                ", projects=" + this.getProjectName() +
                '}';
    }

    private String getSkillsName() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int size = this.skills.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            Skill skill = this.skills.get(i);
            builder.append(skill.getLanguage());
            builder.append(" ");
            builder.append(skill.getLevel());
        }
        builder.append("}");
        return builder.toString();
    }

    private String getProjectName() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int size = this.projects.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            Project project = this.projects.get(i);
            builder.append(project.getName());
        }
        builder.append("}");
        return builder.toString();
    }
}
