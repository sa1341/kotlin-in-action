package chapter04


fun main(args: Array<String>) {

    val user2 = User2.newSubscribingUser("a79007714@gmail.com")
    println("user2 nickName = ${user2.nickName}")
}


class User2 private constructor(val nickName: String){

    companion object {
        fun newSubscribingUser(email: String) = User2(email.substringBefore("@"))

        fun newFacebookUser(accountId: Int) {
            User2(accountId.toString())
        }
    }
}