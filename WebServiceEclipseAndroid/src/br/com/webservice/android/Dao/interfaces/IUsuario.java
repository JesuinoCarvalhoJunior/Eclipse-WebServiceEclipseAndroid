package br.com.webservice.android.Dao.interfaces;

import java.util.ArrayList;

import br.com.webservice.android.dominio.Usuario;

public interface IUsuario {

	public boolean inserirUsuario(Usuario usuario); 
	//
	public boolean atualizarUsuario(Usuario usuario) ;
	//
	public boolean excluirUsuario(Usuario usuario) ;
	//
	public ArrayList<Usuario> buscarTodosUsuairos(); 
	//
	public Usuario buscaUsuarioPorId(int id);
	// sobrecarga
	//public abstract boolean excluirUsuario(int id); 
	
	
	public Usuario Autenticar(String login, String senha);
	//public boolean Autenticar(String login, String senha);
	
}
