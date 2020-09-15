package ru.vladikadiroff.physiognomy.ui.adapters.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.domain.test.models.TestResultModel
import ru.vladikadiroff.physiognomy.ui.adapters.test.viewholders.TestResultViewHolder

class TestResultAdapter: RecyclerView.Adapter<TestResultViewHolder>() {

    private var listResult: MutableList<TestResultModel> = ArrayList()

    fun setAdapter(list: List<TestResultModel>){
        listResult.clear()
        listResult.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestResultViewHolder {
        return TestResultViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_test_result, parent, false))
    }

    override fun getItemCount(): Int {
        return listResult.count()
    }

    override fun onBindViewHolder(holder: TestResultViewHolder, position: Int) {
        holder.bind(listResult[position])
    }
}