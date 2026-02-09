package br.com.agenda.aplicacao;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		contatoDao.deleteByID(5);
		
		try {
			for (Contato c : contatoDao.getContatos()) {
				System.out.println("Contato: " + c.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
