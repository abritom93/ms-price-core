package cl.price.core.adapters.jdbc.repositories;


import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.price.core.adapters.jdbc.entities.PriceEntity;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {	
	
	@Query(value = "select top(1) prices.* from prices where prices.brand_id= :brandId and prices.product_id= :productId and :applicationDate BETWEEN prices.start_date AND prices.end_date order by prices.priority desc",nativeQuery = true)
	Optional<PriceEntity> findAllByBrandIdAndProductIdAndApplicationDate(
			@Param("applicationDate") LocalDateTime applicationDate,
			@Param("productId") Long productId,
			@Param("brandId") Long brandId
	);

}
