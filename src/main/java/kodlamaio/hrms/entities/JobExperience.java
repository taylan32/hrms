package kodlamaio.hrms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_experiences")
public class JobExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne()
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@Column(name = "work_place_name")
	@NotBlank(message = "is yeri adi bos olamaz")
	private String workPlaceName;

	@ManyToOne
	@JoinColumn(name = "job_title_id")
	private JobTitle jobTitle;

	@Column(name = "year_of_start")
	@NotBlank(message = "tarih bos birakilamaz")
	@Length(min = 4, max = 4, message = "gecersiz tarih girisi")
	private String yearOfStart;

	@Column(name = "release_year")
	private String releaseYear;

	@Column(name = "is_continued")
	@Value("${some.key:false}")
	private boolean isContinued;

}
