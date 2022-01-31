package kodlamaio.hrms.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

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
public class Candidate extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@NotBlank(message = "isim bos birakilamaz")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	@NotBlank(message = "soyad bos birakilamaz")
	private String lastName;

	@Column(name = "identity_number")
	@NotBlank(message = "TC zorunlu")
	@Size(min = 11, max = 11, message = "TC is gecersiz")
	private String identityNumber;

	@Column(name = "birth_year")
	@Length(min = 4, max = 4, message = "gecersiz dogum tarihi")
	@NotBlank(message = "Dogum yılı bos bırakılamaz")
	private String birthYear;
	
	@OneToMany(mappedBy = "candidate")
	private List<School> schools;
 
}
