package syncasync.jdbc_mvc.repository;

import syncasync.jdbc_mvc.model.Characteristic;
import syncasync.jdbc_mvc.model.CharacteristicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CharacteristicRepository extends JpaRepository<Characteristic, CharacteristicId> {
    @Query(value = "select * from characteristic where id_prod=:idProd", nativeQuery = true)
    Set<Characteristic> findCharacteristicByProduct(int idProd);
}
