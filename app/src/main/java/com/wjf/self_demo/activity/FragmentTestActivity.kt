package com.wjf.self_demo.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.wjf.self_demo.R
import com.wjf.self_demo.databinding.ActivityFragmentTestBinding
import com.wjf.self_demo.fragment.AFragment
import org.jxxy.debug.corekit.common.BaseActivity
import org.jxxy.debug.corekit.util.singleClick

class FragmentTestActivity : BaseActivity<ActivityFragmentTestBinding>() {
    private var count = 0
    override fun bindLayout(): ActivityFragmentTestBinding =
        ActivityFragmentTestBinding.inflate(layoutInflater)

    override fun initView() {
        view.changeBtn.singleClick {
            changePage()
        }
    }

    override fun subscribeUi() {
        changePage()
    }

    private fun changePage() {
        if (count % 2 == 0) {
            changePage(
                "A",
                AFragment().apply {
                    ftag = "A"
                },
            )
        } else {
            changePage(
                "B",
                AFragment().apply {
                    ftag = "B"
                },
            )
        }
        count++
    }

    private fun changePage(tag: String, fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        hideAllFragment(beginTransaction)

        // 移除已有的页面，重新创建
        val regFragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (regFragment != null) {
            beginTransaction.remove(regFragment)
        }

        // 添加新页面
        beginTransaction.add(R.id.fragment, fragment, tag)
        beginTransaction.show(fragment)

        beginTransaction.commitAllowingStateLoss()
    }

    private fun hideAllFragment(@NonNull fragmentTransaction: FragmentTransaction) {
        for (fragment in supportFragmentManager.fragments) {
            fragmentTransaction.hide(fragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Log.i("wjftc", "onCreate: 1")
        super.onCreate(savedInstanceState, persistentState)
        Log.i("wjftc", "onCreate: 2")
    }

    override fun onStart() {
        Log.i("wjftc", "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.i("wjftc", "onResume: ")
        super.onResume()
    }

    override fun onPause() {
        Log.i("wjftc", "onPause: ")
        super.onPause()
    }

    override fun onStop() {
        Log.i("wjftc", "onStop: ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("wjftc", "onDestroy: ")
        super.onDestroy()
    }
}
