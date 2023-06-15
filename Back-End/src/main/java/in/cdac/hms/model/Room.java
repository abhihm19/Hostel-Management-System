package in.cdac.hms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room extends Base {
	
	@Column(unique = true)
	private Integer roomNo;
	
	@Column(name = "is_vacant")
	private Boolean isVacant;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hostel_id", referencedColumnName ="id" )
	private Hostel hostel;
	
	@OneToOne
	private Student student;
}
