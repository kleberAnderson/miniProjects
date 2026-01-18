package edu.curso;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class ContatoControl {

    private ObservableList<Contato> lista = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");

    private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(
            LocalDate.now()
    );

    private ContatoDAO contatoDAO = new ContatoDAOImpl();

    public void apagar( int indice ) {
        Contato c = lista.get(indice);
        contatoDAO.deletar( c.getId() );
    }

    public void editar( int indice ) {
        Contato c = lista.get(indice);
        paraTela( c );
    }

    public Contato procurarContatoPorId( long id ) {
        for (Contato c : lista) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void gravar() {
        Contato contato = paraEntidade();
        Contato contatoAntigo = procurarContatoPorId( contato.getId() );
        if ( contatoAntigo != null ) {
            contatoDAO.atualizar(contato.getId(), contato);
        } else {
            contatoDAO.criar( contato );
            lista.add( contato );
        }
    }

    public void pesquisar() {
        for (Contato c : lista) {
            if ( c.getNome().contains( nome.get() )) {
                paraTela(c);
                break;
            }
        }
    }

    public Contato paraEntidade() {
        Contato contato = new Contato();
        contato.setId( id.get() );
        contato.setNome( nome.get() );
        contato.setTelefone( telefone.get() );
        contato.setEmail( email.get() );
        contato.setNascimento( nascimento.get() );
        return contato;
    }

    public void paraTela( Contato contato ) {
        if (contato != null) {
            id.set( contato.getId() );
            nome.set( contato.getNome() );
            telefone.set( contato.getTelefone() );
            email.set( contato.getEmail() );
            nascimento.set( contato.getNascimento() );
        }
    }

    public void limparTela() {
        id.set( 0 );
        nome.set("");
        telefone.set("");
        email.set("");
        nascimento.set( LocalDate.now() );
    }

    public ObservableList<Contato> getLista() {
        return lista;
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public ObjectProperty<LocalDate> nascimentoProperty() {
        return nascimento;
    }
}
