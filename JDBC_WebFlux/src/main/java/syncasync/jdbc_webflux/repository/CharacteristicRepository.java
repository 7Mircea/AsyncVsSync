package syncasync.jdbc_webflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import syncasync.jdbc_webflux.model.Characteristic;
import syncasync.jdbc_webflux.model.CharacteristicId;

import java.util.Set;

public interface CharacteristicRepository extends JpaRepository<Characteristic, CharacteristicId> {
    @Query(value = "select * from characteristic where id_prod=:idProd", nativeQuery = true)
    public Set<Characteristic> findCharacteristicByProduct(int idProd);
}
