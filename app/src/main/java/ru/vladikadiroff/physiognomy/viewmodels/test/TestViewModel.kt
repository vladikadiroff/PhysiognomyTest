package ru.vladikadiroff.physiognomy.viewmodels.test

import androidx.lifecycle.*
import ru.vladikadiroff.physiognomy.PhysiognomyApplication
import ru.vladikadiroff.physiognomy.domain.test.models.TestQuestionVariantModel
import ru.vladikadiroff.physiognomy.domain.test.models.TestQuestionModel
import ru.vladikadiroff.physiognomy.domain.test.TestsInteractorImpl
import ru.vladikadiroff.physiognomy.extensions.default
import ru.vladikadiroff.physiognomy.extensions.set
import javax.inject.Inject

class TestViewModel: ViewModel(){

    @Inject
    lateinit var interactor: TestsInteractorImpl

    private var count: Int = 0
    private var listQuestions: List<TestQuestionModel>

    val title = MutableLiveData<String>().default(initialValue = "")
    val description = MutableLiveData<String>().default(initialValue = "")
    val listVariants = MutableLiveData<List<TestQuestionVariantModel>>().default(initialValue = listOf())
    val questionsCount = MutableLiveData<String>().default(initialValue = "")
    val finishTest = MutableLiveData<Boolean>().default(initialValue = false)

    init {
        PhysiognomyApplication.instance.testsComponent.inject(viewModel = this)
        listQuestions = interactor.getTest().questions
        setQuestion()
    }

    fun getTestName() = interactor.getTest().name

    fun onAnswer(answer: Int){
        interactor.setAnswer(answer = answer, count = count)
        count++
        if(count != listQuestions.count()) setQuestion()
        else finish()
    }

    private fun setQuestion(){
        val question = listQuestions[count]
        title.set(question.name)
        description.set(question.description)
        listVariants.set(question.listVariants)
        questionsCount.set((count + 1).toString() + "/" + listQuestions.count())
    }

    private fun finish(){
        finishTest.set(true)
        count = 0
        setQuestion()
        finishTest.set(false)
    }


}