package dao;

import java.util.List;

import bean.Product;

public interface ProductDao {
	void addProduct(Product product);
	void deleteProductById();
	void deleteProductByCat();
	Product findCheapestProductInCat();
	List<Product> findProductByCat();
	Product findProductById();
	void updateProduct();
	List<Product> findExpiredProducts();
}
