package ru.vladikadiroff.physiognomy.ui.adapters.test.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_test_result.*
import ru.vladikadiroff.physiognomy.domain.test.models.TestResultModel

class TestResultViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(model: TestResultModel){

        textResultHeader.text = model.name
        textResult.text = model.text

    }

}