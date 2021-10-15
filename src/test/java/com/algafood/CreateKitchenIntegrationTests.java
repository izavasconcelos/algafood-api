package com.algafood;

import com.algafood.domain.kitchen.entity.Kitchen;
import com.algafood.domain.kitchen.service.KitchenService;
import com.algafood.infrastructure.common.exception.EntityUsedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CreateKitchenIntegrationTests {

    @Autowired
    KitchenService kitchenService;

    @Test
    void shouldCreateKitchen() {
        // cenário
        Kitchen newKitchen = new Kitchen();
        newKitchen.setName("nova cozinha");

        // ação
        newKitchen = kitchenService.createKitchen(newKitchen);

        // validação
        assertThat(newKitchen).isNotNull();
        assertThat(newKitchen.getId()).isNotNull();
    }

    @Test
    void shouldNotCreateKitchenWhenNameExists() {
        // cenário
        Kitchen newKitchen = new Kitchen();
        newKitchen.setName("nova cozinha");

        // ação
        EntityUsedException exception =
            Assertions.assertThrows(EntityUsedException.class, () -> {
                kitchenService.createKitchen(newKitchen);
            });

        // validação
        assertThat(exception).isNotNull();
    }


}
