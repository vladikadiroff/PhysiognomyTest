package ru.vladikadiroff.physiognomy.ui.adapters.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.ui.adapters.store.viewholders.StoreViewHolder
import ru.vladikadiroff.physiognomy.domain.store.models.StoreModel

class StoreAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var storeItemsList: MutableList<StoreModel> = ArrayList()
    var onItemClick: ((StoreModel) -> Unit)? = null

    fun updateStore(storeList: List<StoreModel>){
        storeItemsList.clear()
        storeItemsList.addAll(storeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return storeItemsList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StoreViewHolder).bind(storeItemsList[position], onItemClick)
    }
}