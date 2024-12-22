package arlenyAres.FutCat_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import arlenyAres.FutCat_Api.models.Organizador;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Integer> {
    // Sin métodos custom
}
