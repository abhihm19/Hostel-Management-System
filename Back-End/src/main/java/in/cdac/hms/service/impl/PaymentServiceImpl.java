package in.cdac.hms.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.cdac.hms.dto.PaymentDto;
import in.cdac.hms.model.Payment;
import in.cdac.hms.repository.PaymentRepository;
import in.cdac.hms.service.IPaymentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {
	
	private PaymentRepository paymentRepository;	

	@Override
	public Page<PaymentDto> getTransactions(Pageable pageable) {
		Page<Payment> payments = paymentRepository.findAll(pageable);
		List<PaymentDto> paymentDtos = payments.stream().map((payment) -> mapToPaymentDto(payment)).collect(Collectors.toList());
	    return new PageImpl<>(paymentDtos, pageable, payments.getTotalElements());
	}
	
	private PaymentDto mapToPaymentDto(Payment payment) {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setId(payment.getId());
		paymentDto.setTransactionId(payment.getTransactionId());
		paymentDto.setStudentName(payment.getStudent().getUser().getFirstName());
		paymentDto.setHostelName(payment.getStudent().getRoom().getHostel().getName());
		paymentDto.setRoomNo(payment.getStudent().getRoom().getRoomNo());
		paymentDto.setTransactionDate(LocalDateTime.now());
		paymentDto.setTransactionStatus(payment.getTransactionStatus());
		return paymentDto;
	}
}
