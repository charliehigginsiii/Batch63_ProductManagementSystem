package service;

import java.util.List;

import bean.Product;
import dao.ProductDao;
import dao.ProductDaoImpl;

public class ProductServiceImpl implements ProductService {
	ProductDao impl=new ProductDaoImpl();

	@Override
	public void addProduct(Product product) {
		impl.addProduct(product);
		
	}

	@Override
	public void deleteProductById() {
		impl.deleteProductById();
		
	}

	@Override
	public void deleteProductByCat() {
		impl.deleteProductByCat();
		
	}

	@Override
	public Product findCheapestProductInCat() {
		return impl.findCheapestProductInCat();
	}

	@Override
	public List<Product> findProductByCat() {
		return impl.findProductByCat();
	}

	@Override
	public Product findProductById() {
		return impl.findProductById();
	}

	@Override
	public void updateProduct() {
		impl.updateProduct();
	}

	@Override
	public List<Product> findExpiredProducts() {
		return null;
	}
	

}
