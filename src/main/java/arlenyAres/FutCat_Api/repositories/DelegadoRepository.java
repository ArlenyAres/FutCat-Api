package arlenyAres.FutCat_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import arlenyAres.FutCat_Api.models.Delegado;

@Repository
public interface DelegadoRepository extends JpaRepository<Delegado, Integer> {

}