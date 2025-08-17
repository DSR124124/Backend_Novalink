package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.repository.UsuarioRepository;
import com.diegoygabriela.backend_novalink.service.Inter.CodigoRelacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class CodigoRelacionServiceImpl implements CodigoRelacionService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LONGITUD_CODIGO = 8;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public String generarCodigoRelacion() {
        String codigo;
        do {
            codigo = generarCodigoAleatorio();
        } while (usuarioRepository.existsByCodigoRelacion(codigo));
        
        return codigo;
    }

    @Override
    public boolean validarCodigoRelacion(String codigo) {
        if (codigo == null || codigo.length() != LONGITUD_CODIGO) {
            return false;
        }
        
        Optional<Usuario> usuario = usuarioRepository.findByCodigoRelacion(codigo);
        return usuario.isPresent() && usuario.get().getDisponibleParaPareja();
    }

    @Override
    public Integer obtenerUsuarioPorCodigo(String codigo) {
        Optional<Usuario> usuario = usuarioRepository.findByCodigoRelacion(codigo);
        return usuario.map(Usuario::getIdUsuario).orElse(null);
    }

    @Override
    public void invalidarCodigoRelacion(String codigo) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCodigoRelacion(codigo);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setDisponibleParaPareja(false);
            usuarioRepository.save(usuario);
        }
    }
    
    private String generarCodigoAleatorio() {
        StringBuilder codigo = new StringBuilder(LONGITUD_CODIGO);
        for (int i = 0; i < LONGITUD_CODIGO; i++) {
            codigo.append(CARACTERES.charAt(RANDOM.nextInt(CARACTERES.length())));
        }
        return codigo.toString();
    }
}
