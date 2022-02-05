package kodlamaio.hrms.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "job_advertisement_confirmations")
public class JobAdvertisementConfirmation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "is_confirmed")
	@NotBlank
	private boolean isConfirmed;

	@Column(name = "confirmation_date")
	private LocalDateTime confirmationDate;

	@OneToOne
	@JoinColumn(name = "job_advertisement_id")
	private JobAdvertisement jobAdvertisement;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;

	@ManyToOne
	@JoinColumn(name = "job_advertisement_confirmation_type_id")
	private JobAdvertisementConfirmationType jobAdvertisementConfirmationType;

}