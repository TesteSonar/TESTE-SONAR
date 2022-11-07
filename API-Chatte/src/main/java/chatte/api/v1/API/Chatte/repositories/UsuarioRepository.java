package chatte.api.v1.API.Chatte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import chatte.api.v1.API.Chatte.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmailAndSenha(String email, String senha);

}
