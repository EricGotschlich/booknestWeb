package de.htwberlin.webtech;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/book")
    public Book testRoute() {
        return new Book(
                "The Alchemist",
                "Paulo Coelho",
                "An Andalusian shepherd boy named Santiago travels from his homeland in Spain to the Egyptian pyramids in search of treasure. "
                        + "Along the way, he learns profound lessons about listening to his heart, recognizing opportunity, and following his personal legend.",
                "Fiction", "Finished"
        );
    }
}
