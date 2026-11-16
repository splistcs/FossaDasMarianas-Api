package fdmapi.realizarpedido.model;

/* Java import */
import java.math.BigDecimal;

import fdmapi.produto.model.Sku;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Item
 * Mapeado conforme os Astah, o objetivo é realizar um recorte
 * do Sku - "snapshot" - de seus valores para compor o pedido.
 *
 * @see fdmapi.produto.model.Sku
 * @author Magocho
 */

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Column(name = "quantidade")
  private Integer quantidade;

  @Column(name = "preco_final")
  private BigDecimal precoFinal;

  @ManyToOne(optional = false)
  @JoinColumn(name = "sku_id", nullable = false) // coloquei o nullable = false para evitar de bugar quando um item for removido e ele estiver no carrinho de alguém.
  private Sku sku;
}
