/*
*
* Aula com Nataniel Paiva
*
* Esse projeto poderá ser distribuído da forma que você achar melhor
* O importante é que você aprenda de verdade!
*
 */
package io.spring.aula.natan.config;

import io.spring.aula.natan.entity.Perfil;
import io.spring.aula.natan.entity.Usuario;
import io.spring.aula.natan.repository.PerfilRepository;
import io.spring.aula.natan.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nataniel Paiva <nataniel.paiva@gmail.com>
 */
@Component
public class CargaInicial implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UsuarioRepository usuarioRepository_1;
    
    @Autowired
    UsuarioRepository usuarioRepository_2;
    
    @Autowired
    UsuarioRepository usuarioRepository_3;
    
    @Autowired
    PerfilRepository perfilRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {

        List<Perfil> perfis = perfilRepository.findAll();

        if (perfis.isEmpty()) {
            perfilRepository.save(new Perfil("ROLE_ADMIN"));
            perfilRepository.save(new Perfil("ROLE_OREIA"));
            perfilRepository.save(new Perfil("ROLE_MASTER"));

            Perfil perfil_admin = perfilRepository.findByNome("ROLE_ADMIN");
            Perfil perfil_master = perfilRepository.findByNome("ROLE_MASTER");

            List<Perfil> novosPerfis_admin = new ArrayList<>();
            List<Perfil> novosPerfis_master = new ArrayList<>();

            novosPerfis_admin.add(perfil_admin);
            novosPerfis_master.add(perfil_master);

            usuarioRepository_1.save(new Usuario("ADMIN", "admin@gmail.com", "123", novosPerfis_admin));
            usuarioRepository_2.save(new Usuario("FEDE", "fede@gmail.com", "123", novosPerfis_admin));
            usuarioRepository_3.save(new Usuario("PAULA", "paula@gmail.com", "123", novosPerfis_master));

        }

    }

}
