/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alberto
 */
@Table(name="PROFESSORES")
@Entity
@NamedQuery(name = "getAllProfessores", query = "SELECT p FROM Professor p")
public class Professor extends Utilizador{

    //private List<PropostaTrabalho> propostasTrabalho;
    
    public Professor() {
    }

    public Professor(String username, String name, String password, String email) {
        super(username, name, password, email);
    }
    
    
    
    
}
