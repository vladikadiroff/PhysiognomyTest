package ru.vladikadiroff.physiognomy.ui.adapters.test.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_test.*
import ru.vladikadiroff.physiognomy.domain.test.models.TestModel

class TestsViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(model: TestModel, onItemClick: ((Int) -> Unit)?){

        textTestTitle.text = model.name
        textTestDescription.text = model.description
        buttonTestStart.setOnClickListener { onItemClick?.invoke(model.id) }

        if(model.icon != "") {
            Glide.with(containerView)
                .load(model.icon)
                .into(imageTest)

        }

    }

}