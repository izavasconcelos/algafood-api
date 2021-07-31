package com.algafood.infrastructure;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.city.repository.CityRepository;
import com.algafood.domain.kitchen.repository.KitchenRepository;
import com.algafood.domain.repository.*;
import com.algafood.domain.restaurant.repository.RestaurantRepository;
import com.algafood.domain.state.repository.StateRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context =
        new SpringApplicationBuilder(AlgafoodApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    KitchenRepository repository = context.getBean(KitchenRepository.class);
    RestaurantRepository restaurantRepository = context.getBean(RestaurantRepository.class);
    StateRepository stateRepository = context.getBean(StateRepository.class);
    CityRepository cityRepository = context.getBean(CityRepository.class);
    PaymentTypeRepository paymentTypeRepository = context.getBean(PaymentTypeRepository.class);
    PermissaoRepository permissaoRepository = context.getBean(PermissaoRepository.class);

  }
}
