package ma.app.microservicecommande.Controller;

import ma.app.microservicecommande.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandeHealthIndicator implements HealthIndicator {

    @Autowired
    private CommandeRepository repository;

    @Override
    public Health health() {
        boolean hasCommandes = repository.count() > 0;
        if (hasCommandes) {
            return Health.up().build();
        } else {
            return Health.down().withDetail("No Commandes found", 0).build();
        }
    }
}

