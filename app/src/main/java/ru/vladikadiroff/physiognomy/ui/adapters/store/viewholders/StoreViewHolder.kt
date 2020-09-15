package ru.vladikadiroff.physiognomy.ui.adapters.store.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_store.*
import ru.vladikadiroff.physiognomy.domain.store.models.StoreModel

class StoreViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{

    fun bind(storeModel: StoreModel, onItemClick: ((StoreModel) -> Unit)?){

        textStoreItem.text = storeModel.name
        textStorePriceItem.text = "${storeModel.price}\u20BD"
        textStoreItemDescription.text = storeModel.description


        containerView.setOnClickListener {
            onItemClick?.invoke(storeModel)
        }

    }

}