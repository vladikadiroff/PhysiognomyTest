package ru.vladikadiroff.physiognomy.ui.adapters.test

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.domain.test.models.TestQuestionVariantModel
import ru.vladikadiroff.physiognomy.ui.adapters.test.viewholders.TestViewHolder

class TestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    private var mTestVariantList: MutableList<TestQuestionVariantModel> = ArrayList()
    var onItemClick: ((Int) -> Unit)? = null

    fun setFacialFeatures(facialFeatureList: List<TestQuestionVariantModel>) {
        mTestVariantList.clear()
        mTestVariantList.addAll(facialFeatureList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TestViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_test_variant, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TestViewHolder).bind(variant = mTestVariantList[position], onItemClick = onItemClick)
    }

    override fun getItemCount(): Int {
        return mTestVariantList.count()
    }

}