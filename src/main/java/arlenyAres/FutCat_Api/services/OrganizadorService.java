package arlenyAres.FutCat_Api.services;

import arlenyAres.FutCat_Api.models.Organizador;
import arlenyAres.FutCat_Api.repositories.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizadorService {
    private final OrganizadorRepository organizadorRepository;

    @Autowired
    public OrganizadorService(OrganizadorRepository organizadorRepository) {
        this.organizadorRepository = organizadorRepository;
    }

    public List<Organizador> obtenerTodosLosOrganizadores() {
        return organizadorRepository.findAll();
    }

    public Organizador obtenerOrganizadorPorId(Integer id) {
        return organizadorRepository.findById(id).orElse(null);
    }

    public Organizador registrarOrganizador(Organizador organizador) {
        return organizadorRepository.save(organizador);
    }

    public void eliminarOrganizador(Integer id) {
        organizadorRepository.deleteById(id);
    }
}
