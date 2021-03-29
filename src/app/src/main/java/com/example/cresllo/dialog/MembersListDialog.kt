package com.example.cresllo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cresllo.R
import com.example.cresllo.adapters.LabelColorsListItemAdapter
import com.example.cresllo.adapters.MemberListItemsAdapter
import com.example.cresllo.modals.Users
import kotlinx.android.synthetic.main.dialog_list.view.*

abstract class MembersListDialog(
        context: Context,
        private var list: ArrayList<Users>,
        private val title: String = ""
) : Dialog(context) {

    private var adapter: MemberListItemsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_list, null)

        setContentView(view)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setUpRecyclerView(view)
    }

    private fun setUpRecyclerView(view: View) {
        view.tvTitle.text = title

        if (list.size > 0) {

            view.rvList.layoutManager = LinearLayoutManager(context)
            adapter = MemberListItemsAdapter(context, list)
            view.rvList.adapter = adapter

            adapter!!.setOnClickListener(object :
                    MemberListItemsAdapter.OnClickListener {
                override fun onClick(position: Int, user: Users, action:String) {
                    dismiss()
                    onItemSelected(user, action)
                }
            })
        }
    }

    protected abstract fun onItemSelected(user: Users, action:String)
}