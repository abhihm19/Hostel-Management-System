package in.cdac.hms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "hostels")
public class Hostel extends Base {
	
	@Column(unique = true, nullable = false)
	private String name;	
	
	@Column(name = "contact_person")
	private String contactPerson;
	
	@Column(name = "contact_mobile_no")
	private String contactMobileNo;	
	
	private String address;
	
	@Column(name = "hostel_fees")	
	private Integer hostelFees;
	
	@OneToMany
	private List<Room> rooms = new ArrayList<>();
}
