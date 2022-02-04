package kodlamaio.hrms.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kodlamaio.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Employer extends User {

	@Column(name = "company_name", unique = true)
	@NotBlank(message = "sirket adi bos birakilamaz")
	private String companyName;

	@Column(name = "web_site", unique = true)
	@NotBlank(message = "web adresi bos olamaz")
	private String webSite;

	@Column(name = "phone_number")
	@NotBlank(message = "telefon numarasi bos birakilamaz")
	private String phoneNumber;

	@Column(name = "is_confirmed")
	private boolean isConfirmed;

	@JsonIgnore
	@OneToMany(mappedBy = "employer")
	private List<JobAdvertisement> jobAdvertisements;

}
