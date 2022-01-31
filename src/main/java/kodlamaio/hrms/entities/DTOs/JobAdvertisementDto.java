package kodlamaio.hrms.entities.DTOs;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

	private int id; // jobAdvertisementId

	private int amount;

	private Date creationDate;

	private Date deadLine;

	private String companyName;

	private String jobTitle;

	private String cityName;

}
