package br.com.webservice.android.conexao;

import java.sql.SQLException;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {

		ConexaoMysql con = new ConexaoMysql();
		
	//	ConexaoMysql.obterConexao();

		System.out.println(ConexaoMysql.status);
	}

}
