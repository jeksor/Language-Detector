package com.esorokin.lantector.ui.fragment.detect

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.esorokin.lantector.R
import com.esorokin.lantector.presentation.error.UserError
import com.esorokin.lantector.presentation.presenter.detect.DetectLanguagePresenter
import com.esorokin.lantector.presentation.view.detect.DetectLanguageView
import com.esorokin.lantector.ui.fragment.BaseFragment
import com.esorokin.lantector.ui.plugin.*
import com.esorokin.lantector.utils.ViewUtils
import com.esorokin.lantector.utils.ext.snack
import kotlinx.android.synthetic.main.fragment_detect_language.*

class DetectLanguageFragment : BaseFragment(), DetectLanguageView {
    @InjectPresenter
    internal lateinit var presenter: DetectLanguagePresenter

    private val errorPlugin: ErrorPlugin = DialogErrorPlugin({ context })
    private val progressPlugin: ProgressPlugin = ProgressPlugin({ activity })
    private val alertMessage: AlertMessagePlugin = AlertMessagePlugin({ activity })
    private val toolbarPlugin: ToolbarPlugin = ToolbarPlugin({ activity as AppCompatActivity }, R.string.screen_title_detect_language)

    override fun initPlugins() {
        super.initPlugins()
        compositionPlugin.attach(errorPlugin)
        compositionPlugin.attach(progressPlugin)
        compositionPlugin.attach(alertMessage)
        compositionPlugin.attach(toolbarPlugin)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_detect_language, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detectLanguageButton.setOnClickListener {
            ViewUtils.hideKeyboard(activity)
            presenter.userClickStartDetect(inputEditText.text.toString())
        }
    }

    override fun showDetectingProgress() {
        progressPlugin.showProgress()
    }

    override fun hideDetectingProgress() {
        progressPlugin.hideProgress()
    }

    override fun noInternetConnection() {
        detectLanguageButton.snack(getString(R.string.error_internet_connection)) {
            setAction(R.string.button_retry, { presenter.userClickRetryDetect(inputEditText.text.toString()) })
        }
    }

    override fun showError(error: UserError) {
        errorPlugin.showUiError(userError = error, errorHideListener = { presenter.userHideError() })
    }

    override fun hideError() {
        errorPlugin.hideUiError()
    }

    override fun showDetectingResult(language: String) {
        alertMessage.showMessage(
                title = language.capitalize(),
                hideListener = { presenter.userHideDetectingResult() })
    }

    override fun hideDetectingResult() {
        alertMessage.hideMessage()
        inputEditText.text.clear()
    }
}
