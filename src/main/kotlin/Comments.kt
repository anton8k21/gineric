data class Comments(
    val id: Int,
val uid: Int,
val nid: Int,
val oid: Int,
val date: Int,
var message: String,
val replyTo: Int,
    var status: Boolean = true
) {
}