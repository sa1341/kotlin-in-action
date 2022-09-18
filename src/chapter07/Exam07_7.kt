package chapter07

interface Subject {
    fun register(observer: Observer)
    fun unregister(observer: Observer)
    fun notifyObservers()
    fun getUpdate(observer: Observer): Any?
}

interface Observer {
    fun update()
}

class Topic: Subject {

    private val observers: MutableList<Observer> = mutableListOf()
    private var message: String? = null

    override fun register(observer: Observer) {
        if(!observers.contains(observer)) observers.add(observer)
    }

    override fun unregister(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { it.update() }
    }

    override fun getUpdate(observer: Observer): Any? {
        return message
    }

    fun postMessage(_message: String) {
        println("Message sended to Topic: $message")
        message = _message
        notifyObservers()
    }
}

class TopicSubscriber(
    val name: String,
    val topic: Subject
): Observer {

    override fun update() {
        val msg = topic.getUpdate(this)
        println("$name = ::got message >> $msg")
    }
}

fun main(args: Array<String>) {

    val topic = Topic()

    val observer1 = TopicSubscriber("observer1", topic)
    val observer2 = TopicSubscriber("observer2", topic)
    val observer3 = TopicSubscriber("observer3", topic)

    topic.register(observer1)
    topic.register(observer2)
    topic.register(observer3)

    topic.postMessage("내일 출근해야되네.. 하..")
}