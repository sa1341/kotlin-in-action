package chapter08

data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

val log = listOf(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

fun main(args: Array<String>) {

    val averageWindowsDuration = log
        .filter { it.os == OS.WINDOWS }
        .map { it.duration }
        .average()

    println("averageWindowsDuration = $averageWindowsDuration")
    //println("v1 averageWindowsDuration = ${log.averageDurationFor(OS.WINDOWS)}")

    val averageMobileDuration = log
        .filter { it.os in setOf(OS.IOS, OS.ANDROID) }
        .map(SiteVisit::duration)
        .average()

    println("averageMobileDuration = $averageMobileDuration")

    println(log.averageDurationFor {
        it.os in setOf(OS.IOS, OS.ANDROID) && it.path == "/signup"
    })

}

// v1
/*
fun List<SiteVisit>.averageDurationFor(os: OS) =
    filter { it.os == os }.map { it.duration }.average()
*/

// v2
fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()


