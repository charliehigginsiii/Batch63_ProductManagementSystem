package client;

import bean.Product;
import service.ProductService;
import service.ProductServiceImpl;

public class ProductClient {
	public static void main(String[]args) {
		ProductService service=new ProductServiceImpl();
		service.addProduct(new Product());
	}
}
