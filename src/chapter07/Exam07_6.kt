package chapter07

fun main(args: Array<String>) {
    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    printEntries(map)
    printEntries2(map)
}

fun printEntries(map: Map<String, String>) {
    for ((key, value) in map) {
        println("key = $key, value = $value")
    }
}

fun printEntries2(map: Map<String, String>) {
    for (entry in map.entries) {
        val key = entry.component1()
        val value = entry.component2()
        println("key = $key, value = $value")
    }
}




