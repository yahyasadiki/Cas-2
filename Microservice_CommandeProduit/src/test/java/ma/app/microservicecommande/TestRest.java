package ma.app.microservicecommande;

import ma.app.microservicecommande.Model.Commande;
import ma.app.microservicecommande.Repository.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommandeControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandeRepository repository;

    @Test
    void testGetAllCommandes() throws Exception {
        mockMvc.perform(get("/api/commandes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCommandeById() throws Exception {
        Commande commande = new Commande();
        commande = repository.save(commande);

        mockMvc.perform(get("/api/commandes/" + commande.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCommande() throws Exception {
        String newCommandeJson = "{\"name\":\"New Commande\"}";

        mockMvc.perform(post("/api/commandes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCommandeJson))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCommande() throws Exception {
        Commande commande = new Commande();
        commande = repository.save(commande);
        String updatedCommandeJson = "{\"name\":\"Updated Commande\"}";

        mockMvc.perform(put("/api/commandes/" + commande.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedCommandeJson))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCommande() throws Exception {
        Commande commande = new Commande();
        commande = repository.save(commande);

        mockMvc.perform(delete("/api/commandes/" + commande.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetProduitById() throws Exception {
        mockMvc.perform(get("/api/commandes/Produits/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable());
    }
}