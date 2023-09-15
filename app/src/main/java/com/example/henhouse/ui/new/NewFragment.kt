package com.example.henhouse.ui.new

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.henhouse.R
import com.example.henhouse.bdd.AppDatabase
import com.example.henhouse.entity.Chicken

class NewFragment : Fragment() {

    companion object {
        fun newInstance() = NewFragment()
        const val REQUEST_CODE_IMAGE = 101
    }

    private var selectImageUri: Uri? = null
    private lateinit var viewModel: NewViewModel
    lateinit var imageView: ImageView
    lateinit var buttonUpload: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeType = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
            startActivity(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewViewModel::class.java)

        var db = AppDatabase.getDatabase(requireActivity().applicationContext)

        var race = view?.findViewById<EditText>(R.id.newRace)
        var name = view?.findViewById<EditText>(R.id.newName)
        var btnSubmit = view?.findViewById<Button>(R.id.newButton)

        btnSubmit?.setOnClickListener {

            if (name != null && race != null) {
                val chicken = Chicken(name.text.toString(), race.text.toString())

                if (db != null) {
                    Toast.makeText(context, "Chicken create", Toast.LENGTH_LONG)
                    db.chickenDao().insertAll(chicken)
                }
            }
        }

        imageView = view?.findViewById(R.id.imageView)!!
        buttonUpload = view?.findViewById(R.id.buttonLoadPicture)!!

        Toast.makeText(context, "selectImageUri.toString()", Toast.LENGTH_LONG)
        buttonUpload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

        //openImageChooser()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Toast.makeText(context, "selectImageUri.toString()", Toast.LENGTH_LONG)
        if (requestCode == Activity.RESULT_OK) {
            selectImageUri = data?.data
            imageView.setImageURI(selectImageUri)
        }
    }
}