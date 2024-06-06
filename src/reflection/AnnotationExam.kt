package reflection

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Shape(
    val texts: Array<String>,
)

@Shape(["A", "B"])
class AnnotationExam
