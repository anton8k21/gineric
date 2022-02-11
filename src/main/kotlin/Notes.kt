data class Notes(
    val id: Int,
    var title: String,
    var text: String,
    val date: Int,
    var comment: List<Comments> = emptyList<Comments>(),
    val read_comments: Int,
    val view_url: String,
    var privacy_view: String,
    var privacy_comment: String,
    val can_comment: Int,
    val text_wiki: String,

) {
}