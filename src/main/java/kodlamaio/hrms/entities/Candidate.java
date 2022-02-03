package kodlamaio.hrms.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import kodlamaio.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "candidates")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Candidate extends User {

	@NotBlank(message = "isim bos birakilamaz")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	@NotBlank(message = "soyad bos birakilamaz")
	private String lastName;

	@Column(name = "identity_number", unique = true, nullable = false, length = 11)
	@NotBlank(message = "TC zorunlu")
	private String identityNumber;

	@Column(name = "birth_year", length = 4)
	@NotBlank(message = "dogum yili bos olamaz")
	private String birthYear;

	@OneToMany(mappedBy = "candidate")
	private List<School> schools;

	@OneToMany(mappedBy = "candidate")
	private List<JobExperiment> jobExperiments;

	@OneToMany(mappedBy = "candidate")
	private List<Language> languages;

	@OneToMany(mappedBy = "candidate")
	private List<SocialMediaAccount> socialMediaAccounts;

	@OneToMany(mappedBy = "candidate")
	private List<Skill> skills;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "candidate")
	@PrimaryKeyJoinColumn
	private CandidateAbout candidateAbout;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "candidate")
	@PrimaryKeyJoinColumn
	private CandidatePhoto candidatePhoto;

	/*
	 * @OneToMany(mappedBy = "candidate") private List<CV> cvs;
	 */

}
