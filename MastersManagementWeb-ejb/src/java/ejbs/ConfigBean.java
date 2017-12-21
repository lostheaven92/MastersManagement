/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class ConfigBean {
    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");    
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private EstudanteBean estudanteBean;
    
    @EJB
    private InstituicaoBean instituicaoBean;
    
    @PostConstruct
    public void populateDB(){
        try {
            estudanteBean.criar("a1", "a1", "Jose", "jose@dad.com");
            estudanteBean.criar("a2", "a2", "Jessica", "jessica@dad.com");
            estudanteBean.criar("a3", "a3", "Alberto", "alberto@dad.com");
            
            instituicaoBean.criar("i1", "i1", "Instituicao1", "inst1@dad.com");
            instituicaoBean.criar("i2", "i2", "Instituicao2", "inst2@dad.com");
            instituicaoBean.criar("i3", "i3", "Instituicao3", "inst3@dad.com");
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

}
