package service;

import java.util.List;

import bean.Product;

public interface ProductService {
	void addProduct(Product product);
	void deleteProductById();
	void deleteProductByCat();
	Product findCheapestProductInCat();
	List<Product> findProductByCat();
	Product findProductById();
	void updateProduct();
	List<Product> findExpiredProducts();
}
