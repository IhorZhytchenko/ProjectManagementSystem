package homework3.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "cutomer_name")
    private String name;
    @Column(name = "country")
    private String country;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private List<Project> projects;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", country='" + this.country + '\'' +
                ", projects=" + this.getProjectName() +
                '}';
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
