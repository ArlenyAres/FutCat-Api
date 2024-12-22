package arlenyAres.FutCat_Api.repositories;

import arlenyAres.FutCat_Api.models.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Integer> {
    // Sin métodos custom, CRUD basico
}