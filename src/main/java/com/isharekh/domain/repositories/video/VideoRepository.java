package com.isharekh.domain.repositories.video;

import com.isharekh.domain.models.videos.Video;
import org.springframework.data.repository.PagingAndSortingRepository;

/*
Create By: Ron Rith
Create Date: 2/28/2018
*/
public interface VideoRepository extends PagingAndSortingRepository<Video,Long> {
}
