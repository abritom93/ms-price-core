package cl.price.core.adapters.jdbc.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import cl.price.core.domain.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="prices")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PriceEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long brandId;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private Long priceList;
	
	private Long productId;
	
	private Integer priority;
	
	@Column(precision = 8, scale = 2)
	private BigDecimal price;
	
	@Column(length = 3)
	private String currency;

	public Price toDomain() {
		return Price.builder()
				.id(this.id)
				.brandId(this.brandId)
				.startDate(this.startDate)
				.endDate(this.endDate)
				.priceList(this.priceList)
				.productId(this.productId)
				.priority(this.priority)
				.price(this.price)
				.currency(this.currency)
				.build();
	}
}
