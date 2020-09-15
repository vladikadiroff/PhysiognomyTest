package ru.vladikadiroff.physiognomy.domain.store.converters

import ru.vladikadiroff.physiognomy.data.models.store.StoreAPI
import ru.vladikadiroff.physiognomy.domain.store.models.StoreModel

class StoreConverter {
    fun fromApi(model: StoreAPI): StoreModel{
        return StoreModel(
            id = model.id,
            name = model.name,
            icon = model.icon,
            picture = model.picture,
            description = model.description,
            text = model.text,
            price = model.price,
            link = model.link
        )
    }
}