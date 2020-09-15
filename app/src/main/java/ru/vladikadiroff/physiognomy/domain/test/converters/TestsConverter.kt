package ru.vladikadiroff.physiognomy.domain.test.converters

import ru.vladikadiroff.physiognomy.data.models.test.TestAPI
import ru.vladikadiroff.physiognomy.data.models.test.TestQuestionsAPI
import ru.vladikadiroff.physiognomy.domain.test.models.TestModel
import ru.vladikadiroff.physiognomy.domain.test.models.TestQuestionModel
import ru.vladikadiroff.physiognomy.domain.test.models.TestQuestionVariantModel

class TestsConverter {

    fun fromAPI(model: TestAPI): TestModel {
        return TestModel(
            id = model.id,
            name = model.name,
            description = model.description,
            icon = model.icon,
            isFree = model.isFree,
            marketId = model.marketId,
            questions = model.questions.map(::fromTestQuestionsAPI)
        )
    }

    private fun fromTestQuestionsAPI(model: TestQuestionsAPI): TestQuestionModel{
        return TestQuestionModel(
            name = model.name,
            description = model.description,
            listVariants = model.facial_features.map { list ->
                TestQuestionVariantModel(
                    name = list.name,
                    image = list.image
                )
            }
        )
    }

}