package dtos;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Instituicao")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstituicaoDTO extends UtilizadorDTO implements Serializable{

    public InstituicaoDTO() {
    }

    public InstituicaoDTO(String username, String password, String nome, String email) {
        super(username, password, nome, email);
    }
    
    @Override
    public void reset() {
        super.reset();
    }
}
