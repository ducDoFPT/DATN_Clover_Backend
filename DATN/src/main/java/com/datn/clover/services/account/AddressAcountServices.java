package com.datn.clover.services.account;

import com.datn.clover.DTO.Sellers.AddressSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.AddressSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Address;
import com.datn.clover.inter.AccountJPA;
import com.datn.clover.inter.AddressJPA;
import com.datn.clover.responeObject.AddressAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressAcountServices {
    @Autowired
    AddressJPA addressJPA;
    @Autowired
    private AccountJPA accountJPA;
    @Autowired
    private AccountSellerService accountSellerService;
    @Autowired
    private AccountSellerJPA accountSellerJPA;
    private AddressSellerJPA addressSellerJPA;

    public AddressAccountResponse setResponse(Address address){
            AddressAccountResponse response = new AddressAccountResponse();
        response.setCity(address.getCity());
        response.setDistrict(address.getDistrict());
        response.setNation(address.getNation());
        response.setProvince(address.getProvince());
        response.setWards(address.getWards());
        response.setStreetnameNumber(address.getStreetnameNumber());
        return response;
    }

    public ResponseEntity<AddressAccountResponse> create(AddressSellerBean addressBean, Optional<Account> account){
    try {
        if(account.isPresent() && account.get().getAddresses() == null ){
            Address address  = new Address();
            address.setCity(addressBean.getCity());
            address.setDistrict(addressBean.getDistrict());
            address.setNation(addressBean.getNation());
            address.setProvince(addressBean.getProvince());
            address.setWards(addressBean.getWards());
            address.setStreetnameNumber(addressBean.getStreetnameNumber());
            address.setAccount(account.get());
            Address address1 = addressJPA.save(address);
            return ResponseEntity.ok(setResponse(address1));
        }
        return ResponseEntity.status(500).body(new AddressAccountResponse());
    }catch (Exception e){
        e.printStackTrace();
        return ResponseEntity.status(500).body(new AddressAccountResponse());
    }


}

    public ResponseEntity<AddressAccountResponse> update(AddressSellerBean addressBean, String username){
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(username);
            if(account.isPresent()){
                Address address  = account.get().getAddresses();
                address.setCity(addressBean.getCity());
                address.setDistrict(addressBean.getDistrict());
                address.setNation(addressBean.getNation());
                address.setProvince(addressBean.getProvince());
                address.setWards(addressBean.getWards());
                address.setStreetnameNumber(addressBean.getStreetnameNumber());
                address.setAccount(account.get());
                Address rs = addressJPA.save(address);
                return ResponseEntity.ok(setResponse(rs));
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){

            return ResponseEntity.status(500).body(new AddressAccountResponse());
        }
    }

}
