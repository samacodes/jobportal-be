package sama.company.jobportalbe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Time;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer jobId;

    private String imageUrl;

    private String title;

    private String description;

    private String location;

    private String salary;

    private Time createdAt;

    // make a foreign key to the company table
    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId") // this is the foreign key
    private Company company;

    public Job() {
        super();
    }

    public Job(Integer jobId, String imageUrl, String title, String description, String location, String salary, Company company) {
        this.jobId = jobId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.company = company;
        this.createdAt = new Time(System.currentTimeMillis());
    }


    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }
}
