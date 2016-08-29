package tfzr.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfzr.store.model.ProductOrder;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Integer>{

}
