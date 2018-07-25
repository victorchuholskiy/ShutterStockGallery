package com.gmail.victorchuholskiy.shutterstockgallery.filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import com.gmail.victorchuholskiy.shutterstockgallery.R
import android.widget.ArrayAdapter
import android.widget.Button
import com.gmail.victorchuholskiy.shutterstockgallery.utils.ARG_CATEGORY_ID
import com.gmail.victorchuholskiy.shutterstockgallery.utils.ARG_QUERY_TEXT

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class FilterFragment : Fragment(), FilterContract.View {

	override lateinit var presenter: FilterContract.Presenter

	private lateinit var etQuery: EditText
	private lateinit var spCategories: Spinner

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_filter, container, false)
		with(view) {
			etQuery = findViewById(R.id.et_query)
			spCategories = findViewById(R.id.sp_categories)

			findViewById<Button>(R.id.btn_apply).apply { setOnClickListener( { presenter.btnApplyClicked(etQuery.text.toString(), spCategories.selectedItemPosition) }) }
			findViewById<Button>(R.id.btn_reset).apply { setOnClickListener( { presenter.btnResetClicked() }) }
		}
		presenter.setData(context, arguments!!.getString(ARG_QUERY_TEXT, ""), arguments!!.getInt(ARG_CATEGORY_ID, -1))
		return view
	}

	override fun onResume() {
		super.onResume()
		presenter.start()
	}

	override fun initCategoriesSpinner(categories: List<String>, position: Int) {
		val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, categories)
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		spCategories.adapter = adapter
		spCategories.setSelection(position)
	}

	override fun setSpinnerPosition(position: Int) {
			spCategories.setSelection(position)
	}

	override fun setQueryText(text: String) {
		etQuery.setText(text)
	}

	override fun finishActivity(queryText: String, categoryId: Int) {
		val intent = Intent()
		intent.putExtra(ARG_QUERY_TEXT, queryText)
		intent.putExtra(ARG_CATEGORY_ID, categoryId)
		activity!!.setResult(Activity.RESULT_OK, intent)
		activity!!.finish()
	}

	companion object {
		fun newInstance(bundle: Bundle): FilterFragment {
			val fragment = FilterFragment()
			fragment.arguments = bundle
			return fragment
		}
	}
}