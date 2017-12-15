/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;

public abstract class Bean <E extends Serializable> {

    @PersistenceContext
    protected EntityManager em;
    
    protected static final ModelMapper mapper = new ModelMapper();
    
    protected abstract Collection<E> getAll();
    
    protected <Entity extends Serializable, DTO> DTO toDTO(Entity entity, Class<DTO> dtoClass) {
        return mapper.map(entity, dtoClass);
    }
    
    protected <Entity extends Serializable, DTO> Collection<DTO> toDTOs(Collection<Entity> entities, Class<DTO> dtoClass) {
        return entities.parallelStream().map(e -> toDTO(e, dtoClass)).collect(Collectors.toList());
    }
    
    protected <DTO> Collection<DTO> getAll(Class<DTO> dtoClass) {
        return toDTOs(getAll(), dtoClass);
    }
}
