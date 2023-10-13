package sama.company.jobportalbe.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobApplyDTO {
    @JsonProperty("job_id")
    private Integer jobId;

    public JobApplyDTO() {
        super();
    }

    @JsonCreator
    public JobApplyDTO(@JsonProperty("job_id") Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
