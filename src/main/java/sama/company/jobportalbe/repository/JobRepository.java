package sama.company.jobportalbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sama.company.jobportalbe.models.Job;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {
    // add methods to get jobs
    Optional<Job> findByJobId(Integer jobId);
    // method to get all the jobs listed limited to 10 jobs
    @Query("SELECT j FROM Job j ORDER BY j.jobId DESC")
    List<Job> findAllJobs();

    // add a method with query which more than one parameter so search by title  and location and description and salary and company name but only if they are present
    @Query("SELECT j FROM Job j WHERE " +
            "LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%')) AND " +
            "LOWER(j.description) LIKE LOWER(CONCAT('%', :description, '%')) AND " +
            "LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')) AND " +
            "LOWER(j.salary) LIKE LOWER(CONCAT('%', :salary, '%')) AND " +
            "LOWER(j.company.companyName) LIKE LOWER(CONCAT('%', :companyName, '%'))")
    List<Job> searchJobsByTitleAndLocationAndDescriptionAndSalaryAndCompanyName(String title, String location, String description, String salary, String companyName);

    // search job by title and location
    @Query("SELECT j FROM Job j WHERE " +
            "LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%')) AND " +
            "LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Job> searchJobsByTitleAndLocation(String title, String location);
}
