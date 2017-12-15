/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.EstudanteDTO;
import entities.Estudante;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/students")
public class EstudanteBean extends Bean<Estudante>{

    public void create(String username, String name, String password, String email){
        Estudante novoEstudante = new Estudante(username, name, password, email);
        em.persist(novoEstudante);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public Collection<EstudanteDTO> getAllREST(){
        return this.getAll(EstudanteDTO.class);
    }
    
    @Override
    protected Collection<Estudante> getAll() {
        return em.createNamedQuery("getAllStudents").getResultList();
    }
}
