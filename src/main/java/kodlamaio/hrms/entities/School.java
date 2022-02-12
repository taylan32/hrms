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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schools")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "school_name")
	@NotBlank(message = "okul adi bos birakilamaz")
	private String schoolName;

	@Column(name = "department")
	@NotBlank(message = "bolum bos birakilamaz")
	private String department;

	@Column(name = "year_of_start")
	@Length(max = 4, min = 4, message = "gecersiz giris")
	private String yearOfStart;

	@Column(name = "graduation_year")
	private String graduationYear;

	@Column(name = "is_completed")
	private boolean isCompleted;

	@ManyToOne()
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

}
