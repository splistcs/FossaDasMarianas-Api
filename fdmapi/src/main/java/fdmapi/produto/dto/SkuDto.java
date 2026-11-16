package fdmapi.produto.dto;

/* Java import */
import java.math.BigDecimal;
import java.util.Map;

import fdmapi.produto.model.Sku;

/**
 * SkuDto
 * Servir como transporte para Class Sku,
 * isolando o db, o curioso é ser praticamente
 * autocontida como a Class Endereco.
 *
 * @see fdmapi.mantercadastro.model.Endereco
 * @see fdmapi.produto.model.Sku
 *
 * @author Magocho
 */

public record SkuDto(Long id, Integer estoque, BigDecimal preco, 
    Map<String, String> especificacoes, Integer pesoGramas, 
    String codigoUniversal, Integer alturaCm, Integer larguraCm, 
    Integer comprimentoCm) {

  public static SkuDto from(Sku sku) {
    if (sku == null) {
      return null; // estou tendo um bug que acontece quando deleto um sku e ele está no carrinho de alguém, tentando resolver
    }

    return new SkuDto(sku.getId(), sku.getEstoque(), sku.getPreco(),
        sku.getEspecificacoes(), sku.getPesoGramas(), 
        sku.getCodigoUniversal(), sku.getAlturaCm(), sku.getLarguraCm(), 
        sku.getComprimentoCm());
  }
}
