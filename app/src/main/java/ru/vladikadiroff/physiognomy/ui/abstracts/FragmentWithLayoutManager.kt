package ru.vladikadiroff.physiognomy.ui.abstracts

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

open class FragmentWithLayoutManager (@LayoutRes contentLayout: Int): Fragment(contentLayout) {

    companion object {
        private const val LAYOUT_MANAGER_STATE = "layout_manager_state"
    }

    protected open var layoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER
    protected open var gridLayoutCount = 3
    protected var layoutManager: LinearLayoutManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Создание LayoutManager и восстановление состояния при навигации
        when(layoutManagerType){
            LayoutManagerType.LINEAR_LAYOUT_MANAGER -> setupLinearLayoutManager(view.context)
            LayoutManagerType.GRID_LAYOUT_MANAGER -> setupGridLayoutManager(view.context)
        }

        // Восстановление состояния LayoutManager при перевороте экрана
        if (savedInstanceState != null)
            layoutManager?.onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE))

    }

    private fun setupLinearLayoutManager(context: Context){
        if (layoutManager == null) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        } else {
            val state = layoutManager?.onSaveInstanceState()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager?.onRestoreInstanceState(state)
        }
    }

    private fun setupGridLayoutManager(context: Context){

        if (layoutManager == null) {
            layoutManager = GridLayoutManager(context, gridLayoutCount, GridLayoutManager.VERTICAL, false)
        } else {
            val state = layoutManager?.onSaveInstanceState()
            layoutManager = GridLayoutManager(context, gridLayoutCount, GridLayoutManager.VERTICAL, false)
            layoutManager?.onRestoreInstanceState(state)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(LAYOUT_MANAGER_STATE, layoutManager?.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }

    enum class LayoutManagerType {
        LINEAR_LAYOUT_MANAGER,
        GRID_LAYOUT_MANAGER
    }

}