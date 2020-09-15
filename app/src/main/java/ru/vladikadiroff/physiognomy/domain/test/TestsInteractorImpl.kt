package ru.vladikadiroff.physiognomy.domain.test

import androidx.lifecycle.MutableLiveData
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsLoadingStatus
import ru.vladikadiroff.physiognomy.domain.test.keys.TestsResultLoadingStatus
import ru.vladikadiroff.physiognomy.domain.test.models.TestModel
import ru.vladikadiroff.physiognomy.domain.test.usecases.TestAnswersCounter
import ru.vladikadiroff.physiognomy.domain.test.usecases.TestResultLoading
import ru.vladikadiroff.physiognomy.domain.test.usecases.TestsLoading

class TestsInteractorImpl(
    private val tests: TestsLoading,
    private val testResult: TestResultLoading,
    private val answersCounter: TestAnswersCounter
) : TestsInteractor {

    private var currentTest: Int = 0

    // Статус загрузки тестов
    override fun getTestsLoadingStatus(): MutableLiveData<TestsLoadingStatus>{
        return tests.getStatus()
    }

    // Статус загрузки результата
    override fun getTestResultLoadingStatus(): MutableLiveData<TestsResultLoadingStatus>{
        return testResult.getStatus()
    }

    // Устанавливаем текущий тест
    override fun setCurrentTest(testId: Int){
        currentTest = testId
    }


    // Получить список вопросов
    override fun getTest(): TestModel {
        answersCounter.clear() // Очищаем счетчик вопросов
        return tests.getTestById(id = currentTest)
    }

    // Запись ответов
    override fun setAnswer(answer: Int, count: Int) {
        answersCounter.onAnswer(answer = answer, count = count)
    }

    // Загрузка тестов
    override fun loadTests(){
        tests.load()
    }

    // Загрузка результата
    override fun loadResult(){
        testResult.load(answers = answersCounter.answers)
    }

    // Остановить загрузку
    fun stop(){
        tests.stop()
        testResult.stop()
    }

}


