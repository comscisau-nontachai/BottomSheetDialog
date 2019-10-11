package com.dev.bottomsheetdialog

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStateChanged(p0: View, p1: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)

        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback)

        btn_show.setOnClickListener {

            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetDialog.show()
        }

        //bottom sheet event

        val btn1 = bottomSheetView.findViewById<TextView>(R.id.menu_bottom_sheet_snackbar)
        val btn2 = bottomSheetView.findViewById<TextView>(R.id.menu_bottom_sheet_loading)
        val btn3 = bottomSheetView.findViewById<TextView>(R.id.menu_bottom_sheet_confirm)
        val btn4 = bottomSheetView.findViewById<TextView>(R.id.menu_bottom_sheet_progreebar)

        btn1.setOnClickListener {

            Snackbar.make(btn_show, "This is Snackbar", Snackbar.LENGTH_SHORT).show()
            bottomSheetDialog.hide()

        }
        btn2.setOnClickListener {
            var progressdialog = ProgressDialog(this).apply {
                title = "Loading..."
                setMessage("Loadind...")
                max = 100
                progress = 0
                setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            }.also { progressDialog -> progressDialog.show() }

            val handler = Handler()

            Thread(Runnable {
                while (progressdialog.progress <= progressdialog.max) {
                    Thread.sleep(50)
                    handler.post {
                        progressdialog.incrementProgressBy(1)
                    }

                    if(progressdialog.progress == progressdialog.max)
                        progressdialog.dismiss()
                }

            }).start()


        }
        btn3.setOnClickListener {

            AlertDialog.Builder(this).apply {
                title = "Exit !!"
                setMessage("you want to exit")
            }.also {
                it.setPositiveButton(
                    "Yes"
                ) { dialog, p1 ->
                    dialog.dismiss()
                    bottomSheetDialog.hide()
                }
                it.setNegativeButton("No", null)
            }.run {
                show()
            }
        }
        btn4.setOnClickListener {
            val builder = AlertDialog.Builder(this).apply {
                title = "Loading2...."
                setMessage("Loading2.....")
                setView(R.layout.layout_loading_progressbar)
            }
            val dialog = builder.create()
            dialog.show()
        }

    }
}
