package com.dededesignworkshop.seisankun_api.application.controller;

import com.dededesignworkshop.seisankun_api.domain.object.BorrowHistory;
import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import com.dededesignworkshop.seisankun_api.domain.object.PaymentHistory;
import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.domain.service.PaymentService;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.BorrowMoneyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    @NotNull
    private final PaymentService paymentService;

    @RequestMapping(value = "/v1/payment/history/{travel_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PaymentHistory> findByTravelId(@PathVariable("travel_id") Integer travel_id){
        return this.paymentService.findByTravelId(travel_id);
    }

    @RequestMapping(value = "/v1/payment/info/{payment_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Payment> findByPaymrntId(@PathVariable("payment_id") Integer payment_id){
        return this.paymentService.findByPaymentId(payment_id);
    }

    @RequestMapping(value = "/v1/payment/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Payment createPayment(@RequestBody Payment payment, BindingResult result){
        this.paymentService.createPayment(payment);
        return payment;
    }

    @RequestMapping(value = "v1/payment/data/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Payment> updatePayment(@RequestBody Payment payment, BindingResult result){
        Optional<Payment> updatedPayment = this.paymentService.updatePayment(payment);
        return updatedPayment;
    }

    @RequestMapping(value = "v1/payment/data/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer deleteTravel(@RequestBody Payment payment, BindingResult result){
        this.paymentService.softDeletePayment(payment);
        return payment.getId();
    }

    @RequestMapping(value = "/v1/borrower/{payment_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Optional<User>> findBorrowerByPaymentId(@PathVariable("payment_id") Integer payment_id){
        return this.paymentService.findBorrowerByPaymentId(payment_id);
    }

	@RequestMapping(value = "/v1/borrow_money/{travel_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<BorrowMoneyEntity> findBorrowMoneyByTravelId(@PathVariable("travel_id") Integer travel_id){
    	return this.paymentService.findBorrowMoneyByTravelId(travel_id);
	}

    @RequestMapping(value = "/v1/borrow_history/show/{borrower_id}/{travel_hash_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BorrowHistory> findBorrowHistoryByBorrowerId(@PathVariable("borrower_id") Integer borrowerId, @PathVariable("travel_hash_id") String travelHashId){
        return this.paymentService.findBorrowHistoryByBorrowerIdAndTravelHashId(borrowerId, travelHashId);
    }

}
