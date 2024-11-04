package database;


import com.mysql.cj.jdbc.MysqlDataSource;

import beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public Connection getConnection(){
        if(connection == null) {

            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("shop");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setPort(8889);
            try{
                connection = dataSource.getConnection();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            System.out.println(connection.toString());
        }
        return connection;
    }
    /**
     * Retrieves all products from the database.
     *
     * @return a ResultSet containing all product data, or null if a database error occurs
     */
    public ResultSet getProducts() {
        String sql = "select * from products";
        resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resultSet;
    }
    
    public int addNewUser(User user){
        int result = 0;
        try {
	        preparedStatement = getConnection().prepareStatement("INSERT INTO users(first_name, last_name,email, password) VALUES(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, user.getFirstName());
	        preparedStatement.setString(2, user.getLastName());
	        preparedStatement.setString(3, user.getEmail());
	        preparedStatement.setString(4, user.getPassword());
	        	
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            return resultSet.getInt(1);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public boolean isUserRegistered(String email){
    	boolean res = false;
		try {
			 preparedStatement = getConnection().prepareStatement("SELECT email FROM users WHERE email = ?");
			 preparedStatement.setString(1, email); 
		     ResultSet resultSet = preparedStatement.executeQuery();
		     return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
    }
    public boolean verifyCredentials(String email, String password) {
    	boolean res = false;
		try {
			 preparedStatement = getConnection().prepareStatement("SELECT email FROM users WHERE email = ? AND password = ?");
			 preparedStatement.setString(1, email); 
			 preparedStatement.setString(2, password);
		     ResultSet resultSet = preparedStatement.executeQuery();
		     return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
    }
   
}