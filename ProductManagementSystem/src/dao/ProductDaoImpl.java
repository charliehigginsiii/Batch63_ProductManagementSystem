package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import bean.Product;

public class ProductDaoImpl implements ProductDao {
	
	Properties prop=new Properties();
	/*ProductDaoImpl classobj=new ProductDaoImpl();
	Class obj=classobj.getClass();
	URL propertiesfilepath=obj.getResource("test.properties");*/
	
	@Override
	public void addProduct(Product product) {
		try {
			FileInputStream input=new FileInputStream("C:/Users/Chuck/git/Batch63_ProductManagementSystem/ProductManagementSystem/src/test.properties");
			prop.load(input);
			input.close();
			System.out.println("Enter Product Id: ");
			product.setPid(new Scanner(System.in).nextInt());
			System.out.println("Enter Product Name: ");
			product.setPname(new Scanner(System.in).nextLine());
			System.out.println("Enter Product Category: ");
			product.setPcat(new Scanner(System.in).nextLine());
			System.out.println("Enter Product Price: ");
			product.setPrice(new Scanner(System.in).nextDouble());
			System.out.println("Enter Product Expiry Date: ");
			product.setExpiry_date(new Scanner(System.in).nextLine());
			System.out.println("Enter Manufacture Date");
			product.setManufacture_date(new Scanner(System.in).nextLine());
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("insert into products(pid, pname, pcat, manufacture_date,price, expiry_date)values(?,?,?,?,?,?)");
			prepared.setInt(1,product.getPid());
			prepared.setString(2,product.getPname());
			prepared.setString(3,product.getPcat());
			prepared.setString(4,product.getManufacture_date());
			prepared.setDouble(5, product.getPrice());
			prepared.setString(6, product.getExpiry_date());
			prepared.execute();
			System.out.println("1 row inserted.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProductById() {
		try {
			FileInputStream input=new FileInputStream("C:/Users/Chuck/git/Batch63_ProductManagementSystem/ProductManagementSystem/src/test.properties");
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("delete from products where pid=?");
			prepared.setInt(1,new Scanner(System.in).nextInt());
			prepared.execute();
			System.out.println("1 row inserted.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteProductByCat() {
		try {
			FileInputStream input=new FileInputStream("C:/Users/Chuck/git/Batch63_ProductManagementSystem/ProductManagementSystem/src/test.properties");
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("delete from products where pcat=?");
			System.out.println("Enter Product Id");
			prepared.setString(1,new Scanner(System.in).nextLine());
			prepared.execute();
			System.out.println("1 row inserted.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Product findCheapestProductInCat() {
		Product fetchedproduct=null;
		try {
			FileInputStream input=new FileInputStream("C:/Users/Chuck/git/Batch63_ProductManagementSystem/ProductManagementSystem/src/test.properties");
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("select * from products where pcat=? order by price limit 0,1");
			System.out.println("Enter category");
			prepared.setString(1, new Scanner(System.in).nextLine());
			ResultSet results=prepared.executeQuery();
			while(results.next()) {
				fetchedproduct=new Product(results.getInt(1),results.getString(2),results.getString(3),results.getString(5)
				,results.getDouble(4),results.getString(6));
			}
			System.out.println("1 row inserted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fetchedproduct;
	}

	@Override
	public List<Product> findProductByCat() {
		List<Product> list=new ArrayList();
		try {
			FileInputStream input=new FileInputStream("C:/Users/Chuck/git/Batch63_ProductManagementSystem/ProductManagementSystem/src/test.properties");
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("select * from products where pcat=?");
			System.out.println("Enter Product Category: ");
			prepared.setString(1,new Scanner(System.in).nextLine());
			ResultSet results=prepared.executeQuery();
			while(results.next()) {
				list.add(new Product(results.getInt(1),results.getString(2),results.getString(3),results.getString(5)
				,results.getDouble(4),results.getString(6)));
			}
			System.out.println("1 row inserted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Product findProductById() {
		Product fetchedproduct=null;
		try {
			FileInputStream input=new FileInputStream("C:/Users/Chuck/git/Batch63_ProductManagementSystem/ProductManagementSystem/src/test.properties");
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("select * from products order where pid=?");
			System.out.println("Enter Id");
			prepared.setInt(1,new Scanner(System.in).nextInt());
			ResultSet results=prepared.executeQuery();
			while(results.next()) {
				fetchedproduct=new Product(results.getInt(1),results.getString(2),results.getString(3),results.getString(5)
				,results.getDouble(4),results.getString(6));
			}
			System.out.println("1 row inserted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fetchedproduct;
	}

	@Override
	public void updateProduct() {
		try {
			FileInputStream input=new FileInputStream("C:/Users/Chuck/git/Batch63_ProductManagementSystem/ProductManagementSystem/src/test.properties");
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			System.out.println("Choose option 1-6:"+"\n"+
					"1. Update id"+"\n"+
					"2. Update name"+"\n"+
					"3. Update category"+"\n"+
					"4. Update manufacture_date"+"\n"+
					"5. Update price"+"\n"+
					"6. Update expiry date"+"\n");
			int option=new Scanner(System.in).nextInt();
			if(option==1) {
				System.out.println("Update Product id: ");
				PreparedStatement prepared=con.prepareStatement("update products set pid=? where pid=?");
				System.out.println("What do you want to change the id to? ");
				prepared.setInt(1,new Scanner(System.in).nextInt());
				System.out.println("Which id do you want to change? ");
				prepared.setInt(2,new Scanner(System.in).nextInt());
				prepared.execute();
			}else if(option==2) {
				System.out.println("Update Product name: ");
				PreparedStatement prepared=con.prepareStatement("update products set pname=? where pid=?");
				System.out.println("What do you want to change the name to? ");
				prepared.setString(1,new Scanner(System.in).nextLine());
				System.out.println("What is the id of the name you want to change? ");
				prepared.setInt(2,new Scanner(System.in).nextInt());
				prepared.execute();
			}else if(option==3) {
				System.out.println("Update Product category: ");
				PreparedStatement prepared=con.prepareStatement("update products set pcat=? where pid=?");
				System.out.println("What do you want to change the category to? ");
				prepared.setString(1,new Scanner(System.in).nextLine());
				System.out.println("What is the id of the category you want to change? ");
				prepared.setInt(2,new Scanner(System.in).nextInt());
				prepared.execute();
			}else if(option==4) {
				System.out.println("Update Product Manufacture date: ");
				PreparedStatement prepared=con.prepareStatement("update products set manufacture_date=? where pid=?");
				System.out.println("What do you want to change the manufacture date to? ");
				prepared.setString(1,new Scanner(System.in).nextLine());
				System.out.println("What is the id of the manufacture date you want to change? ");
				prepared.setInt(2,new Scanner(System.in).nextInt());
				prepared.execute();
			}else if(option==5) {
				System.out.println("Update Product Price: ");
				PreparedStatement prepared=con.prepareStatement("update products set price=? where pid=?");
				System.out.println("What do you want to change the price to? ");
				prepared.setDouble(1,new Scanner(System.in).nextDouble());
				System.out.println("What is the id of the price you want to change? ");
				prepared.setInt(2,new Scanner(System.in).nextInt());
				prepared.execute();
			}else if(option==6) {
				System.out.println("Update Product Expiry date: ");
				PreparedStatement prepared=con.prepareStatement("update products set expiry_date=? where pid=?");
				System.out.println("What do you want to change the expiry date to? ");
				prepared.setString(1,new Scanner(System.in).nextLine());
				System.out.println("What is the id of the expiry date you want to change? ");
				prepared.setInt(2,new Scanner(System.in).nextInt());
				prepared.execute();
			}
			
			
			System.out.println("1 row inserted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Product> findExpiredProducts() {
		List<Product> list=new ArrayList();
		try {
			FileInputStream input=new FileInputStream("C:/Users/Chuck/git/Batch63_ProductManagementSystem/ProductManagementSystem/src/test.properties");
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("select * from products where pcat=?");
			System.out.println("Enter Product Category: ");
			prepared.setString(1,new Scanner(System.in).nextLine());
			ResultSet results=prepared.executeQuery();
			while(results.next()) {
				list.add(new Product(results.getInt(1),results.getString(2),results.getString(3),results.getString(5)
				,results.getDouble(4),results.getString(6)));
			}
			System.out.println("1 row inserted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

}
