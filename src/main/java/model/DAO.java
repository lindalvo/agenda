package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {
	/** modulo de conexão **/
	// pamateros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimeZone=UTC";
	private String user = "root";
	private String password = "mysql";

	// Método de conexão
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
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
