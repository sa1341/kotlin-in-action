package chapter05

/**
 * 스코프 함수의 예제들을 각각 정리하였습니다.
 * let, run, apply, also, with(확장함수 x)
 */

fun main() {

    // let 예제 - 보통 null이 아닌경우를 처리하고 싶을 때 사용함.
    val str: String? = "안녕하세요"
    val result: Int? = str?.let {
        println(it)
        val abc: String? = "abc"
        val def: String? = "def"

        if(!abc.isNullOrEmpty() && !def.isNullOrEmpty()) {
            println("abcdef가 null 아님")
        }

        1234
    }

    println("result = $result")

    // run 예제코드
    /*
    AS-IS

    val config = DatabaseClient()
    config.url = "localhost:3306"
    config.username = "mysql"
    config.password = "1234"
    val connected = config.connect()

    println(connected)
    */

    // TO-BE 특정 프로퍼티 값들을 초기화 후 실행을 할때 사용하면 좋음(람다식 내부에서 객체 참조를 사용)
    val connected: Boolean = DatabaseClient().run {
        url = "localhost:3306"
        username = "mysql"
        password = "1234"
        connect()
    }
    println(connected)

    // with 예제코드 - scope 함수 중 유일하게 확장함수가 아닌 메서드임
    var str2 = "안녕하세요"

    with(str2) {
        println("length = $length")
    }

    // apply 예제코드 - 주로 Data Class와 같이 내부적으로 프로퍼티 초기화를 하는 경우 많이 사용
    val client: DatabaseClient = DatabaseClient().apply {
        url = "localhost:3306"
        username = "mysql"
        password = "1234"
        connect()
    }

    println(client)

    // also 예제코드 - 부수 작업이 필요한 경우 사용하는 scope 메서드임
    client.connect().also {
        println("connect result = $it")
    }

    val user = User(name = "준영", password = "1234").also {
        it.validate()
    }

    println(user)
}

class DatabaseClient {
    var url: String? = null
    var username: String? = null
    var password: String? = null

    // DB에 접속하고 Boolean 결과를 반환
    fun connect(): Boolean {
        println("DB 접속 중 ...")
        Thread.sleep(1000)
        println("DB 접속 완료")
        return true
    }

    override fun toString(): String {
        return "DatabaseClient(url=$url, username=$username, password=$password)"
    }
}

class User(
    val name: String,
    val password: String
) {
    fun validate() {
        if (name.isNotEmpty() && password.isNotEmpty()) {
            println("검증 성공!")
        } else {
            println("검증 실패!")
        }
    }
}


