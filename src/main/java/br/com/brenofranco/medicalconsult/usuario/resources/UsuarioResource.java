package br.com.brenofranco.medicalconsult.usuario.resources;

import br.com.brenofranco.medicalconsult.usuario.domain.Usuario;
import br.com.brenofranco.medicalconsult.usuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarUsuario(id);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarCliente(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario upCliente = usuarioService.atualizarUsuario(id, usuario);
        return ResponseEntity.ok().body(upCliente);
    }
}
