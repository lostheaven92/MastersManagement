package ejbs;

import dtos.InstituicaoDTO;
import entities.Instituicao;
import exceptions.EntityAlreadyExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import javax.validation.ConstraintViolationException;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ejb.EJBException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("/instituicoes")
public class InstituicaoBean extends Bean<Instituicao>{
    private static final String ERR_INSTITU_EXISTS = "Já existe uma instituição com o mesmo username.";
    
    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void criarREST(InstituicaoDTO instituicaoDTO) throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            criar(instituicaoDTO.getUsername(),instituicaoDTO.getPassword(),instituicaoDTO.getNome(),instituicaoDTO.getEmail());
        } catch (EntityAlreadyExistsException | MyConstraintViolationException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
    public void criar(String username, String password, String name, String email) throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Instituicao.class, username) != null) {
                throw new EntityAlreadyExistsException(ERR_INSTITU_EXISTS);
            }
            Instituicao instituicao = new Instituicao(username, name, password, email);
            em.persist(instituicao);
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    @Override //TODO:
    protected Collection<Instituicao> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
