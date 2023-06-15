package in.cdac.hms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "concerns")
public class Concern extends Base {
	
	private String subject;
	
	private String message;
	
	@ManyToOne
	@JoinColumn( name = "student_id", referencedColumnName = "id")
	private Student student;
}
