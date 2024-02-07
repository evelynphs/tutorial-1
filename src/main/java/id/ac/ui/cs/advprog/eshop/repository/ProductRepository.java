package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository{
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
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
         throw new NoSuchElementException("Product not found");
    }

    public void edit(Product editedProduct){
        Product product = findById(editedProduct.getProductId());
        product.setProductName(editedProduct.getProductName());
        product.setProductQuantity(editedProduct.getProductQuantity());
    }

    public void delete(String productId){
        Product deletedProduct = findById(productId);
        productData.remove(deletedProduct);
    }
}