package guru.springframework.msscjacksonexamples.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

@JsonTest
class BeerDtoTest extends BaseTest{

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserialize() throws IOException {
        String json = "{\"beerName\": \"BeerName\",\n" +
                "  \"beerStyle\": \"Ale\",\n" +
                "  \"upc\": 123123123123,\n" +
                "  \"price\": \"12.99\",\n" +
                "  \"createdDate\": \"2019-06-03T21:01:53-0400\",\n" +
                "  \"lastUpdatedDate\": \"2019-06-03T21:01:53.628287-04:00\",\n" +
                "  \"myLocalDate\": \"2012-08-15\",\n" +
                "  \"beerId\": \"8ed4c7eb-ef3a-437e-823e-a26497ed7e71\"}\n";
        BeerDto dto = objectMapper.readValue(json, BeerDto.class);

        System.out.println(dto);

    }
}