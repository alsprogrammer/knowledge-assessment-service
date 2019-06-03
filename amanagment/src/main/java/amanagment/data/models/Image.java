package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Value
@Builder
public class Image implements IModel {
    private final String id = IdGenerator.generateId(this);
    @NonNull
    private final String caption;
    @NonNull
    private final String image;
    @NonNull
    private final int imageHash;

    public static ImageBuilder builder() {
        return new ImageBuilder(){
            @Override
            public Image build() {
                prebuild();
                return super.build();
            }
        };
    }

    public static class ImageBuilder  {
        void prebuild(){
            imageHash = new HashCodeBuilder().append(image).toHashCode();
        }
    }

    @Override
    public String getIdPrefix() {
        return "IM";
    }
}
