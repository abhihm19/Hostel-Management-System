package in.cdac.hms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.cdac.hms.dto.PaymentDto;

public interface IPaymentService {
	Page<PaymentDto> getTransactions(Pageable pageable);
}
