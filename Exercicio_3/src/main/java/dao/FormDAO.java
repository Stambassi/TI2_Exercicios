package dao;

import model.Form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FormDAO extends DAO {	
	public FormDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Form form) {
		boolean status = false;
		try {
			String sql = "INSERT INTO form (id_usuario, id_animal, status, visitas_ong, experiencia, moradia, disponibilidade, viagem, comentarios) "
		               + "VALUES (" + form.getIdUsuario() + "," + form.getIdAnimal() + "," + form.getStatus() + "," + form.getVisitas() + ","
		               + form.getExp() + ",'"  + form.getMoradia() + "','"  + form.getDisponibilidade() + "','"
		               + form.getViagem() + "','"  + form.getComentarios() + "'); ";
			
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Form get(int id) {
		Form form = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM form WHERE id_form="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 form = new Form(rs.getInt("id_form"),rs.getInt("id_animal"),rs.getInt("id_usuario"), rs.getInt("status"), 
	        			 rs.getBoolean("visitas_ong"), rs.getBoolean("experiencia"),
	        			 rs.getString("moradia"), rs.getString("disponibilidade"), rs.getString("viagem"), 
	        			 rs.getString("comentarios"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return form;
	}
	
	
	public List<Form> get() {
		return get("");
	}

	
	public List<Form> getOrderByID() {
		return get("id_form");		
	}
	
	
	public List<Form> getOrderByStatus() {
		return get("status");		
	}
	
	
	public List<Form> getOrderByIdAnimal() {
		return get("id_animal");		
	}
	
	
	private List<Form> get(String orderBy) {
		List<Form> produtos = new ArrayList<Form>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM form" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Form p = new Form(rs.getInt("id_form"),rs.getInt("id_animal"),rs.getInt("id_usuario"), rs.getInt("status"), 
	        			 rs.getBoolean("visitas_ong"), rs.getBoolean("experiencia"),
	        			 rs.getString("moradia"), rs.getString("disponibilidade"), rs.getString("viagem"), 
	        			 rs.getString("comentarios"));
	        	produtos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}
	
	
	public boolean update(Form form) {
		boolean status = false;
		try {  
			String sql = "UPDATE form SET status = " + form.getStatus() + ", "
					   + "visitas_ong = " + form.getVisitas() + ", " 
					   + "experiencia = " + form.getExp() + ", "
					   + "moradia = '" + form.getMoradia() + "', " 
					   + "disponibilidade = '" + form.getDisponibilidade() + "', "
					   + "viagem = '" + form.getViagem() + "', "
					   + "comentarios = '" + form.getComentarios() + "' "
					   + "WHERE id_form = " + form.getIdForm();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM form WHERE id_form = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}