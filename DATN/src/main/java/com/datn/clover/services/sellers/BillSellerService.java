package com.datn.clover.services.sellers;

import com.datn.clover.entity.Bill;
import com.datn.clover.responeObject.BillResponse;
import org.springframework.stereotype.Service;

@Service
public class BillSellerService {

    public BillResponse setResponse(Bill bill) {
        BillResponse response = new BillResponse();
        response.setFullname(bill.getFullname());
        response.setEmail(bill.getEmail());
        response.setPhone(bill.getPhone());
        response.setDetailBills(bill.getDetailBills());
        response.setBuyDay(bill.getBuyDay());
        response.setStatus(bill.getStatus());
        response.setVoucher(bill.getVoucher());
        response.setAddressBill(bill.getAddressBill());
        response.setDiscountVoucher(bill.getDiscountVoucher());
        response.setTotalPayment(bill.getTotalPayment());
        response.setShipMoney(bill.getShipMoney());
        response.setPaymentMethods(bill.getPaymentMethods());
        response.setShipBills(bill.getShipBills());
        return response;
    }
}
