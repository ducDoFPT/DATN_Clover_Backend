package com.datn.clover.services.sellers;

import com.datn.clover.DTO.Sellers.ShopSellerBean;
import com.datn.clover.JPAs.ShopSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Shop;
import com.datn.clover.responeObject.ShopSellerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ShopSellerService {
    @Autowired
    private ShopSellerJPA shopRepository;

    public ShopSellerResponse setResponse(Shop shop) {
        ShopSellerResponse res = new ShopSellerResponse();
        res.setId(shop.getId());
        res.setName(shop.getName());
        res.setCity(shop.getCity());
        res.setProvince(shop.getProvince());
        res.setDistrict(shop.getDistrict());
        res.setWards(shop.getWards());
        res.setStreetnameNumber(shop.getStreetnameNumber());
        res.setNation(shop.getNation());
        res.setEvaluatesFeedbacks(shop.getEvaluatesFeedbacks());
        res.setProducts(shop.getProducts());
        return res;
    }


    public Shop createShop(ShopSellerBean shop, Account seller) {
        Shop shopEntity = new Shop();
        shopEntity.setName(shop.getName());
        shopEntity.setAccount(seller);
        shopEntity.setCity(shop.getCity());
        shopEntity.setDistrict(shop.getDistrict());
        shopEntity.setNation(shop.getNation());
        shopEntity.setProvince(shop.getProvince());
        shopEntity.setStreetnameNumber(shop.getStreetnameNumber());
        shopEntity.setWards(shop.getWards());
        return shopRepository.save(shopEntity);
    }

    public Shop updateShop(String id, ShopSellerBean shopDetails) {
        Optional<Shop> optionalShop = shopRepository.findById(id);
        if (optionalShop.isPresent()) {
            Shop shop = optionalShop.get();
            shop.setName(shopDetails.getName());
            shop.setProvince(shopDetails.getProvince());
            shop.setCity(shopDetails.getCity());
            shop.setDistrict(shopDetails.getDistrict());
            shop.setWards(shopDetails.getWards());
            shop.setStreetnameNumber(shopDetails.getStreetnameNumber());
            shop.setNation(shopDetails.getNation());
            return shopRepository.save(shop);
        } else {
            throw new RuntimeException("Shop not found with id " + id);
        }
    }
}
