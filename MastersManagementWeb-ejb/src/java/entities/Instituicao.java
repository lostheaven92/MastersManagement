/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Alberto
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllInstituicoes", query="SELECT i FROM Instituicao i ORDER BY i.nome")
})
public class Instituicao extends Utilizador{

    //private List<PropostaTrabalho> propostasTrabalho;
    
    public Instituicao() {
    }

    public Instituicao(String username, String name, String password, String email) {
        super(username, name, password, email);
    }
}
