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
    @NamedQuery(name = "getAllStudents", query="SELECT s FROM Estudante s ORDER BY s.nome")
})
public class Estudante extends Utilizador{

    //private List<Candidatura> candidaturas;
    //private List<Orientador> orientadores;
    
    public Estudante() {
    }

    public Estudante(String username, String name, String password, String email) {
        super(username, name, password, email);
    }
    
    
}
