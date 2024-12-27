package ma.app.microservicecommande.Repository;

import ma.app.microservicecommande.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}


