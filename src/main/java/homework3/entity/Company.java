package homework3.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private List<Developer> developers;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private List<Project> projects;

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", city='" + this.city + '\'' +
                ", developers=" + this.getDeveloperName()+
                ", projects=" + this.getProjectName() +
                '}';
    }

    private String getDeveloperName() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int size = this.developers.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            Developer developer = this.developers.get(i);
            builder.append(developer.getFirstName());
            builder.append(" ");
            builder.append(developer.getLastName());
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
