package guru.springframework.sfgrestdocsexample.web.mappers;

import guru.springframework.sfgrestdocsexample.domain.Beer;
import guru.springframework.sfgrestdocsexample.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto BeerToBeerDto(Beer beer);

    Beer BeerDtoToBeer(BeerDto dto);
}
