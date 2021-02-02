package cn.ysf.ratingbar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.ysf.common.base.BaseFragment
import cn.ysf.ratingbar.R
import kotlinx.android.synthetic.main.fragment_style.*

class StyleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_style, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratingBar3.setOnRatingChangeListener { _, rating ->
            tvRate.text = "当前rate:$rating"
        }
    }
}