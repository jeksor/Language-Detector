package com.esorokin.lantector.ui.fragment.history

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.esorokin.lantector.R
import com.esorokin.lantector.model.data.DetectedLanguageText
import com.esorokin.lantector.presentation.error.UserError
import com.esorokin.lantector.presentation.presenter.history.HistoryPresenter
import com.esorokin.lantector.presentation.view.history.HistoryView
import com.esorokin.lantector.ui.adapter.history.HistoryAdapter
import com.esorokin.lantector.ui.fragment.BaseFragment
import com.esorokin.lantector.ui.plugin.DialogErrorPlugin
import com.esorokin.lantector.ui.plugin.ErrorPlugin
import com.esorokin.lantector.ui.plugin.ToolbarPlugin
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : BaseFragment(), HistoryView {
    @InjectPresenter
    internal lateinit var presenter: HistoryPresenter

    private val errorPlugin: ErrorPlugin = DialogErrorPlugin({ activity!! })

    private lateinit var adapter: HistoryAdapter

    override fun initPlugins() {
        super.initPlugins()
        compositionPlugin.attach(ToolbarPlugin({ activity as AppCompatActivity }, R.string.screen_title_history))
        compositionPlugin.attach(DialogErrorPlugin({ activity!! }))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistoryAdapter(context)
        historyListView.adapter = adapter
        historyListView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        historyListView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    }

    override fun showError(error: UserError) {
        errorPlugin.showUiError(userError = error, errorHideListener = { presenter.userHideError() })
    }

    override fun hideError() {
        errorPlugin.hideUiError()
    }

    override fun showItems(items: List<DetectedLanguageText>) {
        adapter.items = items
        adapter.notifyDataSetChanged()
        historyListView.visibility = View.VISIBLE
        emptyImageView.visibility = View.GONE
        emptyTextView.visibility = View.GONE
    }

    override fun hideItems() {
        adapter.items = listOf()
        adapter.notifyDataSetChanged()
        historyListView.visibility = View.GONE
        emptyImageView.visibility = View.VISIBLE
        emptyTextView.visibility = View.VISIBLE
    }
}
