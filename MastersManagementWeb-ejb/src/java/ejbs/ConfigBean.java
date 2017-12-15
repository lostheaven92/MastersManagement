/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class ConfigBean {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private EstudanteBean estudanteBean;
    
    @PostConstruct
    public void populateDB(){
        estudanteBean.create("a1", "Jose", "a1", "jose@dad.com");
        estudanteBean.create("a2", "Jessica", "a2", "jessica@dad.com");
        estudanteBean.create("a3", "Alberto", "a3", "alberto@dad.com");
    }

}
