package client;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import bean.Product;
import service.ProductService;
import service.ProductServiceImpl;

public class ProductClient {
	public static void main(String[]args) {
		ProductService service=new ProductServiceImpl();
		boolean runprogram=true;
		
		while(runprogram) {
			System.out.println("Choose option 1-9:"+"\n"+
					"1. Insert into database"+"\n"+
					"2. Delete product by Id"+"\n"+
					"3. Delete product by category"+"\n"+
					"4. Find cheapest product in category"+"\n"+
					"5. Find product by category"+"\n"+
					"6. Find product by id"+"\n"+
					"7. Update product"+"\n"+
					"8. Find expired products"+"\n"+
					"9. Exit"+"\n");
			int option=new Scanner(System.in).nextInt();
			if(option==1) {
				Product product=new Product();
				System.out.println("Enter Product Id: ");
				product.setPid(new Scanner(System.in).nextInt());
				System.out.println("Enter Product Name: ");
				product.setPname(new Scanner(System.in).nextLine());
				System.out.println("Enter Product Category: ");
				product.setPcat(new Scanner(System.in).nextLine());
				System.out.println("Enter Product Price: ");
				product.setPrice(new Scanner(System.in).nextDouble());
				System.out.println("Enter Product Expiry Date (YYYY-MM-dd): ");
				product.setExpiry_date(java.sql.Date.valueOf(new Scanner(System.in).nextLine()));
				System.out.println("Enter Product Manufacture Date (YYYY-MM-dd): ");
				product.setManufacture_date(java.sql.Date.valueOf(new Scanner(System.in).nextLine()));
				service.addProduct(product);
			}else if(option==2) {
				service.deleteProductById();
			}else if(option==3) {
				service.deleteProductByCat();
			}else if (option==4) {
				Product foundproduct=service.findCheapestProductInCat();
				System.out.println("[Product Id: "+foundproduct.getPid()+",  Product Name: "+foundproduct.getPname()+",  Product Category: "+
						foundproduct.getPcat()+",  Product Price: $"+foundproduct.getPrice()+",  Product Manufacture Date: "+
						foundproduct.getManufacture_date()+",  Product Expiration Date: "+foundproduct.getExpiry_date()+"]");
			}else if(option==5) {
				List<Product> list=service.findProductByCat();
				for(int i=0;i<list.size();i++) {
					System.out.println("[Product Id: "+list.get(i).getPid()+",  Product Name: "+list.get(i).getPname()+",  Product Category: "+
							list.get(i).getPcat()+",  Product Price: $"+list.get(i).getPrice()+",  Product Manufacture Date: "+
							list.get(i).getManufacture_date()+",  Product Expiration Date: "+list.get(i).getExpiry_date()+"]");
				}
			}else if(option==6) {
				Product foundproduct=service.findProductById();
				System.out.println("[Product Id: "+foundproduct.getPid()+",  Product Name: "+foundproduct.getPname()+",  Product Category: "+
						foundproduct.getPcat()+",  Product Price: $"+foundproduct.getPrice()+",  Product Manufacture Date: "+
						foundproduct.getManufacture_date()+",  Product Expiration Date: "+foundproduct.getExpiry_date()+"]");
			}else if(option==7) {
				service.updateProduct();
			}else if(option==8) {
				List<Product> list=service.findExpiredProducts();
				System.out.println(list);
				/*for(int i=0;i<list.size();i++) {
					System.out.println("[Product Id: "+list.get(i).getPid()+",  Product Name: "+list.get(i).getPname()+",  Product Category: "+
							list.get(i).getPcat()+",  Product Price: $"+list.get(i).getPrice()+",  Product Manufacture Date: "+
							list.get(i).getManufacture_date()+",  Product Expiration Date"+list.get(i).getExpiry_date()+"]");
				}*/
			}else if(option==9) {
				runprogram=false;
			}
		}
	}
}
