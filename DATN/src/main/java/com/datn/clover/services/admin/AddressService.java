package com.datn.clover.services.admin;

import com.datn.clover.DTO.admin.AddressBean;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Address;
import com.datn.clover.inter.AccountJPA;
import com.datn.clover.inter.AddressJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressJPA addressJPA;

    @Autowired
    AccountJPA accountJPA;

    public List<Address> getAllAddress() {return addressJPA.findAll();}

    public Address getAddressById(String id) {
        return addressJPA.findById(id).orElse(null);
    }

    public Address createAddress(AddressBean addressBean) {
        Address address = new Address();
        address.setProvince(addressBean.getProvince());
        address.setCity(addressBean.getCity());
        address.setDistrict(addressBean.getDistrict());
        address.setWards(addressBean.getWards());
        address.setStreetnameNumber(addressBean.getStreetnameNumber());
        address.setNation(addressBean.getNation());

        Account account = accountJPA.findById(addressBean.getAccountId()).orElse(null);
        if (account != null) {
            address.setAccount(account);
        }

        return addressJPA.save(address);

    }

    public Address updateAddress(String id ,AddressBean addressBean) {
        Address address = addressJPA.findById(id).orElse(null);
        if (address != null) {
            address.setProvince(addressBean.getProvince());
            address.setCity(addressBean.getCity());
            address.setDistrict(addressBean.getDistrict());
            address.setWards(addressBean.getWards());
            address.setStreetnameNumber(addressBean.getStreetnameNumber());
            address.setNation(addressBean.getNation());

            return addressJPA.save(address);
        }
        return null;
    }

    public Address deleteAddress(String id) {
        Address address = addressJPA.findById(id).orElse(null);
        if (address != null) {
            addressJPA.delete(address);
            return address;
        }
        return address;
    }

}
