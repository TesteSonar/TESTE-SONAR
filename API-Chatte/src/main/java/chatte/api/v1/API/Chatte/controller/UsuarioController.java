package chatte.api.v1.API.Chatte.controller;

import chatte.api.v1.API.Chatte.entities.Status;
import chatte.api.v1.API.Chatte.entities.Usuario;
import chatte.api.v1.API.Chatte.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Usuario RestController", description = "Responsável por gerir usuarios nesse projeto")
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Operation(
            summary = "Método responsável por buscar todos os usuários",
            description = "Esse método é responsável por retornar uma lista com todos os usuários cadastrados " +
                    "na base de dados" +
                    "\nPode retornar os seguintes status http: 204, 200"
    )
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> lista = usuarioRepository.findAll();

        if(lista.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(lista);
    }

    @Operation(
            summary = "Método responsável por realizar o login do usuário na aplicação",
            description = "Esse método é responsável por realizar o login do usuário na aplicação, " +
                    "recebe como parâmetro o email e a senha desse usuário" +
                    "\nPode retornar os seguintes status http: 200, 403"
    )
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity<Boolean> logar(@PathVariable("email") @Valid String email, @PathVariable("senha") @Valid String senha) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if(usuario == null) {
            return ResponseEntity.status(403).body(false);
        }

        usuario.setStatusUsuario(Status.DISPONIVEL.getDescricao());
        return ResponseEntity.status(200).body(true);
    }

    @Operation(
            summary = "Método responsável por fazer o logoff do usuário na aplicação",
            description = "Esse método é responsável por fazer o logoff do usuário na aplicação, recebe como parâmetro" +
                    "o email e a senha desse usuário" +
                    "\nPode retornar os seguintes status http: 200, 403"
    )
    @PostMapping("/logoff/{email}/{senha}")
    public ResponseEntity<Boolean> deslogar(@PathVariable("email") @Valid String email, @PathVariable("senha") @Valid String senha) {
        Usuario u = usuarioRepository.findByEmailAndSenha(email, senha);

        if(u == null) {
            return ResponseEntity.status(403).body(false);
        }

        u.setStatusUsuario(Status.OFFLINE.getDescricao());

        return ResponseEntity.status(200).body(true);
    }

    @Operation(
            summary = "Método responsável por cadastrar um novo usuário",
            description = "Esse método é responsável por inserir um usuário na base de dados, recebe como parâmetro um " +
                    "objeto do tipo Usuario" +
                    "\nPode retornar os seguintes status http: 201"
    )
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario u) {
        usuarioRepository.save(u);

        return ResponseEntity.status(201).body(u);
    }

    @Operation(
            summary = "Método responsável por atualizar as informações do usuário",
            description = "Esse método é responsável por atualizar as informações do usuário na base de dados, recebe " +
                    "como parâmetro um objeto do tipo Usuario e o id do usuário que será atualizado" +
                    "\nPode retornar os seguintes status http: 200, 404"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Usuario u, @PathVariable("id") int id) {
        u.setIdUsuario(id);

        if(usuarioRepository.existsById(id)) {
           return ResponseEntity.status(200).body(usuarioRepository.save(u));
        }

        return ResponseEntity.status(404).build();
    }

    @Operation(
            summary = "Método responsável por deletar um usuário",
            description = "Esse método é responsável por deletar um usuário da base de dados, recebe como parâmetro " +
                    "o id do usuário que será deletado" +
                    "\nPode retornar os seguintes status http: 200, 404"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }
}
