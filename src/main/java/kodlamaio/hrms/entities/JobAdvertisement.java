package kodlamaio.hrms.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
	
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "job_advertisements")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/*
	@Column(name = "employer_id")
	private int employerId;

	@Column(name = "city_id")
	private int cityId;
	
	
	@Column(name = "job_title_id")
	private int jobTitleId;
	*/
	
	@ManyToOne
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne()
	@JoinColumn(name = "job_title_id")
	private JobTitle jobTitle;
	
	@NotBlank(message = "tanim bos olamaz")
	@Column(name = "description")
	private String description;

	@Column(name = "min_salary")
	private int minSalary;

	@Column(name = "max_salary")
	private int maxSalary;

	@Column(name = "amount")
	private int amount;

	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "dead_line")
	private Date deadLine;

	@Column(name = "is_active")
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "working_time_id")
	private WorkingTime workingTime;

	
	@ManyToOne()
	@JoinColumn(name = "working_type_id")
	private WorkingType workingType;
}
