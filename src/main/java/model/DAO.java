package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** modulo de conex�o **/
	// pamateros de conex�o

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimeZone=UTC";
	private String user = "root";
	private String password = "mysql";

	// M�todo de conex�o
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void inserirContato(JavaBeans contato) {
		String create = "insert into dbagenda.contatos (nome,fone,email) values (?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** crud read**/
	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<JavaBeans>();
		String read = "select * from dbagenda.contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery(read);
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// populando o arraylist
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
            return contatos; 
		} catch (Exception e) {
			System.out.println(e);
			return null;
			// TODO: handle exception
		}
	}
	
	public JavaBeans selecionarContato(Integer pidcon) {
		String read2 = "select * from dbagenda.contatos where idcon = " + String.valueOf(pidcon);
		JavaBeans resultado = null;
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			ResultSet rs = pst.executeQuery(read2);
			if (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				resultado = new JavaBeans(idcon,nome,fone,email);
			}
			con.close();
			return resultado;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void editarContato(JavaBeans contato) {
		String create = "update dbagenda.contatos set nome=?,fone=?,email=? where idcon=?;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void excluirContato(String pidcon) {
		String delete = "delete from dbagenda.contatos where idcon=?;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, pidcon);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
