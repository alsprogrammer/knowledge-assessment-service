package amanagment.data.models;

import java.util.UUID;

public interface IModel {
    String getIdPrefix();
    default String generateId() {
        return getIdPrefix() + UUID.randomUUID().toString();
    }
}
