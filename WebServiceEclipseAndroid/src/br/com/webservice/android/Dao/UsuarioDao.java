package br.com.webservice.android.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.webservice.android.conexao.ConexaoMysql;
import br.com.webservice.android.dominio.Usuario;
import br.com.webservice.android.Dao.interfaces.*;
//import br.com.webservice.android.ConexaoMysql;
//import br.com.webservice.android.Usuario;

public class UsuarioDao implements IUsuario {

	//
	private Connection conexao;
	public UsuarioDao(){
		
		conexao = null;
		try {
			this.conexao = new ConexaoMysql().conectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public boolean inserirUsuario(Usuario usuario) {
	//	Connection conexao =  null;
		try {
						
		//	conexao = new ConexaoMysql().conectar();
	
	
			 
			String queryInserir = "INSERT INTO USUARIO VALUES(NULL, ?, ?,?,MD5(?))";

			PreparedStatement ppStm = conexao.prepareStatement(queryInserir);
			// indice sempre comeca com 1
			ppStm.setString(1, usuario.getNome());
			ppStm.setInt(2, usuario.getIdade());
			ppStm.setString(3, usuario.getLogin());
			ppStm.setString(4, usuario.getSenha());
			ppStm.executeUpdate();
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	//
	public boolean atualizarUsuario(Usuario usuario) {
		//Connection conexao = null;
		
		try {
		//	 Connection conexao = ConexaoMysql.obterConexao();
		//	conexao = new ConexaoMysql().conectar();
			
			String queryUpdate = "UPDATE USUARIO SET NOME = ?, IDADE = ? WHERE ID = ?";

			PreparedStatement ppStm = conexao.prepareStatement(queryUpdate);
			// indice sempre comeca com 1
			ppStm.setString(1, usuario.getNome());
			ppStm.setInt(2, usuario.getIdade());
			ppStm.setInt(3, usuario.getId());

			ppStm.executeUpdate();
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	//
	public boolean excluirUsuario(Usuario usuario) {
	//	Connection conexao = null;
		
		try {
		//	 Connection conexao = ConexaoMysql.obterConexao();
			//	ConexaoMysql con = new ConexaoMysql();


			String queryDelete = "DELETE FROM USUARIO WHERE ID = ?";

			PreparedStatement ppStm = conexao.prepareStatement(queryDelete);
			ppStm.setInt(1, usuario.getId());

			ppStm.executeUpdate();
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	//
	public ArrayList<Usuario> buscarTodosUsuairos() {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

	//	Connection conexao = null;
		
		try {
		//	 Connection conexao = ConexaoMysql.obterConexao();
		

			// Connection conexao = ConexaoMysql.obterConexao();

			String querySelect = "SELECT * FROM USUARIO";

			//PreparedStatement ppStm = conexao.prepareStatement(querySelect);

			PreparedStatement ppStm = conexao.prepareStatement(querySelect);
			
			ResultSet rSet = ppStm.executeQuery();

			// se achou o primeiro usuario entao retornou o usuario
			while (rSet.next()) {
				Usuario user = new Usuario();

				// indice sempre comeca com 1
				user.setId(rSet.getInt(1));
				user.setNome(rSet.getString(2));
				user.setIdade(rSet.getInt(3));
				lista.add(user);
				user = null;
			}
			rSet.close();
			ppStm.close();
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

	//
	public Usuario buscaUsuarioPorId(int id) {
		Usuario user = null;
		// if (confereAutenticacao)
		// {
	//	Connection conexao = null;
		
		try {
		//	 Connection conexao = ConexaoMysql.obterConexao();
	//		conexao = new ConexaoMysql().conectar();
			// Connection conexao = ConexaoMysql.obterConexao();

			String queryInserir = "SELECT * FROM USUARIO WHERE ID = ?";
			PreparedStatement ppStm = conexao.prepareStatement(queryInserir);
			// indice sempre comeca com 1
			ppStm.setInt(1, id);
			ResultSet rSet = ppStm.executeQuery();

			// se achou o primeiro usuario entao retornou o ususairo
			if (rSet.next()) {

				user = new Usuario();
				// indice sempre comeca com 1
				user.setId(rSet.getInt(1));
				user.setNome(rSet.getString(2));
				user.setIdade(rSet.getInt(3));
				// se nao achou
			} else {
				return user;
			}
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// }
		}finally{
			try {
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	/*
	 * public boolean Autenticar(String login, String senha) { Usuario user =
	 * null;
	 * 
	 * // if (confereAutenticacao) // { try { Connection conexao =
	 * ConexaoMysql.obterConexao();
	 * 
	 * 
	 * String autenticar =
	 * "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = MD5(?)";
	 * PreparedStatement ppStm = conexao.prepareStatement(autenticar);
	 * 
	 * // indice sempre comeca com 1
	 * 
	 * 
	 * ppStm.setString(1, login); ppStm.setString(2, senha); ResultSet rSet =
	 * ppStm.executeQuery();
	 * 
	 * // se achou o primeiro usuario entao retornou o ususairo if (rSet.next())
	 * {
	 * 
	 * user = new Usuario();
	 * 
	 * user.setLogin(rSet.getString(4)); user.setSenha(rSet.getString(5));
	 * 
	 * // se nao achou } else { return false; } conexao.close(); } catch
	 * (Exception e) { e.printStackTrace(); return false; // } } return true; }
	 */

	public Usuario Autenticar(String login, String senha) {
		Usuario user = null;

		// if (confereAutenticacao)
		// {
	//	Connection conexao = null;
		
		try {
		//	 Connection conexao = ConexaoMysql.obterConexao();
	//		conexao = new ConexaoMysql().conectar();


	//		ConexaoMysql con = new ConexaoMysql();
			// con.obterConexao();
		//	Connection conexao = con.obterConexao();
			// Connection conexao = ConexaoMysql.obterConexao();
			
			String autenticar = "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = MD5(?)";
			PreparedStatement ppStm = conexao.prepareStatement(autenticar);

			// indice sempre comeca com 1
			ppStm.setString(1, login);
			ppStm.setString(2, senha);
			ResultSet rSet = ppStm.executeQuery();

			// se achou o primeiro usuario entao retornou o ususairo
			if (rSet.first()) {
				user = new Usuario();

				user.setLogin(rSet.getString(4));
				user.setSenha(rSet.getString(5));
				// se nao achou
			} else {
				return user;
			}
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
			return user;
			// }
		}
		return user;
	}

	/*
	 * @Override public boolean Autenticar(String login, String senha) { //
	 * Usuario userLogar = null; confereAutenticacao = false;
	 * 
	 * try { Connection conexao = ConexaoMysql.obterConexao();
	 * 
	 * String autenticar =
	 * "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = MD5(?)";
	 * PreparedStatement ppStm = conexao.prepareStatement(autenticar); // indice
	 * sempre comeca com 1
	 * 
	 * ppStm.setString(1, login); ppStm.setString(2, senha);
	 * 
	 * ppStm.executeQuery();
	 * 
	 * confereAutenticacao = true; conexao.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return false; } //return
	 * confereAutenticacao; return true; }
	 */

	/*
	 * // sobrecarga public boolean excluirUsuario(int id) {
	 * 
	 * return excluirUsuario(new Usuario(id, "", 0, "", "")); }
	 * 
	 * /// pegar o ip public static InetAddress ObterIpHostName() {
	 * 
	 * InetAddress ip = null; String hostname; try { ip =
	 * InetAddress.getLocalHost(); hostname = ip.getHostName();
	 * System.out.println("Your current IP address : " + ip);
	 * System.out.println("Your current Hostname : " + hostname);
	 * 
	 * } catch (UnknownHostException e) {
	 * 
	 * e.printStackTrace(); } return ip; }
	 */

}
