package ru.vladikadiroff.physiognomy.ui.adapters.test.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_test_variant.*
import ru.vladikadiroff.physiognomy.domain.test.models.TestQuestionVariantModel

class TestViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    fun bind(variant: TestQuestionVariantModel, onItemClick: ((Int) -> Unit)?){

        textTestVariant.text = variant.name

        Glide.with(containerView)
            .load(variant.image)
            .into(imageTestVariants)


        containerView.setOnClickListener{
            onItemClick?.invoke(adapterPosition)
        }

    }
}

