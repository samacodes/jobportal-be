package sama.company.jobportalbe.dto;

import org.springframework.stereotype.Component;
import sama.company.jobportalbe.models.Job;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobMapper {
    public JobSearchResponseDTO mapJobToDTO(Job job, Boolean isApplied) {
        // Perform the mapping from Job to JobSearchResponseDTO here
        return new JobSearchResponseDTO(
                job.getJobId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getSalary(),
                job.getCompany().getCompanyName(),
                job.getImageUrl(),
                job.getCreatedAt().toString(),
                isApplied
        );
    }

    public List<JobSearchResponseDTO> mapJobsToDTOList(List<Job> jobs, List<Integer> appliedJobIds) {
        // Perform the mapping from List<Job> to List<JobSearchResponseDTO> here
        return jobs.stream()
                .map(job -> mapJobToDTO(job, appliedJobIds.contains(job.getJobId())))
                .collect(Collectors.toList());
    }
}
