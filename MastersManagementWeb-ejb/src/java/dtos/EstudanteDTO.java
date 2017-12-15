package dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Estudante")
@XmlAccessorType(XmlAccessType.FIELD)
public class EstudanteDTO extends UtilizadorDTO implements Serializable{
   
    public EstudanteDTO(){
    }

    public EstudanteDTO(
            String username,
            String password,
            String nome,
            String email) {
        super(username, password, nome, email);
    }
    
    @Override
    public void reset() {
        super.reset();
    }
}
