package amanagment.data.models;

import com.google.common.hash.Hashing;
import lombok.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.nio.charset.StandardCharsets;

@Data
public class Image implements IModel {
    private final String id;
    @NonNull
    private final String caption;
    @NonNull
    private final String image;

    public Image(ImageBuilder builder) {
        this.id = builder.id;
        this.caption = builder.caption;
        this.image = builder.image;
    }

    @Override
    public String generateId() {
        return getIdPrefix() + Hashing.sha256()
                .hashString(image, StandardCharsets.UTF_8)
                .toString();
    }

    public static ImageBuilder builder() {
        return new ImageBuilder();
    }

    @Override
    public String getIdPrefix() {
        return "IM";
    }

    public static class ImageBuilder {
        private String id;
        private String caption;
        private String image;

        public ImageBuilder() {
        }

        public ImageBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public ImageBuilder image(String image) {
            this.image = image;
            return this;
        }

        public Image build() {
            this.id = "IM" + Hashing.sha256()
                    .hashString(image, StandardCharsets.UTF_8)
                    .toString();
            return new Image(this);
        }
    }
}
