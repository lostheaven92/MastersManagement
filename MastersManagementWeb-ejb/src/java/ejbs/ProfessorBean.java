/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.ProfessorDTO;
import entities.Professor;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.Collection;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jose
 */
@Stateless
@Path("/professores")
public class ProfessorBean extends Bean<Professor>{
    private static final String ERR_STDNT_EXISTS = "A user with that username already exists.";
    
    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void criarREST(ProfessorDTO professorDTO) throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            criar(professorDTO.getUsername(),professorDTO.getPassword(),professorDTO.getNome(),professorDTO.getEmail());
        } catch (EntityAlreadyExistsException | MyConstraintViolationException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void criar(String username, String password, String name, String email) throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Professor.class, username) != null) {
                throw new EntityAlreadyExistsException(ERR_STDNT_EXISTS);
            }
            Professor professor = new Professor(username, name, password, email);
            em.persist(professor);
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
    public Collection<ProfessorDTO> getAllREST(){
        return this.getAll(ProfessorDTO.class);
    }

    @Override
    protected Collection<Professor> getAll() {
        return em.createNamedQuery("getAllProfessores").getResultList();
    }
    
    @PUT
    @Path("/update")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editarREST(ProfessorDTO student) throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            editar(student.getUsername(),student.getPassword(),student.getNome(),student.getEmail());
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    private void editar(String username, String password, String nome, String email)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Professor professor = em.find(Professor.class, username);
            if (professor == null) {
                throw new EntityDoesNotExistsException("Não existe nenhum estudante com esse username.");
            }
            professor.setPassword(password);
            professor.setNome(nome);
            professor.setEmail(email);
            em.merge(professor);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    @DELETE
    @Path("/remove/{userid}")
    @Produces(MediaType.APPLICATION_XML)
    public void eliminarREST(@PathParam("userid") String userid) throws EntityDoesNotExistsException{
        try {
            eliminar(userid);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void eliminar(String username) throws EntityDoesNotExistsException {
        try {
            Professor professor = em.find(Professor.class, username);
            if (professor == null) {
                throw new EntityDoesNotExistsException("There is no student with that username.");
            }
            
            em.remove(professor);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
