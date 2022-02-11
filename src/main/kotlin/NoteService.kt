import java.io.FileNotFoundException

class NoteService  {
    var notes = mutableListOf<Notes>()

    fun add(note: Notes) {
        notes += note
    }

    fun createComment(comments: Comments, note: Notes) {
        if (notes.contains(note)) {
            notes.get(notes.indexOf(note)).comment += comments
        }else
        throw NoteNotFoundException("Не найдена заметка в которую вы хртите добавить коментарий")
    }

    fun delete(note: Notes) {
        notes.removeAt(notes.indexOf(note))
    }

    fun deleteComment(comments: Comments) {
        if (comments.status){
            comments.status = false
        }
        throw NoteNotFoundException("Коментарий уже удалён")
    }

    fun edit(id: Int, title: String, text: String, privacy_view: String, privacy_comment: String): Notes {
        for (index in notes.indices) {
            if (notes[index].id == id) {
                notes[index].title = title
                notes[index].text = text
                notes[index].privacy_view = privacy_view
                notes[index].privacy_comment = privacy_comment
                return notes[index]
            }
        }
        throw NoteNotFoundException("Не найдена заметка с введёным id")
    }

    fun editComment(comment_id: Int, message: String): Comments {
        for (index in notes.indices) {
            for (i in notes[index].comment.indices) {
                if (notes[index].comment[i].id == comment_id && notes[index].comment[i].status !== false) {
                    notes[index].comment[i].message = message
                    return notes[index].comment[i]
                }
            }
        }
        throw NoteNotFoundException("Не найден коммент с введённым id")
    }

    fun getById(id: Int): Notes {
        for (index in notes.indices) {
            if (notes[index].id == id) {
                return notes[index]
            }
        }
        throw NoteNotFoundException("Не найдена заметка с введёным id")
    }

    fun getComments(note: Notes): List<Comments> {
        var getComment = listOf<Comments>()
        for (index in note.comment.indices){
            if (note.comment[index].status)
                getComment += note.comment[index]
        }
        return getComment
    }

    fun restoreComment(comments: Comments) {
        if (comments.status){
            throw NoteNotFoundException("Нельзя востановить уже существующий коментарий")
        }
        comments.status = true
    }
}