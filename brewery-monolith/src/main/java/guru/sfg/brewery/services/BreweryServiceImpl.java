package guru.sfg.brewery.services;

import guru.sfg.brewery.domain.Brewery;
import guru.sfg.brewery.repositories.BreweryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryServiceImpl implements BreweryService{

    private BreweryRepository breweryRepository;
    public BreweryServiceImpl(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    @Override
    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }
}
