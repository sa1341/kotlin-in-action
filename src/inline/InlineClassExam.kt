package inline

fun main() {
    val key = Key("비밀번호 486")
    println(key)

    val userId = 1L
    val bookId = 2L
    handle(
        bookId = bookId,
        userId = userId,
    )

    val inlineUserId = Id<User>(userId)
    val inlineBookId = Id<Book>(bookId)
    handleV2(inlineUserId, inlineBookId)
}

@JvmInline
value class Key(val key: String)

class User(
    val id: Id<User>,
    val name: String,
)

class Book(
    val id: Id<Book>,
    val author: String,
)

fun handle(userId: Long, bookId: Long) {}

fun handleV2(userId: Id<User>, bookId: Id<Book>) {
}

@JvmInline
value class Id<T>(val id: Long)

@JvmInline
value class Number(val num: Long) {
    init {
        require(num in 1..10)
    }
}