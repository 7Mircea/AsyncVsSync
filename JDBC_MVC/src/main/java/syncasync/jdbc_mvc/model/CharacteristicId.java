package syncasync.jdbc_mvc.model;

import java.io.Serializable;
import java.util.Objects;

public class CharacteristicId implements Serializable {
    private Product product;
    private int idCharacteristic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacteristicId that = (CharacteristicId) o;
        return idCharacteristic == that.idCharacteristic && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, idCharacteristic);
    }

    //    @Override
//    public int hashCode() {
//        return product.hashCode() + idCharacteristic;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null)
//            return false;
//        if (!obj.getClass().getName().equals(this.getClass().getName())) return false;
//        return this.hashCode() == obj.hashCode();
//    }
}