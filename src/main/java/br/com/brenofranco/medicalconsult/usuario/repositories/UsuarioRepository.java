package br.com.brenofranco.medicalconsult.usuario.repositories;

import br.com.brenofranco.medicalconsult.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
