package br.edu.ifpb.infra.jsf.converter;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Pessoas;
import br.edu.ifpb.domain.PessoasJDBC;
import br.edu.ifpb.infra.persistence.memory.PessoasEmJDBC;
import br.edu.ifpb.infra.persistence.memory.PessoasEmMemoria;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.sql.SQLException;

@FacesConverter(value = "converter.Dependente")
public class ConverterDependente implements Converter {

    //private Pessoas service = new PessoasEmMemoria();
    private PessoasJDBC service = new PessoasEmJDBC();

    @Override
    public Object getAsObject(
        FacesContext context,
        UIComponent component,
        String value) {
        if (value == null) {
            return null;
        }
        Dependente dep = new Dependente();
        try {
            Dependente d = this.service.localizarDependenteComId(Long.parseLong(value));
            if(d != null){
                dep = d;
            }
            return dep;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dep;
//return new Dependente(value,"");

    }

    @Override
    public String getAsString(
        FacesContext context,
        UIComponent component,
        Object value) {

        if (value == null) {
            return null;
        }
        Dependente dep = (Dependente) value;
        return dep.getUuid();
//        return dep.getNome();
    }

}
