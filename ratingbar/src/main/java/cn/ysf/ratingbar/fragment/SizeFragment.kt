package cn.ysf.ratingbar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.ysf.ratingbar.R
import kotlinx.android.synthetic.main.fragment_size.*

class SizeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_size, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratingBar1.setOnRatingChangeListener { _, rating ->
            Log.e("ratingBar", "rating:$rating")
            tvRatingValue.text = "value:$rating"
        }

        ratingBar2.setOnRatingChangeListener { _, rating ->
            Log.e("aaa", "rating:$rating")
        }

        ratingBar3.setOnRatingChangeListener { _, rating ->
            Log.e("aaa", "rating:$rating")
        }
    }
}