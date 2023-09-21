package com.praveen.spacexapp.network.models.launches

data class Links(
    var article_link: String?,
    var flickr_images: List<String?>?,
    var mission_patch: String?,
    var mission_patch_small: String?,
    var presskit: String?,
    var reddit_campaign: String?,
    var reddit_launch: String?,
    var reddit_media: String?,
    var reddit_recovery: Any?,
    var video_link: String?,
    var wikipedia: String?,
    var youtube_id: String?
)