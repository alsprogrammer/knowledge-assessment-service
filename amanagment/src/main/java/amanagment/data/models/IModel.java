package amanagment.data.models;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public interface IModel {
    String getIdPrefix();

    default String getUniqueId() {
        return UUID.randomUUID().toString();
    }
}
