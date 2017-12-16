/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.EstudanteDTO;
import entities.Estudante;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.Collection;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/students")
public class EstudanteBean extends Bean<Estudante>{
    private static final String ERR_STDNT_EXISTS = "A user with that username already exists.";
   
    
    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void criarREST(EstudanteDTO estudanteDTO) throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            criar(estudanteDTO.getUsername(),estudanteDTO.getPassword(),estudanteDTO.getNome(),estudanteDTO.getEmail());
        } catch (EntityAlreadyExistsException | MyConstraintViolationException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
    public void criar(String username, String password, String name, String email) throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Estudante.class, username) != null) {
                throw new EntityAlreadyExistsException(ERR_STDNT_EXISTS);
            }
            Estudante estudante = new Estudante(username, name, password, email);
            em.persist(estudante);
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
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

    @PUT
    @Path("/update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateREST(EstudanteDTO student) throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            editar(
                    student.getUsername(),
                    student.getPassword(),
                    student.getNome(),
                    student.getEmail());
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void editar(String username, String password, String nome, String email)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Estudante estudante = em.find(Estudante.class, username);
            if (estudante == null) {
                throw new EntityDoesNotExistsException("Não existe nenhum estudante com esse username.");
            }
            System.out.println("Ola");
            estudante.setPassword(password);
            estudante.setNome(nome);
            estudante.setEmail(email);
            em.merge(estudante);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
