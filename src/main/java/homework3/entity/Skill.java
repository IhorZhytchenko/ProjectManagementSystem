package homework3.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "language")
    private String language;
    @Column(name = "level")
    private String level;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "developers_skills",
            joinColumns = @JoinColumn(name = "id_skill"),
            inverseJoinColumns = @JoinColumn(name = "id_developer"))
    private List<Developer> developers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + this.id +
                ", language='" + this.language + '\'' +
                ", level='" + this.level + '\'' +
                ", developers=" + this.getDeveloperName()+
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
}
