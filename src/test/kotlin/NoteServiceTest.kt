import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    val notes = Notes(
        id = 21,
        title = "",
        text = " ",
        date = 15,
        comment = emptyList(),
        read_comments = 3,
        view_url = "",
        privacy_view = "",
        privacy_comment = "",
        can_comment = 2,
        text_wiki = ""
    )

    val comments = Comments(
        id = 7,
        uid = 5,
        nid = 8,
        oid = 1,
        date = 3,
        message = "",
        replyTo = 4
    )

    @Test
    fun add() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val notes2 = notes.copy(id = 8)
        val notes3 = notes.copy(id = 38)
        val notes4 = notes.copy(id = 25)

        noteService.add(notes1)
        assertEquals(noteService.notes[0], notes1)
    }

    @Test
    fun createCommentTest() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val comments1 = comments.copy(id = 15)

        noteService.add(notes1)
        noteService.createComment(comments1, notes1)
        assertEquals(noteService.notes[0].comment[0], comments1)
    }

    @Test(expected = NoteNotFoundException :: class)
    fun createCommentTestException(){
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val notes2 = notes.copy(id = 17)
        val comments1 = comments.copy(id = 15)

        noteService.add(notes1)
        noteService.createComment(comments1, notes2)

    }

    @Test
    fun delete() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)

        noteService.add(notes1)
        noteService.delete(notes1)
        assertEquals(noteService.notes.isEmpty(), true)
    }

    @Test
    fun deleteComment() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val comments1 = comments.copy(id = 15)

        noteService.add(notes1)
        noteService.createComment(comments1, notes1)
        noteService.deleteComment(comments1)
        assertFalse(noteService.notes[0].comment[0].status)
    }
    @Test(expected = NoteNotFoundException :: class)
    fun deleteCommentTestException(){
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val comments1 = comments.copy(id = 15, status = false)

        noteService.add(notes1)
        noteService.createComment(comments1, notes1)
        noteService.deleteComment(comments1)
    }

    @Test
    fun editTest() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)

        noteService.add(notes1)
        noteService.edit(id = 13, title = "Generics и коллекции", text = " ", privacy_view = "", privacy_comment = "df")
        assertEquals(noteService.notes[0].title, "Generics и коллекции")
    }

    @Test(expected = NoteNotFoundException :: class)
    fun editTestException(){
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)

        noteService.add(notes1)
        noteService.edit(id = 10, title = "Generics и коллекции", text = " ", privacy_view = "", privacy_comment = "df")
    }

    @Test
    fun editCommentTest() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val comments1 = comments.copy(id = 15)

        noteService.add(notes1)
        noteService.createComment(comments1, notes1)
        noteService.editComment(15,"Generics и коллекции")
        assertEquals(noteService.notes[0].comment[0].message, "Generics и коллекции")
    }

    @Test(expected = NoteNotFoundException :: class)
    fun editCommentTestException(){
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val comments1 = comments.copy(id = 15)

        noteService.add(notes1)
        noteService.createComment(comments1, notes1)
        noteService.editComment(comment_id = 10, message = "Generics и коллекции")
    }

    @Test
    fun getById() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val notes2 = notes.copy(id = 8)
        val notes3 = notes.copy(id = 38)

        noteService.add(notes1)
        noteService.add(notes2)
        noteService.add(notes3)

        val result = noteService.getById(id = 8)
        assertEquals(result, notes2)
    }
    @Test(expected = NoteNotFoundException :: class)
    fun getByIdTestException(){
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val notes2 = notes.copy(id = 8)
        val notes3 = notes.copy(id = 38)

        noteService.add(notes1)
        noteService.add(notes2)
        noteService.add(notes3)

        noteService.getById(id = 32)
    }

    @Test
    fun getComments() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val comments1 = comments.copy(id = 15)
        val comments2 = comments.copy(id = 33)

        noteService.add(notes1)
        noteService.createComment(comments1, notes1)
        noteService.createComment(comments2, notes1)
        val result = noteService.getComments(notes1)
        assertEquals(noteService.notes[0].comment, result)
    }

    @Test
    fun restoreComment() {
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val comments1 = comments.copy(id = 15)
        val comments2 = comments.copy(id = 33)

        noteService.add(notes1)
        noteService.createComment(comments1, notes1)
        noteService.deleteComment(comments1)
        noteService.restoreComment(comments1)
        assertTrue(noteService.notes[0].comment[0].status)
    }

    @Test(expected = NoteNotFoundException :: class)
    fun restoreCommentTestException(){
        val noteService = NoteService()
        val notes1 = notes.copy(id = 13)
        val comments1 = comments.copy(id = 15)
        val comments2 = comments.copy(id = 33)

        noteService.add(notes1)
        noteService.createComment(comments1, notes1)
        noteService.restoreComment(comments1)
    }
}