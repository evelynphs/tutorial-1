package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository{
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        String id = UUID.randomUUID().toString();
        product.setProductId(id);
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String productId){
        for(Product product : productData){
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        return null;
    }

    public void edit(Product editedProduct){
        Product product = findById(editedProduct.getProductId());
        product.setProductName(editedProduct.getProductName());
        product.setProductQuantity(editedProduct.getProductQuantity());
    }
}