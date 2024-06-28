package guru.sfg.brewery.bootstrap;

import guru.sfg.brewery.domain.Beer;
import guru.sfg.brewery.domain.Brewery;
import guru.sfg.brewery.domain.Customer;
import guru.sfg.brewery.repositories.BeerRepository;
import guru.sfg.brewery.repositories.BreweryRepository;
import guru.sfg.brewery.repositories.CustomerRepository;
import guru.sfg.brewery.web.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultBreweryLoader implements CommandLineRunner {

    public static final String TASTING_ROOM = "Tasting Room";
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    private final BreweryRepository breweryRepository;
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;


    public DefaultBreweryLoader(BreweryRepository breweryRepository,
                                BeerRepository beerRepository, CustomerRepository customerRepository) {
        this.breweryRepository = breweryRepository;
        this.beerRepository = beerRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBreweryData();
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {
            customerRepository.save(Customer.builder()
                    .customerName(TASTING_ROOM)
                    .apiKey(UUID.randomUUID())
                    .build());
        }
    }

    private void loadBreweryData() {
        if (breweryRepository.count() == 0){
            breweryRepository.save(Brewery
                    .builder()
                    .breweryName("Cage Brewing")
                    .build());

            Beer mangoBobs = Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle(BeerStyleEnum.IPA)
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(BEER_1_UPC)
                    .build();

            beerRepository.save(mangoBobs);

            Beer galaxyCat = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle(BeerStyleEnum.PALE_ALE)
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(BEER_2_UPC)
                    .build();

            beerRepository.save(galaxyCat);

            Beer pinball = Beer.builder()
                    .beerName("Pinball Porter")
                    .beerStyle(BeerStyleEnum.PORTER)
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(BEER_3_UPC)
                    .build();

            beerRepository.save(pinball);

        }
    }
}
