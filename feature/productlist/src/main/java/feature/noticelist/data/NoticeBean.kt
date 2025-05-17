package feature.noticelist.data

data class NoticeBean(
    val updateStatus: String,
    val comment: String,
    val isForce: String,
    val version: String,
    val app_id: String,
    val slug: String,
    val url: String,

    val noticeUpdate: Boolean,
    val isGray: Boolean
)