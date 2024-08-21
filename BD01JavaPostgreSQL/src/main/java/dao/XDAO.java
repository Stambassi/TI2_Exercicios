package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.X;

public class XDAO extends DAO {
	
	public XDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(X x) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO X (ID, Y) "
				       + "VALUES ("+x.getID()+ ", '" + x.getX() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public X get(int ID) {
		X x = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id=" + ID;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 x = new X(rs.getInt("ID"), rs.getBoolean("Y"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return x;
	}
	
	
	public List<X> getAllX() {
		return get("");
	}

	
	public List<X> getOrderByID() {
		return get("id");		
	}
	
	
	public List<X> getOrderByX() {
		return get("y");		
	}
	
	
	private List<X> get(String orderBy) {	
	
		List<X> x = new ArrayList<X>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM X" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	X u = new X(rs.getInt("id"), rs.getBoolean("y"));
	            x.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return x;
	}

	
	
	
	public boolean updateX(X x) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE X SET Y = '" + x.getX() + "'"
					   + " WHERE ID = " + x.getID();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int ID) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM x WHERE ID = " + ID;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}