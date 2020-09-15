package ru.vladikadiroff.physiognomy.ui.adapters.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.domain.test.models.TestModel
import ru.vladikadiroff.physiognomy.ui.adapters.test.viewholders.TestsViewHolder

class TestsAdapter: RecyclerView.Adapter<TestsViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null
    private var listTests: MutableList<TestModel> = ArrayList()

    fun setTests(list: List<TestModel>){
        listTests.clear()
        listTests.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsViewHolder {
        return TestsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_test, parent, false))
    }

    override fun getItemCount(): Int {
        return listTests.count()
    }

    override fun onBindViewHolder(holder: TestsViewHolder, position: Int) {
        holder.bind(listTests[position], onItemClick)
    }

}