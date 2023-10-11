package sama.company.jobportalbe.dto;

import org.springframework.stereotype.Component;
import sama.company.jobportalbe.models.Job;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobMapper {
    public JobSearchResponseDTO mapJobToDTO(Job job) {
        // Perform the mapping from Job to JobSearchResponseDTO here
        return new JobSearchResponseDTO(
                job.getJobId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getSalary(),
                job.getCompany().getCompanyName(),
                job.getCreatedAt().toString() // Format the createdAt as needed
        );
    }

    public List<JobSearchResponseDTO> mapJobsToDTOList(List<Job> jobs) {
        return jobs.stream()
                .map(this::mapJobToDTO)
                .collect(Collectors.toList());
    }
}
