package in.cdac.hms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "payments")
public class Payment extends Base {
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "transaction_status")
	private Boolean transactionStatus;
	
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;
	
	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;
}
