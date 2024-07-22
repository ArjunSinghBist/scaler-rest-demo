package org.scaler.demo.project.model.projection;

import java.util.Optional;

public interface IProductProjection {

    Optional<String> getName();
    Optional<String> getDescription();
}
