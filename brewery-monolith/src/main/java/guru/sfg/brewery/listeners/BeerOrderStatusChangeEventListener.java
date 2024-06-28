package guru.sfg.brewery.listeners;

import guru.sfg.brewery.events.BeerOrderStatusChangeEvent;
import guru.sfg.brewery.web.mappers.DateMapper;
import guru.sfg.brewery.web.model.OrderStatusUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class BeerOrderStatusChangeEventListener {

    RestTemplate restTemplate;
    DateMapper dateMapper = new DateMapper();

    public BeerOrderStatusChangeEventListener(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    @EventListener
    public void listen(BeerOrderStatusChangeEvent event){

        OrderStatusUpdate update = OrderStatusUpdate.builder()
                .id(event.getBeerOrder().getId())
                .orderId(event.getBeerOrder().getId())
                .version(event.getBeerOrder().getVersion() != null ? event.getBeerOrder().getVersion().intValue() : null)
                .createdDate(dateMapper.asOffsetDateTime(event.getBeerOrder().getCreatedDate()))
                .lastModifiedDate(dateMapper.asOffsetDateTime(event.getBeerOrder().getLastModifiedDate()))
                .orderStatus(event.getBeerOrder().getOrderStatus().toString())
                .customerRef(event.getBeerOrder().getCustomerRef())
                .build();

        try{
            log.debug("Posting to callback url");
            if (event.getBeerOrder().getOrderStatusCallbackUrl() != null) {
                restTemplate.postForObject(event.getBeerOrder().getOrderStatusCallbackUrl(), update, String.class);
            }
        } catch (Throwable t){
            log.error("Error Preforming callback for order: " + event.getBeerOrder().getId(), t);
        }
    }
}
