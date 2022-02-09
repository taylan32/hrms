package kodlamaio.hrms.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kodlamaio.hrms.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email", unique = true)
	@Email(message = "email gecersiz")
	@NotBlank(message = "email bos birakilamaz")
	private String email;

	@Column(name = "password")
	@Length(min =  4, message = "sifre en az 4 karakterden olusmali")
	@NotBlank(message = "sifre bos birakilamaz")
	private String password;
	
	@OneToOne(mappedBy = "user")
	@JoinColumn(name = "image_id",referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"user"})
	Image image;
	

}
