package ru.vladikadiroff.physiognomy.domain.test.converters

import ru.vladikadiroff.physiognomy.data.models.test.TestResultAPI
import ru.vladikadiroff.physiognomy.domain.test.models.TestResultModel

class TestResultConverter {

    fun fromApi(model: TestResultAPI): TestResultModel{
        return TestResultModel(name = model.name, text = model.text)
    }

}