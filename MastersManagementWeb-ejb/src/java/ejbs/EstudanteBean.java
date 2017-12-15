/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Estudante;
import java.util.Collection;
import javax.ejb.Stateless;

@Stateless
public class EstudanteBean extends Bean<Estudante>{

    public void create(String username, String name, String password, String email){
        Estudante novoEstudante = new Estudante(username, name, password, email);
        em.persist(novoEstudante);
    }
    
    public Collection<Estudante> getAllREST(){
        return this.getAll();
    }
    
    @Override
    protected Collection<Estudante> getAll() {
        return em.createNamedQuery("getAllStudents").getResultList();
    }
}
