package mk.ukim.finki.crypto_project.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDatabase;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double current_price;
}
