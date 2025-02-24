package com.salesianos.geekhub.files.service.local;

import com.salesianos.geekhub.files.model.AbstractFileMetadata;
import com.salesianos.geekhub.files.model.FileMetadata;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class LocalFileMetadataImpl extends AbstractFileMetadata {

    public static FileMetadata of(String filename, String fileUrl) {
        return LocalFileMetadataImpl.builder()
                .id(filename)
                .filename(filename)
                .URL(fileUrl)
                .build();
    }
}
