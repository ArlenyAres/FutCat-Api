package arlenyAres.FutCat_Api.services;

import arlenyAres.FutCat_Api.models.Jornada;
import arlenyAres.FutCat_Api.repositories.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaService {
    private final JornadaRepository jornadaRepository;

    @Autowired
    public JornadaService(JornadaRepository jornadaRepository) {
        this.jornadaRepository = jornadaRepository;
    }

    public List<Jornada> obtenerJornadasPorTorneo(Integer torneoId) {
        return jornadaRepository.findByFixtureTorneoId(torneoId);
    }
}
