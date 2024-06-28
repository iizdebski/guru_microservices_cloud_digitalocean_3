package guru.sfg.brewery.repositories;

import guru.sfg.brewery.domain.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BreweryRepository extends JpaRepository<Brewery, UUID> {
}
