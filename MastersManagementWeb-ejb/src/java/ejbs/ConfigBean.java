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
    
    @PostConstruct
    public void populateDB(){
        try {
            estudanteBean.criar("a1", "Jose", "a1", "jose@dad.com");
            estudanteBean.criar("a2", "Jessica", "a2", "jessica@dad.com");
            estudanteBean.criar("a3", "Alberto", "a3", "alberto@dad.com");
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

}
