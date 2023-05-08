package exercise
fun main() {
    val users = createUsers()
    users.forEach(System.out::println)
}
fun createUsers(): Map<UserId, List<User>> {
    val users = arrayOf<User>(
        User(id = 1, name = "임준영", email = "a79007714@gmail.com", age = 32),
        User(id = 1, name = "임준영", email = "ahffkdbfkrql@naver.com", age = 32),
        User(id = 2, name = "임지우", email = "syn1341@naver.com", age =8),
        User(id = 3, name = "임성희", email = "a79007714@daum.com", age= 9)
    )

    return users.groupBy { it.getKey() }
}

fun User.getKey() = UserId(id = this.id, name = this.name)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val age: Int
)
data class UserId(
    val id: Int,
    val name: String
)