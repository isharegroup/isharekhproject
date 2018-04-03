package com.isharekh.domain.repositories.fileupload;

import com.isharekh.domain.models.fileupload.FileUpload;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by : Ron Rith
 * Create Date: 01/28/2018.
 */
public interface FileUploadRepository extends PagingAndSortingRepository<FileUpload,Long> {
}
