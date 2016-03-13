package br.com.webservice.android.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ConexaoMysql implements Runnable {

	static String status;
	private static String URL = "jdbc:mysql://localhost/bdwebservice";
	private static String USER = "root";
	private static String SENHA = "root";
	private static int PORTA = 3306;

	private Connection conn;

	public ConexaoMysql() {
		super();

		this.URL = String.format(this.URL, this.PORTA);
	       try {
			conectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        disconectar();
	}

	// public static Connection obterConexao() {//throws SQLException {

	public Connection conectar()  throws SQLException {

		Thread thread = new Thread();
		thread.start();

		try {
			thread.join();
			status = "Conectado ao BD.";

			// try {
			// Class.forName("com.mysql.jdbc.Driver").newInstance();

			// status = "Conectado ao BD.";
			/*
			 * }
			 * 
			 * catch (SQLException e) { status = e.getMessage();
			 */
		} catch (Exception e) {
			status = e.getMessage();
		}
		 return DriverManager.getConnection(URL, USER, SENHA);

		// return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection(URL, USER, SENHA);

		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			status = e.getMessage();
		} catch (Exception e) {
			status = e.getMessage();
		}

	}

	public void disconectar() {
		try {
			if (this.conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException ex) {
			System.out.println("ERRO: " + ex);
		} finally {
			this.conn = null;
		}
	}
	
	/*

	public ResultSet select(String query) {
		this.conectar();
		ResultSet resultSet = null;
		try {
			resultSet = new ExecuteDB(this.conn, query).execute().get();
		} catch (Exception e) {
			status = e.getMessage();
		}
		return resultSet;
	}

	public ResultSet execute(String query) {
		this.conectar();
		ResultSet resultSet = null;
		try {
			resultSet = new ExecuteDB(this.conn, query).execute().get();
		} catch (Exception e) {
			status = e.getMessage();
		}
		return resultSet;
	}
	
	*/

}
