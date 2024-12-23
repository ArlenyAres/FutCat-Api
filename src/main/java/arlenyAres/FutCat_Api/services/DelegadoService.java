package arlenyAres.FutCat_Api.services;

import arlenyAres.FutCat_Api.models.Delegado;
import arlenyAres.FutCat_Api.repositories.DelegadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegadoService {
    private final DelegadoRepository delegadoRepository;

    @Autowired
    public DelegadoService(DelegadoRepository delegadoRepository) {
        this.delegadoRepository = delegadoRepository;
    }

    public List<Delegado> obtenerTodosLosDelegados() {
        return delegadoRepository.findAll();
    }

    public Delegado obtenerDelegadoPorId(Integer id) {
        return delegadoRepository.findById(id).orElse(null);
    }

    public Delegado registrarDelegado(Delegado delegado) {
        return delegadoRepository.save(delegado);
    }

    public void eliminarDelegado(Integer id) {
        delegadoRepository.deleteById(id);
    }
}
