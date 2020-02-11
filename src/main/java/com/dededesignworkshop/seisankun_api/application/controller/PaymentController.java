package com.dededesignworkshop.seisankun_api.application.controller;

import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import com.dededesignworkshop.seisankun_api.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    @NotNull
    private final PaymentService paymentService;

    @RequestMapping(value = "/v1/payment/history/{travel_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Stream<Payment> findByTravelId(@PathVariable("travel_id") Integer travel_id){
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

}
