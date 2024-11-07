package com.wjf.self_demo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wjf.self_demo.databinding.FragmentABinding
import org.jxxy.debug.corekit.common.BaseFragment

class AFragment : BaseFragment<FragmentABinding>() {
    var ftag: String = ""

    override fun bindLayout(): FragmentABinding = FragmentABinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun subscribeUi() {
    }

    override fun onAttach(context: Context) {
        Log.d("wjftc", "onAttach: $ftag")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("wjftc", "onCreate: $ftag")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.d("wjftc", "onCreateView: $ftag")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("wjftc", "onViewCreated: $ftag")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("wjftc", "onActivityCreated: $ftag")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("wjftc", "onStart: $ftag")
        super.onStart()
    }

    override fun onResume() {
        Log.d("wjftc", "onResume: $ftag")
        super.onResume()
    }

    override fun onPause() {
        Log.d("wjftc", "onPause: $ftag")
        super.onPause()
    }

    override fun onStop() {
        Log.d("wjftc", "onStop: $ftag")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("wjftc", "onDestroyView: $ftag")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("wjftc", "onDestroy: $ftag")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("wjftc", "onDetach: $ftag")
        super.onDetach()
    }
}
