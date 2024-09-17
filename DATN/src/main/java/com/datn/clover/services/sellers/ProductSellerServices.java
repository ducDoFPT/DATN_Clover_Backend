package com.datn.clover.services.sellers;

import com.datn.clover.DTO.Sellers.ProductSellerBean;
import com.datn.clover.JPAs.ProductImageSellerJPA;
import com.datn.clover.JPAs.PropertiValueSellerJPA;
import com.datn.clover.entity.*;
import com.datn.clover.inter.ProductJPA;
import com.datn.clover.inter.PromotionJPA;
import com.datn.clover.inter.TypeProductJPA;
import com.datn.clover.responeObject.ProductSellerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductSellerServices {


    private final TypeProductJPA typeProductJPA;
    private final PromotionJPA promotionJPA;
    private final ProductJPA productJPA;
    private final UploadSellerServices uploadSellerServices;
    private final ProductImageSellerJPA productImageSellerJPA;
    private final PropertiValueSellerJPA propertiValueSellerJPA;

    public ProductSellerServices(TypeProductJPA typeProductJPA, PromotionJPA promotionJPA, ProductJPA productJPA, UploadSellerServices uploadSellerServices, ProductImageSellerJPA productImageSellerJPA, PropertiValueSellerJPA propertiValueSellerJPA) {
        this.typeProductJPA = typeProductJPA;
        this.promotionJPA = promotionJPA;
        this.productJPA = productJPA;
        this.uploadSellerServices = uploadSellerServices;
        this.productImageSellerJPA = productImageSellerJPA;
        this.propertiValueSellerJPA = propertiValueSellerJPA;
    }

    public ProductSellerResponse setResponsee(Product product){
        ProductSellerResponse response = new ProductSellerResponse();
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setProdImages(product.getProdImages().stream().toList());
        return response;
    }

    public ResponseEntity<ProductSellerResponse> create(ProductSellerBean product, List<MultipartFile> files, Shop shop){
       try {
           Product pro = new Product();
           pro.setName(product.getName());
           pro.setDescription(product.getDescription());
           pro.setPrice(product.getPrice());
           pro.setQuantity(product.getQuantity());
           pro.setShop(shop);
           TypeProduct typeProduct = typeProductJPA.findById(product.getProdTypeID()).get();
           Optional<Promotion> promotion = promotionJPA.findById(product.getPromotionID());
           pro.setProdType(typeProduct);
           pro.setPromotion(promotion.get());
           Set<PropertiesValue> propertiesValues = new LinkedHashSet<>();
           for (String id : product.getPropertiesValues()){
               Optional<PropertiesValue> property = propertiValueSellerJPA.findById(id);
               property.ifPresent(pro::addPropertiesValue);
           }

           pro.setPropertiesValues(propertiesValues);
           Product proRS = productJPA.save(pro);
           ProdImage prodImage = new ProdImage();
           for (MultipartFile file : files) {
               String fileName = uploadSellerServices.uploadFile(file);
               prodImage.setProd(proRS);
               prodImage.setName(fileName);
               productImageSellerJPA.save(prodImage);
           }
           return ResponseEntity.ok(setResponsee(proRS));
       }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.badRequest().build();
       }

    }

    public ResponseEntity<ProductSellerResponse> update(ProductSellerBean product, List<MultipartFile> files){
        try {
            Optional<Product> pro = productJPA.findById(product.getId());
            if(pro.isPresent()){
                pro.get().setName(product.getName());
                pro.get().setDescription(product.getDescription());
                pro.get().setPrice(product.getPrice());
                pro.get().setQuantity(product.getQuantity());
                TypeProduct typeProduct = typeProductJPA.findById(product.getProdTypeID()).get();
                Optional<Promotion> promotion = promotionJPA.findById(product.getPromotionID());
                pro.get().setProdType(typeProduct);
                pro.get().setPromotion(promotion.get());
                Product proRS = productJPA.save(pro.get());
                List<ProdImage> productImages = productImageSellerJPA.findByProductId(proRS.getId().toString());

                // Lấy danh sách tên file từ các ảnh đã có trong cơ sở dữ liệu
                List<String> existingImageNames = productImages.stream()
                        .map(ProdImage::getName)
                        .toList();

                // Xóa ảnh không có trong danh sách tải lên
                for (ProdImage prodImage : productImages) {
                    if (files.stream().noneMatch(file -> Objects.equals(file.getOriginalFilename(), prodImage.getName()))) {
                        productImageSellerJPA.delete(prodImage); // Xóa ảnh không có trong danh sách tải lên
                    }
                }

                // Thêm ảnh mới hoặc cập nhật
                for (MultipartFile file : files) {
                    if (!existingImageNames.contains(file.getOriginalFilename())) {
                        // Tải lên file mới nếu tên file không tồn tại trong danh sách hiện có
                        String fileName = uploadSellerServices.uploadFile(file);
                        ProdImage newProdImage = new ProdImage();
                        newProdImage.setProd(proRS);
                        newProdImage.setName(fileName);
                        productImageSellerJPA.save(newProdImage);
                    }
                }

                return ResponseEntity.ok(setResponsee(proRS));
            }

            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}
