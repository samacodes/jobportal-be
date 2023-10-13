package sama.company.jobportalbe.dto;

import sama.company.jobportalbe.models.Job;

import java.util.List;

public class JobSearchResponseDTO {
    private Integer id;
    private String title;
    private String desc;
    private String location;
    private String salary;
    private String company;
    private String createdAt;
    private String image;
    private Boolean isApplied;

    public JobSearchResponseDTO() {
        super();
    }

    public JobSearchResponseDTO(Integer id, String title, String desc, String location, String salary, String company, String image, String createdAt, Boolean isApplied) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.salary = salary;
        this.company = company;
        this.image = image;
        this.createdAt = createdAt;
        this.isApplied = isApplied;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getApplied() {
        return isApplied;
    }

    public void setApplied(Boolean applied) {
        isApplied = applied;
    }
}
