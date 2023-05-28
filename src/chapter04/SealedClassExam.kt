package chapter04

sealed class Developer {

    abstract val name: String
    abstract fun code(language: String)
}

data class BackendDeveloper(
    override val name: String
): Developer() {

    override fun code(language: String) {
        println("저는 백엔드 개발자입니다. ${language}를 사용합니다.")
    }
}

data class FrontendDeveloper(
    override val name: String
): Developer() {

    override fun code(language: String) {
        println("저는 프론트엔드 개발자입니다. ${language}를 사용합니다.")
    }
}

data class AndroidDeveloper(
    override val name: String
): Developer() {

    override fun code(language: String) {
        println("저는 안드로이드 개발자입니다. ${language}를 사용합니다.")
    }
}
object DeveloperPool {
    val pool = mutableMapOf<String, Developer>()

    fun add(developer: Developer) = when (developer) {
        is BackendDeveloper -> pool[developer.name] = developer
        is FrontendDeveloper -> pool[developer.name] = developer
        is AndroidDeveloper -> pool[developer.name] = developer
        is OtherDeveloper -> println("지원하지 않는 개발자 종류 입니다.")
    }

    fun get(name: String) = pool[name]
}

object OtherDeveloper: Developer() {
    override val name: String = "익명"

    override fun code(language: String) {
        TODO("Not yet implemented")
    }
}

fun main() {
    val backendDeveloper = BackendDeveloper(name = "진")
    DeveloperPool.add(backendDeveloper)

    val frontendDeveloper = FrontendDeveloper(name = "준영")
    DeveloperPool.add(frontendDeveloper)

    val androidDeveloper = AndroidDeveloper(name = "안드로")
    DeveloperPool.add(androidDeveloper)

    println(DeveloperPool.get("진"))
    println(DeveloperPool.get("준영"))
    println(DeveloperPool.get("안드로"))
    println(DeveloperPool.get("anonymous"))
}