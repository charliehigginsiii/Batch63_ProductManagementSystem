package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import bean.Product;

public class ProductDaoImpl implements ProductDao {
	
	Properties prop=new Properties();
	URL propertiesfilepath=ProductDaoImpl.class.getResource("test.properties");
	
	@Override
	public void addProduct(Product product) {
		try {
			FileInputStream input=new FileInputStream(propertiesfilepath.getFile());
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("insert into products(pid, pname, pcat, manufacture_date,price, expiry_date)values(?,?,?,?,?,?)");
			prepared.setInt(1,product.getPid());
			prepared.setString(2,product.getPname());
			prepared.setString(3,product.getPcat());
			prepared.setDate(4,(Date) product.getManufacture_date());
			prepared.setDouble(5, product.getPrice());
			prepared.setDate(6, (Date) product.getExpiry_date());
			prepared.execute();
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
			FileInputStream input=new FileInputStream(propertiesfilepath.getFile());
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("delete from products where pid=?");
			prepared.setInt(1,new Scanner(System.in).nextInt());
			prepared.execute();
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
			FileInputStream input=new FileInputStream(propertiesfilepath.getFile());
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
			FileInputStream input=new FileInputStream(propertiesfilepath.getFile());
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
				//int pid, String pname, String pcat, String manufacture_date, double price, String expiry_date
				fetchedproduct=new Product(results.getInt(1),results.getString(2),results.getString(3),results.getDate(4)
				,results.getDouble(5),results.getDate(6));
			}
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
			FileInputStream input=new FileInputStream(propertiesfilepath.getFile());
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
				list.add(new Product(results.getInt(1),results.getString(2),results.getString(3),results.getDate(4)
						,results.getDouble(5),results.getDate(6)));
			}
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
			FileInputStream input=new FileInputStream(propertiesfilepath.getFile());
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			PreparedStatement prepared=con.prepareStatement("select * from products where pid=?");
			System.out.println("Enter Id");
			prepared.setInt(1,new Scanner(System.in).nextInt());
			ResultSet results=prepared.executeQuery();
			while(results.next()) {
				fetchedproduct=new Product(results.getInt(1),results.getString(2),results.getString(3),results.getDate(4)
						,results.getDouble(5),results.getDate(6));
			}
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
			FileInputStream input=new FileInputStream(propertiesfilepath.getFile());
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

	public List<Product> findExpiredProducts() {
		List<Product> list=new ArrayList();
		try {
			FileInputStream input=new FileInputStream(propertiesfilepath.getFile());
			prop.load(input);
			input.close();
			Class.forName(prop.getProperty("jdbc.driver"));
			Connection con=DriverManager.getConnection(prop.getProperty("jdbc.url"),prop.getProperty("jdbc.username"),prop.getProperty("jdbc.password"));
			Statement st=con.createStatement();
			st.execute("Use batch63");
			DateFormat dateformatter=new SimpleDateFormat("YYYY-MM-dd");
			java.util.Date date = new java.util.Date();
			Date currentdate=new Date(date.getTime());
			String currentdatetext=dateformatter.format(currentdate);
			PreparedStatement prepared=con.prepareStatement("select * from products where expiry_date<=?");
			prepared.setDate(1, java.sql.Date.valueOf(currentdatetext));
			ResultSet results=prepared.executeQuery();
			while(results.next()) {
				list.add(new Product(results.getInt(1),results.getString(2),results.getString(3),results.getDate(4)
						,results.getDouble(5),results.getDate(6)));
			}
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
