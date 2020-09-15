package ru.vladikadiroff.physiognomy.domain.test.usecases

class TestAnswersCounter {

    private val mAnswers = mutableMapOf<Int, Int>()

    val answers: String
        get() {
            val builder = StringBuilder()
            mAnswers.forEach { (key, value) -> builder.append("$key:$value;") }
            return builder.toString()
        }

    fun onAnswer(answer: Int, count: Int){
        mAnswers[count] = answer
    }

    fun clear() {
        mAnswers.clear()
    }



}