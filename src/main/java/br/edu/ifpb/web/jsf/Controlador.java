package br.edu.ifpb.web.jsf;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import br.edu.ifpb.domain.*;
import br.edu.ifpb.domain.service.AlteraNomeDasPessoas;
import br.edu.ifpb.infra.persistence.memory.PessoasEmJDBC;
import br.edu.ifpb.infra.persistence.memory.PessoasEmMemoria;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 01/02/2021, 10:01:33
 */
//@ManagedBean
@Named
//@RequestScoped
@SessionScoped
public class Controlador implements Serializable {

    private Pessoa pessoa = new Pessoa();
    private Pessoa pessoaPesquisada = new Pessoa();
    private CPF cpfPesquisado = new CPF();
    private Dependente dependente = new Dependente();

    private AlteraNomeDasPessoas service = new AlteraNomeDasPessoas();

    /*private Pessoas pessoas = new PessoasEmMemoria();*/
    private PessoasJDBC pessoas = new PessoasEmJDBC();

    public String redirecionar() {
        // executando a lógica de negócio
        service.alteraNome(pessoa);
        pessoa.alterarNome();
        // redirecionando...
        return null; // fica na página original
//        return "home"; // encmainhar a requisição à página 
//        return "home.xhtml?faces-redirect=true"; // nova requisição
    }

    public String adicionar() {
        // deveríamos ter um objeto responsável por encapsular essa regra de negócio
        Pessoa retorno = null;
        try {
            retorno = this.pessoas.localizarPessoaComId(
                    this.pessoa.getId()
            );
            if(retorno == null){
                this.pessoas.nova(this.pessoa);
            }else{
                this.pessoas.atualizar(this.pessoa);
            }
            this.pessoa = new Pessoa();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String excluir(Pessoa pessoa){
        try {
            this.pessoas.excluir(pessoa);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pessoaPesquisada = new Pessoa();
        return null;
    }
    public String editar(Pessoa pessoa){
        this.pessoa = pessoa;
        return "edit";
    }

    public String adicionarDependente() {
        try {
            this.pessoas.novo(dependente);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.dependente = new Dependente();
        return null;
    }


    public List<Dependente> todosOsDependentes(){
        try {
            return this.pessoas.todosOsDepentendes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Pessoa> todasAsPessoas() {
        try {
            return this.pessoas.todas();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pessoa pessoaPorCpf(){
        pessoaPesquisada.setCpf(cpfPesquisado);
        try {
            pessoaPesquisada = pessoas.localizarPorCPF(pessoaPesquisada.getCpf());
            return pessoaPesquisada;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Pessoa" + pessoaPesquisada.toString());
        return null;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }

    public Pessoa getPessoaPesquisada(){return pessoaPesquisada;}

    public void setPessoaPesquisada(Pessoa pessoaPesquisada) {
        this.pessoaPesquisada = pessoaPesquisada;
    }

    public CPF getCpfPesquisado() {
        return cpfPesquisado;
    }

    public void setCpfPesquisado(CPF cpfPesquisado) {
        this.cpfPesquisado = cpfPesquisado;
    }
}
